package controllers;

import Classes.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class ClientScreenController {

    @FXML
    private JFXButton userInfo;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Label uname;
    @FXML
    private Label name;
    @FXML
    private JFXButton logOut;

    // currently logged in user
    private User user;

    // stage of the window
    private Stage primaryStage = new Stage();

    public int display(User loggedIn) throws IOException {
        user = loggedIn;

        Parent homePageClient = FXMLLoader.load(getClass().getResource("/fxml/homePageClient.fxml"));

        primaryStage.setScene(new Scene(homePageClient));
        primaryStage.setTitle("Job Planner");

        // application closes when this window closes
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });

        // wait for the user to log out
        primaryStage.showAndWait();

        return 1;
    }

    @FXML
    private void showUserInfo() throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/fxml/drawer.fxml"));
        drawer.setSidePane(anchorPane);
        drawer.setOverLayVisible(false);

        //uname = FXMLLoader.load(getClass().getResource("/fxml/drawer.fxml"));
        //name = FXMLLoader.load(getClass().getResource("/fxml/drawer.fxml"));

        //uname.setText("Username: ");//+user.getUsername());
        //name.setText("Name: ");//+user.getName());

        if(drawer.isShown()){
            drawer.close();
        }
        else {
            drawer.open();
        }
    }

    @FXML
    private void logOut(){
        // gets the current open window and closes it
        Stage stage = (Stage) logOut.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void showJobs(){

    }

    @FXML
    private void requestJob(){

    }

    @FXML
    private void showReqJobs(){

    }

    @FXML
    private void refreshJobs(){

    }

}