import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class DecryptionAndCod {
    public static void decryption() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите адрес файла источника: ");
        String inputFile = sc.nextLine();
        System.out.println("Введите адрес файла приёмника: ");
        String outputFile = sc.nextLine();
        System.out.println("Введите число код для расшифровки: ");
        int cod = sc.nextInt();
        try (FileReader reader = new FileReader(inputFile);
             FileWriter writer = new FileWriter(outputFile)) {
            while (reader.ready()) {
                char character = (char) reader.read();
                writer.write(TestConstructor.testConstructor(character, cod));
            }
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
