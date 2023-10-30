
import sosgame2.GameBoard;
import sosgame2.SOSGame2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;


public class SOSGame2Test {

    private SOSGame2 game;

    @BeforeEach
    public void setUp() {
        game = new SOSGame2();
    }

    @Test
    public void testUpdatePlayerSelectionRed() {
        GameBoard.setCurrentColor(Color.RED);
        SOSGame2.updatePlayerSelection();
        assertTrue(SOSGame2.redButton.isSelected());
        assertFalse(SOSGame2.blueButton.isSelected());
    }

    @Test
    public void testMain() {
        // This is a basic test to ensure the main method can be called without exceptions.
        // In real-world scenarios, GUI-based main methods are rarely tested this way.
        assertDoesNotThrow(() -> SOSGame2.main(new String[]{}));
    }
    
    void testMenuInitialization() {
        try {
           
            javax.swing.JFrame frame = new javax.swing.JFrame();
            SOSGame2.initializeMenu(frame); 
            assertNotNull(frame.getJMenuBar(), "Menu bar should be initialized");
        } catch (Exception e) {
            fail("Menu initialization should not throw any exceptions.");
        }
    }


    @Test
    void testControlPanelInitialization() {
        assertNotNull(SOSGame2.initializeControlPanel(), "Control panel initialization should return a non-null object.");
    }
}
