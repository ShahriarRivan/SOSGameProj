
import sosgame2.SOSGame2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SOSGame2Test {

    @BeforeEach
    void setUp() {
        new SOSGame2();
    }



    @Test
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
