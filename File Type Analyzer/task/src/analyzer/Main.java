package analyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        if (args.length < 2) {
            System.out.println("Please provide a directory and a patterns DB");
            System.exit(0);
        }

        String directory = args[0];
        String patternsDb = args[1];

        String[] filenameList = new File(directory).list();

        List<Pattern> sortedPatterns = loadPatterns(patternsDb);

//        filenameList.forEach(System.out::println);

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (String filename : filenameList) {
            executor.submit(() -> {
                byte[] fileContent = readFile(directory + "/" + filename);
                String fileContentStr = new String(fileContent, StandardCharsets.ISO_8859_1);

                String fileType = null;
                for (Pattern pattern : sortedPatterns) {
                    if (RabinKarp.contains(fileContentStr, pattern.getPattern())) {
                        fileType = pattern.getFileType();
                        break;
                    }
                }

                if (fileType == null) {
                    fileType = "Unknown file type";
                }

                System.out.println(filename + ": " + fileType);
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    private static List<Pattern> loadPatterns(String patternsDb) throws FileNotFoundException {
        Scanner patternScanner = new Scanner(new File(patternsDb));
        List<Pattern> patterns = new ArrayList<>();

        while (patternScanner.hasNextLine()) {
            String[] line = patternScanner.nextLine().split(";");
            line[1] = line[1].replaceAll("\"", "");
            line[2] = line[2].replaceAll("\"", "");
            patterns.add(new Pattern(Integer.parseInt(line[0]), line[1], line[2]));
        }
        patternScanner.close();

//        patterns.sort((o1, o2) -> Integer.compare(o1.getPriority(), o2.getPriority()));
        patterns.sort(Comparator.comparingInt(Pattern::getPriority).reversed());

        return patterns;
    }

    private static byte[] readFile(String filename) {
        try {
            return Files.readAllBytes(Path.of(filename));
        } catch (IOException e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static boolean stringContains(String text, String pattern) {
        return text.contains(pattern);
    }

}
