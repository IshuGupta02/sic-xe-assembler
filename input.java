import java.io.*;
import java.util.*;

public class input{

    public static String[][] parseData(File input_file) throws FileNotFoundException{
        
        int lines=0;
        
        Scanner myReader = new Scanner(input_file);
        while (myReader.hasNextLine()) {
            lines++;
            String data = myReader.nextLine();
        }
        myReader.close();

        String[][] parsed= new String[lines][3];

        myReader = new Scanner(input_file);
        for(int i=0; i<lines; i++){
            String data = myReader.nextLine();
            System.out.println(data);

            Scanner sc= new Scanner(data);
            if(!(data.charAt(0)==' ')){
                parsed[i][0]=sc.next();
                parsed[i][1]=sc.next();
                if(sc.hasNext()){
                    parsed[i][2]=sc.next();
                }
                
            }
            else{
                parsed[i][1]=sc.next();
                if(sc.hasNext()){
                    parsed[i][2]=sc.next();
                }
            }

            System.out.println(parsed[i][0]);
            System.out.println(parsed[i][1]);
            System.out.println(parsed[i][2]);
            
        }
        myReader.close();

        System.out.println(Arrays.toString(parsed));

        return parsed;
    }


    public static void main(String[] args) throws FileNotFoundException{
        File myObj = new File("code.txt");
        parseData(myObj);

    }
}