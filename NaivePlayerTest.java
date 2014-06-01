

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class for NaivePlayer class
 *
 * @author  Sonam Bhutia
 * @version 1
 */
public class NaivePlayerTest
{
    private NaivePlayer player;
    /**
     * Default constructor for test class NaivePlayerTest
     */
    public NaivePlayerTest()
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
        player = new NaivePlayer();
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
    public void makeMove1()
    {
        int val = player.makeMove(5, 10);
        assertTrue(val <= 5 && val >0);
        
    }

    @Test
    public void makeMove2()
    {
        int val = player.makeMove(10, 5);
        assertTrue(val <= 5 && val >0);
    }
}


