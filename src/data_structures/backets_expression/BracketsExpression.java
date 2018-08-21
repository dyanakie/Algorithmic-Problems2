package data_structures.backets_expression;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class BracketsExpression {

    public static void Start_Backward(String[] S, int position, int intended_position){


        for(int i = intended_position; i <=position; i++ ){

            System.out.print(S[i]);

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> container = new Stack<>();

        String[] S = br.readLine().split( "");

        for(int i = 0; i < S.length; i++){

            if(S[i].charAt(0) == '('){

                container.push(i);

            }

            if(S[i].charAt(0) == ')'){

                Start_Backward(S, i, container.pop());
                System.out.println();
            }


        }


    }

}
