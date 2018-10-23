package au.com.mm.codingChallenge.domain.events;


import au.com.mm.codingChallenge.domain.EventRecord;
import au.com.mm.codingChallenge.domain.Game;
import au.com.mm.codingChallenge.domain.Team;

public interface Event {

    /*
    *  This is where you do all the business logic with teams
    * */
    void executeEvent(Game game , EventRecord eventRecord);


}
