import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class GUIAlertBox {

    public static void display(String message) {

        /**
         * Initialize Stage
         */
        Stage window = new Stage();

        /**
         * Create Window
         */
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("User finder");
        window.setMinWidth(250);
        window.setMinHeight(150);

        /**
         * Label
         */
        Label label = new Label();
        label.setText(message);

        /**
         * Button & Action
         */
        Button closeButton = new Button("Continue");
        closeButton.setOnAction(event -> window.close());

        /**
         * Panel
         */
        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(label, closeButton);
        vBox.setAlignment(Pos.CENTER);

        /**
         * Scene
         */
        Scene scene3 = new Scene(vBox);

        /**
         * Windows Settings
         */
        window.getIcons().add(new Image("uom.gif"));
        window.centerOnScreen();
        window.setScene(scene3);
        window.showAndWait();
    }
}