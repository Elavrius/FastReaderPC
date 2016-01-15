import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by elavrius on 11.01.16.
 */
public class Main extends Application {
    Scene scene;
    ResizableCanvas canvas;
    boolean activeCtrl=false;
    @Override
    public void start(Stage stage) throws Exception {

        Container.pane = new Pane();
        Container.pane.setMinSize(300, 200);

        canvas = new ResizableCanvas();

        Container.menu = new TopMenu();

        Container.open = new TopMenuButtonChild("Open", 1){
            @Override
            public void handle(){
                Container.file.desactivate();
                Utility.openFile();
            }
        };
        Container.exit = new TopMenuButtonChild("Exit", 2){
            @Override
            public void handle(){
                Utility.close(stage);
            }
        };

        Container.about = new TopMenuButtonChild("About", 1){
            @Override
            public void handle(){
                Stage st=new Stage();
                Canvas c=new Canvas(200,120);
                GraphicsContext lgc=c.getGraphicsContext2D();
                lgc.setFill(Color.BLACK);
                lgc.fillRect(0,0,200,200);
                lgc.setFill(Color.WHITE);
                lgc.fillText("This program was",20,20);
                lgc.fillText("made by Elavrius,",20,35);
                lgc.fillText("if You want to ",20,50);
                lgc.fillText("support developers,",20,65);
                lgc.fillText("please do something",20,80);
                lgc.fillText("good today!!!",20,95);
                Pane p=new Pane(c);
                st.setScene(new Scene(p));
                st.setResizable(false);
                st.show();
            }
        };
        Container.shortcuts = new TopMenuButtonChild("Shortcuts", 2){
            @Override
            public void handle(){
                Stage st=new Stage();
                Canvas c=new Canvas(220,145);
                GraphicsContext lgc=c.getGraphicsContext2D();
                lgc.setFill(Color.BLACK);
                lgc.fillRect(0,0,220,145);
                lgc.setFill(Color.WHITE);
                lgc.fillText("SPACEBAR - Start/Stop",20,20);
                lgc.fillText("LEFT - Previous word,",20,35);
                lgc.fillText("RIGHT - Next word",20,50);
                lgc.fillText("UP - Increase speed",20,65);
                lgc.fillText("DOWN - Decrease speed",20,80);
                lgc.fillText("CTRL+UP - Increase font",20,95);
                lgc.fillText("CTRL+DOWN - Decrease font",20,110);
                lgc.fillText("CTRL+0 - Default font",20,125);
                Pane p=new Pane(c);
                st.setScene(new Scene(p));
                st.setResizable(false);
                st.show();
            }
        };

        TopMenuButtonChild[] fileMenuButtons = {Container.open, Container.exit};
        TopMenuButtonChild[] helpMenuButtons = {Container.about, Container.shortcuts};

        Container.fileMenu = new MenuButtonMenu(fileMenuButtons, fileMenuButtons.length);
        Container.helpMenu = new MenuButtonMenu(helpMenuButtons, helpMenuButtons.length);
        Container.file = new TopMenuButton("File", 0, 0, Container.fileMenu);
        Container.help = new TopMenuButton("Help", 50, 0, Container.helpMenu);

        Container.startStop=new ControlButton(50,"Go"){
            @Override
            public void handle(){
                Reader.pause();
                if(Reader.getActive()){
                Container.startStop.setText("II");}
                else{Container.startStop.setText("GO");}
            }
        };
        Container.previousWord =new ControlButton(40,"<"){
            @Override
            public void handle(){
                if(!Reader.getActive() && Reader.text!=null){
                    Reader.previousWord();
                }
            }
        };
        Container.nextWord=new ControlButton(40,">"){
            @Override
            public void handle(){
                if(!Reader.getActive() && Reader.text!=null){
                    Reader.nextWord();
                }
            }
        };

        TopMenuButton[] temp = {Container.file, Container.help};
        Container.topMenuButtons = temp;

        Container.pane.getChildren().add(canvas);
        Container.pane.getChildren().add(Container.flow);
        Container.pane.getChildren().add(Container.menu);
        Container.pane.getChildren().add(Container.file);
        Container.pane.getChildren().add(Container.fileMenu);
        Container.pane.getChildren().add(Container.help);
        Container.pane.getChildren().add(Container.open);
        Container.pane.getChildren().add(Container.exit);
        Container.pane.getChildren().add(Container.about);
        Container.pane.getChildren().add(Container.shortcuts);
        Container.pane.getChildren().add(Container.previousWord);
        Container.pane.getChildren().add(Container.nextWord);
        Container.pane.getChildren().add(Container.startStop);

        Container.menu.widthProperty().bind(Container.pane.widthProperty());
        canvas.widthProperty().bind(Container.pane.widthProperty());
        canvas.heightProperty().bind(Container.pane.heightProperty());

        stage.setScene(scene = new Scene(Container.pane));
        stage.setTitle("Hello");
        actionListener();
        stage.show();
    }

    public void actionListener() {
        scene.setOnMouseClicked(e -> {
                    for (TopMenuButton button : Container.topMenuButtons) {
                        if (button.isMouseClicked() && !button.isMouseOn()) {
                            button.desactivate();
                        }
                    }
                }
        );

        scene.setOnKeyReleased(e ->{
            if(e.getCode().equals(KeyCode.CONTROL)){
                activeCtrl=false;
            }
        });

        scene.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.SPACE)) {
                Reader.pause();
                if(Reader.getActive()){
                    Container.startStop.setText("II");}
                else{Container.startStop.setText("GO");}

            }
            if(e.getCode().equals(KeyCode.UP)){
                if(activeCtrl){
                    canvas.changeSize(1);
                    canvas.showSize=true;
                }
                else{
                Reader.setSpeed(-1);
                canvas.showSpeed=true;
                }
            }
            if(e.getCode().equals(KeyCode.DOWN)){
                if(activeCtrl){
                    canvas.changeSize(-1);
                    canvas.showSize=true;
                }
                else{
                Reader.setSpeed(1);
                canvas.showSpeed=true;
                }
            }
            if(e.getCode().equals(KeyCode.RIGHT)){
                if(!Reader.getActive() && Reader.text!=null){
                    Reader.nextWord();
                }
            }
            if(e.getCode().equals(KeyCode.LEFT)){
                if(!Reader.getActive() && Reader.previousText!=null){
                    Reader.previousWord();
                }
            }
            if(e.getCode().equals(KeyCode.CONTROL)){
                activeCtrl=true;
            }

            if(e.getCode().equals(KeyCode.getKeyCode("0"))){
                if (activeCtrl){
                    canvas.setSize(20);
                    canvas.showSize=true;
                }
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
