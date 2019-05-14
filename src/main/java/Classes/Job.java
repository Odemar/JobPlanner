package Classes;

import java.util.ArrayList;
import java.sql.Date;

public class Job {
   public Date date;
    public String client,eventName,location,start,staffString,dateStringTable;
    private int maxStaff; // status=0 for not accepted status = 1 for accepted
    private ArrayList<String> staffListUserName;


    public Job(Date date,String client,String eventName,String location,String start,int maxStaff,ArrayList<String> staffListUserName) {

        this.date = date;
        this.client = client;
        this.eventName = eventName;
        this.location = location;
        this.start = start;
        this.maxStaff = maxStaff;
        this.staffListUserName = staffListUserName;
        updateStaff(); // to view current staff on table

    }
    // returns a string for use in request table
    public String getDateStringTable(){
        dateStringTable = date.getDate() + "/" + date.getMonth() + "/" + (date.getYear()+1900);
        return dateStringTable;
    }

    public void addStaff(String username){

        staffListUserName.add(username);
          updateStaff();

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
        String string = dateString + " " + client + " " + eventName + " " + location + " " + start + " " + maxStaff+ " " + usernameListString;
        return string;
    }
    private String getDateString(){
        String string = date.getDate() + " " + date.getMonth() + " " + (date.getYear()); // date
        return string;
    }
    public void updateStaff(){
        staffListUserName.remove(""); // adds an empty string for some reason each time? temporary fix
        this.staffString = staffListUserName.size() + "/" + maxStaff;

    }

    // tableview uses these getters to get data from our object
    public String getClient(){
        return client;
    }
    public int getMaxStaff(){
        return maxStaff;
    }

    public String getEventName(){
        return eventName;
    }
    public String getLocation(){
        return location;
    }
    public String getStaffString(){
        updateStaff();
        return staffString;
    }
    public String getStart(){
        return start;
    }
    public ArrayList<String> getStaff(){
        return  staffListUserName;
    }



}
