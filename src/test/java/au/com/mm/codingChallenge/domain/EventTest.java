package au.com.mm.codingChallenge.domain;

import au.com.mm.codingChallenge.domain.events.Event;
import au.com.mm.codingChallenge.service.EventFactoryService;
import au.com.mm.codingChallenge.service.EventFactoryServiceImpl;
import au.com.mm.codingChallenge.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class EventTest {

    private Game game;

    private List<EventRecord> eventRecordList;

    private EventFactoryService eventFactoryService;

    static String TEAM_A = "A";

    static String TEAM_B = "B";


    @Before
    public void setUp() {
        eventRecordList = Utils.processCsvFileForGivenTimestamp("events.csv","90:00");

        eventFactoryService = new EventFactoryServiceImpl();

        MockitoAnnotations.initMocks(this);
    }

    private void initializeGame(Boolean teamAHasPossession, Boolean teamBHasPossession) {
        game = new Game();
        Team teamA = new Team();
        teamA.setTeamName("A");
        teamA.setHasPossession(teamAHasPossession);
        teamA.setScore(0);
        teamA.setNumberOfShots(0);
        teamA.setProcessionStartTime(0);
        teamA.setPossession(0);

        Team teamB = new Team();
        teamB.setTeamName("B");
        teamB.setHasPossession(teamBHasPossession);
        teamB.setScore(0);
        teamB.setNumberOfShots(0);
        teamB.setProcessionStartTime(0);
        teamB.setPossession(0);


        game.addTeam(teamA);
        game.addTeam(teamB);
        game.setEventRecords(eventRecordList);
    }

/*    @Test
    public void shouldHaveListOfEvents()
    {
        Assertions.assertThat(eventRecordList != null).isTrue();
        Assertions.assertThat(eventRecordList.size() >0).isTrue();
    }*/

    @Test
    public void testStartEvent() {
        //Given
        initializeGame(false, false);
        EventRecord startEventRecord = new EventRecord();
        startEventRecord.setEventType("START");
        startEventRecord.setTeamName(TEAM_A);
        startEventRecord.setEventMin(0);
        startEventRecord.setEventSeconds(0);
        // validate
        validateTeamAHasPossession(startEventRecord);
    }

    @Test
    public void testPossessEvent() {
        //Given
        initializeGame(false, true);
        EventRecord possessEventRecord = new EventRecord();
        possessEventRecord.setEventType("POSSESS");
        possessEventRecord.setTeamName(TEAM_A);
        possessEventRecord.setEventMin(0);
        possessEventRecord.setEventSeconds(0);
        //validate
        validateTeamAHasPossession(possessEventRecord);
    }

    private void validateTeamAHasPossession(EventRecord eventRecord) {

        //When
        Event event = eventFactoryService.populateEvent(eventRecord);
        event.executeEvent(game, eventRecord);


        //Then
        Team teamA = game.getTeams().stream().filter(e -> "A".equals(e.getTeamName())).findFirst().get();
        Team teamB = game.getTeams().stream().filter(e -> "B".equals(e.getTeamName())).findFirst().get();
        Assertions.assertThat(teamA.getHasPossession()).isTrue();
        Assertions.assertThat(teamB.getHasPossession()).isFalse();
    }

    @Test
    public void testBreakOrEndEvent() {
        //Given
        initializeGame(true, false);
        EventRecord breakEventRecord = new EventRecord();
        breakEventRecord.setEventType("BREAK");
        breakEventRecord.setEventMin(0);
        breakEventRecord.setEventSeconds(0);

        //When
        Event breakOrEndEvent = eventFactoryService.populateEvent(breakEventRecord);
        breakOrEndEvent.executeEvent(game, breakEventRecord);

        //Then any teams should not have possession
        assertNoTeamHasPossession();

        //Given
        initializeGame(true, false);
        breakEventRecord.setEventType("END");

        //When
        breakOrEndEvent = eventFactoryService.populateEvent(breakEventRecord);
        breakOrEndEvent.executeEvent(game, breakEventRecord);
        //Then any teams should not have possession
        assertNoTeamHasPossession();
    }

    private void assertNoTeamHasPossession() {
        Team teamA = game.getTeams().stream().filter(e -> "A".equals(e.getTeamName())).findFirst().get();
        Team teamB = game.getTeams().stream().filter(e -> "B".equals(e.getTeamName())).findFirst().get();
        Assertions.assertThat(teamA.getHasPossession()).isFalse();
        Assertions.assertThat(teamB.getHasPossession()).isFalse();
    }


    @Test
    public void testScoreEvent() {
        //Given
        initializeGame(true, false);
        EventRecord scoreEventRecord = new EventRecord();
        scoreEventRecord.setEventType("SCORE");
        scoreEventRecord.setTeamName(TEAM_A);

        //When
        Event scoreEvent = eventFactoryService.populateEvent(scoreEventRecord);
        scoreEvent.executeEvent(game, scoreEventRecord);

        //Then it should increment number of shots of Team A
        Team teamA = game.getTeams().stream().filter(e -> "A".equals(e.getTeamName())).findFirst().get();
        Assertions.assertThat(teamA.getScore() == 1).isTrue();

    }

    @Test
    public void testShotEvent() {
        //Given
        initializeGame(true, false);
        EventRecord scoreEventRecord = new EventRecord();
        scoreEventRecord.setEventType("SHOT");
        scoreEventRecord.setTeamName(TEAM_A);

        //When
        Event shotEvent = eventFactoryService.populateEvent(scoreEventRecord);
        shotEvent.executeEvent(game, scoreEventRecord);

        //Then
        Team teamA = game.getTeams().stream().filter(e -> "A".equals(e.getTeamName())).findFirst().get();
        Assertions.assertThat(teamA.getNumberOfShots() == 1).isTrue();

    }


    @Test
    public void testDefaultEvent() {
        //Given
        initializeGame(false, true);
        EventRecord scoreEventRecord = new EventRecord();
        scoreEventRecord.setEventType("Invalid Event");
        scoreEventRecord.setTeamName(TEAM_A);

        //When
        Event defaultEvent = eventFactoryService.populateEvent(scoreEventRecord);
        defaultEvent.executeEvent(game, scoreEventRecord);

        //Then
        Team teamA = game.getTeams().stream().filter(e -> "A".equals(e.getTeamName())).findFirst().get();
        Team teamB = game.getTeams().stream().filter(e -> "B".equals(e.getTeamName())).findFirst().get();
        Assertions.assertThat(teamA.getNumberOfShots() == 0).isTrue();
        Assertions.assertThat(teamB.getNumberOfShots() == 0).isTrue();

        Assertions.assertThat(teamA.getScore() == 0).isTrue();
        Assertions.assertThat(teamB.getScore() == 0).isTrue();

        Assertions.assertThat(teamA.getHasPossession()).isFalse();
        Assertions.assertThat(teamB.getHasPossession()).isTrue();

    }


}
