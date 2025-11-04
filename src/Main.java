import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int countFile = 0;
        while (true) {
            int countLine = 0;
            googlebotCount = 0;
            yandexbotCount = 0;
            System.out.println("Укажите путь до файла");
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();
            if (isDirectory == true) {
                System.out.println("Указана директория, а не файл");
            } else if (fileExists == false) {
                System.out.println("Файл не существует");
            } else {
                countFile++;
                System.out.println("Путь указан верно. Это файл номер " + countFile);
                FileReader fileReader = null;
                try {
                    fileReader = new FileReader(path);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                BufferedReader reader =
                        new BufferedReader(fileReader);
                String line;
                try {
                    while (true) {
                        if (!((line = reader.readLine()) != null)) break;
                        countLine++;

                        int length = line.length();
                        if (length > 1024) {
                            throw new ExceptionLongExceeded("В строке: " + countLine + " превышена длина в 1024 символ");
                        }
                        analyzeUserAgent(line, countLine);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                System.out.println("Количество строк: " + countLine);
                System.out.println("Запросов от Googlebot: " + googlebotCount);
                System.out.println("Запросов от YandexBot: " + yandexbotCount);
            }
        }
    }

    private static int googlebotCount = 0;
    private static int yandexbotCount = 0;

    private static void analyzeUserAgent(String line, int lineNumber) {
        int lastQuoteIndex = line.lastIndexOf('"');
        if (lastQuoteIndex == -1) return;

        int secondLastQuoteIndex = line.lastIndexOf('"', lastQuoteIndex - 1);
        if (secondLastQuoteIndex == -1) return;

        String userAgent = line.substring(secondLastQuoteIndex + 1, lastQuoteIndex);

        if (!userAgent.isEmpty()) {
            try {
                String program = extractProgramFromUserAgent(userAgent);
                if (program != null) {
                    if ("Googlebot".equals(program)) {
                        googlebotCount++;
                    } else if ("YandexBot".equals(program)) {
                        yandexbotCount++;
                    }
                }
            } catch (Exception e) {
                System.out.println("Ошибка при обработке User-Agent в строке " + lineNumber + ": " + e.getMessage());
            }
        }
    }

    private static String extractProgramFromUserAgent(String userAgent) {
        int startBrackets = userAgent.indexOf('(');
        int endBrackets = userAgent.indexOf(')');

        if (startBrackets == -1 || endBrackets == -1 || startBrackets >= endBrackets) {
            return null;
        }

        String firstBrackets = userAgent.substring(startBrackets + 1, endBrackets);

        String[] parts = firstBrackets.split(";");

        if (parts.length >= 2) {
            String fragment = parts[1].trim();

            int slashIndex = fragment.indexOf('/');
            if (slashIndex != -1) {
                String program = fragment.substring(0, slashIndex).trim();
                return program;
            } else {
                return fragment;
            }
        }
        return null;
    }
}