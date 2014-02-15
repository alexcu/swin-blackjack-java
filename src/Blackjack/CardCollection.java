/**
 * @class   CardCollection
 * @author  Alex Cummaudo
 * @date    2014-02-15
 * 
 * An abstract class for any collection
 * of cards (implemented in Hand and Deck)
 */
public abstract class CardCollection
{
    //! Collection of cards (use generics to specify type of Vector)
    protected java.util.Vector<Card> _cards;

    public CardCollection()
    {
        // Initialise the Cards
        _cards = new java.util.Vector<Card>();
    }

    /**
     * Calculates net worth of ranks of the cards
     * this collection
     * @param   [int]   Sum of the rank worth
     */
    public int collectionValue()
    {
        int sum = 0;
        for (Card c : _cards)
            sum += c.rankValue();
        return sum;
    }

    /**
     * Prints a deck
     */
    abstract public void print();
}