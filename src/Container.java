import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Created by elavrius on 11.01.16.
 */
public class Container {
    public static Pane pane;
    public static TopMenu menu;
    public static TopMenuButton file;
    public static MenuButtonMenu fileMenu;
    public static MenuButtonMenu helpMenu;
    public static TopMenuButtonChild open;
    public static TopMenuButtonChild exit;
    public static TopMenuButtonChild about;
    public static TopMenuButtonChild shortcuts;
    public static TopMenuButton help;
    public static ControlButton startStop;
    public static ControlButton previousWord;
    public static ControlButton nextWord;

    public static TopMenuButton[] topMenuButtons;

    public static TextFlow flow;
    public static Text text;
}
