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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Main;

import java.io.IOException;


public class StaffScreenController {
    public static JobList jobList;
    @FXML
    private JFXButton userInfo;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private TableView<Job> staffJobView;

    @FXML
    private JFXButton logOut;
    @FXML
    private JFXButton buttonMyJobs;
    @FXML
    private TableColumn<Job, String> tbl_client, tbl_eventname, tbl_location, tbl_time, tbl_date;

    @FXML
    public void initialize(){
    }

    // currently logged in user
    private User user;

    // stage of the window
    private Stage primaryStage = new Stage();

    /**
     * Method that displays the window with the rights of the staff.
     * @param loggedIn
     * @return
     * @throws IOException
     */
    public int display(User loggedIn) throws IOException {
        user = loggedIn;

        Parent homePageStaff = FXMLLoader.load(getClass().getResource("/fxml/homePageStaff.fxml"));

        primaryStage.setScene(new Scene(homePageStaff));
        primaryStage.setTitle("Job Planner");
        primaryStage.getIcons().add(new Image("/images/logo.png"));


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
    public void refreshJobs() throws IOException{
        jobList = new JobList("jobList.txt");
        // refresh the table

        ObservableList<Job> jobListxml;
        tbl_client.setCellValueFactory(new PropertyValueFactory<>("client"));
        tbl_eventname.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        tbl_location.setCellValueFactory(new PropertyValueFactory<>("location"));
        tbl_time.setCellValueFactory(new PropertyValueFactory<>("start"));
        tbl_date.setCellValueFactory(new PropertyValueFactory<>("dateStringTable"));


        jobListxml = jobList.getStaffJobs(Main.loginData);
        staffJobView.setItems(jobListxml);
        staffJobView.refresh();}

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


}
