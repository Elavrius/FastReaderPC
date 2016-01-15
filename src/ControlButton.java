import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 * Created by elavrius on 12.01.16.
 */
public class ControlButton extends Canvas {

    String text;

    GraphicsContext gc = getGraphicsContext2D();
    public boolean animationComplete=true;
    private int size;
    private boolean mouseEntered=false;

    public ControlButton(int size, String text){
        this.size=size;
        setHeight(size);
        setWidth(size);
        actionListener();
        setText(text);
        draw();

    }

    public void setText(String s){
        text=s;
        draw();
    }

    public void draw(){
        if(mouseEntered){
            gc.setFill(Color.RED);
        }
        else{
            gc.setFill(Color.DARKRED);
        }
        gc.fillOval(0,0,size,size);
        gc.setFill(Color.BLACK);
        gc.fillOval(2,2,size-4,size-4);
        if(mouseEntered){
            gc.setFill(Color.RED);
        }
        else{
            gc.setFill(Color.DARKRED);
        }
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(Font.font("Verdana",20));
        gc.fillText(text,size/2,size/2);
    }

    public void moveSide(int distance){
        animationComplete=false;
        int end=(int)getLayoutX()+distance;
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(this.layoutXProperty(), end);
        KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                animationComplete=true;
            }
        });
        timeline.play();
        //Utility.transition(this.layoutXProperty(), end, 200);
    }

    public void handle(){

    }
    public void actionListener(){
        this.setOnMouseClicked(e ->this.handle());
        this.setOnMouseEntered(e ->{
            mouseEntered=true;
            draw();
        });
        this.setOnMouseExited(e->{
            mouseEntered=false;
            draw();
        });
    }
}
