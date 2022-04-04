// package assignment;

import assignment.input;
import assignment.optable;
import assignment.programBlockDetails;
import assignment.symbolDetails;
import assignment.literalDetails;
import assignment.ltorg_details;
import assignment.expressionEvaluate;
import assignment.blockDetails;
import assignment.pass2;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
// import java.util.Scanner; // Import the Scanner class to read text files
import java.util.*;


public class assembler{
    public static void main(String[] args){

        String[][] parsed_input;

        try {
            File myObj = new File("code1.txt");
            parsed_input= input.parseData(myObj);
        } 
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            return;
        }

        System.out.println("parsed_input: ");

        for(int i=0; i<parsed_input.length; i++){
            for(int j=0; j<3; j++){
                System.out.print(parsed_input[i][j]+" ");
            }
            System.out.println();
        }


        programBlockDetails programBlocks= new programBlockDetails();
        String curr_loc= "0000";
        int curr_block=0;


        //littab
        //literal, location, block- if not assigned, then location= * 
        HashMap<String, literalDetails> littab= new HashMap<>(); 
        

        //symtab
        //symbol, details(value, isRelative, block_number) 
        HashMap<String,symbolDetails> symtab= new HashMap<>();


        int lines= parsed_input.length;


        //final data structure generared after pass1
        String[][] pass1= new String[lines][6];

        // ltorg_locctr, x'05', ltorg_block, locctr 
        // ArrayList<ArrayList<String>> ltorgs= new ArrayList<>();
        HashMap<String, ArrayList<ltorg_details>> ltorgs= new HashMap<>();

        
        for(int i=0; i<lines; i++){

            // System.out.println("curr_loc: "+curr_loc+" curr_block: "+curr_block);

            // System.out.println(i+" : "+Arrays.toString(parsed_input[i]));
            
            pass1[i][3]= curr_loc;
            pass1[i][4]= Integer.toString(curr_block);


            if(parsed_input[i][0]!=null){
                symtab.put(parsed_input[i][0], new symbolDetails(curr_loc, true, curr_block));                                   
            }

            pass1[i][0]= parsed_input[i][0];
            pass1[i][1]= parsed_input[i][1];
            pass1[i][2]= parsed_input[i][2];


            //assembler directives
            if(parsed_input[i][1].equals("START")){

            }
            else if(parsed_input[i][1].equals("END")){

            }
            else if(parsed_input[i][1].equals("EQU")){

                // System.out.println("EQU found at "+i);

                if(parsed_input[i][0]!=null){
                    if(parsed_input[i][2].equals("*")){
                        
                        symtab.put(parsed_input[i][0], new symbolDetails(curr_loc, true, curr_block));
                    
                    }
                    else{
                        String operand= parsed_input[i][2];
                        // System.out.println(operand);
                        String curr_operand="";

                        ArrayList<String> expression= new ArrayList<>();

                        for(int p=0; p<operand.length(); p++){
                            if(operand.charAt(p)=='+' || 
                            operand.charAt(p)=='-' ||
                            operand.charAt(p)=='*' ||
                            operand.charAt(p)=='/'){

                                expression.add(curr_operand);
                                curr_operand="";
                                expression.add(Character.toString(operand.charAt(p)));

                            }
                            else{
                                curr_operand= curr_operand+operand.charAt(p);
                            }
                            
                        }

                        expression.add(curr_operand);
                        String[] expression_arr= new String[expression.size()];

                        expression_arr= expression.toArray(expression_arr);

                        // System.out.println(Arrays.toString(expression_arr));

                        if(expression_arr.length%2==0){
                            pass1[i][5]= "Not a valid expression";
                            continue;
                        }

                        int k=0;

                        for(; k<expression_arr.length; k+=2){
                            
                            // expression_arr[i]- should be either a constant like 1 or present in symbol table

                            try{
                                if(symtab.containsKey(expression_arr[k])){
                                                                        
                                }
                                else{
                                    Integer.parseInt(expression_arr[k]);
                                }
                            }
                            catch(Exception e){
                                pass1[i][5]= expression_arr[k]+" has not been defined yet";
                                break;
                                                                
                            }
                        }

                        // System.out.println("before declared relative");

                        if(k!=expression_arr.length+1){
                            continue;
                        }

                        int relativePlus=0;
                        int relativeMinus=0;

                        // System.out.println("declared relative");

                        int block=0;

                        try{
                            Integer.parseInt(expression_arr[0]);
                        }
                        catch(Exception e){
                            if(symtab.get(expression_arr[0]).isRelative){
                                relativePlus++;
                            }
                            
                            block= symtab.get(expression_arr[0]).block_number;
                        }

                        k=1;

                        // System.out.println("Before loop: relativePlus: "+ relativePlus);
                        // System.out.println("relativeMinus: "+ relativeMinus);

                        for(; k<expression_arr.length; k+=2){

                            //check each operator
                            //add first operand in relative +, if it is relative
                            //if the operator is + or -, change the count of relative+ and relative-or absolute, whatever the operand is
                            //if the operator is * or /, check if the operands before and after the operator are absolute, if not, error, if yes, do nothing

                            String operand1= expression_arr[k-1];
                            String operand2= expression_arr[k+1];

                            // System.out.println(operand1+" "+operand2);

                            if(expression_arr[k].equals("+")){

                                try{
                                    Integer.parseInt(operand2); 
                                    // System.out.println("integer");                                   
                                }
                                catch(Exception e){

                                    if(symtab.get(operand2).isRelative){
                                        relativePlus++;
                                    }
                                    
                                    // relativePlus++;   

                                    block= symtab.get(operand2).block_number;  

                                }


                            }
                            else if(expression_arr[k].equals("-")){

                                try{
                                    Integer.parseInt(operand2);                                    
                                }
                                catch(Exception e){
                                    if(symtab.get(operand2).isRelative){
                                        relativeMinus++;   
                                    }
                                    
                                    block= symtab.get(operand2).block_number;                                                                     
                                }

                            }
                            else if(expression_arr[k].equals("*")){
                                try{
                                    Integer.parseInt(operand1);
                                }
                                catch(Exception e){

                                    if(symtab.get(operand1).isRelative){
                                        pass1[i][5]= "relative expression cannot be multiplied";
                                        break;  
                                    }

                                }

                                try{
                                    Integer.parseInt(operand2);
                                }
                                catch(Exception e){

                                    if(symtab.get(operand2).isRelative){
                                        pass1[i][5]= "relative expression cannot be multiplied";
                                        break;  
                                    }

                                }

                            }
                            else if(expression_arr[k].equals("/")){
                                try{
                                    Integer.parseInt(operand1);
                                }
                                catch(Exception e){

                                    if(symtab.get(operand1).isRelative){
                                        pass1[i][5]= "relative expression cannot be divided";
                                        break;  
                                    }

                                }

                                try{
                                    Integer.parseInt(operand2);
                                }
                                catch(Exception e){

                                    if(symtab.get(operand2).isRelative){
                                        pass1[i][5]= "relative expression cannot be divided";
                                        break;  
                                    }

                                }
                                
                            }        
                                                                                    
                        }


                        if(k != expression_arr.length) continue;

                        // System.out.println(Arrays.toString(expression_arr));
                        // System.out.println("relativePlus: "+ relativePlus);
                        // System.out.println("relativeMinus: "+ relativeMinus);

                        if(!(relativePlus-relativeMinus == 1 || relativePlus-relativeMinus == 0)){
                            pass1[i][5]= "Invalid expression";
                            continue;
                        }

                        // System.out.println("here0");

                        int ans= expressionEvaluate.evaluateExp(expression_arr, symtab);

                        if(relativePlus-relativeMinus == 0){
                            // System.out.println("here1");
                            symtab.put(parsed_input[i][0], new symbolDetails(Integer.toHexString(ans), false, curr_block));
                        }
                        else if(relativePlus-relativeMinus == 1){
                            // System.out.println("here2");
                            symtab.put(parsed_input[i][0], new symbolDetails(Integer.toHexString(ans), true, block));
                        }

                        // System.out.println("here3");
   
                    }

                }
                else{
                    //don't do anything
                    continue;
                }

                //compute, check relative/absolute, put in symtab
                // if(parsed_input[i][0]!=null){
                //                    
                // } 
                
            }
            else if(parsed_input[i][1].equals("LTORG")){

                String ltorg_locctr= curr_loc;

                ArrayList<ltorg_details> arr= new ArrayList<>();

                for(Map.Entry<String, literalDetails> entry:littab.entrySet()){                    
                    String litValue= entry.getKey();
                    literalDetails details= entry.getValue();

                    if(details.locctr.equals("*")){

                        arr.add(new ltorg_details(curr_loc, curr_block, litValue));

                        // ltorgs.put(ltorg_locctr, new ltorg_details(curr_loc, curr_block, litValue));

                        littab.put(litValue, new literalDetails(curr_loc,curr_block));


                        int length= 0;

                        if(litValue.charAt(0)=='x'){
                            // System.out.println("x found!");
                            // System.out.println(litValue);
                            // System.out.println(litValue.length());
                            length= (int)(Math.ceil(((double)litValue.length()-3)/2));
                        }
                        else if(litValue.charAt(0)=='c'){
                            length= litValue.length()-3;
                        }
                        else if(litValue.charAt(0)=='b'){
                            length= (int)(Math.ceil(((double)litValue.length()-3)/8));
                        }

                        // System.out.println("length due to ltorg: "+length);
                        
                        curr_loc= Integer.toHexString(Integer.parseInt(curr_loc, 16)+length);

                    }

                    ltorgs.put(ltorg_locctr, arr);

                }
                
            }
            else if(parsed_input[i][1].equals("USE")){
                if(parsed_input[i][2]!=null){
                    try{

                        curr_block = programBlocks.program_blocks.get(parsed_input[i][2]);
                        curr_loc = programBlocks.block_details.get(curr_block);

                    }
                    catch(Exception e){

                        int max=0;

                        for(Map.Entry<String, Integer> entry:programBlocks.program_blocks.entrySet()){

                            max= (int) Math.max(max, entry.getValue());
                            
                        }

                        // programBlocks.program_blocks.entrySet().forEach(entry -> {
                        //     max= (int) Math.max(max, entry.getValue());
                        // });

                        programBlocks.program_blocks.put(parsed_input[i][2], max+1);
                        programBlocks.block_details.put(max+1, "0"); 

                        curr_block = max+1;
                        curr_loc = "0";

                    }
                                                        
                }
                else{
                    curr_block = programBlocks.program_blocks.get("DEFAULT");
                    curr_loc = programBlocks.block_details.get(curr_block);
                }
                
            }
            else if(parsed_input[i][1].equals("BASE")){
                //pass2
                
            }
            else if(parsed_input[i][1].equals("NOBASE")){
                //pass2
                
            }
            else if(parsed_input[i][1].equals("BYTE")){

                int length= 0;

                String operand= parsed_input[i][2];
                if(operand.charAt(1)=='\'' && operand.charAt(operand.length()-1)=='\''){
                    if(operand.charAt(0)=='c'){
                        length= operand.length()-3;
                    }
                    else if(operand.charAt(0)=='x'){
                        length= (int) Math.ceil((double)(operand.length()-3)/2);
                    }
                    else if(operand.charAt(0)=='b'){
                        length= (int) Math.ceil((double)(operand.length()-3)/8);
                    }

                }
                else{
                    pass1[i][5]= "invalid operand for assembler directive BYTE";
                    continue;
                }

                String new_loc= Integer.toHexString(Integer.parseInt(curr_loc, 16)+length);
                curr_loc= new_loc;
                

            }
            else if(parsed_input[i][1].equals("RESB")){
                int no_of_bytes= Integer.parseInt(parsed_input[i][2]);
                String new_loc= Integer.toHexString(Integer.parseInt(curr_loc, 16)+no_of_bytes);
                curr_loc= new_loc;

            }
            else if(parsed_input[i][1].equals("WORD")){
                int operand= Integer.parseInt(parsed_input[i][2]);

                if(Integer.toHexString(operand).length()>6){
                    pass1[i][5]= "operand value exceeds the limit of 3 bytes(1 word)";
                    continue;
                }

                String new_loc= Integer.toHexString(Integer.parseInt(curr_loc, 16)+3);
                curr_loc= new_loc;

            }
            else if(parsed_input[i][1].equals("RESW")){
                int no_of_words= Integer.parseInt(parsed_input[i][2]);
                String new_loc= Integer.toHexString(Integer.parseInt(curr_loc, 16)+no_of_words*3);
                curr_loc= new_loc;
            }

            else if(parsed_input[i][1].equals("ORG")){
                
            }

            //a valid opcode
            else if (optable.optableMap.containsKey(parsed_input[i][1]) || ( parsed_input[i][1].charAt(0)=='+' && optable.optableMap.containsKey(parsed_input[i][1].substring(1)))){

                if(parsed_input[i][1].charAt(0)=='+'){
                    if(optable.optableMap.get(parsed_input[i][1].substring(1)).format==3){
                        // curr_loc+=4;

                        curr_loc= Integer.toHexString(Integer.parseInt(curr_loc, 16)+4);

                    }
                    else{
                        pass1[i][5]="Extended format is not possible for this instruction";
                        continue;
                    }
                }
                else{
                    if(optable.optableMap.get(parsed_input[i][1]).format==1){
                        curr_loc= Integer.toHexString(Integer.parseInt(curr_loc, 16)+1);
                    }
                    else if(optable.optableMap.get(parsed_input[i][1]).format==2){
                        curr_loc= Integer.toHexString(Integer.parseInt(curr_loc, 16)+2);
                    }
                    else if(optable.optableMap.get(parsed_input[i][1]).format==3){
                        curr_loc= Integer.toHexString(Integer.parseInt(curr_loc, 16)+3);
                    }

                }

            }

            //not a valid instruction
            else{
                pass1[i][5]= "Invalid instruction";
                continue;
            }

            if(parsed_input[i][2]!=null && parsed_input[i][2].charAt(0)=='='){
                
                char type= parsed_input[i][2].charAt(1);

                //c,x,b,
                if(type != 'c' && type!='x' && type!='b'){
                    
                    
                }
                
                littab.put(parsed_input[i][2].substring(1), new literalDetails("*",0));
            }

            


            programBlocks.block_details.put(curr_block, curr_loc);

            // System.out.println("After: curr_loc: "+curr_loc+" curr_block: "+curr_block);


        }

        System.out.println('\n');
        
        System.out.println("pass1: ");
        for(int i=0; i<pass1.length; i++){
            for(int j=0; j<6; j++){
                System.out.print(pass1[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println('\n');

        System.out.println("literal Table: ");
        littab.entrySet().forEach(entry -> {
            // System.out.print()
            System.out.println(entry.getKey() + " " + entry.getValue().toString());
        });

        System.out.println('\n');

        


        System.out.println("ltorgs: ");
        ltorgs.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue().toString());
        });

        System.out.println('\n');

        HashMap<Integer, blockDetails> blocktab= new HashMap<>();

        String loc= "0";

        int p=0;
        while(true){
            if(programBlocks.block_details.containsKey(p)){

                blocktab.put(p, new blockDetails(loc,programBlocks.block_details.get(p)));

                loc= Integer.toHexString(Integer.parseInt(loc, 16)+Integer.parseInt(programBlocks.block_details.get(p),16));
            
            }
            else{

                break;

            }

            p++;
        }

        System.out.println("blocktab: ");
        blocktab.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue().toString());
        });

        System.out.println('\n');

        int literal_size=0;

        for(Map.Entry<String, ArrayList<ltorg_details>> entry:ltorgs.entrySet()){
            literal_size += entry.getValue().size();
        }

        String[][] pass1_final= new String[literal_size+pass1.length][6];

        int new_index=0;

        for(int k=0; k<pass1.length; k++){

            pass1_final[new_index][0]=pass1[k][0];
            pass1_final[new_index][1]=pass1[k][1];
            pass1_final[new_index][2]=pass1[k][2];
            pass1_final[new_index][3]=pass1[k][3];
            pass1_final[new_index][4]=pass1[k][4];
            pass1_final[new_index][5]=pass1[k][5];

            new_index++;

            if(pass1[k][1].equals("LTORG")){

                String locctr_ltorg= pass1[k][3];
                int locctr_block= Integer.parseInt(pass1[k][4]);

                ArrayList<ltorg_details> list_literals = ltorgs.get(locctr_ltorg);

                for(int r=0; r<list_literals.size(); r++){
                    pass1_final[new_index][0]= "*";
                    pass1_final[new_index][1]= list_literals.get(r).literal;
                    pass1_final[new_index][3]= list_literals.get(r).locctr;
                    pass1_final[new_index][4]= list_literals.get(r).ltorg_block+ "";
                    new_index++;
                }

            }         
            
        }

        System.out.println("pass1_final: ");
        for(int i=0; i<pass1_final.length; i++){
            for(int j=0; j<6; j++){
                System.out.print(pass1_final[i][j]+" ");
            }
            System.out.println();
        }
 
        //check that locctr of two different blocks can be same and will be overridden in ltorgs
        //improve parsed_input



        // System.out.println("literal Table: ");
        littab.entrySet().forEach(entry -> {
            // System.out.print()
            symtab.put(entry.getKey(), new symbolDetails(entry.getValue().locctr, true,entry.getValue().block_number));
            // System.out.println(entry.getKey() + " " + entry.getValue().toString());
        });

        System.out.println('\n');

        System.out.println("Symbol Table: ");
        symtab.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue().toString());
        });

        System.out.println('\n');


        pass2.generateCode(pass1_final, symtab, blocktab);


    }
}