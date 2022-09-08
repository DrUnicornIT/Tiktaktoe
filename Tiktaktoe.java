import java.util.Scanner;

public class Tiktaktoe {
    enum Status {
        NULL,
        X,
        O
    }
    public static Status[][] buildGame(int boardLength) {
        Status[][]board = new Status[boardLength + 2][boardLength];
        for (int i = 0; i < boardLength; i++) {
            board[i] = new Status [boardLength];
            for (int j = 0; j < boardLength; j++) 
                    board[i][j] = Status.NULL;
        }
        return board;
    }
    public static boolean winGame(Status[][]board, int boardLength) {
        Status winner = Status.NULL; 
        for(int i = 0; i < boardLength; i ++) {
            for (int j = 0; j < boardLength; j++) {
                switch (board[i][j]) {
                    case NULL:
                        System.out.print("[ ]");
                        break;
                    case X:
                        System.out.print("[x]");
                        break;
                    case O:
                        System.out.print("[o]");
                        break;
                }
                if(board[i][j] == Status.NULL) continue;
                Status cur = Status.NULL;

                if(i != 0 && i != boardLength - 1 && board[i][j] == board[i-1][j] && board[i][j] == board[i+1][j]) {
                    cur = board[i][j];
                }
                if(j != 0 && j != boardLength - 1 && board[i][j] == board[i][j-1] && board[i][j] == board[i][j+1]) {
                    cur = board[i][j];
                }
                if(i != 0 && i != boardLength - 1 && j != 0 && j != boardLength - 1 
                && board[i][j] == board[i-1][j-1] && board[i][j] == board[i+1][j+1]) {
                    cur = board[i][j];
                }
                if(cur != Status.NULL) {
                    winner = cur;
                }
            }
            System.out.println();
        }
        if(winner == Status.NULL) return false;
        else {
            System.out.println("winner: " + winner);
            return true; 
        }
    }
    public static void runGame(Status[][]board, int boardLength) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int turnPlay = 0;
        
        while(!exit) {
            switch(turnPlay) {
                case 0: 
                    System.out.println("Den luot cua X");
                    break;
                case 1: 
                    System.out.println("Den luot cua O");
                    break;
            }

            System.out.print("Nhap du lieu: \n");
            while(true) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                x --;
                y --;
                if(board[x][y] != Status.NULL && x < 0 && y < 0 && x >=  boardLength && y >= boardLength) {
                    System.out.println("Khong hop le. Nhap lai: ");
                    continue;
                }
                switch(turnPlay) {
                    case 0: 
                        board[x][y] = Status.X;
                        break;
                    case 1: 
                        board[x][y] = Status.O;
                        break;
                }
                break;
            }
            if(winGame(board, boardLength)) {
                break;
            }
            turnPlay ++;
            turnPlay %= 2;

        }
        scanner.close();
    }
    public static void main(String[] args) {
        System.out.println(" Welcome to Game Tik Tac Toe <3");

        Scanner scanner = new Scanner(System.in);
        int boardLength = 3;
        Status[][] board = buildGame(boardLength);
        runGame(board, 3);
        
        scanner.close();
    }
}