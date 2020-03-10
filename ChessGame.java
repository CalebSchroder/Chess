import java.awt.Color;
import java.util.Arrays;
public class ChessGame {
    private Board board;

    public ChessGame() {
        this.board = new Board();
    }

    public int placeRook(int rank, int file) {
        int influence = 0;
        board.getSquare(rank, file).setPiece("r");
        for(int row = 1; row <= board.getHeight(); row ++) {
            for (int col = 1; col <= board.getLength(); col ++) {
                if (row == rank) {
                    board.getSquare(row, col).toggleHighlight();
                    influence = influence + 1;
                }
                if (col == file) {
                    board.getSquare(row, col).toggleHighlight();
                    influence = influence + 1;
                }
            }   
        }
        return influence;   
    }

    public int placeKnight(int rank, int file) {
        int influence = 0;
        board.clearBoard();
        board.getSquare(rank, file).setPiece("k");
        for (int row = 1; row <= board.getHeight(); row ++) {
            for (int col = 1; col <= board.getLength(); col ++) {
                if (row != rank && col != file && Math.abs(row - rank) + Math.abs(col - file) == 3) {
                    board.getSquare(row, col).toggleHighlight();
                    influence = influence + 1 ;
                }
            }
        }
        return influence;
    }

    public int placeBishop(int rank, int file) {
        int influence = 0;
        board.clearBoard();
        board.getSquare(rank, file).setPiece("b");
        int b = rank - 1;
        for(int i = file; i <= board.getHeight(); i++) {
            b = b + 1;
            if (b > board.getHeight()) {
                break;
            }
            board.getSquare(b, i).toggleHighlight();
            influence = influence + 1 ;

        }
        b = rank + 1;
        for(int i = file; i >= 1; i--) {
            b = b - 1;
            if (b < 1) {
                break;
            }
            board.getSquare(b, i).toggleHighlight();
            influence = influence + 1 ;

        }
        b = rank - 1;
        for(int i = file; i >= 1; i--) {
            b = b + 1;
            if (b > board.getHeight()) {
                break;
            }
            board.getSquare(b, i).toggleHighlight();
            influence = influence + 1 ;

        }
        b = rank + 1;
        for(int i = file; i <= board.getHeight(); i++) {
            b = b - 1;
            if (b < 1) {
                break;
            }
            board.getSquare(b, i).toggleHighlight();
            influence = influence + 1 ;

        }
        return influence;
    }

    public int placeQueen(int rank, int file) {

        board.clearBoard();
        int influence = placeBishop(rank, file) + placeRook(rank, file);
        board.clearBoard();
        placeBishop(rank, file);
        placeRook(rank, file);
        board.getSquare(rank, file).setPiece("q");
        return influence;
    }

    public void printBoard() {
        System.out.println(board);
        
    }

    public int[] mostInfluence() {
        int[] best = new int[2];
        int largest = placeQueen(1,1);

        for (int row = 1; row <= board.getHeight(); row ++) {
            for(int col = 1; col <=board.getLength(); col ++) {
                if (placeQueen(row,col) > largest ) {
                    largest = placeQueen(row,col);
                    best[0] = row;
                    best[1] = col;
                }
            }
        }
        for (int row = 1; row <= board.getHeight(); row ++) {
            for(int col = 1; col <=board.getLength(); col ++) {
                
            
            }
        }
        return best;
    }

    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame();
        System.out.println(chessGame.placeQueen(5,5));
        chessGame.printBoard();
        System.out.println(Arrays.toString(chessGame.mostInfluence()));
    }
}