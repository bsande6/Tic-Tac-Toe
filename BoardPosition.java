package cpsc2150.extendedTicTacToe;

/**
 * @invarient rowPos and colPos should be integer values
 *
 */
public class BoardPosition {
    private int rowPos;
    private int colPos;

    /**
     * @pre rowPos and colPos should be integers
     * @param rowPos specified row
     * @param colPos specified column
     * @post sets boardPosition rowPos = parameter and colPos = parameter
     */
    public BoardPosition(int rowPos, int colPos) {
        this.rowPos = rowPos;
        this.colPos = colPos;
    }

    /**
     * @Pre boardPosition object must exist
     * @return the row
     * @post will return integer indicating row
     */
    public int getRow() {
        return rowPos;
    }

    /**
     * @pre boardPosition object must exist
     * @return the col
     * @post will return integer containing column
     */
    public int getColumn() {
        return colPos;
    }

    /**
     * @param pos space that is being compared
     * @pre cpsc2150.extendedTicTacToe.BoardPosition obj != null
     * @return boolean
     * @post positions are equal if they have same row and col
     */
    @Override
    public boolean equals(Object obj) {
        BoardPosition pos = (BoardPosition) obj;
        if (this.rowPos == pos.rowPos && pos.colPos == this.colPos) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @pre BoardPostion obj != null
     * @return value of String, rather than address
     */
    @Override
    public String toString() {
        String position = "<" + rowPos + ">," + "<"+ colPos + ">";
        return position;
    }
}