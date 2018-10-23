package au.com.mm.codingChallenge.utils;

import au.com.mm.codingChallenge.domain.EventRecord;
import com.opencsv.CSVReader;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<EventRecord> processCsvFileForGivenTimestamp(String csvFileName, String timeStamp) {
        List<EventRecord> eventRecords = new ArrayList<>();
        try {
            File csvFileNew = ResourceUtils.getFile("classpath:" + csvFileName);
            CSVReader reader = new CSVReader(new FileReader(csvFileNew));
            reader.skip(1);
            String[] line;
            while ((line = reader.readNext()) != null) {

                if (isGivenTimestampGreaterThanEventTimestamp(line[0],timeStamp)) {
                    EventRecord eventRecord = new EventRecord();
                    setEventTime(eventRecord, line[0]);
                    eventRecord.setEventType(line[1]);
                    eventRecord.setTeamName(line[2]);
                    eventRecords.add(eventRecord);
                } else {
                    break;
                }
            }
            // This event will be added as default event to calculate the possession
            EventRecord printSummaryEvent = new EventRecord();
            setEventTime(printSummaryEvent, timeStamp);
            printSummaryEvent.setEventType("PRINTSUMMARY");
            eventRecords.add(printSummaryEvent);

        } catch (IOException e) {
            e.printStackTrace();// To be replaced with loggers
        }
        return eventRecords;
    }

    private static void setEventTime(EventRecord eventRecord, String eventTime) {
        if (eventTime != null && !eventTime.isEmpty()) {
            String[] timeStamp = eventTime.split(":");
            if (timeStamp != null && timeStamp.length > 1) {
                eventRecord.setEventMin(Integer.parseInt(timeStamp[0]));
                eventRecord.setEventSeconds(Integer.parseInt(timeStamp[1]));
            }
        }
    }

    public static Boolean isGivenTimestampGreaterThanEventTimestamp(String eventTimestamp, String givenTimestamp) {
        Integer eventTimestampInSeconds = getTimestampInSeconds(eventTimestamp);
        Integer givenTimestampInSeconds = getTimestampInSeconds(givenTimestamp);

        return givenTimestampInSeconds > eventTimestampInSeconds;
    }

    public static Integer getTimestampInSeconds(String timestamp) {
        Integer timestampInSeconds = 0;
        if (timestamp != null && !timestamp.isEmpty()) {
            String[] timeStamp = timestamp.split(":");
            if (timeStamp != null && timeStamp.length > 1) {
                timestampInSeconds = timestampInSeconds + (Integer.parseInt(timeStamp[0]) * 60);
                timestampInSeconds = timestampInSeconds + Integer.parseInt(timeStamp[1]);
            }
        }
        return timestampInSeconds;
    }

    public static Integer getTimestampInSeconds(Integer min, Integer seconds) {
        Integer timestampInSeconds = 0;
                timestampInSeconds = timestampInSeconds + (min * 60);
                timestampInSeconds = timestampInSeconds + seconds;
        return timestampInSeconds;
    }

    public static String calculatePossessionPercentage(Integer teamPossession,Integer totalTime) {
        Float totalTimeOfGame = Float.valueOf(totalTime.toString());
        Float totalTimeOfTeam = Float.valueOf(teamPossession.toString());
        Float percentage = (totalTimeOfTeam/totalTimeOfGame) * 100;
        return percentage.toString() +"%";
    }


}
