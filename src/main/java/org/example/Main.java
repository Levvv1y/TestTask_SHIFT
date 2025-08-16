package org.example;

import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Использование: java -jar filter-utility.jar [опции] file1 file2 ...");
            System.out.println("Опции:");
            System.out.println("  -s   краткая статистика");
            System.out.println("  -f   полная статистика");
            System.out.println("  -o PATH  путь для файлов");
            System.out.println("  -p PREFIX  префикс имен");
            System.out.println("  -a   добавление в существующие файлы");
            return;
        }

        boolean fullStats = false;
        boolean append = false;
        Path outDir = Path.of(".");
        String prefix = "";

        List<String> files = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-s": fullStats = false; break;
                case "-f": fullStats = true; break;
                case "-a": append = true; break;
                case "-o":
                    if (i + 1 < args.length) {
                        outDir = Path.of(args[++i]);
                    } else {
                        System.err.println("Не указан путь для -o");
                        return;
                    }
                    break;
                case "-p":
                    if (i + 1 < args.length) {
                        prefix = args[++i];
                    } else {
                        System.err.println("Не указан префикс для -p");
                        return;
                    }
                    break;
                default:
                    files.add(args[i]);
            }
        }

        if (files.isEmpty()) {
            System.err.println("Нет входных файлов");
            return;
        }

        FileProcessor processor = new FileProcessor(outDir, prefix, append, fullStats);
        processor.processFiles(files);
        processor.writeResults();
        processor.printStats();
    }
}
