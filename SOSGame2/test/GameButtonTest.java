
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sosgame2.GameButton;
import static org.junit.jupiter.api.Assertions.*;

public class GameButtonTest {

    private GameButton gameButton;

    @BeforeEach
    void setUp() {
        gameButton = new GameButton(3, 4);
    }

    @Test
    void testGameButtonInitialization() {
        assertNotNull(gameButton, "GameButton should be properly initialized");
    }

    @Test
    void testRowRetrieval() {
        assertEquals(3, gameButton.getRow(), "GameButton should return correct row position");
    }

    @Test
    void testColumnRetrieval() {
        assertEquals(4, gameButton.getCol(), "GameButton should return correct column position");
    }

    @Test
    void testButtonDefaultBackground() {
        assertEquals(java.awt.Color.WHITE, gameButton.getBackground(), "Default background color should be white");
    }

    @Test
    void testButtonTextInitialization() {
        assertEquals("", gameButton.getText(), "Initial button text should be empty");
    }
}
