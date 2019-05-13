package Classes;

import java.util.ArrayList;
import java.sql.Date;

public class Job {
   public Date date;
    public String client,eventName,location,start,staffString;
    private int maxStaff,status; // status=0 for not accepted status = 1 for accepted
    private ArrayList<String> staffListUserName;


    public Job(Date date,String client,String eventName,String location,String start,int maxStaff, int status,ArrayList<String> staffListUserName) {
        this.status = status;
        this.date = date;
        this.client = client;
        this.eventName = eventName;
        this.location = location;
        this.start = start;
        this.maxStaff = maxStaff;
        this.staffListUserName = staffListUserName;
        updateStaff(); // to view current staff on table

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
        String string = dateString + " " + client + " " + eventName + " " + location + " " + start + " " + maxStaff+ " " + status + " " + usernameListString;
        return string;
    }
    private String getDateString(){
        String string = date.getDate() + " " + date.getMonth() + " " + (date.getYear()-1900); // date
        return string;
    }
    public void updateStaff(){

        this.staffString = staffListUserName.size() + "/" + maxStaff;
    }

    // tableview uses these getters to get data from our object
    public String getClient(){
        return client;
    }

    public String getEventName(){
        return eventName;
    }
    public String getLocation(){
        return location;
    }
    public String getMaxStaff(){
        String staffString = staffListUserName.size() + "/" + maxStaff;
        return staffString;
    }
    public String getStart(){
        return start;
    }

}
