import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите первое число:");
        int firstNumber = new Scanner(System.in).nextInt();
        System.out.println("Введите второе число:");
        int secondNumber = new Scanner(System.in).nextInt();
        int sum = firstNumber + secondNumber;
        int difference = firstNumber - secondNumber;
        int multiply = firstNumber * secondNumber;
        double quotient = (double) firstNumber / secondNumber;
        System.out.println("Сумма двух чисел " +firstNumber+ " и " +secondNumber+ " = " +sum);
        System.out.println("Разность двух чисел " +firstNumber+ " и " +secondNumber+ " = " +difference);
        System.out.println("Произведение двух чисел " +firstNumber+ " и " +secondNumber+ " = " +multiply);
        System.out.println("Частное двух чисел " +firstNumber+ " и " +secondNumber+ " = " +quotient);
    }
}
