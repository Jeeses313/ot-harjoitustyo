
package pong.tools;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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
    
    public static Rectangle createRectangle(int width, int height, double x, double y) {
        Rectangle newRectangle = new Rectangle(width, height);
        newRectangle.setTranslateX(x);
        newRectangle.setTranslateY(y);
        return newRectangle;
    }
    
    public static Circle createCircle(int radius, double x, double y) {
        Circle newCircle = new Circle(radius);
        newCircle.setTranslateX(x);
        newCircle.setTranslateY(y);
        return newCircle;
    }
}
