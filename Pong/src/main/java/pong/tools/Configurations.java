package pong.tools;

import pong.dao.ConfigurationDao;
import java.util.HashMap;
import javafx.scene.input.KeyCode;

public class Configurations {

    public HashMap<String, String> configs;
    private ConfigurationDao configurationDao;

    public Configurations(String fileName) {
        this.configs = configurationDao.initConfigurations(fileName);
    }

    public void resetConfigs() {
        this.configs = configurationDao.resetConfigurations();
    }

    public KeyCode getKey(String keyName, KeyCode defaultKey) {
        KeyCode key = KeyCode.getKeyCode(this.configs.get(keyName));
        if (key == null) {
            try {
                key = KeyCode.valueOf(this.configs.get(keyName));
            } catch (Exception e) {
                key = defaultKey;
                this.setKey(keyName, key);
            }
        }
        return key;
    }

    public int getInt(String intName, int defaultInt) {
        try {
            return Integer.parseInt(this.configs.get(intName));
        } catch (Exception e) {
            this.setInt(intName, defaultInt);
            return Integer.parseInt(this.configs.get(intName));
        }
    }

    public double getDouble(String doubleName, double defaultDouble) {
        try {
            return Double.parseDouble(this.configs.get(doubleName));
        } catch (Exception e) {
            this.setDouble(doubleName, 1);
            return Double.parseDouble(this.configs.get(doubleName));
        }

    }

    public void setKey(String keyName, KeyCode key) {
        this.configs.put(keyName, key.toString());
        configurationDao.setProperty(keyName, key.toString());
    }

    public void setInt(String intName, int integer) {
        this.configs.put(intName, integer + "");
        configurationDao.setProperty(intName, integer + "");
    }

    public void setDouble(String doubleName, double doubleValue) {
        this.configs.put(doubleName, doubleValue + "");
        configurationDao.setProperty(doubleName, doubleValue + "");
    }
}
