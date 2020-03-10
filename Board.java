import java.awt.Color;

public class Board{
    private Square[][] squares;

    public Board() {
        squares = new Square[8][8];

        for (int row = 0; row < squares.length; row ++) {
            boolean isBlack = false;
            if (row % 2 == 0) {
                isBlack = true;
            }
            for (int col = 0; col < squares[row].length; col ++) {
                int rank = squares.length - row;
                int file = col + 1;
                Color color = Color.WHITE;
                if(isBlack) {
                    color = Color.BLACK;
                }

                squares[row][col] = new Square(rank,file,color);
                isBlack = !isBlack;
                
            }
        }
    }

    public int getHeight() {
        return squares.length;

    }
    public int getLength() {
        return squares[0].length;

    }
    public Square getSquare(int rank, int file){
        int row = squares.length - rank;
        int col = file - 1;
        return squares[row][col];
    }
    public String toString() {
        String wow = "";
        for (int row = 0; row < squares.length; row++) {
            wow += "\n";
            for (int col = 0; col < squares[row].length; col++) {
                if (squares[row][col].getColor() == Color.BLACK) {
                    if (squares[row][col].getPiece() != null) {
                        wow +="{" + squares[row][col].getPiece() + "}";
                    } else if (squares[row][col].isHighlighted()) {
                        wow +="{h}";
                    } else {
                        wow += "{}";
                    }
                } else {
                    if (squares[row][col].getPiece() != null) {
                        wow += "(" + squares[row][col].getPiece() + ")";
                    } else if (squares[row][col].isHighlighted()) {
                        wow +="(h)";
                    } else {
                        wow += "()";
                    }
                }
                wow += "\t";

            }
        }
        return wow;
    }
    public void clearBoard() {
        for (int row = 0; row < squares.length; row++) {
            for (int col = 0; col < squares[row].length; col ++) {
                if (squares[row][col].isHighlighted()) {
                    squares[row][col].toggleHighlight();
                }
            }
        }
    }
}