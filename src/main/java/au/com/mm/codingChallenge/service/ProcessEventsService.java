package au.com.mm.codingChallenge.service;



public interface ProcessEventsService {
    /**
     * This will process all the events for a given timestamp and will print the summary
     * @param timeStamp given timestamp
     */
    void processEventsForGivenTimestamp(String timeStamp);
}
