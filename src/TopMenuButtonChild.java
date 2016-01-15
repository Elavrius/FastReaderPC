import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/**
 * Created by elavrius on 12.01.16.
 */
public class TopMenuButtonChild extends Canvas {

    private GraphicsContext gc = getGraphicsContext2D();
    private int width;
    private int height;
    private int x;
    private int y;
    private String text;
    private boolean mouseOn;
    private boolean mousePressed;
    private int number;

    public TopMenuButtonChild(String name, int number) {
        this.number = number;
        this.width = 100;
        this.height = 25;
        setWidth(width);
        setHeight(height);
        this.y = height * number + number;
        setLayoutY(-100);
        this.text = name;
        actionListener();
        draw();
    }

    public void draw() {
        gc.clearRect(0, 0, width, height);

        if(mouseOn){
            gc.setFill(Color.WHEAT);
        }
        else {
            gc.setFill(Color.WHITE);
        }
        gc.fillRect(0, 0, width, height);

        if(mousePressed){
            gc.setFill(Color.RED);
        }
        else{
            gc.setFill(Color.BLACK);
        }
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText(text, width / 2, height / 2);
    }

    public void close() {
        setLayoutY(-100);
    }

    public void open() {
        setLayoutY(y);
    }

    public void handle(){

    }

    public void actionListener(){
        this.setOnMouseEntered(e -> {
                    mouseOn = true;
                    draw();
                }
        );
        this.setOnMouseExited(e ->{
            mouseOn=false;
            draw();
        });

        this.setOnMousePressed(e ->{
            mousePressed=true;
            draw();
        });
        this.setOnMouseReleased(e->{
            mousePressed=false;
            draw();
            handle();
        });
    }

}
