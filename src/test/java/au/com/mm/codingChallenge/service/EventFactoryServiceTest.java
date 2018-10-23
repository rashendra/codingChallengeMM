package au.com.mm.codingChallenge.service;

import au.com.mm.codingChallenge.domain.EventRecord;
import au.com.mm.codingChallenge.domain.events.*;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

public class EventFactoryServiceTest {


    private EventFactoryService eventFactoryService;

    private EventRecord eventRecord ;


    @Before
    public void setUp()
    {
        eventFactoryService = EventFactoryServiceImpl.getInstance();
        eventRecord = new EventRecord();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateStartEventForStartEvent()
    {
        Event event = getStartEvent(0,0);
        Assertions.assertThat(event instanceof StartEvent).isTrue();
    }


    private Event getStartEvent(Integer min , Integer seconds) {
        eventRecord.setEventType("START");
        eventRecord.setTeamName("A");
        eventRecord.setEventMin(min);
        eventRecord.setEventSeconds(seconds);
        return eventFactoryService.populateEvent(eventRecord);
    }

    @Test
    public void shouldCreateBreakOrEndEventForBreakEvent()
    {
        eventRecord.setEventType("BREAK");
        Event event = eventFactoryService.populateEvent(eventRecord);
        Assertions.assertThat(event instanceof BreakOrEndEvent).isTrue();
    }


    @Test
    public void shouldCreateDefaultEventForAnyInvalidEvent()
    {
        eventRecord.setEventType("ANYText");
        Event event = eventFactoryService.populateEvent(eventRecord);
        Assertions.assertThat(event instanceof DefaultEvent).isTrue();
    }

    @Test
    public void shouldCreatePossessEventForPossessEvent()
    {
        eventRecord.setEventType("POSSESS");
        Event event = eventFactoryService.populateEvent(eventRecord);
        Assertions.assertThat(event instanceof PossessEvent).isTrue();
    }

    @Test
    public void shouldCreateStartEventForScoreEvent()
    {
        eventRecord.setEventType("SCORE");
        Event event = eventFactoryService.populateEvent(eventRecord);
        Assertions.assertThat(event instanceof ScoreEvent).isTrue();
    }

    @Test
    public void shouldCreateShotEventForShotEvent()
    {
        eventRecord.setEventType("SHOT");
        Event event = eventFactoryService.populateEvent(eventRecord);
        Assertions.assertThat(event instanceof ShotEvent).isTrue();
    }



}
