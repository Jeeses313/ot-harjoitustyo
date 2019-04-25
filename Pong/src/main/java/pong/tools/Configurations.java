package pong.tools;

import pong.dao.ConfigurationDao;
import java.util.HashMap;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

/**
 * Luokka säilyttää pelin asetuksia ja tarjoaa metodeja pelin asetuksiin
 * liittyvien tietojen käsittelylle
 */
public class Configurations {

    /**
     * Hajautustaulu asetusten säilyttämiselle
     */
    public HashMap<String, String> configs;
    private ConfigurationDao configurationDao;

    /**
     * Configurations olion konstruktori<br>
     * Pyytää parametrinaan asetuksia sisältävän tiedoston nimen/polun, joka
     * annetaan ConfigurationDao oliolle
     *
     * @param fileName Asetuksia sisältävän tiedoston nimi/polku
     *
     * @see pong.dao.ConfigurationDao
     */
    public Configurations(String fileName) {
        this.configs = configurationDao.initConfigurations(fileName);
    }

    /**
     * Palauttaa asetukset oletusarvoihinsa
     *
     * @see pong.dao.ConfigurationDao#resetConfigurations()
     */
    public void resetConfigs() {
        this.configs = configurationDao.resetConfigurations();
    }

    /**
     * Palauttaa annetulla nimellä asetuksiin talletetun näppäimen<br>
     * Virheen tapahtuessa asetuksiin talletetaan annettu oletusnäppäin ja
     * palautetaan se
     *
     * @param keyName Haettavan näppäimen nimi
     * @param defaultKey Oletusnäppäin, jos tapahtuu virhe näppäintä hakiessa
     * @return Annetulla nimellä talletettu KeyCode olio
     *
     * @see pong.dao.ConfigurationDao#setProperty(java.lang.String,
     * java.lang.String)
     */
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

    /**
     * Palauttaa annetulla nimellä asetuksiin talletetun kokonaisluvun<br>
     * Virheen tapahtuessa asetuksiin talletetaan annettu oletusluku ja
     * palautetaan se
     *
     * @param intName Haettavan luvun nimi
     * @param defaultInt Oletusluku, jos tapahtuu virhe näppäintä hakiessa
     * @return Annetulla nimellä talletettu int arvo
     *
     * @see pong.dao.ConfigurationDao#setProperty(java.lang.String,
     * java.lang.String)
     */
    public int getInt(String intName, int defaultInt) {
        try {
            return Integer.parseInt(this.configs.get(intName));
        } catch (Exception e) {
            this.setInt(intName, defaultInt);
            return Integer.parseInt(this.configs.get(intName));
        }
    }

    /**
     * Palauttaa annetulla nimellä asetuksiin talletetun liukuluvun<br>
     * Virheen tapahtuessa asetuksiin talletetaan annettu oletusluku ja
     * palautetaan se
     *
     * @param doubleName Haettavan luvun nimi
     * @param defaultDouble Oletusluku, jos tapahtuu virhe näppäintä hakiessa
     * @return Annetulla nimellä talletettu double arvo
     *
     * @see pong.dao.ConfigurationDao#setProperty(java.lang.String,
     * java.lang.String)
     */
    public double getDouble(String doubleName, double defaultDouble) {
        try {
            return Double.parseDouble(this.configs.get(doubleName));
        } catch (Exception e) {
            this.setDouble(doubleName, defaultDouble);
            return Double.parseDouble(this.configs.get(doubleName));
        }

    }

    /**
     * Palauttaa annetulla nimellä asetuksiin talletetun värin<br>
     * Virheen tapahtuessa asetuksiin talletetaan oletusväri, joka on musta, ja
     * palautetaan se
     *
     * @param colorName Haettavan värin nimi
     * @return Annetulla nimellä talletettu Color olio
     *
     * @see pong.dao.ConfigurationDao#setProperty(java.lang.String,
     * java.lang.String)
     */
    public Color getColor(String colorName) {
        try {
            return Color.valueOf(configs.get(colorName));
        } catch (Exception e) {
            this.setColor(colorName, Color.BLACK);
            return Color.valueOf(this.configs.get(colorName));
        }
    }

    /**
     * Tallettaa annetulla nimellä asetuksiin annetun näppäimen
     *
     * @param keyName Muutettavan näppäimen nimi
     * @param key Muutettavan näppäimen uusi arvo
     *
     * @see pong.dao.ConfigurationDao#setProperty(java.lang.String,
     * java.lang.String)
     */
    public void setKey(String keyName, KeyCode key) {
        this.configs.put(keyName, key.toString());
        configurationDao.setProperty(keyName, key.toString());
    }

    /**
     * Tallettaa annetulla nimellä asetuksiin annetun kokonaisluvun
     *
     * @param intName Muutettavan luvun nimi
     * @param integer Muutettavan luvun uusi arvo
     *
     * @see pong.dao.ConfigurationDao#setProperty(java.lang.String,
     * java.lang.String)
     */
    public void setInt(String intName, int integer) {
        this.configs.put(intName, integer + "");
        configurationDao.setProperty(intName, integer + "");
    }

    /**
     * Tallettaa annetulla nimellä asetuksiin annetun liukuluvun
     *
     * @param doubleName Muutettavan luvun nimi
     * @param doubleValue Muutettavan luvun uusi arvo
     *
     * @see pong.dao.ConfigurationDao#setProperty(java.lang.String,
     * java.lang.String)
     */
    public void setDouble(String doubleName, double doubleValue) {
        this.configs.put(doubleName, doubleValue + "");
        configurationDao.setProperty(doubleName, doubleValue + "");
    }

    /**
     * Tallettaa annetulla nimellä asetuksiin annetun värin
     *
     * @param colorName Muutettavan värin nimi
     * @param colorValue Muutettavan värin uusi arvo
     *
     * @see pong.dao.ConfigurationDao#setProperty(java.lang.String,
     * java.lang.String)
     */
    public void setColor(String colorName, Color colorValue) {
        this.configs.put(colorName, colorValue + "");
        configurationDao.setProperty(colorName, colorValue + "");
    }
}
