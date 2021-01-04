package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        char[][] table = new char[3][3];
        char[] list = new char[]{'X', 'O', ' '};
        int[] numberOfChars = new int[3];
        char lastInput = 'O';

        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                table[i][j] = ' ';
                System.out.print(table[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");

        boolean winner = false;

        while (!winner) {

            boolean agreesConditions = false;

            while (!agreesConditions) {

                System.out.println("Enter the coordinates: ");
                String rowNumberString = scanner.next();;
                String columnNumberString = scanner.next();


                if (rowNumberString.matches("\\d+") && columnNumberString.matches("\\d+")) {

                    int rowNumber = Integer.parseInt(rowNumberString) - 1;
                    int columnNumber = Integer.parseInt(columnNumberString) - 1;

                    if (rowNumber >= 0 && rowNumber <= 2 && columnNumber >= 0 && columnNumber <= 2) {
                        if (table[rowNumber][columnNumber] == ' ') {
                            if (lastInput == 'O') {
                                lastInput = 'X';
                            }
                            else {
                                lastInput = 'O';
                            }
                            table[rowNumber][columnNumber] = lastInput;
                            agreesConditions = true;
                        }
                        else {
                            System.out.println("This cell is occupied! Choose another one!");
                        }
                    }
                    else {
                        System.out.println("Coordinates should be from 1 to 3!");
                    }

                }
                else {
                    System.out.println("You should enter numbers!");
                }

            }

            boolean rowX = isThereARow(table, list[0]);
            boolean rowO = isThereARow(table, list[1]);

            numberOfChars = count(table);

            printTable(table);

            if (rowX && rowO || Math.abs(numberOfChars[0] - numberOfChars[1]) >= 2) {
                System.out.println("Impossible");
                winner = true;
            }
            else if (!rowX && !rowO && numberOfChars[2] != 0) {

            }
            else if (!rowO && !rowX && numberOfChars[2] == 0) {
                System.out.println("Draw");
                winner = true;
            }
            else if (rowX) {
                System.out.println("X wins");
                winner = true;
            }
            else if (rowO) {
                System.out.println("O wins");
                winner = true;
            }

        }

    }

    public static void printTable(char[][] table) {

        System.out.println("---------");

        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {

                System.out.print(table[i][j] + " ");
            }
            System.out.println("|");
        }

        System.out.println("---------");

    }

    public static boolean isThereARow(char[][] table, char c) {

        for (int i = 0; i < 3; i++) {
            if (checkRow(table, i, c)) {
                return true;
            }
            else if (checkColumn(table, i, c)) {
                return  true;
            }
        }

        return checkDiagonal(table, c);
    }

    public static boolean checkRow(char[][] table, int row, char c) {

        boolean result = false;

        if (table[row][0] == table[row][1] && table[row][1] == table[row][2] && table[row][0] == c) {
            result = true;
        }

        return result;
    }

    public static boolean checkColumn(char[][] table, int column, char c) {

        boolean result = false;

        if (table[0][column] == table[1][column] && table[1][column] == table[2][column] && table[0][column] == c) {
            result = true;
        }

        return  result;
    }

    public static boolean checkDiagonal(char[][] table, char c) {

        boolean result = false;

        if (table[0][0] == table[1][1] && table[1][1] == table[2][2] && table[0][0] == c) {
            result = true;
        }
        else if (table[2][0] == table[1][1] && table[1][1] == table[0][2] && table[1][1] == c) {
            result = true;
        }

        return result;
    }

    public static int[] count(char[][] table) {

        int[] numberOfChars = new int[3];

        char[] list = new char[]{'X', 'O', ' '};

        for (int i = 0; i < 3; i++) {
            numberOfChars[i] = countChars(table, list[i]);
        }

        return numberOfChars;
    }

    public static int countChars(char[][] table, char c) {

        int number = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                if (table[i][j] == c) {
                    number++;
                }
        }

        return number;
    }
}
