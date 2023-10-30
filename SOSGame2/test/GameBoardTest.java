import sosgame2.GameBoard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;

public class GameBoardTest {

    private GameBoard gameBoard;

    @BeforeEach
    void setUp() {
        gameBoard = new GameBoard(8); 
    }

    @Test
    void testGameBoardInitialization() {
        assertNotNull(gameBoard.buttons[0][0], "Button at position (0,0) should be initialized");
        assertEquals("", gameBoard.buttons[0][0].getText(), "Initial button text should be empty");
    }

    @Test
    void testColorToggle() {
        GameBoard.setCurrentColor(Color.RED);
        assertEquals(Color.RED, GameBoard.getCurrentColor(), "Current color should be Red");

        GameBoard.setCurrentColor(Color.BLUE);
        assertEquals(Color.BLUE, GameBoard.getCurrentColor(), "Current color should be Blue");
    }

    @Test
    void testSOSDetectionHorizontal() {
        gameBoard.buttons[0][0].setText("S");
        gameBoard.buttons[0][1].setText("O");
        gameBoard.buttons[0][2].setText("S");
        gameBoard.checkForSOS(0, 1); 
    }

    @Test
    void testSOSDetectionVertical() {
        gameBoard.buttons[0][0].setText("S");
        gameBoard.buttons[1][0].setText("O");
        gameBoard.buttons[2][0].setText("S");
        gameBoard.checkForSOS(1, 0);
    }

   

    @Test
    void testBoardFullDetectionEmpty() {
        assertFalse(gameBoard.isBoardFull(), "Initially, the board should not be full");
    }

    @Test
    void testBoardFullDetectionFull() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gameBoard.buttons[i][j].setText("S"); 
            }
        }
        assertTrue(gameBoard.isBoardFull(), "The board should be detected as full");
    }
    
   
    
    @Test
    void testSOSDetectionDiagonalFromTopLeft() {
        gameBoard.buttons[0][0].setText("S");
        gameBoard.buttons[1][1].setText("O");
        gameBoard.buttons[2][2].setText("S");
        gameBoard.checkForSOS(1, 1);
    }

    @Test
    void testSOSDetectionDiagonalFromTopRight() {
        gameBoard.buttons[0][2].setText("S");
        gameBoard.buttons[1][1].setText("O");
        gameBoard.buttons[2][0].setText("S");
        gameBoard.checkForSOS(1, 1);
    }

    @Test
    void testResetScores() {
        gameBoard.setRedScore(5);
        gameBoard.setBlueScore(5);
        gameBoard.resetScores();
        assertEquals(0, gameBoard.getRedScore(), "Red score should be reset to 0");
        assertEquals(0, gameBoard.getBlueScore(), "Blue score should be reset to 0");
    }

   

    

}

