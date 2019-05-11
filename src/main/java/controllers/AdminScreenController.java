package controllers;


import Classes.User;
import Classes.UserList;
import javafx.scene.control.Button;

import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.layout.StackPane;

import java.io.FileNotFoundException;


public class AdminScreenController {
    public UserList list;

    @FXML ListView listView;

    @FXML
    private Button btn_new,btn_edit,btn_del,btn_rel;


    @FXML
    private void handleButtonActionUser(ActionEvent event){

        if(event.getSource()== btn_rel){
            reloadList();
            System.out.println("test");
    }
        else if(event.getSource()== btn_edit){

            System.out.println("test1");
        }
    }

    private void reloadList(){
        list = new UserList();
        listView.getItems().removeAll();
        try {
            list.readFile("UserList.txt");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        //debug
        list.printUserList();
        for(User user: list.userList) {
            System.out.println(user.getUserString());
            listView.getItems().add(user.getUserString());

        }
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.refresh();

    }
}
