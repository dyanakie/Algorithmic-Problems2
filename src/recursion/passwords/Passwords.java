package recursion.passwords;

import java.io.*;
import java.util.*;

public class Passwords {

    private static int local_count = 0;

    public static boolean goingLeft(int i, int last_i) {

        if(last_i == 1) {
            return false;
        }

        if(last_i == 0 && i != 0){
            return true;
        }

        if(i < last_i && i != 0) {
            return true;
        }

        return false;

    }

    public static boolean goingRight(int i, int last_i) {

        if(last_i == 0) {
            return false;
        }

        if(i == 0){
            return true;
        }

        if(i > last_i){
            return true;
        }

        return false;

    }


    public static void findPassword(String sounds, int[] answer, int index, int number, int count){

        if(index == number){
            //  System.out.println(Arrays.toString(answer));
            local_count++;
            if(local_count == count) {
                System.out.println(Arrays.toString(answer).replace(", ", "").replace("[", "").replace("]", ""));
                System.exit(0);
            }
            return;

        }

        for(int i = 0; i <= 9; i++ ){


            if(index == 0) {
                answer[index] = i;
                findPassword(sounds, answer, index+1, number, count);
            }

            if(sounds.length() != 0 && index != 0 && sounds.charAt(0) == '>'){

                if(goingRight(i, answer[index-1])) {
                    answer[index] = i;
                    findPassword(sounds.substring(1), answer, index + 1, number, count);

                }
            }


            if(sounds.length() != 0 && index != 0 && sounds.charAt(0) == '='){
                if(i == answer[index-1]) {
                    answer[index] = i;
                    findPassword(sounds.substring(1), answer, index + 1, number, count);

                }
            }

            if(sounds.length() != 0 && index != 0 && sounds.charAt(0) == '<'){

                if(goingLeft(i, answer[index-1])) {
                    answer[index] = i;
                    findPassword(sounds.substring(1), answer, index + 1, number, count);
                }
            }




        }

    }



    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int number = Integer.parseInt(br.readLine());



        String sounds = br.readLine();

        int count = Integer.parseInt(br.readLine());

        int[] answer = new int[number];

        findPassword(sounds, answer,0, number, count);






    }
}
