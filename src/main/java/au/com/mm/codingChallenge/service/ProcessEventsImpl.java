package au.com.mm.codingChallenge.service;

import au.com.mm.codingChallenge.domain.EventRecord;
import au.com.mm.codingChallenge.domain.Game;
import au.com.mm.codingChallenge.domain.Team;
import au.com.mm.codingChallenge.domain.events.Event;
import au.com.mm.codingChallenge.utils.GameConstants;
import au.com.mm.codingChallenge.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("processEvents")
public class ProcessEventsImpl implements  ProcessEvents {

    @Autowired
    EventFactoryService eventFactoryService;

    @Override
    public void processEventsForGivenTimestamp(String timeStamp) {
        eventFactoryService  = new EventFactoryServiceImpl();
        // This will only populate events which are inside the given timestamp
        Game game = initializeGame();
        List<EventRecord> eventRecordList = Utils.processCsvFileForGivenTimestamp("events.csv",timeStamp);

        for(EventRecord eventRecord: eventRecordList) {
            Event event  = eventFactoryService.populateEvent(eventRecord);
            event.executeEvent(game,eventRecord);

        }

    }

    private Game initializeGame() {
        Game game = new Game();
        Team teamA = new Team();
        teamA.setTeamName(GameConstants.TEAM_A);
        teamA.setHasPossession(Boolean.FALSE);
        teamA.setScore(0);
        teamA.setNumberOfShots(0);

        Team teamB = new Team();
        teamB.setTeamName(GameConstants.TEAM_B);
        teamB.setHasPossession(Boolean.FALSE);
        teamB.setScore(0);
        teamB.setNumberOfShots(0);

        game.addTeam(teamA);
        game.addTeam(teamB);
        return game;
    }


}
