package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {

    @FXML
    private JFXTextField uname;
    @FXML
    private JFXPasswordField pw;
    @FXML
    private JFXButton loginButton;
    private static Connection c = null;
    private static Statement statement = null;

    @FXML
    private void handleLoginButton() {
        connectDB();

        try {
            statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT pw FROM LOGIN WHERE uname=\""+uname.getText()+"\";");

            rs.next();
            String testPW = rs.getString("pw");

            if(testPW != pw.getText())
                System.out.println("Password does not match."+
                                    "\nTestPW: "+testPW);

            System.out.println("Username: " + uname.getText() + "\nPassword: " + pw.getText());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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
