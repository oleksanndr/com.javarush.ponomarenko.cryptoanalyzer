
public class TestConstructor {

    public static char testConstructor(char chars, int a) {
        int count = a % Sources.ALPHABET.length;
        for (int i = 0; i < Sources.ALPHABET.length; i++) {
            if (chars == Sources.ALPHABET[i]) {
                if ((i + count) < Sources.ALPHABET.length && (i + count) >= 0) {
                    chars = Sources.ALPHABET[i + count];
                    break;
                }
                if ((i + count) >= Sources.ALPHABET.length) {
                    int b = (i + count) - Sources.ALPHABET.length;
                    chars = Sources.ALPHABET[b];
                    break;
                }
                if ((i + count) < 0) {
                    int b = (i + count) + Sources.ALPHABET.length;
                    chars = Sources.ALPHABET[b];
                    break;
                }
            }
        }

        return chars;
    }
}
