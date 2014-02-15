/**
 * @class   Rank
 * @author  Alex Cummaudo
 * @date    2014-02-15
 * 
 * Contains details about ranks
 */
public class Rank
{
    //! Internal rank held.
    private RankType _rank;

    /**
     * @enum    RankType
     *
     * Enumeration for the different types of 
     * ranks
     */
    public static enum RankType
    {
        ACE_LOW,
        ACE_HIGH,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING;
    }
    
    /**
     * Constructor for Rank
     * @param   r   RankType for this rank
     */
    public Rank(RankType r)
    {
        _rank = r;
    }
    
    /**
     * @method  toString
     *
     * Converts my _rank to a printable String
     */
    public String toString()
    {
        switch (_rank)
        {
            case ACE_LOW:   return " A";
            case ACE_HIGH:  return " A";
            case TWO:       return " 2";
            case THREE:     return " 3";
            case FOUR:      return " 4";
            case FIVE:      return " 5";
            case SIX:       return " 6";
            case SEVEN:     return " 7";
            case EIGHT:     return " 8";
            case NINE:      return " 9";
            case TEN:       return "10";
            case JACK:      return " J";
            case QUEEN:     return " Q";
            case KING:      return " K";
            default:        return " ?";
        }
    }

    /**
     * @method  toInt
     * 
     * Converts my _rank to a usable integer
     */
    public int toInt()
    {
        switch (_rank)
        {
            case ACE_LOW:   return 1;
            case ACE_HIGH:  return 11;
            case TWO:       return 2;
            case THREE:     return 3;
            case FOUR:      return 4;
            case FIVE:      return 5;
            case SIX:       return 6;
            case SEVEN:     return 7;
            case EIGHT:     return 8;
            case NINE:      return 9;
            case TEN:       return 10;
            case JACK:      return 10;
            case QUEEN:     return 10;
            case KING:      return 10;
            default:        return -1;      
        }
    }

    /**
     * @method  SwitchAce
     *
     * Swaps an ACE_LOW to an ACE_HIGH and
     * vice-versa
     */
    public void switchAce()
    {
        // If ACE_LOW, then make ACE_HIGH
        // and vice-versa

        // @learning the enum has its own namespace (RankType)
        if (_rank == RankType.ACE_LOW) _rank = RankType.ACE_HIGH;
        else if (_rank == RankType.ACE_HIGH) _rank = RankType.ACE_LOW;
        else throw new IllegalArgumentException("Cannot switch an Ace that is not an Ace");
    }

    /**
     * Compares a rank with my rank to see if they're
     * the same.
     * @param   r           The rank to compare me to
     * @return  [Boolean]   True if the ranks are the same, false otherwise
     */
    public Boolean isEqual(Rank.RankType r)
    {
        return _rank == r;
    }

}