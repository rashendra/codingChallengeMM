package au.com.mm.codingChallenge.service;

import au.com.mm.codingChallenge.domain.EventRecord;
import au.com.mm.codingChallenge.domain.events.StartEvent;
import au.com.mm.codingChallenge.utils.GameConstants;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static au.com.mm.codingChallenge.utils.GameConstants.PRINTSUMMARY;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ProcessEventsServiceImplTest {

    @InjectMocks
    private ProcessEventsService processEventsService;

    @Mock
    EventFactoryService eventFactoryService;



    @Before
    public void setUp()
    {
        processEventsService = ProcessEventsServiceImpl.getInstance();
        eventFactoryService = mock(EventFactoryServiceImpl.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldProcessEventsForGivenTimestamp() {
        EventRecord startEventRecord = new EventRecord();
        startEventRecord.setEventMin(0);
        startEventRecord.setEventSeconds(0);
        startEventRecord.setTeamName(GameConstants.TEAM_A);
        startEventRecord.setEventType("Start");

        EventRecord printSummaryEvent = new EventRecord();
        printSummaryEvent.setEventMin(0);
        printSummaryEvent.setEventSeconds(9);
        printSummaryEvent.setTeamName(GameConstants.TEAM_A);
        printSummaryEvent.setEventType(PRINTSUMMARY);
        Mockito.when(eventFactoryService.populateEvent(any(EventRecord.class))).thenReturn(new StartEvent());

        processEventsService.processEventsForGivenTimestamp("0:09");
        verify(eventFactoryService, times(2)).populateEvent(any(EventRecord.class));
    }


}
