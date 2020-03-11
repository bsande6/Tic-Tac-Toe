package cpsc2150.extendedTicTacToe;

import java.util.ListIterator;

/**
 * The TicTacToe controller class will handle communication between our TicTacToeView and our Model (IGameBoard and BoardPosition)
 *
 * This is where you will write code
 *
 * You will need to include your BoardPosition class, the IGameBoard interface
 * and the implementations from previous homeworks
 * If your code was correct you will not need to make any changes to your IGameBoard classes
 */
public class TicTacToeController{
    //our current game that is being played
    private IGameBoard curGame;
    //The screen that provides our view
    private TicTacToeView screen;
    private int gameOver = 0;
    private int numPlayers = 0;
    private int turn = 1;
    private char player1 = 'X';
    private char player2 = 'O';
    private char player3 = 'A';
    private char player4 = 'G';
    private char player5 = 'M';
    private char player6 = 'S';
    private char player7 = 'T';
    private char player8 = 'H';
    private char player9 = 'I';
    private char player10 = 'K';
    public static final int MAX_PLAYERS = 10;




    /**
     *
     * @param model the board implementation
     * @param view the screen that is shown
     * @param np The number of players for the game
     * @post the controller will respond to actions on the view using the model.
     */
    TicTacToeController(IGameBoard model, TicTacToeView view, int np){
        this.curGame = model;
        this.screen = view;
        this.numPlayers = np;
    }

    /**
     *
     * @param row the row of the activated button
     * @param col the column of the activated button
     * @pre row and col are in the bounds of the game represented by the view
     * @post The button pressed will show the right token and check if a player has won.
     */
    public void processButtonClick(int row, int col) {
        if (gameOver == 0) {
            char player;
            int playerNum = turn % numPlayers;
            if (playerNum == 1) {
                player = player1;
            } else if (playerNum == 2) {
                player = player2;
            } else if (playerNum == 3) {
                player = player3;
            } else if (playerNum == 4) {
                player = player4;
            } else if (playerNum == 5) {
                player = player5;
            } else if (playerNum == 6) {
                player = player6;
            } else if (playerNum == 7) {
                player = player7;
            } else if (playerNum == 8) {
                player = player8;
            } else if (playerNum == 9) {
                player = player9;
            } else {
                if (numPlayers == 2) {
                    player = player2;
                } else if (numPlayers == 3) {
                    player = player3;
                } else if (numPlayers == 4) {
                    player = player4;
                } else if (numPlayers == 5) {
                    player = player5;
                } else if (numPlayers == 6) {
                    player = player6;
                } else if (numPlayers == 7) {
                    player = player7;
                } else if (numPlayers == 8) {
                    player = player8;
                } else if (numPlayers == 9) {
                    player = player9;
                } else {
                    player = player10;
                }
            }
            BoardPosition pos = new BoardPosition(row, col);
            if (curGame.checkSpace(pos) != true) {
                screen.setMessage("That space is unavailable, please pick again");
            } else {
                curGame.placeMarker(pos, player);
                screen.setMarker(row, col, player);
                if (curGame.checkForWinner(pos) == true) {
                    screen.setMessage("Player " + player + " wins! Click anywhere to start a new game.");
                    gameOver = 1;
                } else if (curGame.checkForDraw() == true) {
                    screen.setMessage("Game has ended in a draw Click anywhere to start a neew game");
                    gameOver = 1;
                } else {
                    turn++;
                }
            }
        }
        else {
            newGame();
            gameOver = 0;
        }
    }

    private void newGame()
    {
        screen.dispose();
        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);
    }
}
