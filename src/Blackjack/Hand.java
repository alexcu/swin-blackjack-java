/**
 * @class   Hand
 * @author  Alex Cummaudo
 * @date    2014-02-15
 * 
 * Class for handling a single hand that
 * a player has
 */
public class Hand extends CardCollection
{
    /**
     * Constructor for a Hand.
     */
    public Hand(Deck d)
    {
        // Init super
        super();
    }

    /**
     * Adds a card to the hand
     * @param   c   The card to add to the hand
     */
    public void add(Card c)
    {
        _cards.add(c);
    }

    /**
     * Determines the size of this hand
     * @return  [int]   The size of the hand
     */
    public int size()
    {
        return _cards.size();
    }
    
    /**
     * Determines the best suit in this hand
     * @return  [Suit]   The best suit of the hand
     */
    public Suit bestSuit()
    {
        Suit bestSuit = null;
        int bestSuitVal = 0;
        // For each card
        for (Card c : _cards)
        {
            int cSuitVal = c.getSuit().toInt();
            if (cSuitVal > bestSuitVal)
            {
                bestSuitVal = cSuitVal;
                bestSuit = c.getSuit();
            }
        }
        return bestSuit;
    }

    /**
     * Calculates the most optimal value 
     * for this hand (by optimal, uses best
     * ace such that less than 21)
     * @return  [int]   The value 
     */
    public int value()
    {
        int sum = 0;
        // For every card
        for (Card c : _cards)
        {
            // This card is an ace low?
            if (c.getRank().isEqual(Rank.RankType.ACE_LOW))
            {
                // If adding 1 means still less than 21?
                // if (sum + 1 <= 21) { c.getRank().switchAce(); }

                // If adding 11 means still less than 21?
                // Then switch the ace to ace high
                if (sum + 11 <= 21) { c.getRank().switchAce(); }
            }

            // Add to the sum the value for this card
            sum += c.getRank().toInt();
        }
        return sum;
    }

    /**
     * Determines whether or not this hand is bust
     * @return  [Boolean]   True if bust, false otherwise
     */
    public Boolean isBust()
    {
        return this.value() > 21;
    }

    /**
     * Prints the hand to console
     */
    public void print()
    {
        for (int i = 0; i < _cards.size(); i++)
            Usrio.log("[Card #"+java.lang.Integer.toString(i+1)+"] => "+_cards.get(i).toString());
        Usrio.log("Total Value = "+java.lang.Integer.toString(this.value()));
    }
}