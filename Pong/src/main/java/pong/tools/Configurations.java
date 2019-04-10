package pong.tools;

import java.util.HashMap;
import javafx.scene.input.KeyCode;

public class Configurations {

    private HashMap<String, String> configs;
    private ConfigurationDao configurationDao;

    public Configurations(String fileName) {
        this.configs = configurationDao.initConfigurations(fileName);
    }
    
    public void resetConfigs() {
        this.configs = configurationDao.resetConfigurations();
    }

    public int getDifficulty() {
        return Integer.parseInt(this.configs.get("difficulty"));
    }

    public KeyCode getPauseButton() {
        return KeyCode.getKeyCode(this.configs.get("pause"));
    }

    public KeyCode getPlayer1Up() {
        return KeyCode.getKeyCode(this.configs.get("Player1_Up"));
    }

    public KeyCode getPlayer1Down() {
        return KeyCode.getKeyCode(this.configs.get("Player1_Down"));
    }

    public KeyCode getPlayer2Up() {
        return KeyCode.getKeyCode(this.configs.get("Player2_Up"));
    }

    public KeyCode getPlayer2Down() {
        return KeyCode.getKeyCode(this.configs.get("Player2_Down"));
    }

    public int getEndingPoint() {
        return Integer.parseInt(this.configs.get("endingpoint"));
    }

    public double getSpeedUp() {
        return Double.parseDouble(this.configs.get("speedUp"));
    }

    public int getBallSpeed() {
        return Integer.parseInt(this.configs.get("BallSpeed"));
    }

    public int getBatSpeed() {
        return Integer.parseInt(this.configs.get("BatSpeed"));
    }
    
    public int getPowerups() {
        System.out.println(this.configs.get("powerups"));
        return Integer.parseInt(this.configs.get("powerups"));
    }

    public void setDifficulty(int difficulty) {
        this.configs.put("difficulty", difficulty + "");
        configurationDao.setProperty("difficulty", difficulty + "");
    }

    public void setPauseButton(KeyCode pauseButton) {
        this.configs.put("pause", pauseButton.toString());
        configurationDao.setProperty("pause", pauseButton.toString());
    }

    public void setPlayer1Up(KeyCode player1Up) {
        this.configs.put("Player1_Up", player1Up.toString());
        configurationDao.setProperty("Player1_Up", player1Up.toString());
    }

    public void setPlayer1Down(KeyCode player1Down) {
        this.configs.put("Player1_Down", player1Down.toString());
        configurationDao.setProperty("Player1_Down", player1Down.toString());
    }

    public void setPlayer2Up(KeyCode player2Up) {
        this.configs.put("Player2_Up", player2Up.toString());
        configurationDao.setProperty("Player2_Up", player2Up.toString());
    }

    public void setPlayer2Down(KeyCode palyer2Down) {
        this.configs.put("Player2_Down", palyer2Down.toString());
        configurationDao.setProperty("Player2_Down", palyer2Down.toString());
    }

    public void setEndingPoint(int endingpoint) {
        this.configs.put("endingpoint", endingpoint + "");
        configurationDao.setProperty("endingpoint", endingpoint + "");
    }

    public void setSpeedUp(int speedUp) {
        this.configs.put("speedUp", speedUp + "");
        configurationDao.setProperty("speedUp", speedUp + "");
    }

    public void setBallSpeed(double ballSpeed) {
        this.configs.put("BallSpeed", ballSpeed + "");
        configurationDao.setProperty("BallSpeed", ballSpeed + "");
    }

    public void setBatSpeed(int batSpeed) {
        this.configs.put("BatSpeed", batSpeed + "");
        configurationDao.setProperty("BatSpeed", batSpeed + "");
    }
    
    public void setPowerups(int powerups) {
        this.configs.put("powerups", powerups + "");
        configurationDao.setProperty("powerups", powerups + "");
    }

}
