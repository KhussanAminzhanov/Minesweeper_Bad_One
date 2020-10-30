import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue<Integer> first = new ArrayDeque<>();
        Queue<Integer> second = new ArrayDeque<>();
        int n = scanner.nextInt();
        int id, load;
        int firstLoad = 0;
        int secondLoad = 0;
        for (int i = 0; i < n; i++) {
            id = scanner.nextInt();
            load = scanner.nextInt();
            if (firstLoad <= secondLoad) {
                first.offer(id);
                firstLoad += load;
            }
            else {
                second.offer(id);
                secondLoad += load;
            }
        }
        first.forEach(num -> System.out.print(num + " "));
        System.out.println();
        second.forEach(num -> System.out.print(num + " "));
    }
}