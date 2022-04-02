package assignment;

import java.util.*;

public class optable{

    public static HashMap<String,opcodeInfo> optableMap;
    
    static{
        optableMap = new HashMap<>();

        // ArrayList<String, Integer> opcodes= new ArrayList<>();    

        optableMap.put("ADD", new opcodeInfo("18", 3));
        optableMap.put("ADDF", new opcodeInfo("58", 3));
        optableMap.put("ADDR", new opcodeInfo("90", 3));
        optableMap.put("AND", new opcodeInfo("40", 3));
        optableMap.put("CLEAR", new opcodeInfo("B4", 3));
        optableMap.put("COMP", new opcodeInfo("28", 3));
        optableMap.put("COMPF", new opcodeInfo("88", 3));
        optableMap.put("COMPR", new opcodeInfo("A0", 3));
        optableMap.put("DIV", new opcodeInfo("24", 3));
        optableMap.put("DIVF",new opcodeInfo("64", 3));
        optableMap.put("DIVR", new opcodeInfo("9C", 3));
        optableMap.put("FIX", new opcodeInfo("C4", 3));
        optableMap.put("FLOAT", new opcodeInfo("C0", 3));
        optableMap.put("HLO", new opcodeInfo("F4", 3));
        optableMap.put("J", new opcodeInfo("3C", 3) );
        optableMap.put("JEQ", new opcodeInfo("30", 3));
        optableMap.put("JGT", new opcodeInfo("34", 3));
        optableMap.put("JLT", new opcodeInfo("38", 3));
        optableMap.put("JSUB", new opcodeInfo("48", 3) );
        optableMap.put("LDA", new opcodeInfo("00", 3));
        optableMap.put("LDB", new opcodeInfo("68", 3));
        optableMap.put("LDCH", new opcodeInfo("50", 3));
        optableMap.put("LDF", new opcodeInfo("70", 3));
        optableMap.put("LDL", new opcodeInfo("08", 3));
        optableMap.put("LDS", new opcodeInfo("6C", 3) );
        optableMap.put("LDT", new opcodeInfo("74", 3));       
        optableMap.put("LDX",  new opcodeInfo("04", 3));
        optableMap.put("LPS", new opcodeInfo("D0", 3));
        optableMap.put("MUL", new opcodeInfo("20", 3));
        optableMap.put("MULF", new opcodeInfo("60", 3));
        optableMap.put("MULR", new opcodeInfo("98", 3));
        optableMap.put("NORM", new opcodeInfo("C8", 3));
        optableMap.put("OR", new opcodeInfo("44", 3));
        optableMap.put("RD",  new opcodeInfo("D8", 3));
        optableMap.put("RMO", new opcodeInfo("AC", 3));
        optableMap.put("RSUB", new opcodeInfo("4C", 3));
        optableMap.put("SHIFT", new opcodeInfo("A4", 3));
        optableMap.put("SHIFTR", new opcodeInfo("A8", 3));
        optableMap.put("SIO", new opcodeInfo("F0", 3));
        optableMap.put("SSK", new opcodeInfo("EC", 3));
        optableMap.put("STA", new opcodeInfo("0C", 3));
        optableMap.put("STB", new opcodeInfo("78", 3));
        optableMap.put("STCH", new opcodeInfo("54", 3));
        optableMap.put("STF", new opcodeInfo("80", 3));
        optableMap.put("STI", new opcodeInfo("D4", 3));
        optableMap.put("STL", new opcodeInfo("14", 3));
        optableMap.put("STS", new opcodeInfo("7C", 3));
        optableMap.put("STSW", new opcodeInfo("E8", 3));
        optableMap.put("STT", new opcodeInfo("84", 3));
        optableMap.put("STX", new opcodeInfo("10", 3));
        optableMap.put("SUB",new opcodeInfo("1C", 3));
        optableMap.put("SUBF", new opcodeInfo("5C", 3));
        optableMap.put("SUBR", new opcodeInfo("94", 3) );
        optableMap.put("SVC", new opcodeInfo("B0", 3));
        optableMap.put("TD", new opcodeInfo("E0", 3));
        optableMap.put("TIO", new opcodeInfo("F8", 3));
        optableMap.put("TIX", new opcodeInfo("2C", 3));
        optableMap.put("TIXR", new opcodeInfo("B8", 3));
        optableMap.put("WD", new opcodeInfo("DC", 3));
    }
}


class opcodeInfo{
    String opcode;
    int format;

    opcodeInfo(String opcode, int format){
        this.opcode = opcode;
        this.format= format;

    }

    //format- 1,2,3- 1,2,3/4
}