import com.fasterxml.jackson.annotation.*;

public class Item {
    private String id;
    private String name;
    private int cost;
    @JsonProperty("secret_shop")
    private int secretShop;
    @JsonProperty("side_shop")
    private int sideShop;
    private int recipe;

    public String getId(){ return id; }
    public String getName(){ return name; }
    public int getCost(){ return cost; }
    public int getSecret(){ return secretShop; }
    public int getSide(){ return sideShop; }
    public int getRecipe(){ return recipe; }
}
