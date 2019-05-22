package sample;

import Classes.User;
import controllers.AdminScreenController;
import controllers.ClientScreenController;
import controllers.LoginController;
import controllers.StaffScreenController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    /**
     * The user that has logged into the application.
     */
    public static User loginData;

    int exitcode = 1;

    /**
     * Opens a Log In window to retrieve the correct user data.
     * Then, according to the data, the home page is opened in another window.
     *
     * @param primaryStage main window
     * @throws Exception when the scene fxml files can't be found
     */
    @Override
    public void start(Stage primaryStage) throws IOException {

        while(exitcode!=0) {
            // open a login window and receive the userType
            loginData = new LoginController().display();
            int type = loginData.getType();

            primaryStage.setTitle("Job Planner");

            switch (type) {
                case 0:
                    exitcode = new AdminScreenController().display();
                    break;

                case 1:
                    exitcode = new ClientScreenController().display();
                    break;

                case 2:
                    exitcode = new StaffScreenController().display();
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * This method launches the application.
     * @param args
     */
    public static void main(String[] args) {

        launch(args);
    }

    /**
     * Returns the logged in User.
     * @return logged in user
     */
    public User getUserData(){
        return loginData;
    }


}

