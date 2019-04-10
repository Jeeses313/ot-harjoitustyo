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
        try {
            return Integer.parseInt(this.configs.get("difficulty"));
        } catch (Exception e) {
            this.setDifficulty(1);
            return Integer.parseInt(this.configs.get("difficulty"));
        }

    }

    public KeyCode getPauseButton() {
        KeyCode key = KeyCode.getKeyCode(this.configs.get("pause"));
        if (key == null) {
            key = KeyCode.P;
            this.setPauseButton(key);
        }
        return key;
    }

    public KeyCode getMenuButton() {
        KeyCode key = KeyCode.getKeyCode(this.configs.get("menu"));
        if (key == null) {
            key = KeyCode.M;
            this.setMenuButton(key);
        }
        return key;
    }

    public KeyCode getPlayer1Up() {
        KeyCode key = KeyCode.getKeyCode(this.configs.get("Player1_Up"));
        if (key == null) {
            key = KeyCode.W;
            this.setPlayer1Up(key);
        }
        return key;
    }

    public KeyCode getPlayer1Down() {
        KeyCode key = KeyCode.getKeyCode(this.configs.get("Player1_Down"));
        if (key == null) {
            key = KeyCode.S;
            this.setPlayer1Down(key);
        }
        return key;
    }

    public KeyCode getPlayer2Up() {
        KeyCode key = KeyCode.getKeyCode(this.configs.get("Player2_Up"));
        if (key == null) {
            key = KeyCode.UP;
            this.setPlayer2Up(key);
        }
        return key;
    }

    public KeyCode getPlayer2Down() {
        KeyCode key = KeyCode.getKeyCode(this.configs.get("Player2_Down"));
        if (key == null) {
            key = KeyCode.DOWN;
            this.setPlayer2Down(key);
        }
        return key;
    }

    public int getEndingPoint() {
        try {
            return Integer.parseInt(this.configs.get("endingpoint"));
        } catch (Exception e) {
            this.setEndingPoint(3);
            return Integer.parseInt(this.configs.get("endingpoint"));
        }

    }

    public double getSpeedUp() {
        try {
            return Double.parseDouble(this.configs.get("speedUp"));
        } catch (Exception e) {
            this.setSpeedUp(1);
            return Double.parseDouble(this.configs.get("speedUp"));
        }

    }

    public int getBallSpeed() {
        try {
            return Integer.parseInt(this.configs.get("BallSpeed"));
        } catch (Exception e) {
            this.setBallSpeed(8);
            return Integer.parseInt(this.configs.get("BallSpeed"));
        }

    }

    public int getBatSpeed() {
        try {
            return Integer.parseInt(this.configs.get("BatSpeed"));
        } catch (Exception e) {
            this.setBatSpeed(4);
            return Integer.parseInt(this.configs.get("BatSpeed"));
        }

    }

    public int getPowerups() {
        try {
            return Integer.parseInt(this.configs.get("powerups"));
        } catch (Exception e) {
            this.setPowerups(0);
            return Integer.parseInt(this.configs.get("powerups"));
        }

    }

    public void setDifficulty(int difficulty) {
        this.configs.put("difficulty", difficulty + "");
        configurationDao.setProperty("difficulty", difficulty + "");
    }

    public void setPauseButton(KeyCode pauseButton) {
        this.configs.put("pause", pauseButton.toString());
        configurationDao.setProperty("pause", pauseButton.toString());
    }

    public void setMenuButton(KeyCode menuButton) {
        this.configs.put("menu", menuButton.toString());
        configurationDao.setProperty("menu", menuButton.toString());
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

    public void setSpeedUp(double speedUp) {
        this.configs.put("speedUp", speedUp + "");
        configurationDao.setProperty("speedUp", speedUp + "");
    }

    public void setBallSpeed(int ballSpeed) {
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
