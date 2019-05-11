package controllers;


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


public class AdminScreenController {
    public final UserList list = new UserList();
    @FXML
    private Label label;
    @FXML
    private TextField uname,fname;
    @FXML
    private PasswordField pw;
    @FXML
    public TableView<User> tableView;

    @FXML
    private TableColumn<User, String>  tbl_usertype,tbl_username,tbl_fullname;

    @FXML
    private Button btn_new,btn_edit,btn_del,btn_create,btn_cancel,btn_reload;


    @FXML
    private void handleButtonActionUser(ActionEvent event) throws IOException {
        Parent popup;
        Stage stage = new Stage();
        // refresh the table
       if(event.getSource()== btn_reload){
            tableView.getItems().clear();

            try {
                list.readFile("UserList.txt");
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
           ObservableList<User> userListxml =  FXCollections.observableArrayList(list.userList);
            tbl_usertype.setCellValueFactory(new PropertyValueFactory<User,String>("type"));
            tbl_username.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
            tbl_fullname.setCellValueFactory(new PropertyValueFactory<User,String>("name"));

           tableView.setItems(userListxml);


                System.out.println("Reloaded list");




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
        else if(event.getSource()== btn_create){
          System.out.println("test3");
          String username = uname.getText();
          String password = pw.getText();
          String fullName = fname.getText();
          if(username.equals("") || password.equals("") || fullName.equals("")){
                label.setText("Please fill all the fields!");
          }

          else {
              User user = new User(1, username, password, fullName);
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
       if(event.getSource()== btn_del){



           User userSelect = tableView.getSelectionModel().getSelectedItem();
           tableView.getItems().remove(userSelect);
          list.removeUser(userSelect);














}
    }
}
