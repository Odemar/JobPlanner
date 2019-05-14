package controllers;

import Classes.Job;
import Classes.JobList;
import Classes.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    public static int tableSelect = 0;
    public static JobList jobList,requestList;
    @FXML
    private JFXButton userInfo;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Label uname;
    @FXML
    private Label name;
    @FXML
    private JFXButton logOut,btn_cancel,btn_view_staff;
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

        if(drawer.isShown()){
            drawer.close();
            drawer.setMaxSize(0, 559);
        }
        else {
            drawer.setMaxSize(186, 559);
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




        // refresh the table

        ObservableList<Job> jobListxml;
        tbl_eventname.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        tbl_location.setCellValueFactory(new PropertyValueFactory<>("location"));
        tbl_time.setCellValueFactory(new PropertyValueFactory<>("start"));
        tbl_staff.setCellValueFactory(new PropertyValueFactory<>("staffString"));
        tbl_date.setCellValueFactory(new PropertyValueFactory<>("dateStringTable"));
        if(tableSelect ==0){
            jobList = new JobList("src/main/resources/txtfiles/jobList.txt");
            jobListxml = jobList.getJobsClient(Main.loginData);
            clientView.setItems(jobListxml);}

        else{
            requestList = new JobList("src/main/resources/txtfiles/requestList.txt");
            jobListxml = requestList.getJobsClient(Main.loginData);
            clientView.setItems(jobListxml);
        }
        clientView.refresh();

    }
    @FXML
    private void showPlannedJobs() throws IOException{
        btn_view_staff.setVisible(true);
        btn_cancel.setVisible(true);
        clientView.setVisible(true);
        tableSelect=0;
        refreshJobs();
    }
    @FXML
    private void showReqJobs() throws IOException{
        btn_view_staff.setVisible(true);
        btn_cancel.setVisible(false);
        clientView.setVisible(true);
        tableSelect=1;
        refreshJobs();
    }

    @FXML
    private void requestJob(){
        clientView.setVisible(false);
        btn_cancel.setVisible((false));
        btn_view_staff.setVisible(false);


    }
    @FXML
    private void onActionEventButtonHandler(ActionEvent event) throws IOException{
        if(event.getSource()==btn_view_staff);

        //TODO
        else if(event.getSource() == btn_cancel){
            Job job = clientView.getSelectionModel().getSelectedItem();
            if(tableSelect==0&& clientView.getSelectionModel().getSelectedItem() != null){
                jobList.removeJob(job);
            }
            else{

            }
            refreshJobs();
        }

    }
}
