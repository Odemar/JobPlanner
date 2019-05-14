package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.Main;



public class DrawerController {
    @FXML
    private Label lb_name,lb_uname;


    @FXML
    public void initialize(){
        lb_name.setText("Username : "+ Main.loginData.getName());
        lb_uname.setText("Name : " + Main.loginData.getUsername());
    }
}
