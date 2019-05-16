package Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Scanner;

/**
 * This Class holds an ArrayList of all the jobs in the database.
 * When an object of this class is made, it will read the jobs from a file given to the constructor.
 */
public class JobList {

    /**
     * Arraylist of all jobs
     */
    public ArrayList<Job> jobList;
    /**
     * Name of the file where the data of the all the jobs is stored in
     */
    private String filename;

    /**
     * Constructor of this JobList.
     *
     * @param filename name of the file where the jobs are read from
     * @throws FileNotFoundException when the given file doesn't exist
     */
    public JobList(String filename) throws FileNotFoundException {
        this.filename = filename;
        readFile();
    }

    /**
     * Method to read and process a file with Job data.
     * The method makes use of a Scanner. Using a while-loop, every line gets processed word per word. Therefore it's
     * very important that the text file has a specific format.
     *
     * @throws FileNotFoundException when the given file doesn't exist
     */
    public void readFile() throws FileNotFoundException {
        jobList = new ArrayList<>();
        File file = new File(filename);
        Scanner input = new Scanner(file);

        while (input.hasNextLine()) {
            // reads next line to a String
            String readLine = input.nextLine();
            // splits the read line into words, using the space character as separator
            String words[] = readLine.split(" ");

            // from the words array, each 'word' is assigned to a variable, later used for making a new Job
            Date date = new Date(Integer.parseInt(words[2]), Integer.parseInt(words[1]), Integer.parseInt(words[0]));
            String client = words[3];
            String eventName = words[4];
            String location = words[5];
            String start = words[6];
            int maxStaff = Integer.parseInt(words[7]);
            int endOffLine = words.length - 1;

            /* The last words of the line are the usernames of the staff Users who applied for this Job.
             * An arraylist is created with these following usernames */
            ArrayList<String> staffListUsername = new ArrayList<>();
            for (int i = 8; i <= endOffLine; i++) {
                String username = words[i];
                staffListUsername.add(username);
            }

            // lastly, a new Job is created and added to the joblist
            Job newJob = new Job(date, client, eventName, location, start, maxStaff, staffListUsername);
            jobList.add(newJob);
        }

        // the scanner gets closed
        input.close();
    }

    /**
     * Updates the text file database to the current Jobs in the JobList.
     *
     * @throws IOException when the file isn't found
     */
    public void updateFile() throws IOException {
        File file = new File(filename);
        FileOutputStream fos = new FileOutputStream(file);

        // print every job from the jobList to the file
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        for (Job job : jobList) {
            String jobString = job.toString();
            bw.write(jobString);
            bw.newLine();

        }

        //System.out.println("Writing successful");  // debug

        // closing the buffered writer
        bw.close();
    }

    /**
     * Adds a Job to the JobList and updates the text database.
     *
     * @param job job to be added to the JobList
     * @throws IOException when the text database isn't found
     */
    public void addJobToList(Job job) throws IOException {
        jobList.add(job);
        updateFile();
    }

    /**
     * Removes a Job from the JobList and updates the text database.
     *
     * @param job job to be removed
     * @throws IOException when the text database isn't found
     */
    public void removeJob(Job job) throws IOException {
        Job deleteJob = job;
        String eventName = job.getEventName();

        // for some reason the array doesn't find the given user so instead i have to use this
        // temporary solution
        for (Job jobIt : jobList) {
            if (jobIt.getEventName().equals(eventName)) {
                deleteJob = jobIt;
            }

        }
        jobList.remove(deleteJob);
        updateFile();
    }

    /**
     * Returns an ObservableList of Jobs at the given date.
     *
     * @param date date on which the Jobs occur
     * @return an ObservableList of Jobs with the given date
     */
    public ObservableList<Job> getJobsDate(Date date) {
        ObservableList<Job> jobDate = FXCollections.observableArrayList();

        for (Job job : jobList) {

            if (date.equals(job.date)) {

                jobDate.add(job);
            }
        }

        return jobDate;
    }

    //debug method
    /* public void printList() {
        for (Job job : jobList) {
            System.out.println(job.toString());
        }
    }
    */

    /**
     * Assigns a staff member to a given Job.
     *
     * @param staffName name of the staff member to be assigned
     * @param eventName name of the Job to be updated
     * @throws IOException when the text database isn't found
     */
    public void addStaffJob(String staffName, String eventName) throws IOException {
        int i = 0;
        for (Job job : jobList) {
            if (job.getEventName().equals(eventName)) {
                job.addStaff(staffName);
                // replace the Job at array position 'i' to 'job'
                jobList.set(i, job);
            }
            i++;
        }
        updateFile();
    }

    /**
     * Discharge a staff member from a given Job.
     *
     * @param staffName name of the staff member to be discharged
     * @param eventName name of the job to be updated
     * @throws IOException when the text database isn't found
     */
    public void delStaffJob(String staffName, String eventName) throws IOException {
        int i = 0;
        for (Job job : jobList) {
            if (job.getEventName().equals(eventName)) {
                job.removeStaff(staffName);
                // replace the Job at array position 'i' with 'job'
                jobList.set(i, job);
            }
            i++;
        }
        updateFile();
    }

    /**
     * Returns an ObservableList of Jobs where the specified staff member has applied for.
     *
     * @param staff staff User where the jobs need to be searched for
     * @return ObservableList of Jobs
     */
    public ObservableList<Job> getStaffJobs(User staff) {
        ArrayList<Job> jobArrayList = new ArrayList<>();
        // search the Jobs where the username matches
        for (Job job : jobList) {
            if (job.getStaff().contains(staff.getUsername())) {
                jobArrayList.add(job);
            }
        }
        return FXCollections.observableArrayList(jobArrayList);
    }

    /**
     * Returns an ObservableList of Jobs which the specified client member has made.
     *
     * @param client client User where the Jobs need to be searched for
     * @return ObservableList of Jobs
     */
    public ObservableList<Job> getJobsClient(User client) {
        ArrayList<Job> jobArrayList = new ArrayList<>();
        for (Job job : jobList) {
            if (job.getClient().equals(client.getUsername())) {
                jobArrayList.add(job);
            }
        }
        return FXCollections.observableArrayList(jobArrayList);
    }
}


