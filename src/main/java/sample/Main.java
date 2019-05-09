package sample;

import controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sun.plugin2.util.ParameterNames;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main extends Application {

    private static Connection c;
    private static Statement statement;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // open a login window and receive the userID
        int userID = new LoginController().display();

        //Parent login = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        primaryStage.setTitle("Job Planner");

        // get the user rights, and sets the correct scene
        connectDB();
            int userType;
            try {
                statement = c.createStatement();
                ResultSet rs = statement.executeQuery("SELECT rights FROM LOGIN WHERE id='" + userID + "';");
                if (rs.next()) {
                    userType = rs.getInt("rights");
                } else
                userType = 0;
        } catch (Exception e) {
            e.printStackTrace();
            userType = 0;
        }

        //Parent homePageStaff = FXMLLoader.load(getClass().getResource("/fxml/homePageStaff.fxml"));
       // Parent homePageClient = FXMLLoader.load(getClass().getResource("/fxml/homePageClient.fxml"));
        Parent homePageAdmin = FXMLLoader.load(getClass().getResource("/fxml/homePageAdmin.fxml"));
        switch (userType) {
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

    private static void connectDB() {

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
}

