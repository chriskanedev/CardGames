package Display;

import Display.Output;
import CardGame.Hand;

import java.util.ArrayList;

public class ConsoleOutput implements Output {
    public void output(String display){
        System.out.println(display);
    }
    public void output(int number){
        System.out.println(number);
    }

    public void output(ArrayList<String> displayOutput){
        for (String display : displayOutput){
            output(display);
        }
    }
    public void outputHand(Hand hand){
        String prefix = "";
        String display = "";
        for (CardGame.Card card : hand.getHandOfCards()){
            display += prefix + card.display();
            prefix = ", ";
        }
        output(display);
    }
    public void outputHands(ArrayList<Hand> hands){
        for (Hand hand : hands){
            output(hand.show());
        }
    }
}
