package au.com.mm.codingChallenge;

import au.com.mm.codingChallenge.service.ProcessEventsService;
import au.com.mm.codingChallenge.service.ProcessEventsServiceImpl;
import au.com.mm.codingChallenge.utils.Utils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class CodingChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodingChallengeApplication.class, args);
        System.out.println(" This will fetch the data stored in the resource folder");
        ProcessEventsService processEvents = ProcessEventsServiceImpl.getInstance();
        System.out.println(" Enter a valid time stamp in  mm:ss format");
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String timestamp = scanner.nextLine().trim();
                if(Utils.validateTimestamp(timestamp)) {
                    processEvents.processEventsForGivenTimestamp(timestamp);
                } else {
                    System.out.println("Invalid format! Please enter a valid time stamp in mm:ss format");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
