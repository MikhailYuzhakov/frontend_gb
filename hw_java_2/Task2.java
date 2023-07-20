import java.util.Scanner;

/*
Написать метод «Шифр Цезаря», с булевым параметром зашифрования и расшифрования и числовым ключом.
 */

public class Task2 {
    protected String rusAlphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    public Task2() {
        super();
    }

    public String getPhrase() {
        String phrase = "";
        Scanner in = new Scanner(System.in);
        System.out.println("Введите фразу для шифрования (на русском языке строчными буквами): ");
        phrase = in.nextLine();
        return phrase;
    }

    public int getKey() {
        int key = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Введите цифровой ключ: ");
        key = in.nextInt();
        return key;
    }

    public boolean getDirection() {
        boolean direction = true;
        Scanner in = new Scanner(System.in);
        System.out.println("Введите направление шифрования (true/false): ");
        direction = in.nextBoolean();
        return direction;
    }

    public String crypting(String phrase, int key, boolean direction) {
        StringBuilder cryptPhrase = new StringBuilder();
        int shift = 0;
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) == ' ') {
                i += 1;
                cryptPhrase.append(' ');
            }
            for (int j = 0; j < rusAlphabet.length(); j++) {
                if (rusAlphabet.charAt(j) == phrase.charAt(i)) {
                    if (direction) {
                        if (j - key < 0) {
                            shift = rusAlphabet.length() + j - key;
                        } else  {
                            shift = j - key;
                        }
                        cryptPhrase.append(rusAlphabet.charAt(shift));
                    } else {
                        if (j + key > rusAlphabet.length()) {
                            shift = j + key - rusAlphabet.length();
                        } else  {
                            shift = j + key;
                        }
                        cryptPhrase.append(rusAlphabet.charAt(shift));
                    }
                }
            }
        }
        return cryptPhrase.toString();
    }
}
