package Classes;

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


    // Method of reading in all the users from a text file into an array
    public void readFile(String filename) throws FileNotFoundException {

        userList = new ArrayList<User>();
        File file = new File(filename);
        Scanner input = new Scanner(file);
        while (input.hasNext()) {
            // read first 4 strings of line

            String type  = input.next();
            String username = input.next();
            String password = input.next();
            String name = input.next() ;


                User admin = new User(Integer.parseInt(type),username,password,name);

                userList.add(admin);

            }

        input.close();

    }

    // Method of updating the output file with the current arrayList. Invoked after a user is added or removed.

    private void updateFile(String filename) throws IOException {
        File file = new File(filename);
        FileOutputStream fos = new FileOutputStream(file);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        for(User user: userList) {
            String userString = user.getUserString();
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
        userList.remove(user);
        this.updateFile("UserList.txt");
    }
    // check if password combination is correct
    public boolean isValidPassword(String username,String password) {
        for(User user: userList) {
            if (user.getUsername().equals(username)&& user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;

    }


    public int getType(String username) {
        for(User user: userList) {
            if (user.getUsername().equals(username)){
                return user.getType();
            }
        }
        return -1;
    }

}
