import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice = "e";
        String[] str = {"1", "2", "3", "exit"};


        while (!choice.equals("exit")) {
            System.out.println(Sources.greetings);
            choice = scanner.nextLine();
            boolean contains = Arrays.asList(str).contains(choice);//узнать есть ли этот String choice в массиве String str,
            if (contains) {
                switch (choice) {
                    case "1" -> Encrypt.encrypt();
                    case "2" -> DecryptionAndCod.decryption();
                    case "3" -> System.out.println("Расшифровка методом brute force");
                    case "exit" -> System.out.println("До встречи!");
                    default -> System.out.println("Введите правильный номер");
                }
            } else {
                System.out.println("Введите правильный номер");
            }
        }
    }
}