import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        while(scanner.hasNext()) list.add(scanner.nextInt());

        int n = list.get(list.size() - 1);
        list.remove(list.size() - 1);

        int min = Math.abs(n - list.get(0));
        for (int i = 1; i < list.size(); i++) {
            int res = Math.abs(n - list.get(i));
            if (res < min) min = res;
        }

        for (Integer num : list) {
            if (Math.abs(n - num) == min) nums.add(num);
        }

        Collections.sort(nums);
        for (Integer num : nums) System.out.print(num + " ");
    }
}