
/**
 * The model for radar scan and accumulator
 * 
 * @author @Mir Alishah (Shah)
 * @version 11 December 2014
 */
public class Radar
{
    
    // stores whether each cell triggered detection for the current scan of the radar
    private boolean[][] currentScan;
    private boolean[][] previousScan;
    
    // value of each cell is incremented for each scan in which that cell triggers detection 
    private int[][] accumulator;
    private int[][] slopes;
    
    // location of the monster
    private int monsterLocationRow;
    private int monsterLocationCol;
    private int dx, dy;
    // probability that a cell will trigger a false detection (must be >= 0 and < 1)
    private double noiseFraction;
    
    // number of scans of the radar since construction
    private int numScans;

    /**
     * Constructor for objects of class Radar
     * 
     * @param   rows    the number of rows in the radar grid
     * @param   cols    the number of columns in the radar grid
     */
    public Radar(int rows, int cols, int dx, int dy)
    {
        // initialize instance variables
        currentScan = new boolean[rows][cols]; // elements will be set to false
        previousScan = new boolean[rows][cols];
        accumulator = new int[rows][cols]; // elements will be set to 0
        this.dx = dx;
        this.dy = dy;
        slopes = new int[11][11];
        
        // randomly set the location of the monster (can be explicity set through the
        //  setMonsterLocation method
        monsterLocationRow = (int)(Math.random() * rows);
        monsterLocationCol = (int)(Math.random() * cols);
        
        noiseFraction = 0.05;
        numScans= 0;
    }
    
    /**
     * Performs a scan of the radar. Noise is injected into the grid and the accumulator is updated.
     * 
     */
    public void scan()
    {
        for (int row = 0; row < currentScan.length; row++)
        {
            for (int col = 0; col < currentScan[0].length; col++)
            {
                previousScan[row][col] = currentScan[row][col];
            }
        }
        
        // zero the current scan grid
        for(int row = 0; row < currentScan.length; row++)
        {
            for(int col = 0; col < currentScan[0].length; col++)
            {
                currentScan[row][col] = false;
            }
        }
        
        // detect the monster
        setMonsterLocation();
        
        // inject noise into the grid
        injectNoise(); 
        
        for(int row = 0; row < currentScan.length; row++)
        {
            for(int col = 0; col < currentScan[0].length; col++)
            {
                if (previousScan[row][col] == true)
                {
                    for (int monsterRow = 0; monsterRow < currentScan.length; monsterRow++)
                    {
                        for (int monsterCol = 0; monsterCol < currentScan[0].length; monsterCol++)
                        {
                            if (currentScan[monsterRow][monsterCol] == true)
                            {
                                if ((Math.abs(monsterRow - row) <= 5) && (Math.abs(monsterCol - col) <= 5))
                                {
                                    int changeX = monsterRow - row;
                                    int changeY = monsterCol - col;
                                    
                                    slopes[changeX + 5][changeY + 5] += 1;
                                }
                            }
                        }
                    }
                }
            }
        }
        // update the accumulator
        for(int row = 0; row < currentScan.length; row++)
        {
            for(int col = 0; col < currentScan[0].length; col++)
            {
                if(currentScan[row][col] == true)
                {
                   accumulator[row][col]++;
                }
            }
        }
        
        // keep track of the total number of scans
        numScans++;
    }

    /**
     * Sets the location of the monster
     * 
     * @param   row     the row in which the monster is located
     * @param   col     the column in which the monster is located
     * @pre row and col must be within the bounds of the radar grid
     */
    public void setMonsterLocation()
    {
        monsterLocationRow += dx;
        monsterLocationCol += dy;
        
        if (monsterLocationRow < 0)
        {
            monsterLocationRow += 99;
        }
        else if (monsterLocationCol < 0)
        {
            monsterLocationCol += 99;
        }
        else if (monsterLocationRow > 99)
        {
            monsterLocationRow -= 99;
        }
        else if (monsterLocationCol > 99)
        {
            monsterLocationCol -= 99;
        }
        
        currentScan[monsterLocationRow][monsterLocationCol] = true;
    }
    
     /**
     * Sets the probability that a given cell will generate a false detection
     * 
     * @param   fraction    the probability that a given cell will generate a flase detection expressed
     *                      as a fraction (must be >= 0 and < 1)
     */
    public void setNoiseFraction(double fraction)
    {
        noiseFraction = fraction;
    }
    
    /**
     * Returns true if the specified location in the radar grid triggered a detection.
     * 
     * @param   row     the row of the location to query for detection
     * @param   col     the column of the location to query for detection
     * @return true if the specified location in the radar grid triggered a detection
     */
    public boolean isDetected(int row, int col)
    {
        return currentScan[row][col];
    }
    
    /**
     * Returns the number of times that the specified location in the radar grid has triggered a
     *  detection since the constructor of the radar object.
     * 
     * @param   row     the row of the location to query for accumulated detections
     * @param   col     the column of the location to query for accumulated detections
     * @return the number of times that the specified location in the radar grid has
     *          triggered a detection since the constructor of the radar object
     */
    public int getAccumulatedDetection(int row, int col)
    {
        return accumulator[row][col];
    }
    
    /**
     * Returns the number of rows in the radar grid
     * 
     * @return the number of rows in the radar grid
     */
    public int getNumRows()
    {
        return currentScan.length;
    }
    
    /**
     * Returns the number of columns in the radar grid
     * 
     * @return the number of columns in the radar grid
     */
    public int getNumCols()
    {
        return currentScan[0].length;
    }
    
    /**
     * Returns the number of scans that have been performed since the radar object was constructed
     * 
     * @return the number of scans that have been performed since the radar object was constructed
     */
    public int getNumScans()
    {
        return numScans;
    }
    
    public int[] getDxDy()
    {
        int[] dxdy = new int[2];
        int max = 0;
        
        for (int i = 0; i < slopes.length; i++)
        {
            for (int j = 0; j < slopes[0].length; j++)
            {
                if (slopes[i][j] > max)
                {
                    max = slopes[i][j];
                    
                    dxdy[0] = i - 5;
                    dxdy[1] = j - 5;
                }
            }
        }
        
        return dxdy;
    }
    
    /**
     * Sets cells as falsely triggering detection based on the specified probability
     * 
     */
    private void injectNoise()
    {
        for(int row = 0; row < currentScan.length; row++)
        {
            for(int col = 0; col < currentScan[0].length; col++)
            {
                // each cell has the specified probablily of being a false positive
                if(Math.random() < noiseFraction)
                {
                    currentScan[row][col] = true;
                }
            }
        }
    }
    
}
