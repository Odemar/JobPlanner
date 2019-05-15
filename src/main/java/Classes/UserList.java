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

public class UserList {

    // list of users read from a certain file
    public ArrayList<User> userList;
    // name of the file to read users from
    private String filename;

    public UserList(String filename) throws FileNotFoundException {
        this.filename = filename;
        readFile();
    }

    // Method of reading in all the users from a text file into an array
    public void readFile() throws FileNotFoundException {

        userList = new ArrayList<>();
        File file = new File(filename);
        Scanner input = new Scanner(file);

        while (input.hasNext()) {

            // read first 4 strings of line
            String type = input.next();
            String username = input.next();
            String password = input.next();
            String name = input.next();

            // make a user with the read details
            User user = new User(Integer.parseInt(type), username, password, name);

            // add the user to the list
            userList.add(user);
        }

        // close the scanner
        input.close();

    }

    // Method of updating the output file with the current arrayList. Invoked after a user is added or removed.

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

    // Add or remove methods
    public void addUser(User user) throws IOException {
        userList.add(user);
        this.updateFile();
    }

    public void removeUser(User user) throws IOException {
        User deleteUser = user;
        String username = user.getUsername();
        //for some reason the array doesn't find the given user so instead i have to use this
        // temporary solution
        for (User userl : userList) {
            if (userl.getUsername().equals(username)) {
                deleteUser = userl;
            }

        }
        userList.remove(deleteUser);

        this.updateFile();

        printList();
    }

    /**
     * Searches the user based on the input username. Then compares the input password to the one from the user. If
     * the passwords match, the method returns 'true'.
     * Used by 'LoginController'.
     *
     * @param username The input username at the start of the application.
     * @param password The input password at the start of the application.
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

    public boolean isInList(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username))
                return true;
        }
        return false;

    }

    // debug method
    public void printList() {
        for (User user : userList) {
           // System.out.println(user.toString());
        }
    }

    public int getType(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user.getType();
            }
        }
        return -1;
    }

    public User getUser(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }

        }
        return null;

    }

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
