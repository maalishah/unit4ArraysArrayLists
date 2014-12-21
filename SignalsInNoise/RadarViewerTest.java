

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RadarViewerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RadarViewerTest
{
    /**
     * Default constructor for test class RadarViewerTest
     */
    public RadarViewerTest()
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
    }

    /**
     * Tests the if the change in x and the change y works
     */
    @Test
    public void testOutput()
    {
        final int ROWS = 100;
        final int COLS = 100;
        
        Radar radar = new Radar(ROWS, COLS, 1, 4);
        radar.setNoiseFraction(0.02);
        radar.scan();
        
        for(int i = 0; i < 300; i++)
        {
            radar.scan();
        }
        
        int[] slopes = radar.getDxDy();
        assertEquals(1, slopes[0]);
        assertEquals(4, slopes[1]);
    }
    
    /**
     * Tests the if the change in x and the change y works
     */
    @Test
    public void testOutput2()
    {
        final int ROWS = 100;
        final int COLS = 100;
        
        Radar radar = new Radar(ROWS, COLS, 3, -2);
        radar.setNoiseFraction(0.02);
        radar.scan();
        
        for(int i = 0; i < 300; i++)
        {
            radar.scan();
        }
        
        int[] slopes = radar.getDxDy();
        assertEquals(3, slopes[0]);
        assertEquals(-2, slopes[1]);
    }
    
}
