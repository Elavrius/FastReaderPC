import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Font;

/**
 * Created by elavrius on 11.01.16.
 */
public class ResizableCanvas extends Canvas {

    private int size=20;
    Text t1 = new Text();
    Text t2 = new Text();
    Text t3 = new Text();

    GraphicsContext gc = getGraphicsContext2D();

    public boolean showSpeed=false;
    private int showSpeedCounter=0;
    public boolean showSize=false;
    private int showSizeCounter;

    public ResizableCanvas() {
        Container.flow = new TextFlow();
        //this.widthProperty().addListener(e ->draw());
        //this.heightProperty().addListener(e -> draw());
        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                draw();
            }
        };
        animation.start();

    }

    public void setSize(int n){
        size=20;
    }

    public void changeSize(int n){
        size+=n;
        if (size<5)size=5;
    }

    private void draw() {

        double width = getWidth();
        double height = getHeight();

        drawText(width, height);
        Container.startStop.setLayoutY(height - 50);
        Container.startStop.setLayoutX(width / 2 - 25);
        Container.startStop.draw();

        if (!Reader.getActive()) {
            if(Container.previousWord.animationComplete){
            Container.previousWord.setLayoutX(width / 2 - 60);
            Container.previousWord.setLayoutY(height - 45);
            Container.nextWord.setLayoutX(width / 2 + 20);
            Container.nextWord.setLayoutY(height - 45);}
        }
        else{
            if(Container.previousWord.animationComplete){
            Container.previousWord.setLayoutY(height - 45);
            Container.previousWord.setLayoutX(width / 2 - 20);
            Container.nextWord.setLayoutY(height - 45);
            Container.nextWord.setLayoutX(width / 2 - 20);}
        }
        Container.previousWord.draw();
        Container.nextWord.draw();

        gc.clearRect(0, 0, width, height);
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);

        gc.setFill(Color.WHITE);
        gc.fillRect(0, Math.round(height / 2) - (size+5), width, (size+5)*2);

        if(showSpeed){
            double i=Math.round(60/((double)(Reader.getOriginalSpeed())+1)*60);
            String speedInfo="Speed: "+Double.toString(i)+" W//min";
            gc.setFill(Color.RED);
            gc.fillText(speedInfo,20,50);
            showSpeedCounter++;
            if (showSpeedCounter>120) {
                showSpeed=false;
                showSpeedCounter=0;
            }
        }
        if(showSize){
            String sizeInfo="Font size: "+Integer.toString(size);
            gc.setFill(Color.RED);
            gc.fillText(sizeInfo,width-100,50);
            showSizeCounter++;
            if (showSizeCounter>120) {
                showSize=false;
                showSizeCounter=0;
            }
        }

        //Double.toString(width) + ", " + Double.toString(height)
    }

    public void drawText(double width, double height) {
        String out = Reader.currentText();

        if (Reader.getCounter() <= 1) {
            int length = out.length();
            int midChar = (int) (length / 2);

            Font df = new Font("Verdana", size);
            t1.setFont(df);
            t1.setFill(Color.BLACK);

            t2.setFont(df);
            t2.setFill(Color.RED);

            t3.setFont(df);
            t3.setFill(Color.BLACK);

            if(length==1){
                t1.setText("");
                t2.setText(out);
                t3.setText("");
            }
            else if(length==2){
                t1.setText(out.substring(0,1));
                t2.setText(out.substring(1));
                t3.setText("");
            }
            else if(length==3){
                t1.setText(out.substring(0,1));
                t2.setText(out.substring(1,2));
                t3.setText(out.substring(2));
            }
            else if(length>3){
            t1.setText(out.substring(0, midChar - 1));
            t2.setText(out.substring(midChar - 1, midChar));
            t3.setText(out.substring(midChar, length));}

            Container.flow.getChildren().clear();
            Container.flow.getChildren().addAll(t1, t2, t3);

            double textWidth = Container.flow.getWidth();
            double textHeight = Container.flow.getHeight();

            if (Reader.getCounter() == 0) {

                Container.flow.setOpacity(0);

            } else {
                Container.flow.setOpacity(100);
                Container.flow.setLayoutX(width / 2 - textWidth / 2);// +textWidth/length*correction);
                Container.flow.setLayoutY(height / 2 - textHeight / 2);

            }

        }

    }

}
