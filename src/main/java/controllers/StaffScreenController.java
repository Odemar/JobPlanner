package controllers;

import Classes.Job;
import Classes.JobList;
import Classes.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Main;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;


public class StaffScreenController {
    private static Date dateSelectValue =new Date(1,0,1); //default
    private static int tableSelect =0; // 0 for my jobs and 1 when looking for jobs
    public static JobList jobList;
    @FXML
    private JFXButton userInfo;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private TableView<Job> staffJobView;
    @FXML
    private DatePicker dateSelect;

    @FXML
    private JFXButton logOut;
    @FXML
    private JFXButton buttonMyJobs;
    @FXML
    private TableColumn<Job, String> tbl_client, tbl_eventname, tbl_location, tbl_time, tbl_date,tbl_staff;

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
        if(tableSelect==0){
        jobList = new JobList("jobList.txt");}
        // refresh the table

        ObservableList<Job> jobListxml;
        tbl_client.setCellValueFactory(new PropertyValueFactory<>("client"));
        tbl_eventname.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        tbl_location.setCellValueFactory(new PropertyValueFactory<>("location"));
        tbl_time.setCellValueFactory(new PropertyValueFactory<>("start"));
        tbl_time.setCellValueFactory(new PropertyValueFactory<>("staffString"));
        tbl_date.setCellValueFactory(new PropertyValueFactory<>("dateStringTable"));


        jobListxml = jobList.getStaffJobs(Main.loginData);
        staffJobView.setItems(jobListxml);
        staffJobView.refresh();}

    @FXML
    private void showUserInfo() throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/fxml/drawer.fxml"));
        drawer.setSidePane(anchorPane);
        drawer.setOverLayVisible(false);

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
    private void showOpenJobs(){

    }
    @FXML
    private void calendarValue(){
        LocalDate localDate = (dateSelect.getValue());

        dateSelectValue = new Date(localDate.getYear()-1900,localDate.getMonthValue(),localDate.getDayOfMonth());
    }

}
