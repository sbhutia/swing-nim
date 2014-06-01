
/**
 * Abstract class ComputerPlayer - this class is the parent class of the NaivePlayer or SmartPlayer
 * which defines the function which must be implemented by the child classes
 * @author Sonam Bhutia
 * @version 1
 */
public abstract class ComputerPlayer
{
    /**
     *This is an abstract method which makes the next move depending on the size of the pile
     * and the maximum number of pieces which can be removed at a time
     * @param  pileSize    the current size of the pile
     * @param   maxSize the maximum number of pieces which can be removed
     * @return  the number of pieces removed by the computer player 
     */
    public abstract int makeMove(int pileSize, int maxSize);
}
