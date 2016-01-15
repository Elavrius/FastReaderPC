import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.WritableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by elavrius on 11.01.16.
 */
public class Utility {

    public static void transition(WritableValue target, double end, double timeMilis) {
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(target, end);
        KeyFrame kf = new KeyFrame(Duration.millis(timeMilis), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

    public static void openFile(){
        File book;
        ArrayList<String> words=new ArrayList();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        book = fileChooser.showOpenDialog(new Stage());

        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(book);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert fileScanner != null;
        while (fileScanner.hasNext()){
            String [] temp= fileScanner.nextLine().split(" |\\n");
            for(String s: temp){
                if(s.length()>0)
                words.add(s);
            }
        }

        fileScanner.close();

        Reader.text=words;
        Reader.setCurrentText("Press SPACEBAR to win");
    }

    public static void close(Stage stage){
        stage.close();
    }
}
