
package tooltests;


import javafx.scene.input.KeyCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pong.tools.Configurations;


public class ConfigurationTest {
    Configurations config;
    
    @Before
    public void setUp() {
        config = new Configurations("test.properties");
    }
    
    @Test
    public void newConfigurationHasCorrectDifficulty() {
        assertEquals(1, config.getInt("difficulty",0));
    }
    
    @Test
    public void newConfigurationHasCorrectEndingpoint() {
        assertEquals(5, config.getInt("endingpoint",0));
    }
    
    @Test
    public void newConfigurationHasCorrectBallSpeed() {
        assertEquals(8, config.getInt("BallSpeed",8));
    }
    
    @Test
    public void newConfigurationHasCorrectBatSpeed() {
        assertEquals(4, config.getInt("BatSpeed",0));
    }
    
    @Test
    public void newConfigurationHasCorrectPowerups() {
        assertEquals(0, config.getInt("powerups",1));
    }
    
    @Test
    public void newConfigurationHasCorrectSpeedup() {
        assertEquals(true, Math.abs(1 - config.getDouble("speedUp", 0)) <= 0.000001);
    }
    
    @Test
    public void newConfigurationHasCorrectPauseKey() {
        assertEquals(KeyCode.P, config.getKey("pause",KeyCode.WINDOWS));
    }
    
    @Test
    public void newConfigurationHasCorrectMenuKey() {
        assertEquals(KeyCode.M, config.getKey("menu",KeyCode.WINDOWS));
    }
    
    @Test
    public void newConfigurationHasCorrectP1UpKey() {
        assertEquals(KeyCode.W, config.getKey("Player1_Up",KeyCode.WINDOWS));
    }
    
    @Test
    public void newConfigurationHasCorrectP1DownKey() {
        assertEquals(KeyCode.S, config.getKey("Player1_Down",KeyCode.WINDOWS));
    }
    
    @Test
    public void newConfigurationHasCorrectP2UpKey() {
        assertEquals(KeyCode.UP, config.getKey("Player2_Up",KeyCode.WINDOWS));
    }
    
    @Test
    public void newConfigurationHasCorrectP2DownKey() {
        assertEquals(KeyCode.DOWN, config.getKey("Player2_Down",KeyCode.WINDOWS));
    }
    
    @Test
    public void setKeySetsKey() {
        config.setKey("pause", KeyCode.ALT);
        assertEquals(KeyCode.ALT, config.getKey("pause",KeyCode.WINDOWS));
    }
    
    @Test
    public void setIntSetsInt() {
        config.setInt("difficulty", 2);
        assertEquals(2, config.getInt("difficulty",0));
    }
    
    @Test
    public void setDoubleSetsDouble() {
        config.setDouble("speedUp", 0);
        assertEquals(true, Math.abs(0 - config.getDouble("speedUp", 1)) <= 0.000001);
    }
    
    @Test
    public void resetCongigsResetsValue() {
        config.setInt("difficulty", 2);
        config.resetConfigs();
        assertEquals(1, config.getInt("difficulty",0));
    }
    
    @Test
    public void getKeyAvoidsError() {
        config.configs.put("pause", "test");
        assertEquals(KeyCode.WINDOWS, config.getKey("pause",KeyCode.WINDOWS));
    }
    
    @Test
    public void getIntAvoidsError() {
        config.configs.put("difficulty", "test");
        assertEquals(0, config.getInt("difficulty",0));
    }
    
    @Test
    public void getDoubleAvoidsError() {
        config.configs.put("speedUp", "test");
        assertEquals(true, Math.abs(1 - config.getDouble("speedUp", 1)) <= 0.000001);
    }
}
