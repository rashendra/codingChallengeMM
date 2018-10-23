package au.com.mm.codingChallenge;

import au.com.mm.codingChallenge.service.ProcessEvents;
import au.com.mm.codingChallenge.service.ProcessEventsImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class ExecuteCommand {

//    @Autowired
    ProcessEvents processEvents = new ProcessEventsImpl();

    public void executeCommand(String timestamp) {
        System.out.println("This is the timestamp : "+ timestamp);
        System.out.println(processEvents == null);
        processEvents = new ProcessEventsImpl();
        processEvents.processEventsForGivenTimestamp(timestamp);
    }

}