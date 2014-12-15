public class ArrayOperations2
{
    private int[] values;
    public ArrayOperations2(int[] initialValues)
    {
        values = initialValues;
    }
    public void swapFirstAndLast()
    {
        int end = values.length - 1;
        int tempVar = values[0];
        values[0] = values[end];
        values[end] = tempVar;
    }
    public void shiftRight()
    {
        int end = values.length - 1;
        for (int i=0; i <
    }
}
