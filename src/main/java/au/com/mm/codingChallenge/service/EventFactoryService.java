package au.com.mm.codingChallenge.service;


import au.com.mm.codingChallenge.domain.EventRecord;
import au.com.mm.codingChallenge.domain.events.Event;

public interface EventFactoryService {

    Event populateEvent(EventRecord eventRecord);
}
