package au.com.mm.codingChallenge;

import au.com.mm.codingChallenge.utils.Utils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Scanner;

@ComponentScan("au.com.mm.codingChallenge")
@SpringBootApplication
public class CodingChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodingChallengeApplication.class, args);
        System.out.println(" This will fetch the data stored in the resource folder");

        System.out.println(" Enter a valid time stamp in  mm:ss format");
        ExecuteCommand executeCommand = new ExecuteCommand();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String timestamp = scanner.nextLine().trim();
                if(Utils.validateTimestamp(timestamp)) {
                    executeCommand.executeCommand(timestamp);
                } else {
                    System.out.println("Invalid format! Please enter a valid time stamp in mm:ss format");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
