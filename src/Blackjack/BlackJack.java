/**
 * BlackJack (in Java!)
 *
 * @author  Alex Cummaudo
 * @date    2014-02-15
 */
public class BlackJack
{
    public static void main(String[] args)
    {
        Game g = new Game();
        while (!g.isQuit()) 
        { g.execute(); }
    }
}