import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static String database = "/app/data/database.txt"; // Specify the file name or path
    private static void displayStatistics() {
        Map<String, Integer> courseCount = new HashMap<>();
        int numOfUsers=0;
        String[] courses =null;
         try (BufferedReader reader = new BufferedReader(new FileReader(database))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length >= 3) {
                    String name = data[0].trim();
                    String id = data[1].trim();
                    courses = data[2].trim().split(",");
                    numOfUsers++;
                }
                for(String course :courses) {
                    String trimmedCourse = course.trim();
                int count = courseCount.getOrDefault(trimmedCourse, 0);
                courseCount.put(trimmedCourse, count + 1);
                }
             }
        }catch(IOException e){
            System.out.println("An error occurred while reading the file:" + e.getMessage());
        }
         int numOfBatchFiles = 0;
        int numOfVerifiedBatchFiles = 0;



        File batchDataFolder = new File("/app/data/batch/");
        File[] batchFiles = batchDataFolder.listFiles();
        if (batchFiles != null) {
            numOfBatchFiles = batchFiles.length;
            for (File batchFile : batchFiles) {
                if (batchFile.getName().contains("verified")) {
                    numOfVerifiedBatchFiles++;
                }
            }
        }


        System.out.println("Number of users: " + numOfUsers);
        for (Map.Entry<String, Integer> entry : courseCount.entrySet()) {
            String course = entry.getKey();
            int count = entry.getValue();
            System.out.println("Number of students registered in " + course + " course: " + count);
        }
        System.out.println("Number of batch files: " + numOfBatchFiles);
        System.out.println("Number of verified batch files: " + numOfVerifiedBatchFiles);
    }

    public static void main(String[] args) {
        displayStatistics();
     }
}