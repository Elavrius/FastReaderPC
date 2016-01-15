import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by elavrius on 11.01.16.
 */
public class Reader {

    public static ArrayList<String> text;
    public static ArrayList<String> previousText=new ArrayList();

    private static String currentText = "Hello my new friend";


    private static boolean active = false;
    private static int speed = 15;
    private static int counter = 0;
    private static int originalSpeed=15;

    public static int getCounter() {
        return counter;
    }

    public static void setCurrentText(String s){
        currentText=s;
    }

    public static void pause() {
        if (active) active = false;
        else active = true;
        counter = 1;
        Container.menu.move();
        if(Reader.getActive()){
            Container.previousWord.moveSide(40);
            Container.nextWord.moveSide(-40);}
        else{
            Container.previousWord.moveSide(-40);
            Container.nextWord.moveSide(40);
        }
    }

    public static boolean getActive() {
        return active;
    }

    private static void checkSpeed(){
        boolean mediumSpeed=false;
        boolean slowSpeed=false;
        for(String m: slowMarks){
            if(currentText.substring(currentText.length()-1).equals(m)){
                slowSpeed=true;
                break;
            }
        }
        if(!slowSpeed){
        for(String m: mediumMarks){
            if(currentText.substring(currentText.length()-1).equals(m)){
                mediumSpeed=true;
                break;
            }
        }
        }
        if(slowSpeed){
            speed=speed*2;
        }
        else if(mediumSpeed){
            speed=(int)(speed*1.5);
        }
        else{
            speed=originalSpeed;
        }
    }
    public static void nextWord() {
        if (text.size() > 0) {
            currentText = text.get(0);
            checkSpeed();
            previousText.add(0, currentText);
            text.remove(0);
            counter = 0;
        }
    }

    public static void previousWord() {
        if (previousText.size() > 0) {
            currentText = previousText.get(0);
            text.add(0, currentText);
            previousText.remove(0);
            counter = 0;
        }
    }

    private static String[] slowMarks = {".","?","!"};
    private  static String[] mediumMarks = {",","-","—"};

    public static String currentText() {
        if (active) {
            if (text != null) {
                if (counter >= speed) {
                    nextWord();
                } else {
                    counter++;
                    return currentText;
                }
            }
        } else {
            counter = 1;
        }
        return (currentText);
    }

    public static void setSpeed(int i) {
        speed = originalSpeed+i;
        if (speed <= 1) speed = 1;
        if (speed > 120) speed = 120;
        originalSpeed=speed;
    }

    public static int getSpeed() {
        return speed;
    }
    public static int getOriginalSpeed(){
        return originalSpeed;
    }
}
