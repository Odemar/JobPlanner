package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import Classes.UserList;
import Classes.User;
import java.io.FileNotFoundException;
import java.io.IOException;


import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Class that controls the login window at the start of the application.
 */
public class LoginController {

    // fields and labels used in the login page
    @FXML
    private JFXTextField uname;
    @FXML
    private JFXPasswordField pw;
    @FXML
    private Label loginf;

    // user that logs in; this user(data) will be transferred to the Main class for further use
    private static User loginUser;
    // stage which displays the loginpage
    private Stage loginWindow = new Stage();

    /**
     * Constructor of this class.
     */
    public LoginController() {
    }

    /**
     * Method to display a login window above all other windows.
     * There is no other option than to either log in or close this window and thus the application.
     */
    public User display() throws IOException {

        // gets the scene settings
        Parent login = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));

        // blocks the user clicking in other windows
        loginWindow.initModality(Modality.APPLICATION_MODAL);

        // scene that is displayed
        loginWindow.setScene(new Scene(login));

        // title of the window
        loginWindow.setTitle("JobPlanner Login");

        // icon of the window
        loginWindow.getIcons().add(new Image("/images/logo.png"));


        // if the login window gets closed, the whole application closes
        loginWindow.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });

        // waits for a user input
        loginWindow.showAndWait();

        return loginUser;
    }

    @FXML
    /**
     * Method to handle the button to login.
     * In this method, the inputted credentials get checked with the details found in the database. If there's a match,
     * the user can log in.
     */
    private void handleLoginButton() throws IOException {

        // stores the input username in a variable
        String username = uname.getText();
        // stores the input password in a variable
        String password = pw.getText();
        // makes an UserList of all the users it can find in the txt file
        final UserList userList = new UserList("src/main/resources/txtfiles/UserList.txt");

        // if the passwords match, the window closes, else you get asked to try again
        if (userList.isValidPassword(username, password)) {
            // debug stuff
            System.out.println("You're logged in!");
            loginUser = userList.getUser(username);

            // removes the text on screen about the wrong credentials (if visible anyways)
            loginf.setText("");

            // gets the current open window and closes it
            Stage stage = (Stage) loginf.getScene().getWindow();
            stage.close();

        } else {
            // debug stuff
            System.out.println("Password does not match." +
                    "\nTestPW: " + password);

            // displays text on screen that the credentials are wrong
            loginf.setText("Wrong Password! Try again.");
        }

        // debug stuff
        System.out.println("Username: " + uname.getText() + "\nPassword: " + pw.getText());

    }
}

