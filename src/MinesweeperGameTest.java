import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinesweeperGameTest {

    private MinesweeperGame game;


    @Test
    public void getGameState() {
        game = new MinesweeperGame (0, -1.0, -1);
        assertEquals(game.getGameState(), MinesweeperGame.GameState.PLAYING);
    }


    @Test
    public void getSquareLength1() {
        game = new MinesweeperGame (0, -1.0, -1);
        assertEquals(game.getSquareLength(), 16);
    }

    @Test
    public void getSquareLength2() {
        game = new MinesweeperGame (10, -1.0, -1);
        assertEquals(game.getSquareLength(), 10);
    }



    @Test
    public void getStateOfUnexploredTile() {
        game = new MinesweeperGame (10, -1.0, -1);
        assertEquals(game.getStateOf(10), MinesweeperGame.UNEXPLORED);
    }

    @Test
    public void getStateOfFlaggedTile() {
        game = new MinesweeperGame (10, -1.0, -1);
        game.flagTile(10);
        assertEquals(game.getStateOf(10), MinesweeperGame.FLAGGED);
    }

    @Test
    public void getStateOfExploredTile() {
        game = new MinesweeperGame (10, -1.0, -1);
        game.exploreTile(10);
        assertNotEquals(game.getStateOf(10), MinesweeperGame.FLAGGED);
        assertNotEquals(game.getStateOf(10), MinesweeperGame.UNEXPLORED);
    }


    @Test
    public void exploreInvalidTile() {
        game = new MinesweeperGame (10, -1.0, -1);

        try {
            game.exploreTile(101);
        } catch (AssertionError e) {
            assert true;
        }
    }


    @Test
    public void getStateOfInvalidTile() {
        game = new MinesweeperGame (10, -1.0, -1);
        try {
            game.getStateOf(101);
        } catch (AssertionError e) {
            assert true;
        }
    }

    @Test
    public void exploreMineTile() {
        game = new MinesweeperGame (10, -1.0, -1);
        int boardLength = game.getSquareLength();
        for (int i = 0; i < boardLength * boardLength; i++) {
            game.exploreTile(i);
//            if ()
        }

        try {
            game.exploreTile(101);
        } catch (AssertionError e) {
            assert true;
        }
    }



    @Test
    public void flagTile() {
        game = new MinesweeperGame (10, -1.0, -1);

        assertEquals(game.getNumberOfFlags(), 0);

        game.flagTile(4);
        assertEquals(game.getNumberOfFlags(), 1);
    }

    //remove flag
    @Test
    public void flagAlreadyFlaggedTile() {
        game = new MinesweeperGame (10, -1.0, -1);

        //add first flag
        game.flagTile(4);
        assertEquals(game.getNumberOfFlags(), 1);

        // remove first flag
        game.flagTile(4);
        assertEquals(game.getNumberOfFlags(), 0);
    }

    // invali
    @Test
    public void flagInvalidTile() {
        game = new MinesweeperGame (10, -1.0, -1);

        try {
            game.flagTile(101);
        } catch (AssertionError e) {
            assert true;
        }
    }


    @Test
    public void flagExploredTile() {
        game = new MinesweeperGame (10, -1.0, -1);
        game.exploreTile(10);
        game.flagTile(10);
        assertEquals(game.getNumberOfFlags(), 0);
    }

    @Test
    public void newGameEasy() {
        game = new MinesweeperGame (10, -1.0, -1);
        game.newGame(MinesweeperGame.Difficulty.EASY);

        assertEquals(game.getSquareLength(), 9);
    }

    @Test
    public void newGameIntermediate() {
        game = new MinesweeperGame (10, -1.0, -1);
        game.newGame(MinesweeperGame.Difficulty.INTERMEDIATE);

        assertEquals(game.getSquareLength(), 16);
    }

    @Test
    public void newGameExpert() {
        game = new MinesweeperGame (10, -1.0, -1);
        game.newGame(MinesweeperGame.Difficulty.EXPERT);

        assertEquals(game.getSquareLength(), 22);
    }


    @Test
    public void getEstimatedNumberOfMines() {
        game = new MinesweeperGame (10, -1.0, -1);
        int initialMines = game.getEstimatedNumberOfMines();

        game.flagTile(10);
        assertEquals(game.getEstimatedNumberOfMines(), initialMines-1);
    }
}