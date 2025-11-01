import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int countFile = 0;
        while (true) {
            int countLine = 0;
            int maxLenght = 0;
            int minLenght = 0;
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
                        if (length > maxLenght) {
                            maxLenght = length;
                        }
                        if (minLenght == 0 || length < minLenght) {
                            minLenght = length;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                System.out.println("Количество строк: " + countLine);
                System.out.println("Самая длинная строка: " + maxLenght);
                System.out.println("Самая короткая строка: " + minLenght);
            }
        }
    }
}