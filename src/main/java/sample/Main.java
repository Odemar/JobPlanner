package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent login = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        primaryStage.setTitle("Job Planner");
        primaryStage.setScene(new Scene(login, 822, 517));;
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
