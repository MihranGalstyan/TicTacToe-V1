import java.util.Random;
import java.util.Scanner;

/**
 * Created by Mihran Galstyan
 * All rights reserved
 */
public class Main {
    public static void main(final String[] args) {
        String[][] gameTable = new String[][]{
                {" ", " ", " "},
                {" ", " ", " "},
                {" ", " ", " "}
        };

        welcomeMassage();
        showGameBoard(gameTable);
        play(gameTable);
    }

    private static void play(String[][] gameBoard){
        int random = new Random().nextInt(0, 2);
        if (random == 0) {
            System.out.println("Computer makes first move!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            makeMove("comp", gameBoard);
            showGameBoard(gameBoard);
        }

        while (!winnerCheck(gameBoard)) {
            makeMove("user", gameBoard);
            showGameBoard(gameBoard);
            if (winnerCheck(gameBoard)) {
                System.out.println("You win! Congratulations!");
                break;
            }
            if (isFull(gameBoard)) {
                System.out.println("There is no winner. Try again");
                break;
            }
            System.out.println("Computer is thinking...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            makeMove("comp", gameBoard);
            showGameBoard(gameBoard);
            if (winnerCheck(gameBoard)) {
                System.out.println("Computer was smarter. Try again ;)");
                break;
            }
            if (isFull(gameBoard)) {
                System.out.println("There is no winner. Try again");
                break;
            }
        }
    }

    private static boolean isFull(String[][] gameBoard) {
        boolean isFull = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j].equals(" ")) {
                    isFull = false;
                    break;
                }
            }
        }
        return isFull;
    }

    private static boolean cellIsFree(int position, String[][] gameBoard) {
        switch (position) {
            case 1 -> {
                return gameBoard[2][0].equals(" ");
            }
            case 2 -> {
                return gameBoard[2][1].equals(" ");
            }
            case 3 -> {
                return gameBoard[2][2].equals(" ");
            }
            case 4 -> {
                return gameBoard[1][0].equals(" ");
            }
            case 5 -> {
                return gameBoard[1][1].equals(" ");
            }
            case 6 -> {
                return gameBoard[1][2].equals(" ");
            }
            case 7 -> {
                return gameBoard[0][0].equals(" ");
            }
            case 8 -> {
                return gameBoard[0][1].equals(" ");
            }
            case 9 -> {
                return gameBoard[0][2].equals(" ");
            }
            default -> makeMove("user", gameBoard);
        }
        return false;
    }

    private static void makeMove(String player, String[][] gameBoard) {
        if (player.equals("user")) {
            System.out.println("Make your move!");
            int input = new Scanner(System.in).nextInt();
            if (cellIsFree(input, gameBoard)) {
                switch (input) {
                    case 1 -> gameBoard[2][0] = "X";
                    case 2 -> gameBoard[2][1] = "X";
                    case 3 -> gameBoard[2][2] = "X";
                    case 4 -> gameBoard[1][0] = "X";
                    case 5 -> gameBoard[1][1] = "X";
                    case 6 -> gameBoard[1][2] = "X";
                    case 7 -> gameBoard[0][0] = "X";
                    case 8 -> gameBoard[0][1] = "X";
                    case 9 -> gameBoard[0][2] = "X";
                    default -> makeMove("user", gameBoard);
                }
            } else {
                System.out.println("Cell is not free!");
                makeMove("user", gameBoard);
            }
        } else if (player.equals("comp")) {
            int input = new Random().nextInt(1, 10);
            if (cellIsFree(input, gameBoard)) {
                switch (input) {
                    case 1 -> gameBoard[2][0] = "O";
                    case 2 -> gameBoard[2][1] = "O";
                    case 3 -> gameBoard[2][2] = "O";
                    case 4 -> gameBoard[1][0] = "O";
                    case 5 -> gameBoard[1][1] = "O";
                    case 6 -> gameBoard[1][2] = "O";
                    case 7 -> gameBoard[0][0] = "O";
                    case 8 -> gameBoard[0][1] = "O";
                    case 9 -> gameBoard[0][2] = "O";
                    default -> makeMove("comp", gameBoard);

                }
            } else {
                makeMove("comp", gameBoard);
            }
        }
    }

    private static boolean winnerCheck(String[][] gameBoard) {

        return (gameBoard[2][0].equals(gameBoard[2][1]) && gameBoard[2][0].equals(gameBoard[2][2]) && gameBoard[2][0].equals("X")) ||
                (gameBoard[1][0].equals(gameBoard[1][1]) && gameBoard[1][0].equals(gameBoard[1][2]) && gameBoard[1][0].equals("X")) ||
                (gameBoard[0][0].equals(gameBoard[0][1]) && gameBoard[0][0].equals(gameBoard[0][2]) && gameBoard[0][0].equals("X")) ||

                (gameBoard[0][2].equals(gameBoard[1][2]) && gameBoard[0][2].equals(gameBoard[2][2]) && gameBoard[0][2].equals("X")) ||
                (gameBoard[0][1].equals(gameBoard[1][1]) && gameBoard[0][1].equals(gameBoard[2][1]) && gameBoard[0][1].equals("X")) ||
                (gameBoard[0][0].equals(gameBoard[1][0]) && gameBoard[0][0].equals(gameBoard[2][0]) && gameBoard[0][0].equals("X")) ||

                (gameBoard[0][0].equals(gameBoard[1][1]) && gameBoard[0][0].equals(gameBoard[2][2]) && gameBoard[0][0].equals("X")) ||
                (gameBoard[2][0].equals(gameBoard[1][1]) && gameBoard[2][0].equals(gameBoard[0][2]) && gameBoard[2][0].equals("X")) ||

                (gameBoard[2][0].equals(gameBoard[2][1]) && gameBoard[2][0].equals(gameBoard[2][2]) && gameBoard[2][0].equals("O")) ||
                (gameBoard[1][0].equals(gameBoard[1][1]) && gameBoard[1][0].equals(gameBoard[1][2]) && gameBoard[1][0].equals("O")) ||
                (gameBoard[0][0].equals(gameBoard[0][1]) && gameBoard[0][0].equals(gameBoard[0][2]) && gameBoard[0][0].equals("O")) ||

                (gameBoard[0][2].equals(gameBoard[1][2]) && gameBoard[0][2].equals(gameBoard[2][2]) && gameBoard[0][2].equals("O")) ||
                (gameBoard[0][1].equals(gameBoard[1][1]) && gameBoard[0][1].equals(gameBoard[2][1]) && gameBoard[0][1].equals("O")) ||
                (gameBoard[0][0].equals(gameBoard[1][0]) && gameBoard[0][0].equals(gameBoard[2][0]) && gameBoard[0][0].equals("O")) ||

                (gameBoard[0][0].equals(gameBoard[1][1]) && gameBoard[0][0].equals(gameBoard[2][2]) && gameBoard[0][0].equals("O")) ||
                (gameBoard[2][0].equals(gameBoard[1][1]) && gameBoard[2][0].equals(gameBoard[0][2]) && gameBoard[2][0].equals("O"));
    }

    private static void showGameBoard(String[][] gameBoard) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("[" + gameBoard[i][j] + "]" + "\t");
                if (j == 2) {
                    System.out.println();
                }
            }
        }
    }

    private static void welcomeMassage() {
        System.out.println("******** Welcome to the game TicTacToe! ********");
        String[][] array = new String[3][3];
        array[2][0] = "1";
        array[2][1] = "2";
        array[2][2] = "3";
        array[1][0] = "4";
        array[1][1] = "5";
        array[1][2] = "6";
        array[0][0] = "7";
        array[0][1] = "8";
        array[0][2] = "9";

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("[" + array[i][j] + "]" + "\t");
                if (j == 2) {
                    System.out.println();
                }
            }
        }
        System.out.println("******** Input only numbers from 1 to 9! Good luk! ********");
        System.out.println();
        System.out.println("Game started!");
    }
}
