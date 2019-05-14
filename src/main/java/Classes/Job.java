package Classes;

import java.util.ArrayList;
import java.sql.Date;

/**
 * This class defines a job where staff memebers can apply for.
 */
public class Job {

    //<editor-fold desc="Class variables">
    // date of the job
    public Date date;
    // client who ordered the job
    public String client;
    // name of the job
    public String eventName;
    // location of the job
    public String location;
    // starting time of the job
    public String start;
    // amount of staff needed for the job (ex. 0/4)
    public String staffString;
    // date of the job
    public String dateStringTable;
    // integer to define if job is full
    private int maxStaff; // status = 0 for not accepted status = 1 for accepted
    // arraylist to store all the users in that have applied for the job
    private ArrayList<String> staffListUserName;
    //</editor-fold>

    /**
     * Constructor for the class Job.
     *
     * @param date              Date of the job
     * @param client            Client who organises the job
     * @param eventName         Name of the job
     * @param location          Location of the job
     * @param start             Starting time of the job
     * @param maxStaff          Maximum amount of staff that can apply for the job
     * @param staffListUserName List of staff that has applied
     */
    public Job(Date date, String client, String eventName, String location, String start, int maxStaff, ArrayList<String> staffListUserName) {

        this.date = date;
        this.client = client;
        this.eventName = eventName;
        this.location = location;
        this.start = start;
        this.maxStaff = maxStaff;
        this.staffListUserName = staffListUserName;
        updateStaff(); // to view current staff on table

    }

    /**
     * Returns a string used in the request table.
     *
     * @return
     */
    public String getDateStringTable() {
        dateStringTable = date.getDate() + "/" + date.getMonth() + "/" + (date.getYear() + 1900);
        return dateStringTable;
    }

    /**
     * Method to add staff to the job.
     *
     * @param username
     */
    public void addStaff(String username) {

        staffListUserName.add(username);
        updateStaff();

    }

    /**
     * Method to remove a staff member from the job.
     *
     * @param username
     */
    public void removeStaff(String username) {
        staffListUserName.remove(username);
    }

    /**
     * Method to create a string of the details of the job.
     * Used to print out to the jobList.txt file.
     */
    @Override
    public String toString() {
        String usernameListString = "";
        String dateString = getDateString();
        for (String string : staffListUserName) {
            usernameListString = usernameListString + " " + string;
        }
        String string = dateString + " " + client + " " + eventName + " " + location + " " + start + " " + maxStaff + " " + usernameListString;
        return string;
    }

    /**
     * Method to create a string of the date of the job. (ex. "12 4 119" would be the datestring of 12/04/2019)
     *
     * @return
     */
    private String getDateString() {
        String string = date.getDate() + " " + date.getMonth() + " " + (date.getYear()); // date
        return string;
    }

    /**
     * Method to update the staffString. (ex. '0/4' becomes '1/4')
     */
    public void updateStaff() {
        staffListUserName.remove(""); // adds an empty string for some reason each time? temporary fix
        this.staffString = staffListUserName.size() + "/" + maxStaff;

    }

    //<editor-fold desc="Get-methods used by tableview">

    /**
     * Returns the client that submitted the job.
     *
     * @return
     */
    public String getClient() {
        return client;
    }

    /**
     * Returns the maximum staff that can apply for the job.
     *
     * @return
     */
    public int getMaxStaff() {
        return maxStaff;
    }

    /**
     * Returns the name of the job.
     *
     * @return
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Returns the location of the job.
     *
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     * Returns a string of the amount of staff that is currently assigned to the job (ex. '1/4').
     *
     * @return
     */
    public String getStaffString() {
        updateStaff();
        return staffString;
    }

    /**
     * Returns the starting time of the job.
     *
     * @return
     */
    public String getStart() {
        return start;
    }

    /**
     * Returns an arraylist of the staff that is currently assigned to the job.
     *
     * @return
     */
    public ArrayList<String> getStaff() {
        return staffListUserName;
    }
    //</editor-fold>

}
