package recursion.permutations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class Permutations {

    public static void Permute(int N, int index, HashSet<Integer> check, ArrayList<Integer> temp, ArrayList<String> answers){

        if(index >= N){
            StringBuilder a = new StringBuilder();
            for(int i = 0; i < temp.size(); i++){
                a.append(temp.get(i));
            }
            answers.add(a.toString());
            return;
        }

        for(int i = 1; i < N+1; i++){

            if(!check.contains(i)){

                temp.set(index, i);
                check.add(i);

                Permute(N, index+1, check, temp, answers);

                check.remove(i);

            }

        }

    }



    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        ArrayList<String> answers = new ArrayList<>();
        HashSet<Integer> check = new HashSet<>();
        ArrayList<Integer> temp = new ArrayList<>();
        for(int i = 1; i <N+1; i++){
            temp.add(i);
        }
        Permute(N, 0, check, temp, answers);

        for (String A:
                answers) {

            System.out.println(A);
        }




    }
}