package CardGame;

import java.util.HashMap;
import java.util.Map;

public enum CardRank {
    TWO(2,"Two"),
    THREE(3, "Three"),
    FOUR(4, "Four"),
    FIVE(5, "Five"),
    SIX(6,"Six"),
    SEVEN(7,"Seven"),
    EIGHT(8,"Eight"),
    NINE(9,"Nine"),
    TEN(10, "Ten"),
    JACK(11, "Jack"),
    QUEEN(12,"Queen"),
    KING(13,"King"),
    ACE(14,"Ace");

    private String camelCase;
    private int rank;
    private static final Map<String,CardRank> lookup = new HashMap<String,CardRank>();

    static {
        for (CardRank rank : CardRank.values()) {
            if (rank.getRank() <= 10) {
                lookup.put(Integer.toString(rank.getRank()), rank);
            } else {
                lookup.put(rank.display().substring(0,1), rank);
            }
        }
    }

    CardRank(int rank,  String camelCase){
        this.rank = rank;
        this.camelCase = camelCase;
    }

    public int getValue(){
        return rank % 10;
    }
    public int getRank(){
        return rank;
    }
    public String display(){
        return camelCase;
    }

    public static CardRank getCardRank(String shortCode){
        return lookup.get(shortCode);
    }
}
