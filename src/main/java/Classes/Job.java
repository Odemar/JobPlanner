package Classes;

import java.util.ArrayList;
import java.sql.Date;

/**
 * This class defines a job where staff memebers can apply for.
 */
public class Job {

    //<editor-fold desc="Class variables">
    /**
     * Date of the job.
     */
    public Date date;
    /**
     * Client who ordered the job.
     */
    public String client;
    /**
     * Name of the job.
     */
    public String eventName;
    /**
     * Location of the job.
     */
    public String location;
    /**
     * Starting time of the job.
     */
    public String start;
    /**
     * Amount of staff needed for the job.
     */
    public String staffString;
    /**
     * Date of the job, in String format.
     */
    public String dateStringTable;
    /**
     * Integer to define if the job is full.
     */
    private int maxStaff; // status = 0 for not accepted status = 1 for accepted
    /**
     * Arraylist to store all the users in that have applied for the job.
     */
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

        // to view current staff on table
        updateStaff();

    }

    /**
     * Returns a string used in the request table.
     *
     * @return date in a String format
     */
    public String getDateStringTable() {
        dateStringTable = date.getDate() + "/" + date.getMonth() + "/" + (date.getYear() + 1900);
        return dateStringTable;
    }

    /**
     * Method to add staff to the job.
     *
     * @param username username of the User to be added to the job
     */
    public void addStaff(String username) {

        staffListUserName.add(username);
        updateStaff();
    }

    /**
     * Method to remove a staff member from the job.
     *
     * @param username username of the staff User to be removed
     */
    public void removeStaff(String username) {
        staffListUserName.remove(username);
    }

    /**
     * Method to create a string of the details of the job.
     * Used to print out to the jobList.txt file.
     *
     * @return String with the details of the job separated by spaces
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
     * @return String with the date of the job (day month year)
     */
    private String getDateString() {
        String string = date.getDate() + " " + date.getMonth() + " " + (date.getYear()); // date
        return string;
    }

    /**
     * Method to update the staffString. (ex. '0/4' becomes '1/4')
     */
    public void updateStaff() {
        // adds an empty string for some reason each time? temporary fix
        staffListUserName.remove("");

        this.staffString = staffListUserName.size() + "/" + maxStaff;
    }

    //<editor-fold desc="Get-methods used by tableview">

    /**
     * Returns the client that submitted the job.
     *
     * @return the client that made the job
     */
    public String getClient() {
        return client;
    }

    /**
     * Returns the maximum staff that can apply for the job.
     *
     * @return the max amount of staff that can apply for the job
     */
    public int getMaxStaff() {
        return maxStaff;
    }

    /**
     * Returns the name of the job.
     *
     * @return name of the job
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Returns the location of the job.
     *
     * @return location of the job
     */
    public String getLocation() {
        return location;
    }

    /**
     * Returns a string of the amount of staff that is currently assigned to the job (ex. '1/4').
     *
     * @return String which shows how many staff members have already applied
     */
    public String getStaffString() {
        updateStaff();
        return staffString;
    }

    /**
     * Returns the starting time of the job.
     *
     * @return starting time of the job
     */
    public String getStart() {
        return start;
    }

    /**
     * Returns an arraylist of the staff that is currently assigned to the job.
     *
     * @return ArrayList of usernames from the staff members that have applied for the job
     */
    public ArrayList<String> getStaff() {
        return staffListUserName;
    }
    //</editor-fold>

}
