package Classes;

import java.util.ArrayList;
import java.util.Date;

public class Job {
    public Date date;
    public String client,eventName,location,start,staffString;
    public int maxStaff,status; // status=0 for not accepted status = 1 for accepted
    public ArrayList<String> staffListUserName;
    public ArrayList<User> staffList;

    public Job(Date date,String client,String eventName,String location,String start, String staffString,int maxStaff, int status,ArrayList<String> staffListUserName) {
        this.status = status;
        this.date = date;
        this.client = client;
        this.eventName = eventName;
        this.location = location;
        this.start = start;
        this.staffString = staffString; // to view current staff on table
        this.maxStaff = maxStaff;
        this.staffListUserName = staffListUserName;
        staffList = new ArrayList<User>();

    }

    public void addStaff(User user){
        staffList.add(user);
    }
    public void removeStaff(User user){
        staffList.remove(user);
    }

    @Override
    public String toString(){
        String usernameListString = "";
        String dateString = getDateString();
        for (User user: staffList){
            usernameListString += user.getUsername()+ " ";
        }
        String string = dateString + " " + client + " " + eventName + " " + location + " " + start + " " + staffString + " " + maxStaff + " " + usernameListString;
        return string;
    }
    public String getDateString(){
        String string = date.getDay() + " " + date.getMonth() + " " + date.getYear();
        return string;
    }

    private void findUsers(UserList userList){
        for(String string : staffListUserName){
            staffList.add(userList.getUser(string));
        }
    }
}
