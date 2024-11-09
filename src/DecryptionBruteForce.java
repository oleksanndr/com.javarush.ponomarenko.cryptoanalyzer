import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DecryptionBruteForce {

    public static void decryptionBruteForce() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите адрес файла источника:");
        //String inputFile = "C:\\MyJavaProject\\com.javarush.ponomarenko.cryptoanalyzer\\src\\output.txt";
        String inputFile = sc.nextLine();
        System.out.println("Введите адрес файла приёмника: ");
        String outputFile = sc.nextLine();

        char targetChar = 'a'; // символ для поиска

        // Считать символы из файла в список
        List<Character> characterList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            int character;
            while ((character = reader.read()) != -1) {
                characterList.add((char) character);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Скачиваем из списка первые 100 символов или весь список если небольшой размер
        List<Character> buffer = new ArrayList<>();
        if (characterList.size() > 100) {
            for (int i = 0; i < 100; i++) {
                buffer.add(characterList.get(i));
            }
        } else {
            buffer.addAll(characterList);
        }

        //Проводим подбор и замену символа
        int codes = 0;
        int num = Sources.ALPHABET.length;

        while (codes < Sources.ALPHABET.length) {
            List<Character> bufferDecryptCode = new ArrayList<>();//??? List vs array
            for (int i = 0; i < buffer.size(); i++) {
                char charBuffer = buffer.get(i);
                for (int j = 0; j < Sources.ALPHABET.length; j++) {
                    if (charBuffer == Sources.ALPHABET[j]) {
                        if ((num - 1) < (j + codes)) {
                            int k = (j + codes) - num;
                            bufferDecryptCode.add(Sources.ALPHABET[k]);
                            break;
                        } else {
                            bufferDecryptCode.add(Sources.ALPHABET[j + codes]);
                            break;
                        }
                    }
                }
            }

            // Преобразовать список символов в строку
            StringBuilder stringBuilder = new StringBuilder();
            for (char c : bufferDecryptCode) {
                stringBuilder.append(c);
            }
            String resultString = stringBuilder.toString();
            System.out.println( "Код расшифровки: " + codes + " -> " + resultString);
            codes++;


            // Найти все совпадения символа и вывести их индексы
            List<Integer> indices = new ArrayList<>();
            for (int i = 0; i < resultString.length(); i++) {
                if (resultString.charAt(i) == targetChar) {
                    indices.add(i);
                }
            }
            //System.out.println("Символ найден на индексах: " + indices);
        }
        System.out.println("Выберите из вариантов подходящий \n" +
                "Введите число код : ");
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
