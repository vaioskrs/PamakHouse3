import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIConsole {

    private TextArea textArea = new TextArea();
    private Stage    window   = new Stage();

    public void setTextArea(String textArea) {
        this.textArea.setText(textArea);
    }

    public void closeConsole() {
        window.close();
    }

    public void consoleDisplay() {

        Scene scene;

        /**
         * Text Area
         */
        textArea.setText("Welcome to Pamak Book");
        textArea.setEditable(false);
        textArea.setWrapText(true);

        /**
         * Panel
         */
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(8,8,8,8));
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(textArea);

        scene = new Scene(vBox,300,200);

        /**
         * Window Settings
         */
        window.show();
        window.getIcons().add(new Image("uom.gif"));
        window.setScene(scene);
        window.setTitle("Console display");
        window.setX(200);
        window.setY(190);
    }
}
