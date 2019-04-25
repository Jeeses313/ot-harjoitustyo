package pong.tools;

import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * Luokka tarjoaa metodeja JavaFX:n piirrettävien olioiden luomiselle
 */
public class ComponentCreator {

    /**
     * Metodi luo piirrettävän tekstiolion annettuun paikkaan annetulla fontin
     * koolla ja sisällöllä
     *
     * @param x Luotavan tekstin sijainti x-akselilla
     * @param y Luotavan tekstin sijainti y-akselilla
     * @param font Luotavan tekstin fontin koko
     * @param text Luotavan tekstin sisältö
     * @return Label olio annetuilla ominaisuuksilla
     *
     * @see pong.tools.ComponentCreator#createLabel(int, int, java.lang.String)
     */
    public static Label createLabel(int x, int y, int font, String text) {
        Label newLabel = new Label(text);
        newLabel.setTranslateX(x);
        newLabel.setTranslateY(y);
        newLabel.setFont(new Font(font));
        return newLabel;
    }

    /**
     * Metodi luo piirrettävän tekstiolion annettuun paikkaan annetulla
     * sisällöllä
     *
     * @param x Luotavan tekstin sijainti x-akselilla
     * @param y Luotavan tekstin sijainti y-akselilla
     * @param text Luotavan tekstin sisältö
     * @return Label olio annetuilla ominaisuuksilla
     *
     * @see pong.tools.ComponentCreator#createLabel(int, int, int,
     * java.lang.String)
     */
    public static Label createLabel(int x, int y, String text) {
        Label newLabel = new Label(text);
        newLabel.setTranslateX(x);
        newLabel.setTranslateY(y);
        return newLabel;
    }

    /**
     * Metodi luo piirrettävän napin annettuun paikkaan annetulla koolla ja
     * tekstillä
     *
     * @param x Luotavan napin sijainti x-akselilla
     * @param y Luotavan napin sijainti y-akselilla
     * @param width Luotavan napin leveys
     * @param height Luotavan napin korkeus
     * @param text Luotavan napin teksti
     * @return Button olio annetuilla ominaisuuksilla
     *
     * @see pong.tools.ComponentCreator#createRadioButton(int, int, int, int,
     * java.lang.String)
     */
    public static Button createButton(int x, int y, int width, int height, String text) {
        Button newButton = new Button(text);
        newButton.setTranslateX(x);
        newButton.setTranslateY(y);
        newButton.setPrefSize(width, height);
        return newButton;
    }

    /**
     * Metodi luo piirrettävän napin, jonka voi lisätä ToglleGroup:iin,
     * annettuun paikkaan annetulla koolla ja tekstillä
     *
     * @param x Luotavan napin sijainti x-akselilla
     * @param y Luotavan napin sijainti y-akselilla
     * @param width Luotavan napin leveys
     * @param height Luotavan napin korkeus
     * @param text Luotavan napin teksti
     * @return RadioButton olio annetuilla ominaisuuksilla
     *
     * @see pong.tools.ComponentCreator#createButton(int, int, int, int,
     * java.lang.String)
     */
    public static RadioButton createRadioButton(int x, int y, int width, int height, String text) {
        RadioButton newRadioButton = new RadioButton(text);
        newRadioButton.setTranslateX(x);
        newRadioButton.setTranslateY(y);
        newRadioButton.setPrefSize(width, height);
        newRadioButton.getStyleClass().remove("radio-button");
        newRadioButton.getStyleClass().add("toggle-button");
        return newRadioButton;
    }

    /**
     * Metodi luo piirrettävän värinvalintavalikon annettuun paikkaan annetulla
     * koolla ja annetulla oletusvärillä
     *
     * @param x Luotavan värinvalintavalikon sijainti x-akselilla
     * @param y Luotavan värinvalintavalikon sijainti y-akselilla
     * @param width Luotavan värinvalintavalikon leveys
     * @param height Luotavan värinvalintavalikon korkeus
     * @param color Luotavan värinvalintavalikon oletusväri
     * @return ColorPicker olio annetuilla ominaisuuksilla
     */
    public static ColorPicker createColourPicker(int x, int y, int width, int height, Color color) {
        ColorPicker newColorPicker = new ColorPicker();
        newColorPicker.setTranslateX(x);
        newColorPicker.setTranslateY(y);
        newColorPicker.setPrefSize(width, height);
        newColorPicker.setValue(color);
        return newColorPicker;
    }

    /**
     * Metodi luo piirrettävän suorakulmion annettuun paikkaan annetulla koolla
     * ja annetulla värillä
     *
     * @param width Luotavan suorakulmion leveys
     * @param height Luotavan suorakulmion korkeus
     * @param x Luotavan suorakulmion sijainti x-akselilla
     * @param y Luotavan suorakulmion sijainti y-akselilla
     * @param color Luotavan suorakulmion väri
     * @return Rectangle olio annetuilla ominaisuuksilla
     *
     * @see pong.tools.ComponentCreator#createCircle(int, double, double,
     * javafx.scene.paint.Color)
     * @see pong.tools.ComponentCreator#createDiamond(double, double,
     * javafx.scene.paint.Color)
     */
    public static Rectangle createRectangle(int width, int height, double x, double y, Color color) {
        Rectangle newRectangle = new Rectangle(width, height);
        newRectangle.setTranslateX(x);
        newRectangle.setTranslateY(y);
        newRectangle.setFill(color);
        return newRectangle;
    }

    /**
     * Metodi luo piirrettävän ympyrän annettuun paikkaan annetulla koolla ja
     * annetulla värillä
     *
     * @param radius Luotavan ympyrän säde
     * @param x Luotavan ympyrän sijainti x-akselilla
     * @param y Luotavan ympyrän sijainti y-akselilla
     * @param color Luotavan ympyrän väri
     * @return Circle olio annetuilla ominaisuuksilla
     *
     * @see pong.tools.ComponentCreator#createRectangle(int, int, double,
     * double, javafx.scene.paint.Color)
     * @see pong.tools.ComponentCreator#createDiamond(double, double,
     * javafx.scene.paint.Color)
     */
    public static Circle createCircle(int radius, double x, double y, Color color) {
        Circle newCircle = new Circle(radius);
        newCircle.setTranslateX(x);
        newCircle.setTranslateY(y);
        newCircle.setFill(color);
        return newCircle;
    }

    /**
     * Metodi luo piirrettävän 45 astetta sivulle käännetyn ja 20x20 kokoisen
     * ruudun annettuun paikkaan annetulla värillä
     *
     * @param x Luotavan ruudun sijainti x-akselilla
     * @param y Luotavan ruudun sijainti y-akselilla
     * @param color Luotavan ruudun väri
     * @return Rectangle olio annetuilla ominaisuuksilla
     *
     * @see pong.tools.ComponentCreator#createRectangle(int, int, double,
     * double, javafx.scene.paint.Color)
     * @see pong.tools.ComponentCreator#createCircle(int, double, double,
     * javafx.scene.paint.Color)
     */
    public static Rectangle createDiamond(double x, double y, Color color) {
        Rectangle newDiamond = new Rectangle(20, 20);
        newDiamond.setTranslateX(x);
        newDiamond.setTranslateY(y);
        newDiamond.setFill(color);
        newDiamond.setRotate(45);
        return newDiamond;
    }
}
