import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;

public class GUIMain extends Application{

    private Stage            window;
    private Scene            mainPageScene, editUserScene;
    private Button           loadPamakBookButton, addFriendButton, findMutualFriendButton, printFriendsButton,
                             addToGroupButton,    logInButton,     backToMainPageButton,   createUserButton,
                             savePamakBookButton, editUsersButton, printGroupButton,       printClosedGroupButton,  addToClosedGroupButton;
    private TextField        userNameTextField, emailTextField;
    private ComboBox<String> comboBox1, comboBox2;
    private ArrayList<User>  pamakBookUsers = new ArrayList<>();
    private GUIConsole       console        = new GUIConsole();
    private boolean          flag           = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        /**
         * Initialize Users and Groups and console
         */
        pamakBookUsers = Initializer.initialize(console);

        /**
         * Text Fields
         */
        userNameTextField = new TextField();
        emailTextField    = new TextField();

        userNameTextField.setPromptText("User name");
        emailTextField.setPromptText("E-mail");

        /**
         * Buttons
         */
        createUserButton    = new Button("Create user");
        logInButton         = new Button("Log in");
        savePamakBookButton = new Button("Save Pamak Book");
        loadPamakBookButton = new Button("Load Pamak Book");
        editUsersButton     = new Button("Edit users");

        /**
         * Button Actions
         */
        createUserButton.setOnAction(event -> createUserAction());
        logInButton.setOnAction(event -> logInAction());
        savePamakBookButton.setOnAction(event -> saveFileAction());
        loadPamakBookButton.setOnAction(event -> loadFileAction());
        editUsersButton.setOnAction(event -> {
            comboBox1.requestFocus();
            window.setScene(editUserScene);
            window.setTitle("Edit Users Page");
        });

        /**
         * Main Page Panel
         */
        GridPane mainPagePanel = new GridPane();

        mainPagePanel.setPadding(new Insets(8,8,8,8));
        mainPagePanel.setVgap(8);
        mainPagePanel.setHgap(8);

        GridPane.setConstraints(createUserButton,0,0);
        GridPane.setConstraints(editUsersButton, 0,1);
        GridPane.setConstraints(savePamakBookButton,0,2);
        GridPane.setConstraints(loadPamakBookButton,0,3);
        GridPane.setConstraints(logInButton,1,0);
        GridPane.setConstraints(userNameTextField,1,1);
        GridPane.setConstraints(emailTextField,1,2);

        mainPagePanel.getChildren().addAll(loadPamakBookButton, editUsersButton, createUserButton, savePamakBookButton,
                logInButton, userNameTextField, emailTextField);

        /**
         * Main Page Scene
         */
        mainPageScene = new Scene(mainPagePanel, 300, 150);

        /**
         * Window settings
         */
        console.consoleDisplay();
        createUserButton.requestFocus();
        window.show();
        window.setScene(mainPageScene);
        window.setTitle("Main Page");
        window.getIcons().add(new Image("uom.gif"));
        window.centerOnScreen();
        window.setOnCloseRequest(event -> console.closeConsole());



        /**
         * Edit Users Page
         */

        /**
         * Buttons
         */
        addFriendButton        = new Button("Add friend");
        findMutualFriendButton = new Button("Find mutual");
        printFriendsButton     = new Button("Print your friends");
        addToGroupButton       = new Button("Add to group");
        addToClosedGroupButton = new Button("Add to closed group");
        backToMainPageButton   = new Button("Back to Main Page");
        printGroupButton       = new Button("Print group");
        printClosedGroupButton = new Button("Print closed group");

        Tooltip tooltip  = new Tooltip();
        Tooltip tooltip2 = new Tooltip();

        tooltip.setText("Select from both lists");
        tooltip2.setText("Select from the first list");

        addFriendButton.setTooltip(tooltip);
        findMutualFriendButton.setTooltip(tooltip);
        printFriendsButton.setTooltip(tooltip2);
        addToGroupButton.setTooltip(tooltip2);
        addToClosedGroupButton.setTooltip(tooltip2);

        /**
         * Combo Box
         */
        comboBox1 = new ComboBox<>();
        comboBox2 = new ComboBox<>();

        for(User u: pamakBookUsers) {
            comboBox1.getItems().add(u.getName());
            comboBox2.getItems().add(u.getName());
        }

        /**
         * Labels
         */
        Label label = new Label("Select from both lists");

        /**
         * Button Actions
         */
        addFriendButton.setOnAction(event -> addFriendAction());
        findMutualFriendButton.setOnAction(event -> findMutualAction());
        printFriendsButton.setOnAction(event -> printFriendAction());
        addToGroupButton.setOnAction(event -> addToGroupAction());
        addToClosedGroupButton.setOnAction(event -> addToClosedGroupAction());
        printGroupButton.setOnAction(event -> Initializer.getGroup1().printInfo(console));
        printClosedGroupButton.setOnAction(event -> Initializer.getGroup2().printInfo(console));
        backToMainPageButton.setOnAction(event -> {
            window.setScene(mainPageScene);
            window.setTitle("Main Page");
        });

        /**
         * User Edit Page Panel
         */
        GridPane gridPane2 = new GridPane();

        gridPane2.setPadding(new Insets(8,8,8,8));
        gridPane2.setVgap(8);
        gridPane2.setHgap(8);

        GridPane.setConstraints(addFriendButton,1,0);
        GridPane.setConstraints(addToGroupButton,1,1);
        GridPane.setConstraints(addToClosedGroupButton,1,2);
        GridPane.setConstraints(findMutualFriendButton, 1,3);
        GridPane.setConstraints(printGroupButton,2,0);
        GridPane.setConstraints(printFriendsButton,2,1);
        GridPane.setConstraints(printClosedGroupButton,2,2);
        GridPane.setConstraints(label,0,0);
        GridPane.setConstraints(comboBox1,0,1);
        GridPane.setConstraints(comboBox2,0,2);
        GridPane.setConstraints(backToMainPageButton,0,5);

        gridPane2.getChildren().addAll(printClosedGroupButton, printGroupButton, label,comboBox1, comboBox2, addFriendButton,
                findMutualFriendButton, printFriendsButton, addToClosedGroupButton, addToGroupButton, backToMainPageButton);

        /**
         * User Edit Page Scene
         */
        editUserScene = new Scene(gridPane2, 400, 180);
    }

    private void saveFileAction() {
        try {

            FileChooser saveFileChooser = new FileChooser();
            saveFileChooser.setTitle("Save");
            File saveChosenFile = saveFileChooser.showSaveDialog(window);

            FileOutputStream fileOutputStream     = new FileOutputStream(saveChosenFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(pamakBookUsers);
            objectOutputStream.close();
            fileOutputStream.close();

            console.setTextArea("Pamak Book has been saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFileAction() {
        try {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Load");
            File chosenFile = fileChooser.showOpenDialog(window);

            if(chosenFile != null) {
                FileInputStream fileInputStream     = new FileInputStream(chosenFile);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                pamakBookUsers = (ArrayList<User>) objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();

                String text = "File loaded\n";

                //Prints the loaded users in the console
                for(User u : pamakBookUsers)
                    text += u.getName() + " " + u.getEmail() + "\n";
                console.setTextArea(text);

                //Clears the combo boxes and load them with the new values
                comboBox1.getItems().clear();
                comboBox2.getItems().clear();
                for(User u: pamakBookUsers) {
                    comboBox1.getItems().add(u.getName());
                    comboBox2.getItems().add(u.getName());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createUserAction() {
        GUICreateUser.display(pamakBookUsers, console);

        comboBox1.getItems().clear();
        comboBox2.getItems().clear();

        for(User u: pamakBookUsers){
            comboBox1.getItems().add(u.getName());
            comboBox2.getItems().add(u.getName());
        }
    }

    private void logInAction() {
        User logInUser = new User();

        String name  = userNameTextField.getText();
        String email = emailTextField.getText();

        for(User u: pamakBookUsers)
            if(u.getName().equals(name) && u.getEmail().equals(email)) {
                logInUser = u;
                flag = true;
            }

        if(flag) {
            GUIAlertBox.display("User " + logInUser.getName() + " found");
            GUIUserPage.display(logInUser);
        }
        else
            GUIAlertBox.display("User did not found");
    }

    private void addFriendAction() {
        User tempUser = new User();

        for(User u: pamakBookUsers)
            if(u.getName().equals(comboBox1.getValue())) {
                tempUser = u;
                break;
            }

        for(User u: pamakBookUsers)
            if(u.getName().equals(comboBox2.getValue())) {
                tempUser.addFriend(u, console);
                break;
            }
    }

    private void findMutualAction() {
        User tempUser = new User();
        for(User u: pamakBookUsers)
            if(u.getName().equals(comboBox1.getValue())) {
                tempUser = u;
                break;
            }

        for(User u: pamakBookUsers)
            if(u.getName().equals(comboBox2.getValue())) {
                tempUser.mutualFriends(u, console);
                break;
            }
    }

    private void printFriendAction() {
        User tempUser = new User();
        for(User u: pamakBookUsers) {
            if(u.getName().equals(comboBox1.getValue())) {
                tempUser = u;
                break;
            }
        }
        tempUser.printFriends(console);
    }

    private void addToGroupAction() {
        for(User u: pamakBookUsers)
            if(u.getName().equals(comboBox1.getValue())) {
                Initializer.getGroup1().addToGroup(u, console);
                break;
            }
    }

    private void addToClosedGroupAction() {
        for(User u: pamakBookUsers)
            if(u.getName().equals(comboBox1.getValue())) {
                Initializer.getGroup2().addToGroup(u, console);
                break;
            }
    }
}
