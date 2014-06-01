import java.util.Random;
/**
 * The class naive player is used to model a player who makes a move based on a random number
 * @author Sonam Bhutia 
 * @version 1
 */
public class NaivePlayer extends ComputerPlayer
{
    /**
     * This method for the naive player will return a random valid number which is the number of pieces
     * to take out from the pile
     * @param  pileSize    the current size of the pile
     * @param   maxSize the maximum number of pieces which can be removed
     * @return  the number of pieces removed by the computer player 
     */
    public int makeMove(int pileSize, int maxSize)
    {
        Random random = new Random();
        int validSize = pileSize < maxSize ? pileSize: maxSize;
        return random.nextInt(validSize) + 1;
    }
}
