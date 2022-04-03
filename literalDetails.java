package assignment;

import java.util.*;

public class literalDetails{
    
    public String locctr;
    public int block_number;

    public literalDetails(String locctr, int block_number){
        this.locctr= locctr;
        this.block_number= block_number;
    }

    public String toString(){
        return ("locctr: "+ locctr+ " block number: "+block_number);
    }

}
