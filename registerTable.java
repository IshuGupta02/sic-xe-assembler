package assignment;

import java.util.*;

public class registerTable{

    public static HashMap<String,String> registerMap;
    
    static{
        registerMap = new HashMap<>();
        
        registerMap.put("A", "0");
        registerMap.put("X", "1");
        registerMap.put("L", "2");
        registerMap.put("PC", "8");
        registerMap.put("SW", "9");
        registerMap.put("B", "3");
        registerMap.put("S", "4");
        registerMap.put("T", "5");
        registerMap.put("F", "6");

    }
}
