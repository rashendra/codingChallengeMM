package au.com.mm.codingChallenge.service;

import au.com.mm.codingChallenge.domain.EventRecord;
import au.com.mm.codingChallenge.domain.Game;
import au.com.mm.codingChallenge.domain.Team;
import au.com.mm.codingChallenge.domain.events.Event;
import au.com.mm.codingChallenge.utils.GameConstants;
import au.com.mm.codingChallenge.utils.Utils;

import java.util.List;

public class ProcessEventsServiceImpl implements ProcessEventsService {

    private EventFactoryService eventFactoryService;

    private static ProcessEventsServiceImpl processEventsImpl;

    private ProcessEventsServiceImpl() {
    }

    public static ProcessEventsService getInstance() {
        if(processEventsImpl == null) {
            synchronized(ProcessEventsServiceImpl.class){
                if(processEventsImpl == null) {
                    processEventsImpl = new ProcessEventsServiceImpl();
                }
            }
        }
        return  processEventsImpl;
    }

    @Override
    public void processEventsForGivenTimestamp(String timeStamp) {
        eventFactoryService  = EventFactoryServiceImpl.getInstance();
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
        teamA.setPossession(0);

        Team teamB = new Team();
        teamB.setTeamName(GameConstants.TEAM_B);
        teamB.setHasPossession(Boolean.FALSE);
        teamB.setScore(0);
        teamB.setNumberOfShots(0);
        teamB.setPossession(0);

        game.addTeam(teamA);
        game.addTeam(teamB);
        return game;
    }


}
