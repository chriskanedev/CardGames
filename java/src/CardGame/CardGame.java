package CardGame;
import Display.ConsoleOutput;
import Display.ConsoleInput;
import Display.Input;
import Display.Output;
import Player.Player;
import Player.PlayerType;

import java.util.ArrayList;

public class CardGame {

    public Integer noOfCards = 2;
    private Deck deck;
    private ArrayList<Player> players;
    private Input userInput;
    private Output userOutput;
    private CardGame game;

    public void setNoOfCards(Integer noOfCards) {
        this.noOfCards = noOfCards;
    }

    public Input getUserInput() {
        return userInput;
    }

    public Output getUserOutput(){
        return this.userOutput;
    }


    public void setUserInput(Input userInput){
        this.userInput = userInput;
    }
    public CardGame getGame(){
        return game;
    }

    public Deck getDeck() { return deck;}

    public ArrayList<Player> getPlayers(){
        return players;
    }

    protected void setGame(CardGame game){
        this.game = game;
    }

    public CardGame(){
        this.deck = Deck.getInstance();
        this.userInput = new ConsoleInput();
        this.userOutput = new ConsoleOutput();
        this.players = new ArrayList<Player>();
    }

    private void createHumanPlayer(){
        userOutput.output("What is your name");
        String name = userInput.getInputString();
        players.add(new Player(PlayerType.USER,name,0));
    }

    private void createComputerPlayers(Integer noOfPlayers) {
        Player dealer = new Player(PlayerType.DEALER,"Dealer",17);
        players.add(dealer);
        players.add(new Player(PlayerType.COMPUTER,"Comp 1",0));
        noOfPlayers -= 1; //Remove the dealer
        for (int counter=2;counter < noOfPlayers;counter++){
            players.add(new Player(PlayerType.COMPUTER,"Comp" + counter,0));
        }
    }

    protected void initiatePlayers(){
        players.clear();
        createHumanPlayer();
        userOutput.output("How many players, minimum of two?");
        int noOfPlayers = userInput.getInputInt();
        createComputerPlayers(noOfPlayers);


    }

    public void dealCards(){
        boolean allCards = false;
        int noOfCards;
        if (this.noOfCards == 0) {
            noOfCards = (int) Math.floor(deck.getNumberOfCards()/players.size());
            allCards = true;
        } else {
            noOfCards = this.noOfCards;
        }
        for (Player player: players){
            Hand hand = new Hand();
            hand = dealHand(hand,noOfCards);
            player.setHand(hand);
        }
        if (allCards){
            for (Player player: players){
                if (deck.getNumberOfCards() > 0){
                    player.getHand().add(deck.playACard());
                }
            }
        }
    }

    public Hand dealHand(Hand hand, int noOfCards){

        for (int cardCounter=0;cardCounter<noOfCards;cardCounter++){
            if (deck.getNumberOfCards() > 0) {
                hand.add(deck.playACard());
            }
        }
        return hand;
    }


    public void initiate(){
        initiatePlayers();
        deck.shuffleDeck();
        dealCards();
    }

    public void play(){
        initiate();
        Integer counterOfPlayers = 0;
        while (!playerHasWon(players.get(counterOfPlayers))){
            playerPlaysHand(players.get(counterOfPlayers));
            counterOfPlayers = (counterOfPlayers + 1) % players.size();
        }

    }

    public int getScore(Hand hand){
        return 0;
    }

    public void playerPlaysHand(Player player){

    }

    public boolean playerHasWon(Player player){
        boolean winner = true;
        return winner;
    }

    public void showPlayers(){
        for (Player player: players){
            userOutput.output(player.getName());
            userOutput.output(player.getHand().show());

        }
    }

    public static void main(String[ ] args) {
        CardGame cardGame = new CardGame();
        cardGame.play();
        cardGame.showPlayers();


    }
}
