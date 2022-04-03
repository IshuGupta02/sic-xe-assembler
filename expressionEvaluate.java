package assignment;

import assignment.symbolDetails;
import java.util.*;

public class expressionEvaluate{
    
    public static String[] infixToPostfix(String[] infix){

        // String[] postfix=new String[infix.length];

        // System.out.println("here111");

        // System.out.println("infix: "+Arrays.toString(infix));

        ArrayList<String> postfix= new ArrayList<>();

        Stack<String> operands= new Stack<>();
        Stack<String> operators= new Stack<>();

        for(int i=0; i<infix.length; i++){
            
            if(infix[i].equals("-") || infix[i].equals("+")){

                while(!operators.empty() && !(operators.peek().equals("*") || operators.peek().equals("/"))){
                    postfix.add(operators.pop());
                }
                operators.push(infix[i]);

            }
            else if(infix[i].equals("*") || infix[i].equals("/")){

                while(!operators.empty()){
                    postfix.add(operators.pop());
                }

                operators.push(infix[i]);
                
            }
            else{
                postfix.add(infix[i]);
            }
        }

        while(!operators.empty()){
            postfix.add(operators.pop());
        }

        String[] postfix_arr= new String[postfix.size()];

        postfix_arr= postfix.toArray(postfix_arr);

        // for(int k=0; k<postfix.size(); k++){
        //     System.out.print(postfix.get(k));
        // }

        // System.out.println(Arrays.toString(postfix_arr));

        return postfix_arr;
    }

    public static int evaluateExp(String[] infix, HashMap<String,symbolDetails> symtab){

        // System.out.println("here");
        
        String[] postfix= infixToPostfix(infix);

        // System.out.println("infix: "+Arrays.toString(infix));

        return evaluate(postfix, symtab);

    }

    public static int evaluate(String[] postfix, HashMap<String,symbolDetails> symtab){

        Stack<String> stack= new Stack<>();

        // int answer=0;

        int i=1;

        stack.push(postfix[0]);

        while(!stack.isEmpty()){

            if(i == postfix.length) break;

            if(postfix[i].equals("+")){
                String op2= stack.pop();
                String op1= stack.pop();

                int answer;

                int op1_;
                int op2_;

                try{
                    op1_= Integer.parseInt(op1);
                    
                }
                catch(Exception e){
                    // op1= symtab.get(op1).value;
                    op1_= Integer.parseInt(symtab.get(op1).value, 16);
                }

                try{
                    op2_= Integer.parseInt(op2);
                }
                catch(Exception e){
                    // op1= symtab.get(op1).value;
                    op2_= Integer.parseInt(symtab.get(op2).value, 16);
                }

                answer = op1_+op2_;

                // System.out.println(answer);

                stack.push(answer+"");           

            }   
            else if(postfix[i].equals("-")){
                String op2= stack.pop();
                String op1= stack.pop();

                int answer;

                int op1_;
                int op2_;

                try{
                    op1_= Integer.parseInt(op1);
                    
                }
                catch(Exception e){
                    // op1= symtab.get(op1).value;
                    op1_= Integer.parseInt(symtab.get(op1).value, 16);
                }

                try{
                    op2_= Integer.parseInt(op2);
                }
                catch(Exception e){
                    op2_= Integer.parseInt(symtab.get(op2).value, 16);
                }

                answer = op1_-op2_;  

                stack.push(answer+"");

            }   
            else if(postfix[i].equals("*")){

                String op2= stack.pop();
                String op1= stack.pop();

                int answer;

                int op1_;
                int op2_;

                try{
                    op1_= Integer.parseInt(op1);
                    
                }
                catch(Exception e){
                    // op1= symtab.get(op1).value;
                    op1_= Integer.parseInt(symtab.get(op1).value, 16);
                }

                try{
                    op2_= Integer.parseInt(op2);
                }
                catch(Exception e){
                    op2_= Integer.parseInt(symtab.get(op2).value, 16);
                }

                answer = op1_*op2_;  

                stack.push(answer+"");

            }
            else if(postfix[i].equals("/")){

                String op2= stack.pop();
                String op1= stack.pop();

                int answer;

                int op1_;
                int op2_;

                try{
                    op1_= Integer.parseInt(op1);
                    
                }
                catch(Exception e){
                    // op1= symtab.get(op1).value;
                    op1_= Integer.parseInt(symtab.get(op1).value, 16);
                }

                try{
                    op2_= Integer.parseInt(op2);
                }
                catch(Exception e){
                    op2_= Integer.parseInt(symtab.get(op2).value, 16);
                }

                answer = op1_/op2_;  

                stack.push(answer+"");

            } 
            else{

                stack.push(postfix[i]);

            }
            i++;

        }

        int answer;

        try{
            answer= Integer.parseInt(stack.pop());
            
        }
        catch(Exception e){
            answer= Integer.parseInt(symtab.get(stack.pop()).value, 16);
        }

        // System.out.println("answer: "+answer);

        return answer;
    } 

}
