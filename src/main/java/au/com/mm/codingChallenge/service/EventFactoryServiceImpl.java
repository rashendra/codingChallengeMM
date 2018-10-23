package au.com.mm.codingChallenge.service;

import au.com.mm.codingChallenge.domain.EventRecord;
import au.com.mm.codingChallenge.domain.events.*;
import au.com.mm.codingChallenge.domain.events.enums.EventType;
import org.springframework.stereotype.Service;

@Service("eventFactoryService")
public class EventFactoryServiceImpl implements EventFactoryService {


    @Override
    public Event populateEvent(EventRecord eventRecord) {

        Event event = null;

        if (EventType.BREAK.toString().equals(eventRecord.getEventType())
                || EventType.END.toString().equalsIgnoreCase(eventRecord.getEventType())) {
            event = new BreakOrEndEvent();

        } else if (EventType.START.toString().equalsIgnoreCase(eventRecord.getEventType())) {
            event = new StartEvent();
        }
        else if (EventType.POSSESS.toString().equalsIgnoreCase(eventRecord.getEventType())) {
            event = new PossessEvent();
        } else if (EventType.SHOT.toString().equalsIgnoreCase(eventRecord.getEventType())) {
            event = new ShotEvent();

        } else if (EventType.SCORE.toString().equalsIgnoreCase(eventRecord.getEventType())) {
            event = new ScoreEvent();

        } else if (EventType.PRINTSUMMARY.toString().equalsIgnoreCase(eventRecord.getEventType())) {
            event = new PrintSummaryEvent();

        }else {
            event = new DefaultEvent();
        }

        return event;
    }
}
