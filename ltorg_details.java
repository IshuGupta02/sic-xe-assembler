package assignment;

import java.util.*;

public class ltorg_details{
    public int ltorg_block;
    public String locctr;
    public String literal;

    public ltorg_details(String locctr, int ltorg_block, String literal){
        this.locctr= locctr;
        this.literal= literal;
        this.ltorg_block= ltorg_block;
    }

    public String toString(){
        return ("locctr: "+ locctr+ " literal: "+literal+ " ltorg_block: "+ltorg_block);
    }

}
