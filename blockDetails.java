package assignment;

import java.util.*;

public class blockDetails{

    public String start_addr;
    public String length;
    // public String name;

    public blockDetails(String start_addr, String length){
        this.start_addr= start_addr;
        this.length= length;
        // this.name= name;

    }

    public String toString(){
        return ("start_addr: "+ start_addr+ " length: "+length);
    }

}
