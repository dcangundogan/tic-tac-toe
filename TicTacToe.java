import java.util.Scanner;

public class TicTacToe {
    private final char[][] board = new char[3][3];
    private final Scanner scanner = new Scanner(System.in);
    private char currentPlayer = 'X';

    public TicTacToe() {
        initializeBoard();
    }

    public void play() {
        printBoard();

        while (true) {
            int[] move = getValidMove();
            board[move[0]][move[1]] = currentPlayer;
            printBoard();

            if (hasWinner()) {
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            if (isDraw()) {
                System.out.println("The game is a draw!");
                break;
            }

            switchPlayer();
        }
    }

    private void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                board[row][column] = ' ';
            }
        }
    }

    private int[] getValidMove() {
        while (true) {
            System.out.print("Player " + currentPlayer + ", enter row (1-3): ");
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                scanner.next();
                continue;
            }
            int row = scanner.nextInt() - 1;

            System.out.print("Enter column (1-3): ");
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                scanner.next();
                continue;
            }
            int column = scanner.nextInt() - 1;

            if (row < 0 || row > 2 || column < 0 || column > 2) {
                System.out.println("Row and column must be between 1 and 3.");
            } else if (board[row][column] != ' ') {
                System.out.println("That cell is already occupied.");
            } else {
                return new int[] {row, column};
            }
        }
    }

    private void printBoard() {
        System.out.println();
        for (int row = 0; row < 3; row++) {
            System.out.println(" " + board[row][0] + " | " + board[row][1] + " | " + board[row][2]);
            if (row < 2) {
                System.out.println("-----------");
            }
        }
        System.out.println();
    }

    private boolean hasWinner() {
        for (int index = 0; index < 3; index++) {
            if (board[index][0] == currentPlayer
                    && board[index][1] == currentPlayer
                    && board[index][2] == currentPlayer) {
                return true;
            }

            if (board[0][index] == currentPlayer
                    && board[1][index] == currentPlayer
                    && board[2][index] == currentPlayer) {
                return true;
            }
        }

        boolean firstDiagonal = board[0][0] == currentPlayer
                && board[1][1] == currentPlayer
                && board[2][2] == currentPlayer;
        boolean secondDiagonal = board[0][2] == currentPlayer
                && board[1][1] == currentPlayer
                && board[2][0] == currentPlayer;

        return firstDiagonal || secondDiagonal;
    }

    private boolean isDraw() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (board[row][column] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private void switchPlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }
}
