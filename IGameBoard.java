package cpsc2150.extendedTicTacToe;

/**
 * Interface for the TicTacToe game board that keeps track of the number of rows, columns and amount in a row to win.
 * This interface also checks if a position is valid and determines if a game has ended in a win or draw.
 *
 * Defines: NumRows: Z
 *          NumCols: Z
 *          AmountToWIn Z
 *
 * Constraints: MIN_ROWS <= NumRows <= MAX_ROWS, MIN_COLS <= NumCols <= MAX_COLS and
 *              MIN_WIN_AMOUNT <= AmountToWin <= MAX_WIN_AMOUNT
 *
 * Initialization Ensures: MIN_ROWS <= NumRows <= MAX_ROWS, MIN_COLS <= NumCols <= MAX_COLS and
 *  *                      MIN_WIN_AMOUNT <= AmountToWin <= MAX_WIN_AMOUNT
 *
 */
public interface IGameBoard {
    int MIN_ROWS = 3;
    int MAX_ROWS = 100;
    int MAX_COLS = 100;
    int MIN_COLS = 3;
    int MIN_WIN_AMOUNT = 3;
    int MAX_WIN_AMOUNT = 25;
    /**
     * @return the number of rows will be a positive integer between MIN_ROWS and MAX_ROWS
     * @post will be a positive integer between MIN_ROWS and MAX_ROWS
     */
    public int getNumRows();

    /**
     * @return number of columns
     * @post columns will be a positive integer between MIN_COLS and MAX_COLS
     */
    public int getNumCols();

    /**
     * @return amount in a row to win
     * @post amount to win will be an integer between MIN_WIN_AMOUNT and MAX_WIN_AMOUNT and will not be greater than the
     * number of rows or cols
     */
    public int getNumToWin();

    /** @param pos position on board chosen by user
     * @pre pos should contain a row and col in Z
     * @post return will be boolean based on if position is available
     */
    default boolean checkSpace(BoardPosition pos) {
        if(pos.getRow() >= 0 && pos.getRow() <= getNumRows()-1 && pos.getColumn() >= 0 && pos.getColumn()
                <= getNumCols()-1) {
            if (whatsAtPos(pos) == ' ') {
                return true;
            } else {
                return false;
            }
        }
        else {
          return false;
        }
    }


    /**
     * @param marker position on board chosen by user
     * @param player char representing the current player
     * @pre checkSpace(pos) = true
     * @post marker will be placed onto the board array
     * */
    public void placeMarker(BoardPosition marker, char player);

    /**
     *
     * @param lastPos indicates position user last placed
     * @pre lastPos exists on board
     * @post returns boolean indicating if the game is won
     */
    default boolean checkForWinner(BoardPosition lastPos) {
        char player = whatsAtPos(lastPos);
        if(checkHorizontalWin(lastPos, player)) {
            return true;
        }
        else if(checkVerticalWin(lastPos, player)) {
            return true;
        }
        else if(checkDiagonalWin(lastPos, player)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * @pre checkForWinner(lastPos) = false
     * @post returns boolean indicating if game ends in draw
     */
    default boolean checkForDraw() {
        for(int i=0; i<getNumRows(); i++) {
            for(int j=0; j<getNumCols(); j++) {
                BoardPosition pos = new BoardPosition(i,j);
                if (whatsAtPos(pos) == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *
     * @param lastPos indicates last position marker has been placed on
     * @param player char identifying which player
     * @pre lastPos exists on board, and player is either X or O
     * @post returns boolean to identify if game is won
     */
    default boolean checkHorizontalWin(BoardPosition lastPos, char player) {
        int col = lastPos.getColumn();
        int row = lastPos.getRow();
        int count = 0;
        int stop = 0;
        while (col >= 0 && col < getNumCols() && stop == 0) {

            if (col == 0 ){
                stop = 1;
            }
            else {
                BoardPosition nextPos = new BoardPosition(row,col-1);
                if (isPlayerAtPos(nextPos, player)) {
                    count++;
                    col = col - 1;
                }
                else {
                stop = 1;
                }
            }
        }

        col = lastPos.getColumn();
        stop = 0;
        while (col >= 0 && col < getNumCols() && stop == 0 ) {

            if (col == getNumCols()-1) {
                stop = 1;
            }
            else {
                BoardPosition nextPos = new BoardPosition(row, col+1);
                if (isPlayerAtPos(nextPos, player)) {
                    count++;
                    col = col + 1;
                } else {
                    stop = 1;
                }
            }
        }
        if (count >= getNumToWin()-1) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     *
     * @param lastPos indicates last position marker has been placed on
     * @param player char identifying which player
     * @pre lastPos exists on board, and player is either X or O
     * @post returns boolean to identify if game is won
     */
    default boolean checkVerticalWin(BoardPosition lastPos, char player) {
        int row = lastPos.getRow();
        int col = lastPos.getColumn();
        int stop = 0;
        int count = 0;
        while (row >= 0 && row < getNumRows() && stop == 0) {
            if (row == 0) {
                stop = 1;
            }
            else {
                BoardPosition nextPos = new BoardPosition(row-1,col);
                if (player == (whatsAtPos(nextPos))) {
                    count++;
                    row = row - 1;
                } else {
                    stop = 1;
                }
            }
        }
        row = lastPos.getRow();
        stop = 0;
        while (row >= 0 && row < getNumRows() && stop == 0 ) {

            if (row == getNumRows() - 1) {
                stop = 1;
            } else {
                if (stop != 1) {
                    BoardPosition nextPos = new BoardPosition(row + 1, col);
                    if (isPlayerAtPos(nextPos, player)) {
                        count++;
                        row = row + 1;
                    } else {
                        stop = 1;
                    }
                }
            }
        }
        if (count >= getNumToWin()-1) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     *
     * @param lastPos indicates last position marker has been placed on
     * @param player char identifying which player
     * @pre lastPos exists on board, and player is either X or O
     * @post returns boolean to identify if game is won
     */
    default boolean checkDiagonalWin(BoardPosition lastPos, char player) {
        int row = lastPos.getRow();
        int col = lastPos.getColumn();
        int stop = 0;
        int count = 0;
        while (0<=row && row < getNumRows() && col >= 0 && col < getNumCols() && stop == 0) {
            if (row == 0 || col == 0) {
                stop = 1;
            } else {
                BoardPosition nextPos = new BoardPosition(row - 1, col - 1);
                if (isPlayerAtPos(nextPos, player)) {
                    count++;
                    row = row - 1;
                    col = col - 1;
                } else {
                    stop = 1;
                }
            }
        }
        row = lastPos.getRow();
        col = lastPos.getColumn();
        stop = 0;
        while (0<= row && row < getNumRows() && col >= 0 && col < getNumCols() && stop == 0) {
            if (row == getNumRows() - 1 || col == getNumCols() - 1) {
                stop = 1;
            } else {
                BoardPosition nextPos = new BoardPosition(row + 1, col + 1);
                if (isPlayerAtPos(nextPos, player)) {
                    count++;
                    row = row + 1;
                    col = col + 1;
                } else {
                    stop = 1;
                }
            }
        }
        if (count >=getNumToWin()-1) {
            return true;
        }
        else {
            row = lastPos.getRow();
            col = lastPos.getColumn();
            stop = 0;
            count = 0;
            while (0<=row && row < getNumRows() && col >= 0 && col < getNumCols() && stop == 0) {
                if (row == getNumRows()-1||col ==0) {
                    stop =1;
                }
                else {
                    BoardPosition nextPos = new BoardPosition(row+1,col-1);
                    if (isPlayerAtPos(nextPos, player)) {
                        count++;
                        row = row + 1;
                        col = col - 1;
                    } else {
                        stop = 1;
                    }
                }
            }
            row = lastPos.getRow();
            col = lastPos.getColumn();
            stop = 0;
            while (0<= row && row < getNumRows() && col >= 0 && col < getNumCols() && stop == 0) {
                if (row == 0 || col == getNumCols()-1) {
                    stop =1;
                }
                else {
                    BoardPosition nextPos = new BoardPosition(row - 1, col + 1);
                    if (isPlayerAtPos(nextPos, player)) {
                        count++;
                        row = row - 1;
                        col = col + 1;
                    } else {
                        stop = 1;
                    }
                }
            }
            if (count >= getNumToWin()-1) {
                return true;
            }
            else {
                return false;
            }
        }
    }
    /**
     *
     * @param pos, position on board
     * @return player char or " "
     * @pre pos exists on board
     * @post will return value in the position
     * */
    public char whatsAtPos(BoardPosition pos);
    /**
     *
     * @param pos position on board
     * @param player char representing current player
     * @return  boolean
     * @pre pos should be valid on the board
     * @post will return true if specified player is at position
     */
    default boolean isPlayerAtPos(BoardPosition pos, char player) {

        if (whatsAtPos(pos) == player) {
            return true;
        }
        else {
            return false;
        }
    }


}
