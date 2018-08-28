package arrays.indices;

import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayList;

public class Indices {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] array = new int[N];
        boolean[] is_visited = new boolean[N];

        String[] S = br.readLine().split(" ");

        for(int i = 0; i < S.length; i++){

            array[i] = Integer.parseInt(S[i]);
        }


        int count = 0;
        StringBuilder answer = new StringBuilder("");
        answer.append("0" + " ");
        is_visited[0] = true;
        int position = 0;
        int cycle = -1;
        answer.append(array[position] + " ");

        while (true) {

            position = array[position];

            if(array[position] < 0 || array[position] >= array.length){
                break;
            }

            if(is_visited[array[position]]){
                cycle = array[position];
                break;
            }

            is_visited[position] = true;

            answer.append(array[position] + " ");

        }


        if(cycle < 0) {
            System.out.println(answer);
        }else{

            boolean used = false;
            String[] A = answer.toString().split(" ");
            if(Integer.parseInt(A[0]) == cycle){

                System.out.print("(");
                for(int i = 0; i < A.length; i++){
                    if(i == A.length-1){
                        System.out.print(A[i]);
                    }else {
                        System.out.print(A[i] + " ");
                    }
                }
                System.out.print(")");

            }else {
                for (int i = 0; i < A.length - 1; i++) {
                    if (Integer.parseInt(A[i + 1]) == cycle) {
                        if (used) {
                            continue;
                        }
                        System.out.print(A[i]);
                        System.out.print("(");
                        used = true;
                        continue;
                    }

                    System.out.print(A[i] + " ");
                }

                System.out.print(A[A.length - 1]);
                System.out.print(")");
            }


        }


    }
}

