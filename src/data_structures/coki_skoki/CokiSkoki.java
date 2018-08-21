package data_structures.coki_skoki;

import java.io.*;
import java.util.Stack;

public class CokiSkoki {


    public static void main (String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] buildings = new int[N];


        String[] buildingsString= br.readLine().split(" ");

        for(int i = 0; i < buildingsString.length; i++) {
            buildings[i] = Integer.parseInt(buildingsString[i]);
        }


        Stack<Integer> container = new Stack<Integer>();
        int[] jumps = new int[buildings.length];
        int max_jumps = 0;


        for( int i = buildings.length - 1; i >= 0; i--){


            while(!container.empty() && buildings[i] >= buildings[container.peek()]){

                int index = container.pop();
                jumps[index] = container.size();
                if(max_jumps < jumps[index]){
                    max_jumps = jumps[index];
                }

            }

            container.push(i);

        }

        while(!container.empty()){

            int index = container.pop();
            jumps[index] = container.size();

            if(max_jumps < jumps[index]){
                max_jumps = jumps[index];
            }

        }

        System.out.println(max_jumps);

        for(int i = 0; i < jumps.length; i++){

            System.out.print(jumps[i] + " ");
        }
    }
}
