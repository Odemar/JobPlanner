package controllers;

import Classes.Job;
import Classes.JobList;
import Classes.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Main;

import java.io.IOException;

public class ClientScreenController {
    public static JobList jobList;
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
    @FXML
    private TableView<Job> clientView;
    @FXML
    private TableColumn<Job, String> tbl_eventname, tbl_location, tbl_time, tbl_date, tbl_staff;

    // currently logged in user
    private User user;

    // stage of the window
    private Stage primaryStage = new Stage();

    public int display() throws IOException {


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

        if (drawer.isShown()) {
            drawer.close();
        } else {
            drawer.open();
        }
    }

    @FXML
    private void logOut() {
        // gets the current open window and closes it
        Stage stage = (Stage) logOut.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void refreshJobs() throws IOException {

        jobList = new JobList("jobList.txt");


        // refresh the table

        ObservableList<Job> jobListxml = jobList.getJobsClient(Main.loginData);
        tbl_eventname.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        tbl_location.setCellValueFactory(new PropertyValueFactory<>("location"));
        tbl_time.setCellValueFactory(new PropertyValueFactory<>("start"));
        tbl_staff.setCellValueFactory(new PropertyValueFactory<>("staffString"));
        tbl_date.setCellValueFactory(new PropertyValueFactory<>("dateStringTable"));
        clientView.setItems(jobListxml);
        clientView.refresh();

    }
    @FXML
    private void showPlannedJobs(){

    }
    @FXML
    private void showReqJobs(){

    }

    @FXML
    private void requestJob(){

    }
}
