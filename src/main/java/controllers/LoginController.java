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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import Classes.UserList;
import Classes.User;
import java.io.FileNotFoundException;
import java.io.IOException;



=======
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class LoginController {

    @FXML
    private JFXTextField uname;
    @FXML
    private JFXPasswordField pw;
    @FXML
    private Label loginf;
    private static User loginUser;
    private Stage loginWindow = new Stage();

    // id of the user that will be transferred to the Main class


    public LoginController() {
    }

    /**
     * Method to display a login window above all other windows.
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
     * the user can log in
     */
    private void handleLoginButton() {


        String username = uname.getText();
        String password = pw.getText();
        final UserList userList = new UserList();

        try {
            userList.readFile("UserList.txt");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

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
            System.out.println("Password does not match." +
                    "\nTestPW: " + password);

            // displays text on screen that the credentials are wrong
            loginf.setText("Wrong Password! Try again.");
        }

        System.out.println("Username: " + uname.getText() + "\nPassword: " + pw.getText());

    }

