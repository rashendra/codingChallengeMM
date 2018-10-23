package au.com.mm.codingChallenge.domain;

public class Team {

    private Long teamId;

    private String teamName;

    private Integer possession;

    private Integer processionStartTime;

    private Integer numberOfShots;

    private Integer score;

    private Boolean hasPossession;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getPossession() {
        return possession;
    }

    public void setPossession(Integer possession) {
        this.possession = possession;
    }

    public void addpossession(Integer possession) {
        if(this.possession == null) {
            this.possession = 0;
        }
        this.possession = this.possession +possession;
    }

    public Integer getNumberOfShots() {
        return numberOfShots;
    }

    public void setNumberOfShots(Integer numberOfShots) {
        this.numberOfShots = numberOfShots;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Boolean getHasPossession() {
        return hasPossession;
    }

    public void setHasPossession(Boolean hasPossession) {
        this.hasPossession = hasPossession;
    }

    public Integer getProcessionStartTime() {
        return processionStartTime;
    }

    public void setProcessionStartTime(Integer processionStartTime) {
        this.processionStartTime = processionStartTime;
    }

    public void addScore(){
        if(this.score == null) {
            this.score = 0;
        }
        this.score++;
    }

    public void addnumberOfShot(){
        if(this.numberOfShots == null) {
            this.numberOfShots = 0;
        }
        this.numberOfShots++;
    }
}
