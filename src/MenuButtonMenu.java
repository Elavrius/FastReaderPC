import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by elavrius on 11.01.16.
 */
public class MenuButtonMenu extends Canvas {
    private int height;
    private boolean menuOpen = false;
    GraphicsContext gc = getGraphicsContext2D();
    public TopMenuButtonChild[] child;

    public MenuButtonMenu(TopMenuButtonChild[] c, int size) {
        this.child = c;
        height = 26 * size + 1;
        setLayoutY(-height);
        setWidth(100);
        setHeight(height);
        draw();
    }

    public void draw() {
        double width = getWidth();
        double height = getHeight();
        gc.clearRect(0, 0, width, height);
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, width, height);
    }

    public void close() {
        menuOpen = false;
        this.setLayoutY(-height);
        for (TopMenuButtonChild c : child) {
            c.close();
        }
    }

    public void open() {
        menuOpen = true;
        this.setLayoutY(25);
        for (TopMenuButtonChild c : child) {
            c.open();
        }
    }

    public void move() {
        if (menuOpen) {
            close();
        } else {
            open();
        }
    }
}
