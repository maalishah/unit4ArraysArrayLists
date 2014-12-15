public class ArrayAlgorithms2D
{
    int[][] table = 
        {
            {1,2,3},
            {4,5,6},
            {7,8,9},
            {10,11,12}
        };

    public ArrayAlgorithms2D()
    {
        // initialise instance variables
    }

    public String toString()
    {
        String str = "";
        //table.length is the number of rows in the table
        
        for(int row = 0; row < table.length; row++)
        {
            //table[row].length is the number of columns in the row
            for (int col = 0; col < table[row].length; col++)
            {
                str += table[row][col] + "\t";
            }
            str += "\n";
        }
        return str;
    }
    public String extractRow(int row)
    {
        String str = "";
//         for(int col = 0; col < table[row].length; col++)
//         {
//             str += table[row][col] + "\t";
//         }
        for (int val : table[row])
        {
            str += val + "\t";
        }
        return str;
    }
    public String extractColumn(int col)
    {
        String str = "";
        for( int row = 0; row < table.length; row++)
        {
            str += table[row][col] + "\t";
        }
        return str;
    }
    public static void main(String[] args)
    {
        ArrayAlgorithms2D table = new ArrayAlgorithms2D();
        System.out.println(table.toString());
        System.out.println(table.extractRow(1));
        System.out.println(table.extractColumn(1));
    }

}
