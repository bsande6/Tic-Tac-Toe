package cpsc2150.extendedTicTacToe;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class GameBoardMem extends AbsGameBoard implements IGameBoard {
    private int colNumber;
    private int rowNumber;
    private int winAmount;
    private Map<Character, List<BoardPosition>> boardMap;
    private List<BoardPosition> positionsList = null;

    /**
     *
     * @param row number of rows on board
     * @param col number of columns on board
     * @param wins number in a row to win
     * @pre row and col are integers between 3 and 100 and wins is between 3 and 25
     * and less then the number of rows or cols
     * @post sets the number of columns, rows and winAmount
     */
    public GameBoardMem(int row, int col, int wins) {
        colNumber = col;
        rowNumber = row;
        winAmount = wins;
        boardMap = new HashMap<>();
    }

    public int getNumRows() {
        return rowNumber;
    }

    public int getNumCols() {
        return colNumber;    }

    public int getNumToWin() {
        return winAmount;
    }

    public void placeMarker(BoardPosition marker, char player) {
        if (boardMap.containsKey(player)) {
            List<BoardPosition> playerList = boardMap.get(player);
            playerList.add(marker);
        } else {
            positionsList = new ArrayList<BoardPosition>();
            positionsList.add(marker);
            boardMap.put(player, positionsList);
        }
    }
    public char whatsAtPos(BoardPosition pos) {
        if (boardMap != null) {
            for (Map.Entry<Character, List<BoardPosition>> m : boardMap.entrySet()) {
                if (m.getValue().contains(pos)) {
                    return m.getKey();
                }
            }
        }
        return ' ';
    }

    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        if (boardMap.containsKey(player)) {
            List playerList = boardMap.get(player);
            if (playerList.contains(pos)) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
}
