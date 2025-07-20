package card;

import entity.Entity;


/**
 *
 * @author LUCA
 *
 *         This class simulates a Card
 */
public class Card extends Entity {

    public static final String CARD_BACK = "cardBack";
    private int num;
    private int palo;

  

    public Card(int num, int palo) {
        this.num = num;
        this.palo = palo;
    }

    public int getNum() {
        return num;
    }

    public int getPalo() {
        return palo;
    }


    public String getPathName() {
        String toRet = "";

        switch (palo) {
            case 0:
                toRet += "espada";
                break;
            case 1:
                toRet += "basto";
                break;
            case 2:
                toRet += "oro";
                break;
            case 3:
                toRet += "copa";
                break;
            default:
                return "cardBack";

        }

        toRet += String.valueOf(num);

        return toRet;
    }

    public String toString() {
        return getPathName();
    }


    @Override
    public boolean equals(Object obj) {

        Card other = (Card) obj;
        return this.num == other.getNum() && this.palo == other.getPalo();

    }

}