package assignment;

import java.util.*;

public class symbolDetails{

    boolean isRelative;
    String value;
    int block_number;

    public symbolDetails(String val, boolean isRel, int block){
        isRelative= isRel;
        value= val;
        block_number= block;

    }

    public String toString(){
        return ("value: "+ value+ " block number: "+block_number+ " isRelative: "+isRelative);
    }

}
