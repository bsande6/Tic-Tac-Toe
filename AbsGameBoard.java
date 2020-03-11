package cpsc2150.extendedTicTacToe;

public abstract class AbsGameBoard implements IGameBoard {
    /**
     * @pre number of rows and cols are between MIN_ROWS/MIN_COLS and MAX_ROWS and MAX_COLS
     * @return string representing the board
     * @post board will be returned for printing
     */
    @Override
    public String toString() {
        String boardString = "";
        for (int i=0; i<getNumRows()+1; i++) {
            if (i != 0) {
                boardString = boardString + "\n";
            }
            for (int j=0; j<getNumCols()+1; j++) {
                if (i == 0 && j == 0) {
                    boardString = "  " + "|";
                } else if (i == 0 && j != 0) {
                    if(j < 10) {
                        boardString = boardString + "  " + (j - 1) + "|";
                    }
                    else if (j==10) {
                        boardString = boardString + "  " + (j-1) + "| ";
                    }
                    else  {
                        boardString = boardString +  (j - 1) + "| ";
                    }
                } else if (i != 0 && j == 0) {
                    if (i <11) {
                        boardString = boardString + " " + (i - 1) + "| ";
                    }
                    else {
                        boardString = boardString + (i - 1) + "| ";
                    }
                } else {
                    BoardPosition pos = new BoardPosition(i-1,j-1);
                    boardString = boardString + " " + whatsAtPos(pos) + "| ";
                }
            }
        }
        return boardString;
    }
}
