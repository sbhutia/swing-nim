import java.util.Random;
/**
 * This class is used to model a game of Single Pile Nim
 * @author Sonam bhutia 
 * @version 1
 */
public class NimGame
{
    private int pileSize;
    private int maxMove;
    private ComputerPlayer computerPlayer;
    private Random random;

    /**
     * The constructor for NimGame which takes the max move input by the user and creates a new game. A random pile size is generated between 10 and 90 and a random
     * computer player is chosen from among a naive or smart player
     * @param maxMove   The maximum number of pieces that a player can take out in a single move
     */
    public NimGame(int maxMove)
    {
        random = new Random();
        do
        {
            this.pileSize = random.nextInt(91)+10;
        }while(this.pileSize<maxMove);
        this.maxMove = maxMove;
        Random random = new Random();
        if(random.nextBoolean())
        {
            computerPlayer = new NaivePlayer();
        }else
        {
            computerPlayer = new SmartPlayer();
        }
    }
    
    /**
     * This method returns the current pile size
     * @return  the current number of pieces remaining in the game size of the game
     */
    
    public int getPileSize()
    {
        return this.pileSize;
    }
    
    /**
     * This method returns the maximum move that a user can make
     * @return  the maximum number of pieces that a player can take out
     */
    public int getMaxMove()
    {
        return this.maxMove;
    }
    
    /**
     * This method returns the computer player which has been chosen for this game
     * @return  The computer player which can either be a smart or naive player
     */
    public ComputerPlayer getComputerPlayer()
    {
        return this.computerPlayer;
    }

    /**
     * This method is used to make the move by the human player
     * @param  pieces   the number of pieces to take out 
     */
    public void nextPlayerMove(int pieces)
    {
        if(pieces <= pileSize && pieces <= maxMove && pieces > 0)
        {
            pileSize = pileSize - pieces;
        }
        
    }
    
    /**
     * This method is used to make the next computer move which will depend on the type of computer player
     * @return  the number of pieces taken out by the computer player
     */
    public int nextComputerMove()
    {

        if(pileSize == 0)
        {
            return 0;
        }
        int computerMove = computerPlayer.makeMove(pileSize, maxMove);
        pileSize = pileSize - computerMove;
        return computerMove;

    }
}
