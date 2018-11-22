package au.com.mm.codingChallenge.service;


import au.com.mm.codingChallenge.domain.EventRecord;
import au.com.mm.codingChallenge.domain.events.Event;

public interface EventFactoryService {

    /**
     *  This will create the relevant Event implementation for the given eventRecord
     * @param eventRecord given timestamp
     */
    Event populateEvent(EventRecord eventRecord);
}
