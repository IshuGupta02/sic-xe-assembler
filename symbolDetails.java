package assignment;

import java.util.*;

public class symbolDetails{

    public boolean isRelative;
    public String value;
    public int block_number;

    public symbolDetails(String val, boolean isRel, int block){
        isRelative= isRel;
        value= val;
        block_number= block;

    }

    public String toString(){
        return ("value: "+ value+ " block number: "+block_number+ " isRelative: "+isRelative);
    }

}
