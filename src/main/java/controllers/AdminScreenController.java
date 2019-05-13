package controllers;


import javafx.scene.control.Button;

import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;



public class AdminScreenController {
    @FXML
    private Pane pane_user,pane_calendar;
    @FXML
    private Button btn_user,btn_calendar;

    @FXML
    private void handleButtonAction(ActionEvent event){
        if(event.getSource()== btn_user){
            pane_user.toFront();
            System.out.println("test");
    }
        else if(event.getSource()== btn_calendar){
            pane_calendar.toFront();
            System.out.println("test1");
        }
    }


}
