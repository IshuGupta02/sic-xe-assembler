package assignment;

import java.util.*;

public class programBlockDetails{
    
    //block name, Block number
    public HashMap<String, Integer> program_blocks;

    //Block number, locctr
    public HashMap<Integer, String> block_details;

    public programBlockDetails(){

        program_blocks= new HashMap<>();
        block_details= new HashMap<>();

        program_blocks.put("DEFAULT", 0);
        block_details.put(0, "0");
    
    }

}
