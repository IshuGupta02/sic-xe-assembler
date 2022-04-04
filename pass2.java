package assignment;

import assignment.input;
import assignment.optable;
import assignment.programBlockDetails;
import assignment.symbolDetails;
import assignment.literalDetails;
import assignment.ltorg_details;
import assignment.expressionEvaluate;
import assignment.blockDetails;
import assignment.hexConversion;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
// import java.util.Scanner; // Import the Scanner class to read text files
import java.util.*;

import java.util.*;

public class pass2{

    public static void generateCode(String[][] pass1, HashMap<String,symbolDetails> symtab, HashMap<Integer, blockDetails> blocktab){

        // System.out.println("here");

        String[][] pass2= new String[pass1.length][8];

        boolean error= false;

        int base = 0;
        
        for(int i=0; i<pass1.length; i++){
            if(pass1[i][5]!=null){
                error=true;
            }
        }
        if(error){
            System.out.println("ERROR IN PASS 1, CAN'T GO AHEAD WITH PASS 2!!");
            return;
        }

        boolean baseEnabled= false;

        for(int i=0; i<pass2.length; i++){

            

            pass2[i][0] = pass1[i][0];
            pass2[i][1] = pass1[i][1];
            pass2[i][2] = pass1[i][2];
            pass2[i][3] = pass1[i][3];
            pass2[i][4] = pass1[i][4];
            pass2[i][5] = pass1[i][5];

            // System.out.println(i+" : "+pass2[i][1].charAt(0));


            if(pass2[i][0]!=null && pass2[i][0].equals("*")){

                String operand= pass1[i][1];

                if(operand.charAt(0)=='x'){
                    pass2[i][6]= operand.substring(2,operand.length()-1);
                    
                    int length= (int) Math.ceil((double)(operand.length()-3)/2);

                    while(pass2[i][6].length()<length){
                        pass2[i][6]= "0"+pass2[i][6];
                    }

                }
                else if(operand.charAt(0)=='c'){
                    pass2[i][6] = hexConversion.convertByteToHexadecimal(operand.substring(2,operand.length()-1));

                }
                else if(operand.charAt(0)=='b'){
                    pass2[i][6] = Integer.toHexString(Integer.parseInt(operand.substring(2,operand.length()-1), 2));

                    int length= (int) Math.ceil((double)(operand.length()-3)/8);

                    while(pass2[i][6].length()<length){
                        pass2[i][6]= "0"+pass2[i][6];
                    }

                }

            }   
            else if(pass2[i][1].equals("BYTE")){


                String operand= pass1[i][2];

                if(operand.charAt(0)=='x'){
                    pass2[i][6]= operand.substring(2,operand.length()-1);
                    
                    int length= (int) Math.ceil((double)(operand.length()-3)/2);

                    while(pass2[i][6].length()<length){
                        pass2[i][6]= "0"+pass2[i][6];
                    }

                }
                else if(operand.charAt(0)=='c'){
                    pass2[i][6] = hexConversion.convertByteToHexadecimal(operand.substring(2,operand.length()-1));

                }
                else if(operand.charAt(0)=='b'){
                    pass2[i][6] = Integer.toHexString(Integer.parseInt(operand.substring(2,operand.length()-1), 2));

                    int length= (int) Math.ceil((double)(operand.length()-3)/8);

                    while(pass2[i][6].length()<length){
                        pass2[i][6]= "0"+pass2[i][6];
                    }

                }


            }
            else if(pass2[i][1].equals("WORD")){

                String operand= pass2[i][2];
                pass2[i][6]= Integer.toHexString(Integer.parseInt(operand));

                while(pass2[i][6].length()<6){
                    pass2[i][6]= "0"+pass2[i][6];
                }
                
            }
            else if (optable.optableMap.containsKey(pass1[i][1])){
                if(optable.optableMap.get(pass1[i][1]).format == 1){
                    pass2[i][6] =  optable.optableMap.get(pass1[i][1]).opcode;
                }
                else if(optable.optableMap.get(pass1[i][1]).format == 2){

                    pass2[i][6]= optable.optableMap.get(pass1[i][1]).opcode;

                    String[] components= pass2[i][2].split(",");

                    if(components.length==1){

                        pass2[i][6]= pass2[i][6]+registerTable.registerMap.get(components[0])+"0";

                    }
                    else if(components.length==2){

                        pass2[i][6]= pass2[i][6]+registerTable.registerMap.get(components[0])+registerTable.registerMap.get(components[1]);

                    }
                    else{

                        pass2[i][5]= "Bad operands";

                    }

                    
                }   
                else{

                    String inst= pass2[i][1];

                    int opcode_int= Integer.parseInt(optable.optableMap.get(inst).opcode,16);

                    String final_opcode="";

                    if(pass2[i][2]==null){
                        opcode_int+=3;
                        final_opcode= Integer.toHexString(opcode_int);

                        while(final_opcode.length()<2){
                            final_opcode="0"+final_opcode;
                        }

                        pass2[i][6]=final_opcode+"0000";
                        continue;
                    }

                    int third_hex=0;

                    String operand= pass2[i][2];

                    // System.out.println(operand);

                    String[] components= operand.split(",");

                    if(components.length==1){
                        //do nothing

                    }
                    else if(components.length==2){
                        third_hex+=8;
                        operand= components[0];                   

                    }


                    boolean n=true;
                    boolean i1=true;

                    

                    if(operand.charAt(0)=='#'){
                        n=false;
                    }
                    else if(operand.charAt(0)=='@'){
                        i1=false;
                    }
                    else if(operand.charAt(0)=='='){
                        operand= operand.substring(1);
                    }

                    

                    final_opcode="";

                    if(n){
                        if(i1){
                            
                            opcode_int+=3;

                        }
                        else{
                            opcode_int+=2;

                        }
                    }
                    else{
                        if(i1){
                            opcode_int+=1;

                        }
                        else{
                            //not possible

                        }
                    }

                    final_opcode= Integer.toHexString(opcode_int);
                    while(final_opcode.length()<2){
                        final_opcode="0"+final_opcode;
                    }

                    pass2[i][6] =  final_opcode;
                    
                    String value="";
                    
                    
                    if(n && i1){
                        //direct
                    }
                    else if(n && !i1){
                        //indirect
                        operand= operand.substring(1);
                    }
                    else if(!n && i1){
                        //immediate
                        operand= operand.substring(1);
                    }

                    int op1;

                    // System.out.println("operand: "+operand);

                    try{
                        op1= Integer.parseInt(operand);
                        value= Integer.toHexString(op1);
                    }
                    catch(Exception e){
                        op1= Integer.parseInt(symtab.get(operand).value, 16);
                        if(symtab.get(operand).isRelative){

                            op1= op1 + Integer.parseInt(blocktab.get(symtab.get(operand).block_number).start_addr, 16);

                            int curr_loc= Integer.parseInt(pass2[i][3], 16) + Integer.parseInt(blocktab.get(Integer.parseInt(pass2[i][4])).start_addr, 16);

                            if((op1- curr_loc-3) <=2047 &&  (op1- curr_loc-3) >= -2048){
                                third_hex+=2;
                                value= Integer.toHexString(op1- curr_loc-3);
                            }
                            else if(baseEnabled && op1-base >=0 && op1-base <= 4095){
                                third_hex+=4;
                                value= Integer.toHexString(op1-base);
                            }
                            else{

                                pass2[i][7]= "";
                                if(Integer.toHexString(op1).length()<=3){
                                    value= Integer.toHexString(op1);
                                }
                                else{
                                    pass2[i][5]="addressing not possible without format 4";
                                }


                            }
                            
                        }
                        else{
                            value= Integer.toHexString(op1);
                        }
                    }

                    
                    pass2[i][6]= pass2[i][6]+Integer.toHexString(third_hex);
                    while(value.length()<3){
                        value= "0"+value;
                    }

                    pass2[i][6]= pass2[i][6]+value;

                }

            }
            else if(optable.optableMap.containsKey(pass1[i][1].substring(1))){
                if(pass2[i][1].charAt(0)=='+'){

                    // System.out.println("format 4");

                    boolean n=true;
                    boolean i1=true;

                    String operand= pass2[i][2];

                    if(operand.charAt(0)=='#'){
                        n=false;
                    }
                    else if(operand.charAt(0)=='@'){
                        i1=false;
                    }
                    else if(operand.charAt(0)=='='){
                        operand= operand.substring(1);
                    }

                    String inst= pass2[i][1].substring(1);

                    int opcode_int= Integer.parseInt(optable.optableMap.get(inst).opcode,16);

                    String final_opcode="";

                    if(n){
                        if(i1){
                            
                            opcode_int+=3;

                        }
                        else{
                            opcode_int+=2;

                        }
                    }
                    else{
                        if(i1){
                            opcode_int+=1;

                        }
                        else{
                            //not possible

                        }
                    }

                    final_opcode= Integer.toHexString(opcode_int);
                    while(final_opcode.length()<2){
                        final_opcode="0"+final_opcode;
                    }

                    pass2[i][6] =  final_opcode+"1";
                    
                    String value="";
                    
                    if(n && i1){
                        //direct
                    }
                    else if(n && !i1){
                        //indirect
                        operand= operand.substring(1);
                    }
                    else if(!n && i1){
                        //immediate
                        operand= operand.substring(1);
                    }

                    int op1;

                    try{
                        op1= Integer.parseInt(operand);
                    }
                    catch(Exception e){
                        op1= Integer.parseInt(symtab.get(operand).value, 16);
                        if(symtab.get(operand).isRelative){
                            op1= op1 + Integer.parseInt(blocktab.get(symtab.get(operand).block_number).start_addr, 16);
                            pass2[i][7]= "";
                        }
                    }

                    value= Integer.toHexString(op1);

                    while(value.length()<5){
                        value= "0"+value;
                    }
                    
                    pass2[i][6] = pass2[i][6]+value;
                    
                }


            }
            else if(pass2[i][1].equals("BASE")){
                baseEnabled= true;
                String operand= pass2[i][2];
                try{

                    int base_value;

                    try{
                        base_value = Integer.parseInt(operand);
                    }
                    catch(Exception e){

                        base_value= Integer.parseInt(symtab.get(operand).value, 16);
                        if(symtab.get(operand).isRelative){
                            base_value= base_value + Integer.parseInt(blocktab.get(symtab.get(operand).block_number).start_addr, 16);
                        }
                                                
                    }

                }
                catch(Exception e){
                    pass2[i][5]= "Invalid operand for BASE Assembler directive";
                }
                 
            }
            else if(pass2[i][1].equals("NOBASE")){
                baseEnabled= false;
            }

        }

        System.out.println('\n');
        
        System.out.println("pass2: ");
        for(int i=0; i<pass2.length; i++){
            for(int j=0; j<8; j++){
                System.out.print(pass2[i][j]+" ");
            }
            System.out.println();
        }


        // System.out.println(pass2[10][6]);
        // System.out.println(pass2[11][6]);


        
    }

}
