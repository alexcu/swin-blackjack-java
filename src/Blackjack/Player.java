/**
 * @class   Player
 * @author  Alex Cummaudo
 * @date    2014-02-15
 * 
 * Contains information about player, their hands
 * and their stance on the game (staying, cpu etc.)
 */
public class Player
{
    //! Defines locality types
    private enum LocalityType { CPU, HUMAN; };
    //! Whether this is a CPU or Human player
    private LocalityType _locality;
    //! This player's current hand
    private Hand _hand;
    //! Player's staying or still playing status
    private Boolean _staying;
    //! The source deck I am being dealt from
    private Deck _srcDeck;

    /**
     * Constructor for this player
     * @param   d   The deck which this player is being dealt from
     * @param   h   Whether or not this is a human (true) or CPU (false)
     */
    public Player(Deck d, Boolean h)
    {
        _srcDeck = d;
        _locality = h ? LocalityType.HUMAN : LocalityType.CPU;
        _staying = false;
        this.newHand();
    }

    /**
     * Constructs a new hand for this player,
     * adding two cards to the hand.
     */
    public void newHand()
    {
        _hand = new Hand(_srcDeck);
        _hand.add(_srcDeck.draw());
        _hand.add(_srcDeck.draw());
    }

    /**
     * Getter for hand
     * @return  [Hand]  _hand
     */
    public Hand getHand()
    {
        return _hand;
    }

    /**
     * Returns human status
     * @return  [Boolean] True if the player is a human, false otherwise
     */
    public Boolean isHuman()
    {
        return _locality == LocalityType.HUMAN;
    }

    /**
     * Returns staying status
     * @return  [Boolean] True if the player is staying, false otherwise
     */
    public Boolean isStaying()
    {
        return _staying;
    }

    /**
     * Sets my staying state to true (i.e. I'm staying)
     */
    public void stay()
    {
        _staying = true;
    }
}