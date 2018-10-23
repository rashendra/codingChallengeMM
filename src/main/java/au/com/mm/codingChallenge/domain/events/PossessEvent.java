package au.com.mm.codingChallenge.domain.events;

import au.com.mm.codingChallenge.domain.EventRecord;
import au.com.mm.codingChallenge.domain.Game;
import au.com.mm.codingChallenge.domain.Team;
import au.com.mm.codingChallenge.utils.Utils;

public class PossessEvent implements Event{


    /*
    * Before this event , one team should have possession and the game should have been started
    * */
    @Override
    public void executeEvent(Game game, EventRecord eventRecord) {

        Team currentPossessionTeam = game.getTeams().stream()
                .filter(e-> e.getHasPossession()).findFirst().get();

        Team currentDefendingTeam = game.getTeams().stream()
                .filter(e-> !e.getHasPossession()).findFirst().get();


        Integer eventTimestampInSeconds = Utils.getTimestampInSeconds(eventRecord.getEventMin(), eventRecord.getEventSeconds());
        addPossessionTime(currentPossessionTeam,eventTimestampInSeconds);
        currentPossessionTeam.setHasPossession(Boolean.FALSE);
        currentDefendingTeam.setHasPossession(Boolean.TRUE);
        currentDefendingTeam.setProcessionStartTime(eventTimestampInSeconds);
    }


    private void addPossessionTime(Team currentPossessionTeam, Integer eventTimestampInSeconds ) {
        Integer possessionStartTime = currentPossessionTeam.getProcessionStartTime();
        currentPossessionTeam.addpossession(eventTimestampInSeconds - possessionStartTime);

    }


}
