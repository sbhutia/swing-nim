

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the test class for SmartPlayer class
 *
 * @author  Sonam Bhutia
 * @version 1
 */
public class SmartPlayerTest
{
    private SmartPlayer player;

    /**
     * Default constructor for test class SmartPlayerTest
     */
    public SmartPlayerTest()
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
        player = new SmartPlayer();
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
        assertEquals(1, player.makeMove(6, 5));
    }

    @Test
    public void makeMove2()
    {
        assertEquals(5, player.makeMove(5, 5));
    }

    @Test
    public void makeMove3()
    {
        assertEquals(1, player.makeMove(1, 2));
    }
}



