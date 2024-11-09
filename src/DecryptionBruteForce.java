import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DecryptionBruteForce {
    public static void decryptionBruteForce() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите адрес файла источника:");
        String inputFile = sc.nextLine();
        System.out.println("Введите адрес файла приёмника: ");
        String outputFile = sc.nextLine();

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

        //Скачиваем из списка первые 1000 символов или весь список если небольшой размер
        List<Character> buffer = new ArrayList<>();
        if (characterList.size() > 1000) {
            for (int i = 0; i < 1000; i++) {
                buffer.add(characterList.get(i));
            }
        } else {
            buffer.addAll(characterList);
        }

        // Проводим подбор и замену символа
        List<String> resultStringCode = new ArrayList<>();
        int codes = 0;
        int num = Sources.ALPHABET.length;

        while (codes < Sources.ALPHABET.length) {
            String resultString = getResultString(buffer, num, codes);
            resultStringCode.add(resultString);
            codes++;
        }
        // Найти все совпадения символа и вывести их индексы
        List<Integer> coincidences = new ArrayList<>();
        for (String str : resultStringCode){
            StringTokenizer stringTokenizer = new StringTokenizer(str, " :;/.,><!?");
            int number = 0;
            while (stringTokenizer.hasMoreTokens()) {
                String token = stringTokenizer.nextToken();
                if (findMatches(token) > 0){
                    number++;
                }
            }
            coincidences.add(number);
        }

        int maxValue = Collections.max(coincidences);
        int cod = coincidences.indexOf(maxValue);
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

    private static String getResultString(List<Character> buffer, int num, int codes) {
        List<Character> bufferDecryptCode = new ArrayList<>();
        for (char charBuffer : buffer) {
            for (int j = 0; j < Sources.ALPHABET.length; j++) {
                if (charBuffer == Sources.ALPHABET[j]) {
                    if ((num - 1) < (j + codes)) {
                        int k = (j + codes) - num;
                        bufferDecryptCode.add(Sources.ALPHABET[k]);
                    } else {
                        bufferDecryptCode.add(Sources.ALPHABET[j + codes]);
                    }
                    break;
                }
            }
        }
        // Преобразовать список символов в строку
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : bufferDecryptCode) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    private static int findMatches(String str) {
        List<String> listDictionary = new ArrayList<>();
        String input = "C:\\MyJavaProject\\com.javarush.ponomarenko.cryptoanalyzer\\src\\dictionary.txt";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                listDictionary.add(line);
            }
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        int num = 0;
        int coin = 0;
        while (num < listDictionary.size()) {
            if (str.equalsIgnoreCase(listDictionary.get(num))) coin++;
            num++;
        }
        return coin;
    }
}
