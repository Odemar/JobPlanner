package controllers;


import Classes.Job;
import Classes.User;
import Classes.UserList;
import Classes.JobList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;



public class AdminScreenController {
    //User tab fields
    private static int popupInt = 0; // Indicator for initialize if its on the main program or a pop up
    private String typeString;
    private int typeInt;
    private final ObservableList<String> typeBoxList = FXCollections.observableArrayList("Admin","Client","Staff");
    public static UserList list;
    @FXML
    private Label label;
    @FXML
    private TextField uname,fname;
    @FXML
    private PasswordField pw;
    @FXML
    private TableView<User> userView;
    @FXML
    private ChoiceBox<String> typeBox;
    @FXML
    private TableColumn<User, String>  tbl_usertype,tbl_username,tbl_fullname;

    @FXML
    private Button btn_new,btn_edit,btn_del,btn_create,btn_cancel,btn_ref;
    //Job tab fields

    @FXML
    private DatePicker dateSelect;

    @FXML
    private TableView<Job> jobView;
    @FXML
    private TableColumn<Job,String> tbl_client,tbl_staff,tbl_start,tbl_event,tbl_loc;

    @FXML
    private Button btn_job;

    @FXML
    private void initialize(){

        System.out.println(popupInt);
        list = new UserList();
        if(popupInt==0){// init main screen
        refresh();
        }
        else{ // pop up init
        typeBox.setValue("Staff");
        typeBox.setItems(typeBoxList);
        }

    }
    /**
     *
     * This method handles all the button events for the user tab
     * @throws IOException
     */
    @FXML
    private void handleButtonActionUser(ActionEvent event) throws IOException {

        Parent popup;
        Stage stage = new Stage();

        // popup window to add a new user
      if(event.getSource()== btn_new) {
          popupInt = 1; // going to open new window
           popup = FXMLLoader.load(getClass().getResource("/fxml/addUser.fxml"));

          stage.setScene(new Scene(popup));
          stage.setTitle("Add new user");
          stage.initModality(Modality.APPLICATION_MODAL);
          stage.initOwner(btn_new.getScene().getWindow());
          stage.showAndWait();

      }

        else if(event.getSource()==btn_ref){
            refresh();
      }
        // get data from fields and add them into the arraylist and file
        else if(event.getSource()== btn_create) {


           String username = uname.getText();
           String password = pw.getText();
           String fullName = fname.getText();
           typeString = typeBox.getValue();

           if (username.equals("") || password.equals("") || fullName.equals("")) {
               label.setText("Please fill all the fields!");
           }
           else if (list.isInList(username)) {
               label.setText("Username already taken!");
           } else
               {

               if (typeString.equals("Admin")) {
                   typeInt = 0;
               } else if (typeString.equals("Client")) {
                   typeInt = 1;
               } else {
                   typeInt = 2;
               }

               User user = new User(typeInt, username, password, fullName);

               list.readFile("UserList.txt");
               list.addUser(user);

               stage = (Stage) btn_create.getScene().getWindow();
                popupInt = 0; //getting back to main prg

               stage.close();

           }
       }
        //close the add user menu
      else if(event.getSource()== btn_cancel){
          stage = (Stage) btn_create.getScene().getWindow();
            popupInt =0;
          stage.close();

      }

          // deleting selected list item from the userList
       else if(event.getSource()== btn_del){

           User userSelect = userView.getSelectionModel().getSelectedItem();
           //nothing selected
           if (userSelect != null){
               System.out.println("Selected user: " + userSelect.toString()); //debug
               list.removeUser(userSelect);
               refresh();
           }




}


    }

    public void calendarInit(){
        dateSelect.setShowWeekNumbers(true);

    }

    public void refresh() {
        list = new UserList();
        // refresh the table
        try {
            list.readFile("UserList.txt");

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        ObservableList<User> userListxml =  FXCollections.observableArrayList(list.userList);

        tbl_usertype.setCellValueFactory(new PropertyValueFactory<>("type"));
        tbl_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        tbl_fullname.setCellValueFactory(new PropertyValueFactory<>("name"));
        userView.setItems(userListxml);

    }

    /**
     * Handles all the button events from the Calendar tab
     */
        // need to implement exception when no date is selected
        // need to implement button/method to add user to selected job
        // need to implement method to add a new job
    public void handleButtonActionCalendar(ActionEvent event) throws IOException{
        if(event.getSource()==btn_job){
            jobView.getItems().clear();
            ObservableList<Job> userListxml;
            Date dateSelectValue = Date.valueOf(dateSelect.getValue());
            JobList jobList = new JobList();
            jobList.readFile("jobList.txt");
            userListxml = jobList.getJobsDate(dateSelectValue);

            tbl_client.setCellValueFactory(new PropertyValueFactory<>("client"));
            tbl_event.setCellValueFactory(new PropertyValueFactory<>("eventName"));
            tbl_loc.setCellValueFactory(new PropertyValueFactory<>("location"));
            tbl_start.setCellValueFactory(new PropertyValueFactory<>("start"));
            tbl_staff.setCellValueFactory(new PropertyValueFactory<>("staff"));
            jobView.setItems(userListxml);
    }
}


}


