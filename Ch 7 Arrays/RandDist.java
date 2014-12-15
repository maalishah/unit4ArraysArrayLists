import java.util.Scanner;
public class RandDist
{
    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        Scanner in1=new Scanner(System.in);
        System.out.print("How many times do you want to generate?: ");
        int num = in.nextInt();
        System.out.print("Number of values to test: ");
        int num1=in.nextInt();
        double[] nums=new double[num1];
        for(int i=0;i<num;i++)
        {
            double roll=Math.random()*num1;
            int rollnum=(int)roll;
            nums[rollnum]++;
        }
        for(int i=0;i<nums.length;i++)
        {
            System.out.println(i+"  "+nums[i]);
        }
    }
}