package org.example;

public class Statistics {
    private int count = 0;

    // Для чисел
    private Double min = null;
    private Double max = null;
    private double sum = 0.0;

    // Для строк
    private Integer minLength = null;
    private Integer maxLength = null;

    public void addInteger(long value) {
        count++;
        double v = (double) value;
        sum += v;
        min = (min == null) ? v : Math.min(min, v);
        max = (max == null) ? v : Math.max(max, v);
    }

    public void addFloat(double value) {
        count++;
        sum += value;
        min = (min == null) ? value : Math.min(min, value);
        max = (max == null) ? value : Math.max(max, value);
    }

    public void addString(String s) {
        count++;
        int len = s.length();
        minLength = (minLength == null) ? len : Math.min(minLength, len);
        maxLength = (maxLength == null) ? len : Math.max(maxLength, len);
    }

    public void printSummary(DataType type, boolean full) {
        System.out.println("=== " + type + " ===");
        System.out.println("Count: " + count);
        if (full && count > 0) {
            if (type == DataType.INTEGER || type == DataType.FLOAT) {
                System.out.println("Min: " + min);
                System.out.println("Max: " + max);
                System.out.println("Sum: " + sum);
                System.out.println("Avg: " + (sum / count));
            } else if (type == DataType.STRING) {
                System.out.println("Shortest: " + minLength);
                System.out.println("Longest: " + maxLength);
            }
        }
        System.out.println();
    }

    public int getCount() {
        return count;
    }
}
