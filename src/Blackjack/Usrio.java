/**
 * @class   User IO
 * @author  Alex Cummaudo
 * @date    2014-02-15
 * 
 * Class for basic user i/o methods
 * that log information in a standard
 * fashion
 */
public class Usrio
{
    /**
     * Logs a message to the console in the
     * standard format
     * @param   msg The message to log
     */
    public static void log(String msg)
    {
        System.out.println("   > "+msg);
    }

    /**
     * Receives input and returns it in the 
     * standard format
     * @param   prompt      Prompt to give when asking input
     * @return  [String]    The string that was inputted by the user
     */
    public static String prompt(String prompt)
    {
        System.out.print("  >> "+prompt+": ");
        
        // Scanner to read from System.in (stdin)
        java.util.Scanner s = new java.util.Scanner(System.in);
        
        // Read line from console
        return s.nextLine();

        /*
        // Try to convert the string to an integer if an integer
        // was inputted
        try
        {
            int inputInt = java.lang.Integer.parseInt(nextIntString);
            // Return the integer input (exception would
            // be thrown by now)
            return inputInt;
        }
        // No number inputted?
        catch (NumberFormatException e)
        {
            // Return the string input
            return input;
        }*/
    }
}