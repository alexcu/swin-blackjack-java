/**
 * @class   Game
 * @author  Alex Cummaudo
 * @date    2014-02-15
 * 
 * Manages the entire game; players, deck and who
 * has won
 */
public class Game
{
    //! Player collection
    private java.util.Vector<Player> _players;
    //! The deck for this game
    private Deck _deck;
    //! The player who is the winner
    private Player _winner;
    //! The current player
    private Player _currentPlayer;
    //! Quit status
    private Boolean _quit;

    /**
     * Constructor for game
     */
    public Game()
    {
        Usrio.log("    --   BLACKJACK in Java!   --");
        Usrio.log("    Written By Alex Cummaudo");
        
        this.setup();

        // Ensure false quit
        _quit = false;
    }

    /**
     * Sets up a new game
     */
    public void setup()
    {
        _winner = null;
        _deck = new Deck();

        // Init new players
        _players = new java.util.Vector<Player>();

        // Add the players
        _players.add(new Player(_deck, true));
        _players.add(new Player(_deck, false));

        // Set current player
        _currentPlayer = _players.get(0);
    }

    /**
     * Getter for quit status
     * @return  [Boolean]   _quit
     */
    public Boolean isQuit()
    {
        return _quit;
    }

    /**
     * Prints the menu for the player
     */
    private void menu()
    {
        // Draw the actual menu
        Usrio.log("What do you want to do?");
        Usrio.log("[D]raw");
        Usrio.log("[S]tay");
        Usrio.log("[L]ook at hand");
        Usrio.log("[Q]uit");

        char input = Usrio.prompt("[?]").charAt(0);
        switch (input)
        {
            case 'q':
            case 'Q':
            {
                _quit = true;
                break;
            }
            case 'd':
            case 'D':
            {
                Card drawCard = _deck.draw();
                _currentPlayer.getHand().add(drawCard);

                Usrio.log("You drew a card:");
                
                // The last card they drew
                Usrio.log(drawCard.toString());
                break;
            }
            case 'l':
            case 'L':
            {
                _currentPlayer.getHand().print();
                break;
            }
            case 's':
            case 'S':
            {
                _currentPlayer.stay();
                break;
            }
            case ']':
            {
                _deck.print();
                break;
            }
        }
    }

    /**
     * CPU turn calculation
     */
    private void cpuTurn(Player p)
    {
        // New randomiser
        java.util.Random rand = new java.util.Random(); 

        // 25% chance that CPU won't cheat and look at the
        // next card in the deck
        if (rand.nextInt(100) < 75)
        {
            // While CPU's hand plus the next card's rank <= 21
            while (p.getHand().value() + _deck.topCard().rankValue() <= 21)
            {
                // Deal another card
                p.getHand().add(_deck.draw());
            }
        }
        // Otherwise just deal random 3 times (chance of losing)
        else
        {
           for (int i = rand.nextInt(3); i > 0; i--)
               p.getHand().add(_deck.draw());
        }
        p.stay();
    }

    /**
     * Executes primary actions for the game
     */
    public void execute()
    {
        if (_winner == null)
        {
            Usrio.log("Next round...");
            this.performRound();
        }
        else
        {
            Usrio.log("End of round...");
            this.endRound();
        }
    }


    /**
     * Determines the winner of the players who has the best suit
     * @return  [Player]    The winning player
     */
    private Player winnerByBestSuit()
    {
        Player winner = null;
        int bestSuitVal = 0;

        for (Player p : _players)
        {
            int pBestSuitVal = p.getHand().bestSuit().toInt();
            if (pBestSuitVal > bestSuitVal)
            {
                // Winner is this player with the best suit
                winner = p;
            }
        }

        return winner;
    }

    /**
     * Determines the winner of the players who has the smallest hand
     * @return  [Player]    The winning player
     */
    private Player winnerBySmallestHand()
    {
        Player winner = null;
        int biggestHand = 0;

        for (Player p : _players)
        {
            int pHandSize = p.getHand().size();

            // There's a draw? Return winnerBySmallestHand instead
            if (biggestHand == pHandSize) 
            { 
                Usrio.log("Draw in hand size! Determining winner by best suit..."); 
                return this.winnerByBestSuit(); 
            }
            // Set highest hand to pHandSize if higher than highest hand
            // and player is not bust
            else if (pHandSize > biggestHand)
            {
                biggestHand = pHandSize;
                winner = p;
            }
        }

        // Return the winner
        return winner;
    }


    /**
     * Determines the winner by the best rank value 
     * @return  [Player]    The winning player
     */
    private Player winnerByBestRank()
    {
        int highestHand = 0;
        Player winner = null;
        for (Player p : _players)
        {
            int pHandVal = p.getHand().value();
            
            // There's a draw? Return winnerBySmallestHand instead
            if (highestHand == pHandVal) 
            {
                Usrio.log("Draw in ranks! Determining winner by smallest hand..."); 
                return this.winnerBySmallestHand(); 
            }
            // Set highest hand to pHandVal if higher than highest hand
            // and player is not bust
            else if (pHandVal > highestHand && !p.getHand().isBust())
            {
                highestHand = pHandVal;
                winner = p;
            }
        }

        // Return the winner
        return winner;
    }

    /**
     * Performs a round
     */
    private void performRound()
    {
        // If human, then print a menu
        if (_currentPlayer.isHuman())
            menu();
        // Else, CPU turn
        else cpuTurn(_currentPlayer);

        // Determine if this player is bust
        Boolean busted = _currentPlayer.getHand().isBust();
        
        // Determine if all players are staying
        Boolean allStaying = false;
        for (Player p : _players)
            if (p.isStaying()) allStaying = true;
            else allStaying = false;

        // Break if *all* players are staying or
        // if we have a bust on the current player
        if (allStaying || busted)
        {
            // Determine the winner (1st by best rank)
            _winner = this.winnerByBestRank();
            // Break
            return;
        }
        
        // Move onto the next player if a bust has occurred or
        // if the current player is staying
        if (_currentPlayer.isStaying() || busted)
        {
            int nextPlayerIdx = _players.indexOf(_currentPlayer) + 1;
            if (nextPlayerIdx > _players.size())
                _currentPlayer = _players.get(0);
            else
                _currentPlayer = _players.get(nextPlayerIdx);
        }
    }

    /**
     * Ends a round
     */
    private void endRound()
    {
        if (!_winner.isHuman())
        {
            Usrio.log("* * * * * * LOSER! * * * * * *");
            Usrio.log("Dealer has won the game with the hand:");
            _winner.getHand().print();
            
            Usrio.log("Your hand was:");
            _players.get(0).getHand().print();
        }
        else
        {
            Usrio.log("* * * * * * WINNER! * * * * * *");
            
            Usrio.log("The dealer had the following hand:");
            _players.get(1).getHand().print();

            Usrio.log("Your hand was:");
            _players.get(0).getHand().print();
        }

        Usrio.log("======================");
        Usrio.log(" Starting a new round ");
        Usrio.log("======================");

        // Setup a new game
        this.setup();

    }
}