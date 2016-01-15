import javafx.animation.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 * Created by elavrius on 11.01.16.
 */
public class TopMenu extends Canvas {

    GraphicsContext gc = getGraphicsContext2D();

    private int menuHeight = 25;

    public TopMenu() {
        widthProperty().addListener(evt -> draw());
        setHeight(menuHeight);
    }

    public void move() {
        Container.file.move();
        Container.help.move();
        if (Reader.getActive()) {
            Utility.transition(this.layoutYProperty(), -menuHeight, 200);
            for (TopMenuButton buttons : Container.topMenuButtons) {
                buttons.openMenu.close();
            }
        } else
            Utility.transition(this.layoutYProperty(), 0, 200);
    }

    public void draw() {
        double width = getWidth();
        double height = menuHeight;

        gc.clearRect(0, 0, width, height);
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, width, height);

    }

}
