import java.util.*;

public class check{
    public static void main(String args[]){
        String abc="a23";
        // int a= Integer.parseInt(abc);

        System.out.println(Arrays.toString(abc.split(",")));

        String str1= "T0000001d050000010000691017901ba0131bc0002f200a3b2ff40f102f004f0000";

        String str2= "T0000001D050000010000691017901BA0131BC0002F200A3B2FF40F102F004F0000";

        System.out.println(str2.equals(str1.toUpperCase()));

        // System.out.println(a);

    }
    
}