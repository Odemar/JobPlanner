package controllers;

import Classes.Job;
import Classes.JobList;
import Classes.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Main;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class ClientScreenController {
    public static int tableSelect = 0;
    public static JobList jobList,requestList;
    private final ObservableList<String> times = FXCollections.observableArrayList("00:00","01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00","10:00","11:00","12:00","13:00","14:00",
            "15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23300");
    @FXML
    private DatePicker dateSelect;
    @FXML
    private JFXTextField txt_eventname,txt_location,txt_mxstaff;
    @FXML
    private ChoiceBox<String> cb_start;
    @FXML
    private AnchorPane anchr_addJob;
    @FXML
    private JFXButton userInfo;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Label lb_result;
    @FXML
    private Label name;
    @FXML
    private JFXButton logOut,btn_cancel,btn_view_staff,btn_sendReq;
    @FXML
    private TableView<Job> clientView;
    @FXML
    private TableColumn<Job, String> tbl_eventname, tbl_location, tbl_time, tbl_date, tbl_staff;

    @FXML
    private void initialize() throws IOException{
        lb_result.setVisible(false);
        cb_start.setItems(times);
        cb_start.setValue("00:07");
        showPlannedJobs();

    }



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
            jobList = new JobList("txtfiles/jobList.txt");
            jobListxml = jobList.getJobsClient(Main.loginData);
            clientView.setItems(jobListxml);}

        else{
            requestList = new JobList("txtfiles/requestList.txt");
            jobListxml = requestList.getJobsClient(Main.loginData);
            clientView.setItems(jobListxml);
        }
        clientView.refresh();

    }
    @FXML
    private void showPlannedJobs() throws IOException{
        anchr_addJob.setVisible(false);
        btn_view_staff.setVisible(true);
        btn_cancel.setVisible(true);
        clientView.setVisible(true);
        tableSelect=0;
        refreshJobs();
    }
    @FXML
    private void showReqJobs() throws IOException{
        anchr_addJob.setVisible(false);
        btn_view_staff.setVisible(false);
        btn_cancel.setVisible(true);
        clientView.setVisible(true);
        tableSelect=1;
        refreshJobs();
    }

    @FXML
    private void requestJob(){
        lb_result.setVisible(false);
        anchr_addJob.setVisible(true);
        clientView.setVisible(false);
        btn_cancel.setVisible((false));
        btn_view_staff.setVisible(false);


    }
    @FXML
    private void onActionEventButtonHandler(ActionEvent event) throws IOException{
        // gives a staff list with all the staff usernames
        if(event.getSource()==btn_view_staff);

        //TODO

        // deletes selected item from table
        else if(event.getSource() == btn_cancel){
            Job job = clientView.getSelectionModel().getSelectedItem();
            if(tableSelect==0&& clientView.getSelectionModel().getSelectedItem() != null){
                jobList.removeJob(job);
            }
            else if(tableSelect==1&& clientView.getSelectionModel().getSelectedItem() !=null);
            {
                requestList.removeJob(job);
            }
            refreshJobs();
        }
        else if(event.getSource() ==btn_sendReq){
            LocalDate localDate = (dateSelect.getValue());
            Date date = new Date(localDate.getYear()-1900,localDate.getMonthValue(),localDate.getDayOfMonth());
            String client = Main.loginData.getUsername();
            String eventname = txt_eventname.getText();
            String location = txt_location.getText();
            String start = cb_start.getValue();
            int staffInt = Integer.parseInt(txt_mxstaff.getText());
            if (client.contains(" ") || eventname.contains(" ") || location.contains(" ") || start.contains(" ")){
                lb_result.setText("Textfields may not contain spaces.");
            }
            else {
                Job job = new Job(date,client,eventname,location,start,staffInt,new ArrayList<>());
                requestList.addJobToList(job);
                lb_result.setVisible(true);
            }

        }

    }

}

