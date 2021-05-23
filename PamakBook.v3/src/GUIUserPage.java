import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class GUIUserPage {

    public static void display(User logInUser) {

        ArrayList<String> tempArrayListOfUsersPost = new ArrayList<>();

        /**
         * Window create
         */
        Stage window = new Stage();

        /**
         * Labels
         */
        Label userNameLabel        = new Label(logInUser.getName() + "  " + logInUser.getEmail());
        Label recentPostLabel      = new Label("Recent Posts by Friends");
        Label suggestedFriendLabel = new Label("Suggested friend");

        /**
         * Text Field
         */
        TextField suggestedFriendTextField = new TextField();
        suggestedFriendTextField.setText(new GUIUserPage().suggestedFriend(logInUser));

        /**
         * Text Area
         */
        TextArea writePostTextArea  = new TextArea();
        TextArea friendsPostTextArea = new TextArea();

        writePostTextArea.setPromptText("Enter your post here");
        writePostTextArea.setWrapText(true);
        writePostTextArea.setPrefWidth(250);
        writePostTextArea.setPrefHeight(200);

        friendsPostTextArea.setWrapText(true);
        friendsPostTextArea.setPrefWidth(250);
        friendsPostTextArea.setPrefHeight(200);

        friendsPostTextArea.setText(new GUIUserPage().friendsPost(tempArrayListOfUsersPost, logInUser));

        /**
         * Buttons
         */
        Button closeButton = new Button("Back to Main Page");
        Button postButton  = new Button("Post");

        /**
         * Date & Time
         */
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        /**
         * Button Actions
         */
        closeButton.setOnAction(event -> {
            tempArrayListOfUsersPost.clear();
            window.close();
        });
        postButton.setOnAction(event -> {

            logInUser.addToArrayListOfUserPost(dateFormat.format(date) + ", " + logInUser.getName() + " \n" + writePostTextArea.getText());
            tempArrayListOfUsersPost.add(      dateFormat.format(date) + ", " + logInUser.getName() + " \n" + writePostTextArea.getText());

            Collections.sort(tempArrayListOfUsersPost);

            String m = "";

            for(String s : tempArrayListOfUsersPost)
                m += s.toString() +"\n";

            friendsPostTextArea.setText(m);
        });

        /**
         * Panel
         */
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(8,8,8,8));
        gridPane.setHgap(8);
        gridPane.setVgap(8);

        GridPane.setConstraints(userNameLabel,0,0);;
        GridPane.setConstraints(closeButton,1,0);
        GridPane.setConstraints(writePostTextArea,0,1);
        GridPane.setConstraints(postButton,1,1);
        GridPane.setConstraints(friendsPostTextArea, 0,2);
        GridPane.setConstraints(recentPostLabel, 1,2);
        GridPane.setConstraints(suggestedFriendLabel, 0, 3);
        GridPane.setConstraints(suggestedFriendTextField,1, 3);

        gridPane.getChildren().addAll(suggestedFriendLabel, suggestedFriendTextField, recentPostLabel,friendsPostTextArea, postButton, writePostTextArea, userNameLabel, closeButton);

        /**
         * Scene
         */
        Scene scene = new Scene(gridPane, 430, 500);

        /**
         * Window settings
         */
        recentPostLabel.requestFocus();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("User Page");
        window.getIcons().add(new Image("uom.gif"));
        window.centerOnScreen();
        window.setScene(scene);
        window.showAndWait();
    }

    private String suggestedFriend(User logInUser) {
        String message = "";

        //If log in user friend list is not empty:
        if(!logInUser.getArrayListOfUserFriends().isEmpty()) {
            //Traverse the array list of log in user friends
            for(User friendOfLogIn: logInUser.getArrayListOfUserFriends()) {
                //Traverse the array list of log in user friend friends
                for(User friendOfTemp: friendOfLogIn.getArrayListOfUserFriends()) {
                    //Checks if the user who logged in is in the list of his friend friends list
                    //and finds if the log in user and his friends have not mutual friends
                    if(!logInUser.equals(friendOfTemp) && !friendOfTemp.isHeMyFriend(logInUser))
                        message = friendOfTemp.getName();
                }
            }
        }
        else
            message = "You have no friends";
        return message;
    }

    private String friendsPost(ArrayList<String> tempArrayListOfUsersPost, User logInUser) {
        String message = "";

        //Adds to post list the posts of log in user
        for(int i = 0; i < logInUser.getArrayListOfUserPost().size(); i++)
            tempArrayListOfUsersPost.add(logInUser.getArrayListOfUserPost().get(i));

        //If log in user friend list is not empty:
        if(!logInUser.getArrayListOfUserFriends().isEmpty()){
            //Traverse the log in user friends
            for(User u : logInUser.getArrayListOfUserFriends())
                //Gets the posts of log in user friends
                for(int j = 0; j < u.getArrayListOfUserPost().size(); j++)
                    tempArrayListOfUsersPost.add(u.getArrayListOfUserPost().get(j));
        }

        //Sort the posts
        Collections.sort(tempArrayListOfUsersPost);

        for(String s : tempArrayListOfUsersPost)
            message += s +"\n";

        return message;
    }

}
