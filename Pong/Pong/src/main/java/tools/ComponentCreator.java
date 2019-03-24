
package tools;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
}
