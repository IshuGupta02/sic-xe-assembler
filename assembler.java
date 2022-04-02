// package assignment;

import assignment.input;
import assignment.optable;
import assignment.programBlockDetails;
import assignment.symbolDetails;
import assignment.literalDetails;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
// import java.util.Scanner; // Import the Scanner class to read text files
import java.util.*;


public class assembler{
    public static void main(String[] args){

        String[][] parsed_input;

        try {
            File myObj = new File("code.txt");
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

        
        for(int i=0; i<lines; i++){
            
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

                if(parsed_input[i][0]!=null){
                    if(parsed_input[i][2].equals("*")){
                        
                        symtab.put(parsed_input[i][0], new symbolDetails(curr_loc, true, curr_block));
                    
                    }
                    else{
                        String operand= parsed_input[i][2];
                        String curr_operand="";

                        ArrayList<String> expression= new ArrayList<>();

                        for(int i=0; i<operand.length; i++){
                            if(operand.charAt(i)=='+' || 
                            operand.charAt(i)=='-' ||
                            operand.charAt(i)=='*' ||
                            operand.charAt(i)=='/'){

                                expression.add(curr_operand);
                                curr_operand="";
                                expression.add(Character.toString(operand.charAt(i)));

                            }
                            else{
                                curr_operand= curr_operand+operand.charAt(i);
                            }
                            
                        }

                        expression.add(curr_operand);
                        String[] expression_arr= expression.toArray();

                        if(expression_arr.length()%2==0){
                            pass1[i][5]= "Not a valid expression";
                            continue;
                        }

                        int k=0;

                        for(; k<expression_arr.length; k+=2){
                            
                            // expression_arr[i]- should be either a constant like 1 or present in symbol table

                            try{
                                if(symtab.constainsKey(expression_arr[i])){
                                    
                                }
                                else{
                                    Integer.parseInt(expression_arr[i]);
                                }
                            }
                            catch{
                                pass1[i][5]= expression_arr[i]+" has not been defined yet";
                                break;
                                                                
                            }
                        }

                        if(k!=expression_arr.length){
                            continue;
                        }

                        for(k=0; k<expression_arr.length; k+=2){
                            //check each operator
                            //add first operand in relative +, if it is relative
                            //if the operator is + or -, change the count of relative+ and relative-or absolute, whatever the operand is
                            //if the operator is * or /, check if the operands before and after the operator are absolute, if not, error, if yes, do nothing
                            
                                                                                    
                        }                        
                        
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
                
            }
            else if(parsed_input[i][1].equals("USE")){
                if(parsed_input[i][2]!=null){
                
                }
                else{

                }
                
            }
            else if(parsed_input[i][1].equals("BASE")){
                
            }
            else if(parsed_input[i][1].equals("NOBASE")){
                
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
            else if (optable.optableMap.containsKey(parsed_input[i][1])){

            }

            //not a valid instruction
            else{
                pass1[i][5]= "Invalid instruction";
                continue;
            }

            

            if(parsed_input[i][2]!=null & parsed_input[i][2].charAt(0)=='='){
                
                char type= parsed_input[i][2].charAt(1);

                //c,x,b,
                if(type != 'c' && type!='x' && type!='b'){
                    
                }


                
                littab.put(parsed_input[i][2].substring(1), new literalDetails(curr_loc, curr_block));
            }


        }


        
        System.out.println("pass1: ");
        for(int i=0; i<pass1.length; i++){
            for(int j=0; j<5; j++){
                System.out.print(pass1[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println("literal Table: ");
        littab.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue().toString());
        });

        System.out.println("Symbol Table: ");
        symtab.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue().toString());
        });



    }
}