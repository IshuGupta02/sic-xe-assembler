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

    public static void generateCode(String[][] pass1){

        // System.out.println("here");

        String[][] pass2= new String[pass1.length][7];

        boolean error= false;
        
        for(int i=0; i<pass1.length; i++){
            if(pass1[i][5]!=null){
                error=true;
            }
        }
        if(error){
            System.out.println("ERROR IN PASS 1, CAN'T GO AHEAD WITH PASS 2!!");
            return;
        }

        for(int i=0; i<pass2.length; i++){

            // System.out.println("i: "+i);

            pass2[i][0] = pass1[i][0];
            pass2[i][1] = pass1[i][1];
            pass2[i][2] = pass1[i][2];
            pass2[i][3] = pass1[i][3];
            pass2[i][4] = pass1[i][4];
            pass2[i][5] = pass1[i][5];


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

                    
                    
                    
                }             

            }
            else{
                //do nothing
            }


        }

        System.out.println('\n');
        
        System.out.println("pass2: ");
        for(int i=0; i<pass2.length; i++){
            for(int j=0; j<7; j++){
                System.out.print(pass2[i][j]+" ");
            }
            System.out.println();
        }


        // System.out.println(pass2[10][6]);
        // System.out.println(pass2[11][6]);


        
    }

}
