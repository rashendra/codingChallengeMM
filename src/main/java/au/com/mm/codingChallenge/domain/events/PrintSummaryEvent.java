package au.com.mm.codingChallenge.domain.events;

import au.com.mm.codingChallenge.domain.EventRecord;
import au.com.mm.codingChallenge.domain.Game;
import au.com.mm.codingChallenge.domain.Team;
import au.com.mm.codingChallenge.utils.Utils;

public class PrintSummaryEvent implements Event {

    /*
    * This event will only calculate possession time for the given timestampxx
    * */
    @Override
    public void executeEvent(Game game, EventRecord eventRecord) {
        Team currentPossessionTeam = game.getTeams().stream()
                .filter(e-> e.getHasPossession()).findFirst().get();

        Integer eventTimestampInSeconds = Utils.getTimestampInSeconds(eventRecord.getEventMin(), eventRecord.getEventSeconds());
        addPossessionTime(currentPossessionTeam,eventTimestampInSeconds);
        game.printSummary(eventRecord.getEventMin(), eventRecord.getEventSeconds());
    }

    private void addPossessionTime(Team currentPossessionTeam, Integer eventTimestampInSeconds ) {
        Integer possessionStartTime = currentPossessionTeam.getProcessionStartTime();
        currentPossessionTeam.addpossession(eventTimestampInSeconds - possessionStartTime);
    }
}
