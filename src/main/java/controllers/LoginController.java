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
    private JFXButton loginButton;
    @FXML
    private Label loginf;

    private static Connection c = null;
    private static Statement statement = null;
    private Stage loginWindow = new Stage();

    // id of the user that will be transferred to the Main class
    private int loginID;

    public LoginController() {
    }

    /**
     * Method to display a login window above all other windows.
     */
    public int display() {
        try {
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

            // style of the window: UNDECORATED = no bar at the top to minimize, maximize or close it
            loginWindow.initStyle(StageStyle.UNDECORATED);

            // makes the window be on top of all other windows
            loginWindow.setAlwaysOnTop(true);

            // waits for a user input
            loginWindow.showAndWait();

            return loginID;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @FXML
    /**
     * Method to handle the button to login.
     * In this method, the inputted credentials get checked with the details found in the database. If there's a match,
     * the user can log in
     */
    private void handleLoginButton() {

        // connects to the database
        connectDB();

        try {
            statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT pw FROM LOGIN WHERE uname='" + uname.getText() + "';");

            String testPW = null;
            if (rs.next()) {
                testPW = rs.getString("pw");
                System.out.println(testPW);
            }

            if (testPW == null || !testPW.equals(pw.getText())) {
                System.out.println("Password does not match." +
                        "\nTestPW: " + testPW);

                // displays text on screen that the credentials are wrong
                loginf.setText("Wrong Password! Try again.");
            } else {
                // debug stuff
                System.out.println("You're logged in!");

                // removes the text on screen about the wrong credentials (if visible anyways)
                loginf.setText("");

                // get the ID of the user
                statement = c.createStatement();
                rs = statement.executeQuery("SELECT id FROM LOGIN WHERE uname='" + uname.getText() + "';");
                if (rs.next()) {
                    loginID = rs.getInt("id");
                    System.out.println("LoginID: " + loginID);
                }

                // gets the current open window and closes it
                Stage stage = (Stage) loginf.getScene().getWindow();
                stage.close();
            }

            System.out.println("Username: " + uname.getText() + "\nPassword: " + pw.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void connectDB() {

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
}
