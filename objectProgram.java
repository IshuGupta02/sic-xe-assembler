package assignment;

import assignment.input;
import assignment.optable;
import assignment.programBlockDetails;
import assignment.symbolDetails;
import assignment.literalDetails;
import assignment.ltorg_details;
import assignment.expressionEvaluate;
import assignment.blockDetails;
// import assignment.pass2;

import java.util.*;

public class objectProgram{
    public static void generateObjectProgram(HashMap<Integer, blockDetails> blocktab, String[][] pass2){
        
        generateHeaderRecord(blocktab);

        generateTextRecord(blocktab, pass2);

        generateModificationRecord(pass2, blocktab);

        generateEndRecord(blocktab);
        
    }

    public static void generateModificationRecord(String[][] pass2, HashMap<Integer, blockDetails> blocktab){

        ArrayList<String> modificationRecords= new ArrayList<>();

        for(int i=0; i<pass2.length; i++){

            if(pass2[i][7] != null){

                String record="M";

                if(pass2[i][1].charAt(0)=='+'){

                    String req_addr= Integer.toHexString(Integer.parseInt(pass2[i][3],16) + Integer.parseInt(blocktab.get(Integer.parseInt(pass2[i][4])).start_addr,16)+1);

                    while(req_addr.length()<6){
                        req_addr= "0"+req_addr;
                    }

                    record= record+req_addr+"05";

                }
                else{

                    String req_addr= Integer.toHexString(Integer.parseInt(pass2[i][3],16) + Integer.parseInt(blocktab.get(Integer.parseInt(pass2[i][4])).start_addr,16)+1);

                    while(req_addr.length()<6){
                        req_addr= "0"+req_addr;
                    }

                    record= record+req_addr+"03";                    


                }
                modificationRecords.add(record);
            }
        }

        for(int j=0; j<modificationRecords.size(); j++){
            System.out.println(modificationRecords.get(j));
        }        

    }

    public static void generateTextRecord(HashMap<Integer, blockDetails> blocktab, String[][] pass2){

        String record="T";
        String opcodeCodes="";
        String curr_loc="0";
        String next_loc="0";

        ArrayList<String> textRecords= new ArrayList<>();

        for(int i=0; i<pass2.length; i++){

            curr_loc= Integer.toHexString(Integer.parseInt(pass2[i][3],16) + Integer.parseInt(blocktab.get(Integer.parseInt(pass2[i][4])).start_addr,16));

            if(pass2[i][6]!=null){


                if(opcodeCodes.length()==0){

                    while(curr_loc.length()<6){
                        curr_loc="0"+curr_loc;
                    }
                    record= record+curr_loc;
                }

                if(opcodeCodes.length()+pass2[i][6].length()<=60){
                    if(Integer.parseInt(next_loc,16)==Integer.parseInt(curr_loc,16)){
                        opcodeCodes= opcodeCodes+pass2[i][6];

                        next_loc= Integer.toHexString(Integer.parseInt(pass2[i+1][3],16) + Integer.parseInt(blocktab.get(Integer.parseInt(pass2[i+1][4])).start_addr,16));
                        
                    }
                    else{

                        String len= Integer.toHexString(opcodeCodes.length()/2);
                        while(len.length()<2){
                            len= "0"+len;
                        }
                        record= record+len+opcodeCodes;

                        textRecords.add(record);
                        record="T";

                        i--;
                        next_loc=curr_loc;

                    }

                }
                else{

                    String len= Integer.toHexString(opcodeCodes.length()/2);
                    while(len.length()<2){
                        len= "0"+len;
                    }
                    record= record+len+opcodeCodes;

                    textRecords.add(record);
                    record="T";

                    i--;
                    next_loc=curr_loc;

                }

            }
            else{
                //nothing
            }            

        }

        String len= Integer.toHexString(opcodeCodes.length()/2);

        while(len.length()<2){
            len= "0"+len;
        }
        record= record+len+opcodeCodes;

        textRecords.add(record);

        for(int j=0; j<textRecords.size(); j++){
            System.out.println(textRecords.get(j));
        }


    }

    public static void generateEndRecord(HashMap<Integer, blockDetails> blocktab){

        String start_address= blocktab.get(0).start_addr;

        while(start_address.length()<6){
            start_address= "0"+start_address;
        }

        System.out.println("E"+start_address);

    }

    public static void generateHeaderRecord(HashMap<Integer, blockDetails> blocktab){
        
        String header="";
        header= header+"HSAMPLE";

        int maxBlock=-1;
        String maxStartAdd="0";
        String maxLen="0";


        for(Map.Entry<Integer, blockDetails> entry : blocktab.entrySet()){  

            int block_num= entry.getKey(); 


            if(block_num>=maxBlock){

                maxBlock = block_num;
                maxStartAdd = entry.getValue().start_addr;
                maxLen = entry.getValue().length;

            }
                    
        }

        String start_address= blocktab.get(0).start_addr;

        while(start_address.length()<6){
            start_address= "0"+start_address;
        }


        String length_of_prog= Integer.toHexString(Integer.parseInt(maxStartAdd,16)+Integer.parseInt(maxLen, 16));


        while(length_of_prog.length()<6){
            length_of_prog= "0"+length_of_prog;
        }

        header= header+start_address+length_of_prog;

        System.out.println('\n'+header);

    }



}
