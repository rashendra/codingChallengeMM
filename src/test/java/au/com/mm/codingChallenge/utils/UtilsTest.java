package au.com.mm.codingChallenge.utils;

import au.com.mm.codingChallenge.domain.EventRecord;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

public class UtilsTest {


    @Test
    public void testItShouldPopulateEventRecordsCorrectlyForValidFile() {
        //Given
        List<EventRecord> eventRecordList = Utils.processCsvFileForGivenTimestamp("events.csv","90:00");

        // Then
        Assertions.assertThat(eventRecordList != null).isTrue();
        Assertions.assertThat(eventRecordList.size() >0).isTrue();
    }

    @Test
    public void testItShouldNotPopulateEventRecordsForInvalidFile() {
        //Given
        List<EventRecord> eventRecordList = Utils.processCsvFileForGivenTimestamp("invalid.csv","90:00");

        // Then
        Assertions.assertThat(eventRecordList != null).isTrue();
        Assertions.assertThat(eventRecordList.size() == 0).isTrue();
    }

    @Test
    public void testItShouldNotPopulateEventRecordsForAGivenTimestamp() {
        //Given
        List<EventRecord> eventRecordList = Utils.processCsvFileForGivenTimestamp("events.csv","00:00");

        // Then
        Assertions.assertThat(eventRecordList != null).isTrue();
        Assertions.assertThat(eventRecordList.size() == 1).isTrue();
    }

    @Test
    public void testIsGivenTimestampGreaterThanEventTimestamp() {
        //Given
        String eventTimeStamp = "22:00";
        String givenTimeStamp = "23:00";

        // When
        Boolean givenTimestampGreaterThanEventTimestamp =
                Utils.isGivenTimestampGreaterThanEventTimestamp(eventTimeStamp, givenTimeStamp);
        // Then
        Assertions.assertThat(givenTimestampGreaterThanEventTimestamp).isTrue();
    }

    @Test
    public void testIsEventTimestampGreaterThanGivenTimestamp() {
        //Given
        String eventTimeStamp = "25:00";
        String givenTimeStamp = "23:00";

        // When
        Boolean givenTimestampGreaterThanEventTimestamp =
                Utils.isGivenTimestampGreaterThanEventTimestamp(eventTimeStamp, givenTimeStamp);
        // Then
        Assertions.assertThat(givenTimestampGreaterThanEventTimestamp).isFalse();
    }

    @Test
    public void testGetTimestampInSeconds() {
        //Given
        String givenTimeStamp = "23:56";

        //When
        Integer timeInSeconds =
                Utils.getTimestampInSeconds(givenTimeStamp);

        //Then
        Assertions.assertThat(timeInSeconds == 1436).isTrue();
    }


    @Test
    public void testCalculatePossession() {
        //Given
        Integer teamPossession = 78;
        Integer totalTime = 100;

        //When
        String possessionPercentage =
                Utils.calculatePossessionPercentage(teamPossession, totalTime);

        //Then
        System.out.println("*********");
        System.out.println(possessionPercentage);
    }
}
