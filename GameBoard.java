package cpsc2150.extendedTicTacToe;

import cpsc2150.extendedTicTacToe.BoardPosition;

/**
 * @invariant row > 0, row < MAX_ROW, col > 0, col < MAX_ROW
 * @correspondences rowNumber = numRows, colNumber = numCols and winAmount = AmountToWin
 */
public class GameBoard extends AbsGameBoard implements IGameBoard {
    private char[][] board;
    private int row;
    private int col;
    private int rowNumber;
    private int colNumber;
    private int winAmount;

    /**
     * @pre row,col and wins should be greater than MIN_ROWS, MIN_COLS and MIN_WIN_AMOUNT
     * and greater than MAX_ROWS, MAX_COLS and MAX_WIN_AMOUNT respectively
     * @post creates empty game board with length and width equal to rows and cols
     */
    public GameBoard(int row, int col, int wins) {
        rowNumber = row;
        colNumber = col;
        winAmount = wins;
        board = new char[rowNumber][colNumber];
        for (int i = 0; i<rowNumber; i++) {
            for (int j = 0; j< colNumber; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void placeMarker(BoardPosition marker, char player) {
        row = marker.getRow();
        col = marker.getColumn();
        board[row][col] = player;
    }

    public char whatsAtPos(BoardPosition pos) {
        row = pos.getRow();
        col = pos.getColumn();
        return board[row][col];
    }

    public int getNumRows() {
        return rowNumber;
    }
    public int getNumCols() {
        return colNumber;
    }
    public int getNumToWin() {
        return winAmount;
    }
}


