package com.github.pbbz.dota;
import com.fasterxml.jackson.annotation.*;
import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    private List<Player> players;
    @JsonProperty("radiant_win")
    private boolean radiantWin;
    @JsonProperty("match_id")
    private String matchid;
    @JsonProperty("start_time")
    private long startTime;
    private int duration;
    @JsonProperty("radiant_score")
    private int radiantScore;
    @JsonProperty("dire_score")
    private int direScore;
    @JsonProperty("radiant_name")
    private String radiantName;
    @JsonProperty("dire_name")
    private String direName;
    
    // To Add Picks/Bans

    public List<Player> getPlayers(){ return players; }
    public boolean getRadiantWinner(){ return radiantWin; }
    public String getMatchID(){ return matchid; }
    public long getstartTime(){ return startTime; }
    public int getDuration(){ return duration; }
    public int getScoreRadiant(){ return radiantScore; }
    public int getScoreDire(){ return direScore; }
    public String getRadiantName(){ return radiantName; }
    public String getDireName(){ return direName; }
}