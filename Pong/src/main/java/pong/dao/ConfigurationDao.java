package pong.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

/**
 * Luokka käsittelee tiedostoa, johon pelin asetukset on talletettu
 */
public class ConfigurationDao {

    private static Properties properties;
    private static String fileName;

    /**
     * Alustaa pelin asetukset Configurations oliota varten annetusta
     * tiedostosta ja palauttaa tiedot avain-arvo pareina hajautustaulussa<br>
     * Jos annettua tiedostoa ei ole tai tapahtuu virhe, luodaan uusi tiedosto
     * ja alustetaan se
     *
     * @param file Asetuksia sisältävän tiedoston nimi/polku
     * @return String-String avain-arvo pareja sisältävä HashMap, johon on
     * talletettu asetustiedoston sisältö
     *
     * @see pong.tools.Configurations
     * @see pong.dao.ConfigurationDao#initProperties()
     */
    public static HashMap<String, String> initConfigurations(String file) {
        fileName = file;
        HashMap<String, String> configs = null;
        properties = new Properties();
        try {
            InputStream input = new FileInputStream(fileName);
            properties.load(input);
        } catch (Exception e) {
            initProperties();
        }
        configs = fillHashMap();

        return configs;
    }

    private static HashMap<String, String> fillHashMap() {
        HashMap<String, String> configs = new HashMap<>();
        try {
            Enumeration<?> e = properties.propertyNames();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String value = properties.getProperty(key);
                configs.put(key, value);
            }
        } catch (Exception e) {
        }
        return configs;
    }

    /**
     * Kirjoittaa asetustiedostoon asetusten oletusarvot
     */
    public static void initProperties() {
        try {
            OutputStream output = new FileOutputStream(fileName);
            initData();
            properties.store(output, null);
        } catch (Exception f) {
        }
    }

    private static void initData() {
        properties.setProperty("difficulty", "1");
        properties.setProperty("pause", "P");
        properties.setProperty("menu", "M");
        properties.setProperty("Player1_Up", "W");
        properties.setProperty("Player1_Down", "S");
        properties.setProperty("Player2_Up", "Up");
        properties.setProperty("Player2_Down", "Down");
        properties.setProperty("endingpoint", "5");
        properties.setProperty("speedUp", "1");
        properties.setProperty("BallSpeed", "8");
        properties.setProperty("BatSpeed", "4");
        properties.setProperty("Player1_colour", "0x000000ff");
        properties.setProperty("Player2_colour", "0x000000ff");
        properties.setProperty("Ball_colour", "0x000000ff");
        properties.setProperty("powerups", "0");
    }

    /**
     * Palauttaa asetustiedoston arvot oletusarvoihin ja palauttaa tiedot
     * avain-arvo pareina hajautustaulussa
     *
     * @return String-String avain-arvo pareja sisältävä HashMap, johon on
     * talletettu asetustiedoston sisältö
     *
     * @see pong.dao.ConfigurationDao#initProperties()
     */
    public static HashMap<String, String> resetConfigurations() {
        initProperties();
        return fillHashMap();
    }

    /**
     * Asettaa halutun asetustiedoston arvon halutuksi arvoksi
     *
     * @param key Asetettavan arvon nimi
     * @param value Asetettava arvo
     */
    public static void setProperty(String key, String value) {
        OutputStream output = null;
        try {
            output = new FileOutputStream(fileName);
            properties.setProperty(key, value);
            properties.store(output, null);
        } catch (Exception e) {
        }

    }

}
