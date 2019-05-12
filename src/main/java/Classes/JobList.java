package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class JobList {
    ArrayList<Job> jobList;

    public JobList(){
        jobList = new ArrayList<Job>();
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
            String staffString = words[7];
            int maxStaff = Integer.parseInt(words[8]);
            int maxCount = words.length-1;
            ArrayList<String> staffListUsername = new ArrayList<String>();
            for(int i = 9; i <= maxCount;i++){
                String username = words[i];
                staffListUsername.add(username);

            }
            jobList.add(new Job(date,client,eventName,location,start,staffString,maxStaff,staffListUsername));

        }
    }
}
