/**
 * @class   Suit
 * @author  Alex Cummaudo
 * @date    2014-02-15
 * 
 * Contains information about suits
 */
public class Suit
{
    //! Internal rank held.
    private SuitType _suit;
    
    /**
     * The various possible suit types
     */
    public static enum SuitType
    {
        CLUBS,
        HEARTS,
        DIAMONDS,
        SPADES
    }
    
    /**
     * Constructor for Suit
     * @param   s   Suit to construct this suit with
     */
    public Suit(SuitType s)
    {
        _suit = s;
    }

    /**
     * @method  ToString
     *
     * Converts my _suit to a printable String
     */
    public String toString()
    {
        switch (_suit)
        {
            case CLUBS:     return "C";
            case HEARTS:    return "H";
            case DIAMONDS:  return "D";
            case SPADES:    return "S";
            default:        return "?";
        }
    }
    
    /**
     * @method  ToInt
     * Converts my _suit to an arithmetical Integer
     */
    public int toInt()
    {
        switch (_suit)
        {
            case CLUBS:     return 3;
            case HEARTS:    return 2;
            case DIAMONDS:  return 1;
            case SPADES:    return 4;
            default:        return 0;
        }
    }
}