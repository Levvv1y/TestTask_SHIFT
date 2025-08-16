package org.example;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileProcessor {
    private final Path outputDir;
    private final String prefix;
    private final boolean appendMode;
    private final boolean fullStats;

    private final Map<DataType, List<String>> data = new EnumMap<>(DataType.class);
    private final Map<DataType, Statistics> stats = new EnumMap<>(DataType.class);

    public FileProcessor(Path outputDir, String prefix, boolean appendMode, boolean fullStats) {
        this.outputDir = outputDir;
        this.prefix = prefix;
        this.appendMode = appendMode;
        this.fullStats = fullStats;

        for (DataType type : DataType.values()) {
            data.put(type, new ArrayList<>());
            stats.put(type, new Statistics());
        }
    }

    public void processFiles(List<String> files) {
        for (String filename : files) {
            try (BufferedReader br = Files.newBufferedReader(Path.of(filename))) {
                String line;
                while ((line = br.readLine()) != null) {
                    classify(line);
                }
            } catch (IOException e) {
                System.err.println("Ошибка чтения файла " + filename + ": " + e.getMessage());
            }
        }
    }

    private void classify(String line) {
        line = line.trim();
        if (line.isEmpty()) return;

        try {
            long l = Long.parseLong(line);
            data.get(DataType.INTEGER).add(line);
            stats.get(DataType.INTEGER).addInteger(l);
            return;
        } catch (NumberFormatException ignored) {}

        try {
            double d = Double.parseDouble(line);
            data.get(DataType.FLOAT).add(line);
            stats.get(DataType.FLOAT).addFloat(d);
            return;
        } catch (NumberFormatException ignored) {}

        data.get(DataType.STRING).add(line);
        stats.get(DataType.STRING).addString(line);
    }

    public void writeResults() {
        try {
            Files.createDirectories(outputDir);
        } catch (IOException e) {
            System.err.println("Не удалось создать папку вывода: " + e.getMessage());
            return;
        }

        writeIfNotEmpty(DataType.INTEGER, "integers.txt");
        writeIfNotEmpty(DataType.FLOAT, "floats.txt");
        writeIfNotEmpty(DataType.STRING, "strings.txt");
    }

    private void writeIfNotEmpty(DataType type, String filename) {
        List<String> lines = data.get(type);
        if (lines.isEmpty()) return;

        Path outFile = outputDir.resolve(prefix + filename);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outFile.toFile(), appendMode))) {
            for (String s : lines) {
                bw.write(s);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл " + outFile + ": " + e.getMessage());
        }
    }

    public void printStats() {
        for (DataType type : DataType.values()) {
            stats.get(type).printSummary(type, fullStats);
        }
    }
}
