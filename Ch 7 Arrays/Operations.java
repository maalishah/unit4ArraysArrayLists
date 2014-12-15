public class Operations
{
    public static void main(String[] args)
    {
        double[] x=new double[9];
        x[0]+=8.0;x[1]+=4.0;x[2]+=5.0;x[3]+=21.0;x[4]+=7.0;x[5]+=9.0;x[6]+=18.0;x[7]+=2.0;x[8]+=100.0;
        System.out.println("There are "+x.length+" elements in this list.");
        System.out.println(x[0]);
        System.out.println(x[x.length-1]);
        for(int i=0;i<x.length;i++)
        {
            System.out.println(x[i]);
        }
        for(int i=0;i<30;i++)
        {
            System.out.print("-");
        }
        System.out.println("");
        for(int i=0;i<x.length;i++)
        {
            System.out.println("Index: "+i+" Value: "+x[i]);
        }
        for(int i=0;i<30;i++)
        {
            System.out.print("-");
        }
        System.out.println("");
        for(int i=x.length-1;i>=0;i--)
        {
            System.out.println("Index: "+i+" Value: "+x[i]);
        }
    }
}