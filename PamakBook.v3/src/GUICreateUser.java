import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;

public class GUICreateUser {

    private static final String EMAIL_ERROR = "Invalid e-mail";

    public static void display(ArrayList<User> pamakBookUsers, GUIConsole console) {

        Stage window = new Stage();

        /**
         * Create window
         */
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Create User");
        window.setMinHeight(100);
        window.setMinWidth(100);

        /**
         * Text Field
         */
        TextField userNameTextField = new TextField();
        TextField emailTextField    = new TextField();

        userNameTextField.setPromptText("User name");
        emailTextField.setPromptText("E-mail");

        /**
         * Button & Actions
         */
        Button closeButton  = new Button("Close");
        Button commitButton = new Button("Commit");

        closeButton.setOnAction(event -> window.close());
        commitButton.setOnAction(event -> {
            boolean userExistFlag = false;

            //Checks if name or e-mail already exists
            for (User u : pamakBookUsers) {
                if (userNameTextField.getText().equals(u.getName()) || emailTextField.getText().equals(u.getEmail())) {
                    userExistFlag = true;
                    if(userNameTextField.getText().equals(u.getName()))
                        console.setTextArea("This username already exists");
                    else
                        console.setTextArea("This e-mail already exists");
                }
            }

            //If the values does not exists in database it checks if the
            //e-mail is valid and then it creates the user, otherwise it prints error
            if (!userExistFlag)
                if(emailTextField.getText().startsWith("it") && emailTextField.getText().endsWith("@uom.edu.gr"))
                    pamakBookUsers.add(new User(userNameTextField.getText(), emailTextField.getText(), console));
                else
                    console.setTextArea(EMAIL_ERROR);

        });

        /**
         * Panel
         */
        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(8,8,8,8));
        gridPane.setVgap(8);
        gridPane.setHgap(8);

        GridPane.setConstraints(commitButton,0,0);
        GridPane.setConstraints(userNameTextField,1,0);
        GridPane.setConstraints(emailTextField,1,1);
        GridPane.setConstraints(closeButton,0,1);

        gridPane.getChildren().addAll(closeButton, commitButton,userNameTextField,emailTextField);

        /**
         * Scene
         */
        Scene scene = new Scene(gridPane);

        /**
         * Window settings
         */
        window.setScene(scene);
        window.getIcons().add(new Image("uom.gif"));
        commitButton.requestFocus();
        window.centerOnScreen();
        window.showAndWait();
    }
}
