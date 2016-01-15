import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;


/**
 * Created by elavrius on 11.01.16.
 */

public class TopMenuButton extends Canvas {
    GraphicsContext gc = getGraphicsContext2D();
    private double width = 50;
    private double height = 25;
    private boolean mouseOn = false;
    private boolean mouseClicked = false;
    String text = "";
    MenuButtonMenu openMenu;

    public TopMenuButton(String name, int x, int y, MenuButtonMenu menu) {
        this.openMenu = menu;
        openMenu.setLayoutX(x);
        for (TopMenuButtonChild c : openMenu.child) {
            c.setLayoutX(x);
        }
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.text = name;
        setWidth(width);
        setHeight(height);
        actionListener();
        draw();
    }

    public TopMenuButton(int x, int y, MenuButtonMenu menu) {
        this("", x, y, menu);
    }

    public void draw() {
        gc.clearRect(0, 0, width, height);
        if (mouseOn) {
            gc.setFill(Color.RED);
            gc.fillRect(0, 0, width, height);
        } else {
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, width, height);
        }
        gc.setFill(Color.WHITE);
        gc.fillRect(2, 2, width - 4, height - 4);
        writeText(text);
    }

    public void move() {
        if (Reader.getActive()) {
            Utility.transition(this.layoutYProperty(), -height, 200);
            mouseClicked = false;
        } else {
            Utility.transition(this.layoutYProperty(), 0, 200);
            draw();
        }
    }

    public void setText(String s) {
        this.text = s;
    }

    public boolean isMouseClicked() {
        return mouseClicked;
    }

    public boolean isMouseOn() {
        return mouseOn;
    }

    private void writeText(String s) {
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        if (mouseClicked) {
            gc.setFill(Color.RED);
        } else {
            gc.setFill(Color.BLACK);
        }
        gc.fillText(s, Math.round(width / 2), Math.round(height / 2));
    }

    public void desactivate() {
        this.mouseClicked = false;
        this.openMenu.close();
        this.mouseOn = false;
        this.draw();
    }

    public void actionListener() {

        this.setOnMouseClicked(e -> {
                    if (mouseClicked) mouseClicked = false;
                    else mouseClicked = true;
                    openMenu.move();
                    draw();
                }
        );

        this.setOnMouseEntered(e -> {
                    mouseOn = true;
                    for (TopMenuButton button : Container.topMenuButtons) {
                        if (button.isMouseClicked() && button != this) {
                            button.desactivate();
                            mouseClicked = true;
                            openMenu.open();
                        }
                    }
                    draw();
                }
        );
        this.setOnMouseExited(e -> {
                    mouseOn = false;
                    draw();
                }
        );
    }
}
