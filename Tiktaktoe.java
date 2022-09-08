import java.util.Scanner;

public class Tiktaktoe {
    enum Status {
        NULL,
        X,
        O
    }
    Status[][]board;
    int boardLength;
    int winLength;
    public Status[][] buildGame() {
        Status[][]board = new Status[boardLength + 2][boardLength];
        for (int i = 0; i < boardLength; i++) {
            board[i] = new Status [boardLength];
            for (int j = 0; j < boardLength; j++) 
                    board[i][j] = Status.NULL;
        }
        return board;
    }
    public boolean check(int x, int y) {
        return (0 <= x && x < boardLength && 0 <= y && y < boardLength);
    }

    public Status getWinner() {
        int hx[] = {1, 0, 1, -1};
        int hy[] = {0, 1, 1, 1};
        for(int  h = 0; h < 4; h ++) {
            for(int i = 0; i < boardLength; i++) {
                for(int j = 0; j < boardLength; j++) {
                    if(board[i][j] == Status.NULL) continue;
                    int x = i;
                    int y = j;
                    int len = 0;
                    while(check(x, y)) {
                        if(board[x][y] != board[i][j]) break;
                        len ++;
                        x += hx[h];
                        y += hy[h];
                    }
                    if(len == winLength) {
                        return board[i][j];
                    }
                }
            }
        }
        return Status.NULL;
    }
    public boolean printBoard() {
        Status winner = Status.NULL; 
        boolean canContinue = false;
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
                if(board[i][j] == Status.NULL) {
                    canContinue = true;
                    continue;
                }
            }
            System.out.println();
        }
        if(canContinue == false) {
            System.out.println("Draw");
        }
        winner = getWinner();
        if(winner == Status.NULL) return false;
        else {
            System.out.println("Winner: " + winner);
            return true; 
        }
    }
    public void runGame() {

        Scanner scanner = new Scanner(System.in);
        System.out.println(" Welcome to Game Tik Tac Toe <3");
        System.out.print("Length of board: ");
        boardLength = scanner.nextInt();
        System.out.print("Winning chain length: ");
        winLength = scanner.nextInt();
        board = buildGame();

        boolean exit = false;
        int turnPlay = 0;
        
        while(!exit) {
            switch(turnPlay) {
                case 0: 
                    System.out.println("Turn X");
                    break;
                case 1: 
                    System.out.println("Turn O");
                    break;
            }

            System.out.println("Row Col: ");
            while(true) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                x --;
                y --;
                if(board[x][y] != Status.NULL && check(x, y) == true) {
                    System.out.println("Invalid input, try again ");
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
            if(printBoard()) {
                break;
            }
            turnPlay ++;
            turnPlay %= 2;

        }
        scanner.close();
    }
    public static void main (String[] args) {
        Tiktaktoe game = new Tiktaktoe();
        game.runGame();
    }
}