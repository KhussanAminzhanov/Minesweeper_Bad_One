package minesweeper;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    final static Scanner scanner = new Scanner(System.in);

    public static int input(String text) {
        System.out.print(text);
        try { return scanner.nextInt(); }
        catch (InputMismatchException e) { return -1; }
    }

    public static int getNumMines(String text, int numCells) {
        int numMines = input(text);
        while (numCells < numMines) {
            System.out.println("Wrong input!");
            numMines = input(text);
        }
        return numMines;
    }

    public static void playGame() {
        GameField field = new GameField(9, 9);
        int numMines = getNumMines("How many mines do you want on the field? ", 81);
        field.printField();
        int col = input("Set/delete mines marks or claim a cell as free: ");
        int row = input("");
        String command = scanner.nextLine().toLowerCase().trim();

        while (row > 9 || row < 0 || col > 9 || col < 0) {
            System.out.println("Coordinates should be in range 1-9!");
            col = input("Set/delete mines marks or claim a cell as free: ");
            row = input("");
        }

        field.putMines(numMines, row, col);
        field.putNumbers();
        field.setMark(row - 1, col - 1, command);
        field.printField();


//        field.printState();

        while(!field.isAllMarked()) {
            col = input("Set/delete mines marks or claim a cell as free: ");
            row = input("");
            command = scanner.nextLine().toLowerCase().trim();

            while (row > 9 || row < 0 || col > 9 || col < 0) {
                System.out.println("Coordinates should be in range 1-9!");
                col = input("Set/delete mines marks or claim a cell as free: ");
                row = input("");
            }

            if (!field.setMark(row - 1, col - 1, command)) {
                System.out.println("Failed! You stepped on the mine!");
                field.showMines();
                return;
            }
            field.printField();
//            field.printState();
        }
        System.out.println("Congratulations! You found all mines");
    }

    public static void main(String[] args) {
        playGame();
    }
}
