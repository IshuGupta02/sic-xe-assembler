package assignment;

import java.util.*;
  
public class hexConversion{

    public static String convertByteToHexadecimal(String s)
    {
        byte[] byteArray= s.getBytes();
        String hex = "";
  
        // Iterating through each byte in the array
        for (byte i : byteArray) {
            hex += String.format("%02X", i);
        }
  
        return hex;
    }
  
}
