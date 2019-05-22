package controllers;

import Classes.Job;
import Classes.User;
import Classes.UserList;
import Classes.JobList;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class AdminScreenController {

    //<editor-fold desc="Variables">
    /**
     * Indicator for the type of screen pop up when adding users/jobs and adding/deleting staff from a job.
     */
    private static int popupInt = 0;
    /**
     * String which represents the type of User, instead of a number.
     * (ex. "Client" instead of '1')
     */
    private String typeString;
    /**
     * Integer which represents the type of User.
     * (ex. '1' when the User is a client)
     */
    private int typeInt;

    /**
     * List for the ChoiceBox from which the admin can choose when creating a new User.
     */
    private final ObservableList<String> typeBoxList = FXCollections.observableArrayList("Admin", "Client", "Staff");
    /**
     * List with all Users.
     */
    public static UserList list;
    /**
     * List with all Jobs.
     */
    public static JobList jobList;


    // Buttons in the left sidebar
    @FXML
    private JFXButton btn_users;
    @FXML
    private JFXButton btn_calendar;
    @FXML
    private JFXButton btn_nwreq;
    @FXML
    private JFXButton logOut;

    // User tab fields
    @FXML
    private Label label, lbl_addjob;
    @FXML
    private TextField uname, fname, email, cellnumber;
    @FXML
    private PasswordField pw;
    @FXML
    private TableView<User> userView;
    @FXML
    private ChoiceBox<String> typeBox;
    @FXML
    private TableColumn<User, String> tbl_usertype, tbl_username, tbl_fullname, tbl_mail, tbl_number;
    @FXML
    private Button btn_create, btn_cancel;
    @FXML
    private JFXButton btn_new, btn_edit, btn_del;
    @FXML
    private AnchorPane anc_users;

    //Calendar tab fields
    private static Date dateSelectValue = new Date(1, 0, 1); //default
    private static Job jobSelect = new Job(dateSelectValue, "", "", "", "", 1, new ArrayList<>());
    @FXML
    private DatePicker dateSelect;
    @FXML
    private TableView<Job> jobView;
    @FXML
    private TableColumn<Job, String> tbl_client, tbl_staff, tbl_start, tbl_event, tbl_loc;
    @FXML
    private Button btn_createjob, btn_canceljob,
            btn_staff_add_pop, btn_cancel_staff,
            btn_cancel_staff2, btn_del_staff_pop;
    @FXML
    private JFXButton btn_newjob, btn_del_job, btn_add_staff_job, btn_del_staff_job;
    @FXML
    private ChoiceBox<String> clientBox, staffBox, staffBox2;
    @FXML
    private TextField tf_event, tf_loc, tf_time, tf_staff;
    @FXML
    private AnchorPane anc_calendar;

    // Request tab fields
    @FXML
    private TableView<Job> requestView;
    @FXML
    private TableColumn<Job, String> tbl_client_req, tbl_staff_req, tbl_start_req, tbl_event_req, tbl_loc_req, tbl_date_req;
    @FXML
    private JFXButton btn_req_acc, btn_req_ref;
    @FXML
    private AnchorPane anc_requests;

    // Right sidebar
    @FXML
    private JFXDrawer drawer;


    // stage of the window
    private Stage primaryStage = new Stage();
    //</editor-fold>

    /**
     * Displays a new window with the home page of an admin User, and waits until {@link AdminScreenController#logOut()} closes it.
     *
     * @return '1' when the window has been closed with the logOut button
     * @throws IOException when the requested files are not found
     */
    public int display() throws IOException {

        Parent homePageAdmin = FXMLLoader.load(getClass().getResource("/fxml/homePageAdmin.fxml"));

        // sets the scene, title and icon of the window
        primaryStage.setScene(new Scene(homePageAdmin));
        primaryStage.setTitle("Job Planner");
        primaryStage.getIcons().add(new Image("/images/logo.png"));
        primaryStage.setResizable(false);

        // application closes when this window closes
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });

        // wait for the user to log out
        primaryStage.showAndWait();

        return 1;
    }

    /**
     * Method that is invoked by the pop-up windows for adding/deleting data to the tables.
     *
     * @throws IOException when the text database isn't found
     */
    @FXML
    private void initialize() throws IOException {

        switch (popupInt) {
            case 0:
                break;

            case 1: // add user
                typeBox.setValue("Staff");
                typeBox.setItems(typeBoxList);
                break;

            case 2: // add job
                list.readFile();
                ObservableList<String> clientBoxItems = list.getAllClients();
                //System.out.println((jobBox.size()));  // debug
                clientBox.setItems(clientBoxItems);
                break;

            case 3: // add staff to staffList
                ObservableList<String> staffBoxItems = list.getAvailableStaff(jobSelect.getStaff());
                staffBox.setItems(staffBoxItems);
                staffBox.getSelectionModel().selectFirst();
                break;

            case 4: // delete staff from staffList
                staffBoxItems = FXCollections.observableArrayList(jobSelect.getStaff());
                staffBox2.setItems(staffBoxItems);
                staffBox2.getSelectionModel().selectFirst();
                break;

            default:
                break;
        }
    }

    /**
     * This method handles all the button events for the user tab.
     *
     * @throws IOException when the text database isn't found
     */
    @FXML
    private void handleButtonActionUser(ActionEvent event) throws IOException {
        list = new UserList("txtfiles/UserList.txt");
        Parent popup;
        Stage stage = new Stage();

        // popup window to add a new user
        if (event.getSource() == btn_new) {
            popupInt = 1; // going to open new window
            popup = FXMLLoader.load(getClass().getResource("/fxml/addUser.fxml"));

            stage.setScene(new Scene(popup));
            stage.setTitle("Add new user");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(btn_new.getScene().getWindow());
            stage.showAndWait();

        }

        // get data from fields and add them into the arraylist and file
        else if (event.getSource() == btn_create) {

            String username = uname.getText();
            String password = pw.getText();
            String fullName = fname.getText();
            String mail = email.getText();
            String cell = cellnumber.getText();
            typeString = typeBox.getValue();

            if (username.equals("") || password.equals("") || fullName.equals("") || mail.equals("") || cell.equals("")) {
                label.setText("Please fill in all the fields!");
            } else if (list.isInList(username)) {
                label.setText("Username already taken!");
            } else if (username.contains(" ") || password.contains(" ") || fullName.contains(" ") || mail.contains(" ") || cell.contains(" ")) {
                label.setText("None of the fields may contain spaces!");
            } else {

                if (typeString.equals("Admin")) {
                    typeInt = 0;
                } else if (typeString.equals("Client")) {
                    typeInt = 1;
                } else {
                    typeInt = 2;
                }

                User user = new User(typeInt, username, password, fullName, mail, Integer.parseInt(cell));

                list.addUser(user);

                stage = (Stage) btn_create.getScene().getWindow();
                popupInt = 0; //getting back to sample prg

                stage.close();

            }

        }
        //get a popup to edit userdata
        else if (event.getSource() == btn_edit) {

            //todo

        }
        //close the add user menu
        else if (event.getSource() == btn_cancel) {
            stage = (Stage) btn_create.getScene().getWindow();
            popupInt = 0;
            stage.close();

        }

        // deleting selected list item from the userList
        else if (event.getSource() == btn_del) {

            User userSelect = userView.getSelectionModel().getSelectedItem();
            //nothing selected
            if (userSelect != null) {
                //System.out.println("Selected user: " + userSelect.toString()); //debug
                list.removeUser(userSelect);
                refresh();
            }

        }


    }

    /**
     * Add a way to show jobs for the current day when tab is loaded.
     */
    public void calendarInit() {
        dateSelect.setShowWeekNumbers(true);

    }

    /**
     * Refreshes the calendar table when the mouse enters the screen, if there is no date selected, the table will be empty (will not initialize)
     *
     * @throws IOException when jobList.txt is not in the project folder
     */
    public void refreshCalendar() throws IOException {
        if (dateSelectValue != null) {
            jobList = new JobList("txtfiles/jobList.txt");

            ObservableList<Job> jobListxml;
            tbl_client.setCellValueFactory(new PropertyValueFactory<>("client"));
            tbl_event.setCellValueFactory(new PropertyValueFactory<>("eventName"));
            tbl_loc.setCellValueFactory(new PropertyValueFactory<>("location"));
            tbl_start.setCellValueFactory(new PropertyValueFactory<>("start"));
            tbl_staff.setCellValueFactory(new PropertyValueFactory<>("staffString"));

            jobListxml = jobList.getJobsDate(dateSelectValue);
            jobView.setItems(jobListxml);
            jobView.refresh();
        }
    }

    public void refresh() throws IOException {
        list = new UserList("txtfiles/UserList.txt");
        // refresh the table


        ObservableList<User> userListxml = FXCollections.observableArrayList(list.userList);

        tbl_usertype.setCellValueFactory(new PropertyValueFactory<>("type"));
        tbl_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        tbl_fullname.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbl_mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        tbl_number.setCellValueFactory(new PropertyValueFactory<>("cell"));
        userView.setItems(userListxml);

    }

    /**
     * Handles all the button events from the Calendar tab
     */
    @FXML
    private void handleButtonActionCalendar(ActionEvent event) throws IOException {
        jobList = new JobList("txtfiles/jobList.txt");
        jobList.readFile();

        Parent popup;
        Stage stage = new Stage();

        if (event.getSource() == btn_newjob) {
            popupInt = 2; // going to open new window
            popup = FXMLLoader.load(getClass().getResource("/fxml/addJob.fxml"));

            stage.setScene(new Scene(popup));
            stage.setTitle("Create a new job");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(btn_newjob.getScene().getWindow());
            stage.showAndWait();

        } else if (event.getSource() == btn_createjob) {

            String client = clientBox.getValue();
            String eventName = tf_event.getText();
            String location = tf_loc.getText();
            String startHour = tf_time.getText();
            int maxStaff = Integer.parseInt(tf_staff.getText());
            ArrayList<String> staffListUsername = new ArrayList<>();
            if (client.contains(" ") || eventName.contains(" ") || location.contains(" ") || startHour.contains(" ")) {
                lbl_addjob.setText("Textfields may not contain spaces.");
            } else {
                Job newJob = new Job(dateSelectValue, client, eventName, location, startHour, maxStaff, staffListUsername);
                jobList.addJobToList(newJob);
                stage = (Stage) btn_createjob.getScene().getWindow();
                popupInt = 0; //set value back to homescreen after closing
                stage.close();
            }

        } else if (event.getSource() == btn_canceljob) {
            stage = (Stage) btn_canceljob.getScene().getWindow();
            popupInt = 0;
            stage.close();

        } else if (event.getSource() == dateSelect) {
            LocalDate localDate = (dateSelect.getValue());

            dateSelectValue = new Date(localDate.getYear() - 1900, localDate.getMonthValue(), localDate.getDayOfMonth());

        } else if (event.getSource() == btn_del_job) {
            jobSelect = jobView.getSelectionModel().getSelectedItem();

            //nothing selected
            if (jobSelect != null) {
                //System.out.println("Selected user: " + jobSelect.toString()); //debug
                jobList.removeJob(jobSelect);
                refreshCalendar();

            }

        }
        // add staff to job
        else if (event.getSource() == btn_add_staff_job) {
            //nothing selected
            if (!jobSelect.getEventName().equals("") && jobSelect.getStaff().size() < jobSelect.getMaxStaff()) {
                popupInt = 3; // going to open new window
                popup = FXMLLoader.load(getClass().getResource("/fxml/staffList.fxml"));

                stage.setScene(new Scene(popup));
                stage.setTitle("Select Staff");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initOwner(btn_add_staff_job.getScene().getWindow());
                stage.showAndWait();


            }
        }

        // add button for the popup window to add staff
        else if (event.getSource() == btn_staff_add_pop) {
            // get the job from joblist with the matching selected event, when there are duplicate eventnames this will take the first out of the list
            jobList.addStaffJob(staffBox.getValue(), jobSelect.getEventName());
            stage = (Stage) btn_staff_add_pop.getScene().getWindow();
            popupInt = 0;
            stage.close();

        } else if (event.getSource() == jobView) {
            if (jobView.getSelectionModel().getSelectedItem() != null) {
                jobSelect = jobView.getSelectionModel().getSelectedItem();
            }

        } else if (event.getSource() == btn_cancel_staff) {
            stage = (Stage) btn_cancel_staff.getScene().getWindow();
            popupInt = 0;
            stage.close();

        } else if (event.getSource() == btn_cancel_staff2) {
            stage = (Stage) btn_cancel_staff2.getScene().getWindow();
            popupInt = 0;
            stage.close();

        } else if (event.getSource() == btn_del_staff_job) {
            //nothing selected
            if (!jobSelect.getEventName().equals("")) {
                popupInt = 4; // going to open new window
                popup = FXMLLoader.load(getClass().getResource("/fxml/staffList2.fxml"));

                stage.setScene(new Scene(popup));
                stage.setTitle("Select Staff");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initOwner(btn_add_staff_job.getScene().getWindow());
                stage.showAndWait();

            }

        } else if (event.getSource() == btn_del_staff_pop) {
            jobList.delStaffJob(staffBox2.getValue(), jobSelect.getEventName());
            stage = (Stage) btn_del_staff_pop.getScene().getWindow();
            popupInt = 0;
            stage.close();
        }

    }

    /**
     * Updates the value of the jobselection on mouseclick, so the selected value from the table can be accessed
     */
    public void jobSelectUpdate() {
        if (jobView.getSelectionModel().getSelectedItem() != null) {
            jobSelect = jobView.getSelectionModel().getSelectedItem();
        }
    }

    /**
     * Refreshes the requested jobs table.
     *
     * @throws IOException when the list of requested jobs isn't found
     */
    public void refreshRequest() throws IOException {
        jobList = new JobList("txtfiles/requestList.txt");
        // refresh the table

        ObservableList<Job> jobListxml;
        tbl_client_req.setCellValueFactory(new PropertyValueFactory<>("client"));
        tbl_event_req.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        tbl_loc_req.setCellValueFactory(new PropertyValueFactory<>("location"));
        tbl_start_req.setCellValueFactory(new PropertyValueFactory<>("start"));
        tbl_staff_req.setCellValueFactory(new PropertyValueFactory<>("staffString"));
        tbl_date_req.setCellValueFactory(new PropertyValueFactory<>("dateStringTable"));


        jobListxml = FXCollections.observableArrayList(jobList.jobList);
        requestView.setItems(jobListxml);
        requestView.refresh();
    }

    /**
     * Acts according to the admins button press. If the admin selects a job ans denies it, the job is deleted from the requested
     * job list. If the admin accepts the job, the job will also be deleted from the requested jobs, and added to the job list.
     *
     * @param event button press
     * @throws IOException when either of the lists can't be found
     */
    @FXML
    private void handleButtonOnActionRequest(ActionEvent event) throws IOException {
        JobList requestJobList = new JobList("txtfiles/requestList.txt");
        jobList = new JobList("txtfiles/jobList.txt");
        jobSelect = requestView.getSelectionModel().getSelectedItem();

        // job is removed either way
        requestJobList.removeJob(jobSelect);

        //nothing selected
        if (jobSelect != null && event.getSource() == btn_req_acc) {
            jobList.addJobToList(jobSelect);
        }
        refreshRequest();
    }

    /**
     * Closes the window and makes the Main class open the login window.
     */
    @FXML
    private void logOut() {
        // gets the current open window and closes it
        Stage stage = (Stage) logOut.getScene().getWindow();
        stage.close();
    }

    /**
     * When the admin clicks on the little profile icon, a side drawer slides in showing their information.
     * The drawer needs to be resized to 0, otherwise the invisible plane at the place of the drawer block user input
     * to buttons underneath.
     *
     * @throws IOException when the page isn't found
     */
    @FXML
    private void showUserInfo() throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/fxml/drawer.fxml"));

        drawer.setSidePane(anchorPane);
        drawer.setOverLayVisible(false);

        if (drawer.isShown()) {
            drawer.close();
            drawer.setMaxSize(0, 559);
        } else {
            drawer.setMaxSize(186, 559);
            drawer.open();
        }
    }

    /**
     * When the Users button is pressed.
     */
    @FXML
    private void showUsers() {
        anc_requests.setVisible(false);
        anc_calendar.setVisible(false);
        anc_users.setVisible(true);
        try {
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * When the Calendar button is pressed.
     */
    @FXML
    private void showCalendar() {
        anc_users.setVisible(false);
        anc_requests.setVisible(false);
        anc_calendar.setVisible(true);
        try {
            calendarInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * When the New Requests button is pressed.
     */
    @FXML
    private void showRequests() {
        anc_users.setVisible(false);
        anc_calendar.setVisible(false);
        anc_requests.setVisible(true);
        try {
            refreshRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


