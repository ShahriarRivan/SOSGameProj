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
    void testScoreUpdateForRed() {
       
        GameBoard.setCurrentColor(Color.RED);

    
        gameBoard.buttons[0][0].setText("S");
        gameBoard.buttons[0][1].setText("O");
        gameBoard.buttons[0][2].setText("S");

        // Save current RED score
        int initialRedScore = gameBoard.getRedScore();

        // Call the method to check for SOS
        gameBoard.checkForSOS(0, 1);

        // Check if the RED score increased by 1
        assertEquals(initialRedScore + 1, gameBoard.getRedScore(), "Red score should increase by 1 after detecting an SOS");
    }

    @Test
    void testScoreUpdateForBlue() {
        // Setting the current color to BLUE
        GameBoard.setCurrentColor(Color.BLUE);

        // Creating an SOS sequence on the board
        gameBoard.buttons[0][0].setText("S");
        gameBoard.buttons[0][1].setText("O");
        gameBoard.buttons[0][2].setText("S");

        // Save current BLUE score
        int initialBlueScore = gameBoard.getBlueScore();

        // Call the method to check for SOS
        gameBoard.checkForSOS(0, 1);

        // Check if the BLUE score increased by 1
        assertEquals(initialBlueScore + 1, gameBoard.getBlueScore(), "Blue score should increase by 1 after detecting an SOS");
    }

}

