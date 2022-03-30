// package assignment.optable;

public class optable{

    static HashMap<String, opcodeInfo> optableMap;
    
    static{
        optableMap = new HashMap<>();

        // ArrayList<String, Integer> opcodes= new ArrayList<>();    

        optable.put("ADD", new opcodeInfo(18, 3));
        optable.put("ADDF", new opcodeInfo(58, 3));
        optable.put("ADDR", new opcodeInfo(90, 3));
        optable.put("AND", new opcodeInfo(40, 3));
        optable.put("CLEAR", new opcodeInfo(B4, 3));
        optable.put("COMP", new opcodeInfo(28, 3));
        optable.put("COMPF", new opcodeInfo(88, 3));
        optable.put("COMPR", new opcodeInfo(A0, 3));
        optable.put("DIV", new opcodeInfo(24, 3));
        optable.put("DIVF",new opcodeInfo(64, 3));
        optable.put("DIVR", new opcodeInfo(9C, 3));
        optable.put("FIX", new opcodeInfo(C4, 3));
        optable.put("FLOAT", new opcodeInfo(C0, 3));
        optable.put("HLO", new opcodeInfo(F4, 3));
        optable.put("J", new opcodeInfo(3C, 3) );
        optable.put("JEQ", new opcodeInfo(30, 3));
        optable.put("JGT", new opcodeInfo(34, 3));
        optable.put("JLT", new opcodeInfo(38, 3));
        optable.put("JSUB", new opcodeInfo(48, 3) );
        optable.put("LDA", new opcodeInfo(00, 3));
        optable.put("LDB", new opcodeInfo(68, 3));
        optable.put("LDCH", new opcodeInfo(50, 3));
        optable.put("LDF", new opcodeInfo(70, 3));
        optable.put("LDL", new opcodeInfo(08, 3));
        optable.put("LDS", new opcodeInfo(6C, 3) );
        optable.put("LDT", new opcodeInfo(74, 3));       
        optable.put("LDX",  new opcodeInfo(04, 3));
        optable.put("LPS", new opcodeInfo(D0, 3));
        optable.put("MUL", new opcodeInfo(20, 3));
        optable.put("MULF", new opcodeInfo(60, 3));
        optable.put("MULR", new opcodeInfo(98, 3));
        optable.put("NORM", new opcodeInfo(C8, 3));
        optable.put("OR", new opcodeInfo(44, 3));
        optable.put("RD",  new opcodeInfo(D8, 3));
        optable.put("RMO", new opcodeInfo(AC, 3));
        optable.put("RSUB", new opcodeInfo(4C, 3));
        optable.put("SHIFT", new opcodeInfo(A4, 3));
        optable.put("SHIFTR", new opcodeInfo(A8, 3));
        optable.put("SIO", new opcodeInfo(F0, 3));
        optable.put("SSK", new opcodeInfo(EC, 3));
        optable.put("STA", new opcodeInfo(0C, 3));
        optable.put("STB", new opcodeInfo(78, 3));
        optable.put("STCH", new opcodeInfo(54, 3));
        optable.put("STF", new opcodeInfo(80, 3));
        optable.put("STI", new opcodeInfo(D4, 3));
        optable.put("STL", new opcodeInfo(14, 3));
        optable.put("STS", new opcodeInfo(7C, 3));
        optable.put("STSW", new opcodeInfo(E8, 3));
        optable.put("STT", new opcodeInfo(84, 3));
        optable.put("STX", new opcodeInfo(10, 3));
        optable.put("SUB",new opcodeInfo(1C, 3));
        optable.put("SUBF", new opcodeInfo(5C, 3));
        optable.put("SUBR", new opcodeInfo(94, 3) );
        optable.put("SVC", new opcodeInfo(B0, 3));
        optable.put("TD", new opcodeInfo(E0, 3));
        optable.put("TIO", new opcodeInfo(F8, 3));
        optable.put("TIX", new opcodeInfo(2C, 3));
        optable.put("TIXR", new opcodeInfo(B8, 3));
        optable.put("WD", new opcodeInfo(DC, 3));
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