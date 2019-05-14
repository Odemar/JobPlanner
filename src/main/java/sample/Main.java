package sample;

import Classes.User;
import controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;



public class Main extends Application {

public static User loginData;

    @Override
    public void start(Stage primaryStage) throws Exception {


        // open a login window and receive the userType
        loginData = new LoginController().display();
        System.out.print(loginData);
        int type = loginData.getType();
        //Parent login = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        primaryStage.setTitle("Job Planner");




        //Parent homePageStaff = FXMLLoader.load(getClass().getResource("/fxml/homePageStaff.fxml"));
        //Parent homePageClient = FXMLLoader.load(getClass().getResource("/fxml/homePageClient.fxml"));
        Parent homePageAdmin = FXMLLoader.load(getClass().getResource("/fxml/homePageAdmin.fxml"));

        switch (type) {
            case 0:
                primaryStage.setScene(new Scene(homePageAdmin));
                break;


            default:
                primaryStage.setScene(new Scene(homePageAdmin));

                break;
        }

        //primaryStage.setScene(new Scene(login, 822, 517));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}

