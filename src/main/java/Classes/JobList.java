package Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Scanner;

public class JobList {
    ArrayList<Job> jobList;

    public JobList(){
        jobList = new ArrayList<>();
    }

    public void readFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner input = new Scanner(file);

        while (input.hasNextLine()) {
            String readLine = input.nextLine();
            String words[] = readLine.split(" ");
            Date date = new Date(Integer.parseInt(words[2]),Integer.parseInt(words[1]),Integer.parseInt(words[0]));
            String client = words[3];
            String eventName = words[4];
            String location = words[5];
            String start = words[6];
            int maxStaff = Integer.parseInt(words[7]);
            int status = Integer.parseInt(words[8]);
            int maxCount = words.length-1;
            ArrayList<String> staffListUsername = new ArrayList<>();
            for(int i = 9; i <= maxCount;i++){
                String username = words[i];
                staffListUsername.add(username);

            }
            Job newJob =new Job(date,client,eventName,location,start,maxStaff,status,staffListUsername);
            jobList.add(newJob);

        }
        input.close();

    }

    public void updateFile(String filename) throws IOException{
        File file = new File(filename);
        FileOutputStream fos = new FileOutputStream(file);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        for (Job job: jobList) {
            String jobString = job.toString();
            bw.write(jobString);
            bw.newLine();

        }
        bw.close();
    }
    public void addJobToList(Job job) throws IOException{
        jobList.add(job);
        updateFile("jobList.txt");
    }

    public void removeJob(Job job) throws IOException{
        jobList.remove(job);
        updateFile("jobList.txt");
    }


    public ObservableList<Job> getJobsDate(Date date){
        ObservableList<Job> jobDate = FXCollections.observableArrayList();
        for(Job job: jobList){
            date.equals(job.date);
            jobDate.add(job);
        }
    return jobDate;
    }

}
