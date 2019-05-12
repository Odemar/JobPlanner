package Classes;

import java.util.ArrayList;
import java.sql.Date;

public class Job {
   public Date date;
    private String client,eventName,location,start,staffString;
    private int maxStaff,status; // status=0 for not accepted status = 1 for accepted
    private ArrayList<String> staffListUserName;


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


    }

    public void addStaff(String username){
        staffListUserName.add(username);
    }
    public void removeStaff(String username){
        staffListUserName.remove(username);
    }

    @Override
    public String toString(){
        String usernameListString = "";
        String dateString = getDateString();
        for (String string: staffListUserName){
            usernameListString = usernameListString +" " + string ;
        }
        String string = dateString + " " + client + " " + eventName + " " + location + " " + start + " " + staffString + " " + maxStaff + " " + usernameListString;
        return string;
    }
    private String getDateString(){
        String string = date.getDay() + " " + date.getMonth() + " " + date.getYear();
        return string;
    }


}