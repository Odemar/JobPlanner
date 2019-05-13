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
    public ArrayList<User> userList;

    public UserList(){
        userList = new ArrayList<>();
    }

    // Method of reading in all the users from a text file into an array
    public void readFile(String filename) throws FileNotFoundException {


        File file = new File(filename);
        Scanner input = new Scanner(file);
        while (input.hasNext()) {
            // read first 4 strings of line

            String type = input.next();
            String username = input.next();
            String password = input.next();
            String name = input.next();


            User user = new User(Integer.parseInt(type), username, password, name);

            userList.add(user);

        }

        input.close();

    }

    // Method of updating the output file with the current arrayList. Invoked after a user is added or removed.

    private void updateFile(String filename) throws IOException {
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
        this.updateFile("UserList.txt");
    }

    public void removeUser(User user) throws IOException {
        printList();
        //Doesnt want to remove user for some reason??
        if(userList.remove(user)){
            System.out.println("Deletion succes");
        }
        this.updateFile("UserList.txt");

        printList();
    }

    // check if password combination is correct
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
            System.out.println(user.toString());
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

    public ObservableList<String> getAllClients(){
        ArrayList<String> clientArrayList = new ArrayList<>();
        for(User user: userList){
            if(user.getType()==1){//if user is a client
                clientArrayList.add(user.getUsername());
            }
        }
        ObservableList<String> clientObservableList = FXCollections.observableArrayList(clientArrayList);
        return clientObservableList;
    }
}
