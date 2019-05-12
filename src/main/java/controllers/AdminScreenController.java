package controllers;


import Classes.Job;
import Classes.User;
import Classes.UserList;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
import java.time.LocalDate;
import java.util.ArrayList;


public class AdminScreenController {
    //User tab fields
    private String typeString;
    private int typeInt;
    private ObservableList<String> typeBoxList = FXCollections.observableArrayList("Admin","Client","Staff");
    public  UserList list;
    @FXML
    private Label label;
    @FXML
    private TextField uname,fname,typeBox;
    @FXML
    private PasswordField pw;
    @FXML
    private TableView<User> userView;

    @FXML
    private TableColumn<User, String>  tbl_usertype,tbl_username,tbl_fullname;

    @FXML
    private Button btn_new,btn_edit,btn_del,btn_create,btn_cancel,btn_reload;
    //Job tab fields

    @FXML
    private DatePicker dateSelect;

    @FXML
    private TableView<Job> jobView;
    @FXML
    private TableColumn<Job,String> tbl_client,tbl_staff,tbl_start,tbl_event,tbl_loc;

    @FXML
    private Button btn_job;


    /**
     *
     * This method handles all the button events for the user tab
     * @throws IOException
     */
    @FXML
    private void handleButtonActionUser(ActionEvent event) throws IOException {
        Parent popup;
        Stage stage = new Stage();
        list = new UserList();

        // refresh the table
       if(event.getSource()== btn_reload){


            try {
                list.readFile("UserList.txt");

            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
           userView.getItems().clear();


           ObservableList<User> userListxml =  FXCollections.observableArrayList(list.userList);


            tbl_usertype.setCellValueFactory(new PropertyValueFactory<User,String>("type"));
            tbl_username.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
            tbl_fullname.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
           userView.setItems(userListxml);







        }
        // popup window to add a new user
     else if(event.getSource()== btn_new) {

           popup = FXMLLoader.load(getClass().getResource("/fxml/addUser.fxml"));

          stage.setScene(new Scene(popup));
          stage.initModality(Modality.APPLICATION_MODAL);
          stage.initOwner(btn_new.getScene().getWindow());
          stage.showAndWait();

      }

        // get data from fields and add them into the arraylist and file
        else if(event.getSource()== btn_create) {


           String username = uname.getText();
           String password = pw.getText();
           String fullName = fname.getText();
           typeString = typeBox.getText();

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

               stage.close();

           }
       }
        //close the add user menu
      else if(event.getSource()== btn_cancel){
          stage = (Stage) btn_create.getScene().getWindow();

          stage.close();

      }

          // deleting selected list item from the userList
       else if(event.getSource()== btn_del){



           User userSelect = userView.getSelectionModel().getSelectedItem();
           userView.getItems().remove(userSelect);
          list.removeUser(userSelect);



}


    }

    public void calendarInit(){
        dateSelect.setShowWeekNumbers(true);

    }

    /**
     * Handles all the button events from the Calendar tab
     */

    public void handleButtonActionCalendar(ActionEvent event) throws IOException{
        if(event.getSource()==btn_job){
            ObservableList<User> userListxml =  FXCollections.observableArrayList(list.userList);
          // get selected date and find jobs using getJobsDate
             dateSelect.getValue();

    }
}}

