
/**
 * This class models a smart computer player which will make the movie based on an algorithm
 * @author Sonam Bhutia
 * @version 1
 */
public class SmartPlayer extends ComputerPlayer
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
        int val = pileSize % (maxSize + 1);
        return val>0 ? val : 1;
    }
}
