package au.com.mm.codingChallenge.domain;

import au.com.mm.codingChallenge.utils.GameConstants;
import au.com.mm.codingChallenge.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Team> teams = new ArrayList<>();

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public void addTeam(Team team) {
        this.teams.add(team);
    }

    public void printSummary(Integer timestampMin, Integer timestampSec) {

        System.out.println("TimeStamp, Team, Possession, Shot, Score");
        Integer timestampInSeconds = Utils.getTimestampInSeconds(timestampMin,timestampSec);
        for(Team team: teams) {
            StringBuffer teamSummarySB = new StringBuffer();
            teamSummarySB.append(timestampMin+":"+timestampSec);
            teamSummarySB.append(GameConstants.COMMA_SEPARATOR);
            teamSummarySB.append(team.getTeamName());
            teamSummarySB.append(GameConstants.COMMA_SEPARATOR);
            teamSummarySB.append(Utils.calculatePossessionPercentage(team.getPossession(),timestampInSeconds));
            teamSummarySB.append(GameConstants.COMMA_SEPARATOR);
            teamSummarySB.append(team.getNumberOfShots());
            teamSummarySB.append(GameConstants.COMMA_SEPARATOR);
            teamSummarySB.append(team.getScore());
            System.out.println(teamSummarySB.toString());

        }
        System.out.println("Enter another timestamp in mm:ss format");

    }


}
