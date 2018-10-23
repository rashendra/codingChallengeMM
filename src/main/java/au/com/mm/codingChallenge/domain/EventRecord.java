package au.com.mm.codingChallenge.domain;

public class EventRecord {

    private String eventType;
    private String teamName;
    private Integer eventMin;
    private Integer eventSeconds;
    private Integer eventHour;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getEventMin() {
        return eventMin;
    }

    public void setEventMin(Integer eventMin) {
        this.eventMin = eventMin;
    }

    public Integer getEventSeconds() {
        return eventSeconds;
    }

    public void setEventSeconds(Integer eventSeconds) {
        this.eventSeconds = eventSeconds;
    }

    public Integer getEventHour() {
        return eventHour;
    }

    public void setEventHour(Integer eventHour) {
        this.eventHour = eventHour;
    }
}
