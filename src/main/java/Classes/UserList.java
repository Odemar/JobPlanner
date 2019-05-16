package Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This Class holds an ArrayList of all the Users in the database.
 * When an object of this class is made, it will read the Users from a file given to the constructor.
 */
public class UserList {

    /**
     * List of users read from a certain file
     */
    public ArrayList<User> userList;
    /**
     * Name of the file to read users from
     */
    private String filename;

    /**
     * Constructor of UserList.
     *
     * @param filename name of the text database
     * @throws FileNotFoundException when the text database isn't found
     */
    public UserList(String filename) throws FileNotFoundException {
        this.filename = filename;
        readFile();
    }

    // Method of reading in all the users from a text file into an array

    /**
     * Reads all the User data from the text file database given to the constructor.
     * With the read data, Users are created and put into an ArrayList.
     *
     * @throws FileNotFoundException when the text database isn't found.
     */
    public void readFile() throws FileNotFoundException {

        userList = new ArrayList<>();
        File file = new File(filename);
        Scanner input = new Scanner(file);

        while (input.hasNextLine()) {

            // reads next line to a String
            String readLine = input.nextLine();
            // splits the read line into words, using the space character as separator
            String words[] = readLine.split(" ");

            // read first 4 strings of line
            String type = words[0];
            String username = words[1];
            String password = words[2];
            String name = words[3];
            String mail = words[4];
            String cell = words[5];

            // make a user with the read details
            User user = new User(Integer.parseInt(type), username, password, name, mail, Integer.parseInt(cell));

            // add the user to the list
            userList.add(user);
        }

        // close the scanner
        input.close();

    }

    /**
     * Method for updating the output file with the current arrayList.
     * Invoked after a user is added or removed.
     *
     * @throws IOException when the text database isn't found
     */
    private void updateFile() throws IOException {
        File file = new File(filename);
        FileOutputStream fos = new FileOutputStream(file);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        for (User user : userList) {
            String userString = user.toString();
            bw.write(userString);
            bw.newLine();
        }
        bw.close();
    }

    /**
     * Adding a User to the UserList.
     *
     * @param user new User
     * @throws IOException when the text database isn't found
     */
    public void addUser(User user) throws IOException {
        userList.add(user);
        this.updateFile();
    }

    /**
     * Removing a User from the UserList.
     *
     * @param user User to be removed
     * @throws IOException when the text database isn't found
     */
    public void removeUser(User user) throws IOException {
        User deleteUser = user;
        String username = user.getUsername();
        // for some reason the array doesn't find the given user so instead i have to use this
        // temporary solution
        for (User userl : userList) {
            if (userl.getUsername().equals(username)) {
                deleteUser = userl;
            }

        }
        userList.remove(deleteUser);

        this.updateFile();

        //printList();  // debug method
    }

    /**
     * Searches the User based on the input username. Then compares the input password to the one from the User. If
     * the passwords match, the method returns 'true'.
     *
     * @param username The input username at the start of the application
     * @param password The input password at the start of the application
     * @return true if the passwords match
     */
    public boolean isValidPassword(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;

    }

    /**
     * Check if a User exists based on the unique usernames.
     *
     * @param username username to be checked
     * @return true if the User exists
     */
    public boolean isInList(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username))
                return true;
        }
        return false;
    }

    // debug method
    /* public void printList() {
        for (User user : userList) {
            // System.out.println(user.toString());
        }
    }
    */

    /**
     * Returns the type of the requested User.
     *
     * @param username username of the User to be checked
     * @return type of the User
     */
    public int getType(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user.getType();
            }
        }

        // returns -1 if the User isn't found in the list
        return -1;
    }

    /**
     * Returns all the User details from the User with unique username.
     *
     * @param username username of the User to be given
     * @return user where the username matched
     */
    public User getUser(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Returns an ObservableList of all Users with type '1' (Client).
     *
     * @return ObservableList of clients
     */
    public ObservableList<String> getAllClients() {
        ArrayList<String> clientArrayList = new ArrayList<>();
        for (User user : userList) {
            if (user.getType() == 1) {//if user is a client
                clientArrayList.add(user.getUsername());
            }
        }
        ObservableList<String> clientObservableList = FXCollections.observableArrayList(clientArrayList);
        return clientObservableList;
    }

    /**
     * Returns an ObservableList of all Users with type '2' (Staff) which did not yet apply for the Job.
     *
     * @param staffName ArrayList of staff members who have already applied for the Job
     * @return ObservableList of staff members who have not applied yet
     */
    public ObservableList<String> getAvailableStaff(ArrayList<String> staffName) {
        ArrayList<String> staffArrayList = new ArrayList<>();
        for (User user : userList) {
            if (user.getType() == 2 && !staffName.contains(user.getUsername())) {//if user is a staff and not already in the job
                staffArrayList.add(user.getUsername());
            }
        }
        ObservableList<String> staffObservableList = FXCollections.observableArrayList(staffArrayList);
        return staffObservableList;
    }
}
