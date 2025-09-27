import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int countFile = 0;
        while (true) {
            System.out.println("Укажите путь до файла");
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();
            if (fileExists && isDirectory) {
                countFile++;
                System.out.println("Путь указан верно. Это файл номер " + countFile);
            } else {
                System.out.println("Файл не существует либо путь указан неправильно");
            }
        }
    }
}
