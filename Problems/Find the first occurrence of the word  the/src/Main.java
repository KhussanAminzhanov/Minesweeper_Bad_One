import java.util.Scanner;

class Main {

    public static int contains(String text, String str) {
        if (text.length() < str.length()) return -1;
        char[] charsText = text.toCharArray();
        char[] charsStr = str.toCharArray();
        for (int i = 0; i < charsText.length - charsStr.length; i++) {
            boolean equal = true;
            for (int j = 0; j < str.length(); j++) {
                if (charsStr[j] != charsText[i + j]) {
                    equal = false;
                    break;
                }
            }
            if (equal) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine().toLowerCase();
        System.out.println(contains(text, "the"));
    }
}