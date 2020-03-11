package cpsc2150.extendedTicTacToe;

/**
 * Created by kplis on 4/5/2018.
 */
public class GameSetupController implements IGameBoard {
    private GameSetupScreen view;
    private int max_size = 20;
    private final int MEM_CUTOFF = 64;


    public GameSetupController(GameSetupScreen v)
    {
        view = v;
    }

    public void processButtonClick(int rows, int cols, int players, int numWin )
    {
        String errorMsg = "";
        if(rows < MIN_ROWS || rows > max_size)
        {
            errorMsg += "Rows must be between " +  3 + " and " + max_size;
        }

        if(cols < MIN_COLS || cols > max_size)
        {
            errorMsg += "Columns must be between " +  3 + " and " + max_size;
        }

        if (numWin > rows)
        {
            errorMsg += "Can't have more to win than the number of rows";
        }
        if (numWin > rows)
         {
            errorMsg += "Can't have more to win than the number of Columns";
         }

        if(numWin < MIN_WIN_AMOUNT)
        {
            errorMsg += "Number to win must be at least " + 3;
        }

        if(!errorMsg.equals(""))
        {
            view.displayError(errorMsg);

        }
        else
        {
            view.closeScreen();
            IGameBoard model;
            if(rows * cols <= MEM_CUTOFF) {
                model = new GameBoard(rows, cols, numWin);
            }
            else
            {
                model = new GameBoardMem(rows, cols, numWin);
            }
            TicTacToeView tview = new TicTacToeView(rows, cols);
            TicTacToeController tcontroller = new TicTacToeController(model, tview, players);

            tview.registerObserver(tcontroller);
        }
    }

    public int getNumRows() {
        return 0;
    }

    public int getNumCols() {
        return 0;
    }

    public int getNumToWin() {
        return 0;
    }

    public void placeMarker(BoardPosition marker, char player) {

    }

    public char whatsAtPos(BoardPosition pos) {
        return 0;
    }
}
