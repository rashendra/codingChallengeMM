package au.com.mm.codingChallenge.domain.events;

import au.com.mm.codingChallenge.domain.EventRecord;
import au.com.mm.codingChallenge.domain.Game;
import au.com.mm.codingChallenge.domain.Team;

public class ShotEvent implements Event {

    @Override
    public void executeEvent(Game game , EventRecord eventRecord) {
        Team teamWithCurrentPossession = game.getTeams().stream()
                .filter(e-> e.getTeamName().equalsIgnoreCase(eventRecord.getTeamName())).findFirst().get();
        teamWithCurrentPossession.addnumberOfShot();
    }
}
