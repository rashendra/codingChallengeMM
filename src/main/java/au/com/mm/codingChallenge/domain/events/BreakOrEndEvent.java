package au.com.mm.codingChallenge.domain.events;

import au.com.mm.codingChallenge.domain.EventRecord;
import au.com.mm.codingChallenge.domain.Game;
import au.com.mm.codingChallenge.domain.Team;
import au.com.mm.codingChallenge.utils.Utils;

public class BreakOrEndEvent implements Event {

    @Override
    public void executeEvent(Game game, EventRecord eventRecord) {
        Team currentPossessionTeam = game.getTeams().stream()
                .filter(e-> e.getHasPossession()).findFirst().get();

        Integer eventTimestampInSeconds = Utils.getTimestampInSeconds(eventRecord.getEventMin(), eventRecord.getEventSeconds());
        addPossessionTime(currentPossessionTeam,eventTimestampInSeconds);

        game.getTeams().forEach(e-> e.setHasPossession(Boolean.FALSE));
    }

    private void addPossessionTime(Team currentPossessionTeam, Integer eventTimestampInSeconds ) {
        Integer possessionStartTime = currentPossessionTeam.getProcessionStartTime();
        currentPossessionTeam.addpossession(eventTimestampInSeconds - possessionStartTime);
    }


}
