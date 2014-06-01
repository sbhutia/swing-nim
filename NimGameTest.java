

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class for NimGame class
 *
 * @author  Sonam Bhutia
 * @version 1
 */
public class NimGameTest
{
    private NimGame nimGame1;

    /**
     * Default constructor for test class NimGameTest
     */
    public NimGameTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        nimGame1 = new NimGame(5);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testConstructor()
    {
        NimGame nimGame2 = new NimGame(3);
        assertNotNull(nimGame2);
    }

    @Test
    public void testGetPileSize()
    {
        assertTrue( nimGame1.getPileSize()>=10 && nimGame1.getPileSize()<=100);
    }

    @Test
    public void testGetMaxMove()
    {
        assertEquals(5, nimGame1.getMaxMove());
    }

    @Test
    public void getComputerPlayer()
    {
        assertTrue( nimGame1.getComputerPlayer() instanceof NaivePlayer || nimGame1.getComputerPlayer() instanceof SmartPlayer);
    }

    @Test
    public void testNextPlayerMove()
    {
        int initialPileSize = nimGame1.getPileSize();
        nimGame1.nextPlayerMove(5);
        assertTrue(initialPileSize - 5 == nimGame1.getPileSize());
    }
    
    @Test
    public void testNextPlayerMove2()
    {
        int initialPileSize = nimGame1.getPileSize();
        nimGame1.nextPlayerMove(6);
        assertTrue(initialPileSize == nimGame1.getPileSize());
    }
    
    @Test
    public void testNextPlayerMove3()
    {
        int initialPileSize = nimGame1.getPileSize();
        nimGame1.nextPlayerMove(0);
        assertTrue(initialPileSize == nimGame1.getPileSize());
    }
    
    @Test
    public void testNextComputerMove()
    {
        int initialPileSize = nimGame1.getPileSize();
        int computerMove = nimGame1.nextComputerMove();
        assertTrue(initialPileSize - computerMove == nimGame1.getPileSize());
    }
}





