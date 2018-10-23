package au.com.mm.codingChallenge.domain.events;

import au.com.mm.codingChallenge.domain.EventRecord;
import au.com.mm.codingChallenge.domain.Game;
import au.com.mm.codingChallenge.domain.Team;
import au.com.mm.codingChallenge.utils.Utils;

public class StartEvent implements Event {

    /*
    *  The game is yet to be started, so no one will have possession
    * */
    @Override
    public void executeEvent(Game game, EventRecord eventRecord) {

        Integer eventTimestampInSeconds = Utils.getTimestampInSeconds(eventRecord.getEventMin(), eventRecord.getEventSeconds());

        Team teamWithCurrentPossession = game.getTeams().stream()
                .filter(e-> e.getTeamName().equalsIgnoreCase(eventRecord.getTeamName())).findFirst().get();
        Team teamDefending= game.getTeams().stream()
                .filter(e-> !(e.getTeamName().equalsIgnoreCase(eventRecord.getTeamName()))).findFirst().get();

        teamWithCurrentPossession.setProcessionStartTime(eventTimestampInSeconds);
        teamWithCurrentPossession.setHasPossession(Boolean.TRUE);
        teamDefending.setHasPossession(Boolean.FALSE);
    }
}
