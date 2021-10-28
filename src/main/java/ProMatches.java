import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProMatches {
    @JsonProperty("match_id")
    private String matchID;
    private int duration; // in seconds
    @JsonProperty("start_time")
    private long startTime; // UNIX epoch time
    @JsonProperty("radiant_team_id")
    private String radiantID;
    @JsonProperty("radiant_name")
    private String radiantName;
    @JsonProperty("dire_team_id")
    private String direID;
    @JsonProperty("dire_name")
    private String direName;
    @JsonProperty("leagueid")
    private String leagueID;
    @JsonProperty("league_name")
    private String leagueName;

    public String getMatchID(){ return matchID; }
    public int getDuration(){ return duration; }
    public long getStartTime(){ return startTime; }
    public String getRadiantID(){ return radiantID; }
    public String getDireID(){ return direID; }
    public String getRadiantName(){ return radiantName; }
    public String getDireName(){ return direName; }
    public String getLeagueID(){ return leagueID; }
    public String getLeagueName(){ return leagueName; }
}
