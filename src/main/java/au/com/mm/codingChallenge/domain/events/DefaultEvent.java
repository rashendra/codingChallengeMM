package au.com.mm.codingChallenge.domain.events;


import au.com.mm.codingChallenge.domain.EventRecord;
import au.com.mm.codingChallenge.domain.Game;

public class DefaultEvent implements Event {

    @Override
    public void executeEvent(Game game,EventRecord eventRecord) {
        // This will do nothing if the command is invalid
    }
}
