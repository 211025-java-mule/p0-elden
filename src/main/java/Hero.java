import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown=true) 
public class Hero {
    private String id;
    @JsonProperty("localized_name")
    private String name;
    @JsonProperty("primary_attr")
    private String attribute;
    @JsonProperty("attack_type")
    private String attackType;
    private String[] roles;
    @JsonProperty("base_health")
    private int health;
    @JsonProperty("base_health_regen")
    private int healthRegen;
    @JsonProperty("base_mana")
    private int mana;
    @JsonProperty("base_mana_regen")
    private int manaRegen;
    @JsonProperty("base_armor")
    private int armor;
    @JsonProperty("base_mr")
    private int magicResist; 
    @JsonProperty("base_attack_min")
    private int damageMin;
    @JsonProperty("base_attack_max")
    private int damageMax;
    @JsonProperty("base_str")
    private int str;
    @JsonProperty("base_agi")
    private int agi;
    @JsonProperty("base_int")
    private int intelligence;
    @JsonProperty("str_gain")
    private int strGain;
    @JsonProperty("agi_gain")
    private int agiGain;
    @JsonProperty("int_gain")
    private int intGain;
    @JsonProperty("attack_range")
    private int attackRange;
    @JsonProperty("projectile_speed")
    private int projectileSpeed;
    @JsonProperty("attack_rate")
    private int attackRate;
    @JsonProperty("move_speed")
    private int movespeed;
    @JsonProperty("turn_rate")
    private int turnrate;
    @JsonProperty("pro_ban")
    private int proBan;
    @JsonProperty("pro_pick")
    private int proPick;
    @JsonProperty("pro_win")
    private int proWin;

    public String getId(){ return id; }
    public String getName(){ return name; }
    public String getAttribute(){ return attribute; }
    public String getAttackType(){ return attackType; }
    public String[] getRoles(){ return roles; }
    public int getHealth(){ return health; }
    public int getMana(){ return mana; }
    public int getHealthRegen(){ return healthRegen; }
    public int getManaRegen(){ return manaRegen; }
    public int getArmor(){ return armor; }
    public int getMagicResist(){ return magicResist; }
    public int getDamage(){ return (damageMin + damageMax)/2; }
    public int getStr(){ return str; }
    public int getStrGain(){ return strGain; }
    public int getAgi(){ return agi; }
    public int getAgiGain(){ return agiGain; }
    public int getInt(){ return intelligence; }
    public int getIntGain(){ return intGain; }
    public int getRange(){ return attackRange; }
    public int getProjectileSpeed(){ return projectileSpeed; }
    public int getAttackRate(){ return attackRate; }
    public int getSpeed(){ return movespeed; }
    public int getTurnRate(){ return turnrate; }
    public int getBan(){ return proBan; }
    public int getPick(){ return proPick; }
    public int getWin(){ return proWin; }
}
