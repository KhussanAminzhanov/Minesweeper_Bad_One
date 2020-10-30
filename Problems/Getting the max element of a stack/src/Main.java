import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> max = new ArrayDeque<>();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String command = scanner.next();
            switch (command) {
                case "push": {
                    int num = scanner.nextInt();
                    scanner.nextLine();
                    stack.offerLast(num);
                    if (max.size() == 0) max.push(num);
                    else max.offerLast(Math.max(max.peekLast(), num));
                    break;
                }
                case "max": {
                    System.out.println(max.peekLast());
                    break;
                }
                case "pop": {
                    stack.removeLast();
                    max.removeLast();
                }
            }
        }
    }
}