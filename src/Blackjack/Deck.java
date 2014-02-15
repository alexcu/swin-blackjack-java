/**
 * @class   Deck
 * @author  Alex Cummaudo
 * @date    2014-02-15
 * 
 * Manages a whole deck for a round and
 * its cards
 */
public class Deck extends CardCollection
{
    /**
     * Deck Constructor
     */
    public Deck()
    {
        super();

        // Setup temporary suits and decks to work with
        Suit.SuitType theSuit = Suit.SuitType.CLUBS;
        Rank.RankType theRank = Rank.RankType.ACE_LOW;
        
        // Setup iterators
        int rankIterator    = 0;
        int suitIterator    = 0;

        final int DECK_SUIT_SIZE = 13;
        final int DECK_RANK_SIZE = 4;
        
        // Start assigning suits
        for (suitIterator = 0; suitIterator < DECK_RANK_SIZE; suitIterator++)
        {
            // Determine which suit to assign based on suitIterator
            switch (suitIterator)
            {
                case 0: theSuit = Suit.SuitType.CLUBS;    break;
                case 1: theSuit = Suit.SuitType.HEARTS;   break;
                case 2: theSuit = Suit.SuitType.DIAMONDS; break;
                case 3: theSuit = Suit.SuitType.SPADES;   break;
            }
            
            // Now populate the family of that suit
            for (rankIterator = 0; rankIterator < DECK_SUIT_SIZE; ++rankIterator)
            {
                // Determine which rank to assign based on suitIterator
                switch (rankIterator)
                {
                    case 0: theRank  = Rank.RankType.ACE_LOW; break;
                    case 1: theRank  = Rank.RankType.TWO;     break;
                    case 2: theRank  = Rank.RankType.THREE;   break;
                    case 3: theRank  = Rank.RankType.FOUR;    break;
                    case 4: theRank  = Rank.RankType.FIVE;    break;
                    case 5: theRank  = Rank.RankType.SIX;     break;
                    case 6: theRank  = Rank.RankType.SEVEN;   break;
                    case 7: theRank  = Rank.RankType.EIGHT;   break;
                    case 8: theRank  = Rank.RankType.NINE;    break;
                    case 9: theRank  = Rank.RankType.TEN;     break;
                    case 10: theRank = Rank.RankType.JACK;    break;
                    case 11: theRank = Rank.RankType.QUEEN;   break;
                    case 12: theRank = Rank.RankType.KING;    break;
                }
                
                // Add the card to the cards Vector
                _cards.add(new Card(theRank, theSuit));
            }
        }

        // Shuffle the deck
        this.shuffleDeck();
    }

    /**
     * Shuffles the deck by swapping position of the cards
     * inside them
     */
    private void shuffleDeck()
    {
        // New randomiser
        java.util.Random rand = new java.util.Random(); 

        for (int i = 0; i < _cards.size(); i++)
        {
            // Random number between 0 and DECK_SIZE
            int swapPos = rand.nextInt(_cards.size());
            
            // Swap the ith card with the swapPos card
            java.util.Collections.swap(_cards, i, swapPos);
        }
    }

    /**
     * Draws a card out from the _cards Vector
     * @return  [Card]  The card that's been dealt
     */
    public Card draw()
    {
        // Given we have a card
        if (this.topCard() != null)
        {   
            // Return the 0'th card (top card) and shift
            // all cards down one
            return _cards.remove(0);
        }
        else
        {
            return null;
        }
    }

    /**
     * Returns the topmost card (without removing it)
     * @return  [Card]  card;
     */
    public Card topCard()
    {
        // Ensure we can get a card
        if (_cards.size() > 0)
        {
            // Get the top card from the vector _cards
            return _cards.get(0);
        }
        else
        {
            // Log we have no more cards
            Usrio.log("No more cards in the deck!");
            return null;
        }
    }

    /**
     * Prints the deck to the console
     */
    public void print()
    {
        for (int i = 0; i < _cards.size(); i++)
            Usrio.log("[Card #"+java.lang.Integer.toString(i)+"] => "+_cards.get(i).toString());
        Usrio.log("Total Value = "+java.lang.Integer.toString(super.collectionValue()));
    }
}