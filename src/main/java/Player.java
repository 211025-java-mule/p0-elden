import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties (ignoreUnknown = true)
public class Player {
    @JsonProperty("account_id")
    private int accountID;
    @JsonProperty("player_slot")
    private int playerSlot;
    @JsonProperty("hero_id")
    private String id;
    @JsonProperty("item_0")
    private int item0;
    @JsonProperty("item_1")
    private int item1;
    @JsonProperty("item_2")
    private int item2;
    @JsonProperty("item_3")
    private int item3;
    @JsonProperty("item_4")
    private int item4;
    @JsonProperty("item_5")
    private int item5;
    private int kills;
    private int deaths;
    private int assists;
    @JsonProperty("last_hits")
    private int lastHits;
    private int denies;
    private int level;
    @JsonProperty("net_worth")
    private int networth;

    public int getAccount(){ return accountID; }
    public int getSlot(){ return playerSlot; }
    public String getID(){ return id; }
    public int getItem0(){ return item0; }
    public int getItem1(){ return item1; }
    public int getItem2(){ return item2; }
    public int getItem3(){ return item3; }
    public int getItem4(){ return item4; }
    public int getItem5(){ return item5; }
    public int getKills(){ return kills; }
    public int getDeaths(){ return deaths; }
    public int getAssists(){ return assists; }
    public int getLastHits(){ return lastHits; }
    public int getDenies(){ return denies; }
    public int getLevel(){ return level; }
    public int getNetworth(){ return networth; }

}

