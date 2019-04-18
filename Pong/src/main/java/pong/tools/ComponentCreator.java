
package pong.tools;

import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class ComponentCreator {
 
    public static Label createLabel(int x, int y, int font, String text) {
        Label newLabel = new Label(text);
        newLabel.setTranslateX(x);
        newLabel.setTranslateY(y);
        newLabel.setFont(new Font(font));
        return newLabel;
    }
    
    public static Label createLabel(int x, int y, String text) {
        Label newLabel = new Label(text);
        newLabel.setTranslateX(x);
        newLabel.setTranslateY(y);
        return newLabel;
    }
    
    public static Button createButton(int x, int y, int width, int height, String text) {
        Button newButton = new Button(text);
        newButton.setTranslateX(x);
        newButton.setTranslateY(y);
        newButton.setPrefSize(width, height);
        return newButton;
    }
    
    public static RadioButton createRadioButton(int x, int y, int width, int height, String text) {
        RadioButton newRadioButton = new RadioButton(text);
        newRadioButton.setTranslateX(x);
        newRadioButton.setTranslateY(y);
        newRadioButton.setPrefSize(width, height);
        return newRadioButton;
    }
    
    public static ColorPicker createColourPicker(int x, int y, int width, int height, Color color) {
        ColorPicker newColorPicker = new ColorPicker();
        newColorPicker.setTranslateX(x);
        newColorPicker.setTranslateY(y);
        newColorPicker.setPrefSize(width, height);
        newColorPicker.setValue(color);
        return newColorPicker;
    }
    
    public static Rectangle createRectangle(int width, int height, double x, double y, Color color) {
        Rectangle newRectangle = new Rectangle(width, height);
        newRectangle.setTranslateX(x);
        newRectangle.setTranslateY(y);
        newRectangle.setFill(color);
        return newRectangle;
    }
    
    public static Circle createCircle(int radius, double x, double y, Color color) {
        Circle newCircle = new Circle(radius);
        newCircle.setTranslateX(x);
        newCircle.setTranslateY(y);
        newCircle.setFill(color);
        return newCircle;
    }
    
    public static Rectangle createDiamond(double x, double y, Color color) {
        Rectangle newDiamond = new Rectangle(20, 20);
        newDiamond.setTranslateX(x);
        newDiamond.setTranslateY(y);
        newDiamond.setFill(color);
        newDiamond.setRotate(45);
        return newDiamond;
    }
}
