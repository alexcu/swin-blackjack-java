/**
 * @class   Card
 * @author  Alex Cummaudo
 * @date    2014-02-15
 * 
 * Card class to contain card-specific
 * information
 */
public class Card
{
    private Rank _rank;
    private Suit _suit;

    /**
     * Card Constructor
     * @param   r   Rank to initialise this card with
     * @param   s   Suit to initialise this card with
     */
    public Card (Rank.RankType r, Suit.SuitType s)
    {
        // Declare rank and suit
        _rank = new Rank(r);
        _suit = new Suit(s);
    }

    /**
     * Returns the worth of my rank
     * @return  [int]       The worth of this card's rank
     */
    public int rankValue()
    {
        return _rank.toInt();
    }

    /**
     * Returns the worth of my suit
     * @return  [int]       The worth of this card's suit
     */
    public int suitValue()
    {
        return _suit.toInt();
    }    

    /**
     * Prints a description out of this card
     * @return  [String]    The suit and rank of this card
     */
    public String toString()
    {
        return _rank.toString() + _suit.toString();
    }

    /**
     * Getter for rank
     * @return  [Rank]  _hand
     */
    public Rank getRank()
    {
        return _rank;
    }

    /**
     * Getter for suit
     * @return  [Suit]  _suit
     */
    public Suit getSuit()
    {
        return _suit;
    }
}