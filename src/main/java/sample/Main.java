package sample;

import Classes.User;
import controllers.AdminScreenController;
import controllers.ClientScreenController;
import controllers.LoginController;
import controllers.StaffScreenController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * The user that has logged into the application.
     */
    public static User loginData;

    int exitcode = 1;

    /**
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        while(exitcode!=0) {
            // open a login window and receive the userType
            loginData = new LoginController().display();
            System.out.print(loginData);
            int type = loginData.getType();
            //Parent login = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
            primaryStage.setTitle("Job Planner");


            //Parent homePageClient = FXMLLoader.load(getClass().getResource("/fxml/homePageClient.fxml"));

            switch (type) {
                case 0:
                    exitcode = new AdminScreenController().display(loginData);
                    //primaryStage.setScene(new Scene(login, 822, 517));
                    //primaryStage.show();
                    break;

                case 1:
                    exitcode = new ClientScreenController().display();
                    break;

                case 2:
                    exitcode = new StaffScreenController().display(loginData);
                    //if (exitcode == 0)
                    //    loginData = new LoginController().display();
                    break;

                default:
                    //primaryStage.setScene(new Scene(homePageAdmin));

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

    public User getUserData(){
        return loginData;
    }


}

