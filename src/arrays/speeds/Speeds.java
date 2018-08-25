package arrays.speeds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Speeds {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int c = Integer.parseInt(br.readLine());
        int[] initialSpeed = new int[c];
        int[] correctedSpeed = new int[c];
        int count = 0;
        int max_count = 0;
        int sum = 0;
        int max_sum = 0;
        boolean groupDetected = false;


        for(int i = 0; i < initialSpeed.length; i++){

            initialSpeed[i] = Integer.parseInt(br.readLine());
            correctedSpeed[i] = initialSpeed[i];

        }

        for(int i = 0; i < initialSpeed.length; i++){

            if( i == initialSpeed.length-1){

                if(groupDetected){

                    count++;
                    sum += initialSpeed[i];
                    groupDetected = false;
                }

                if(count == max_count) {

                    if(sum > max_sum) {
                        max_sum = sum;
                    }

                }



                if(count > max_count){
                    max_count = count;
                    max_sum = sum;
                    sum = 0;
                    count = 0;
                }else {

                    sum = 0;
                    count = 0;

                }




                break;
            }

            if(correctedSpeed[i] < correctedSpeed[i+1]){

                correctedSpeed[i+1] = correctedSpeed[i];

                groupDetected = true;
                count++;
                sum += initialSpeed[i];


            }else{

                if(groupDetected){

                    count++;
                    sum += initialSpeed[i];
                    groupDetected = false;
                }

                if(count == max_count) {

                    if(sum > max_sum) {
                        max_sum = sum;
                    }

                }



                if(count > max_count){
                    max_count = count;
                    max_sum = sum;
                    sum = 0;
                    count = 0;
                }else {

                    sum = 0;
                    count = 0;

                }


            }


        }

        if(max_sum == 0){

            for(int i = 0; i < initialSpeed.length; i++){

                if(initialSpeed[i]> max_sum){
                    max_sum = initialSpeed[i];
                }

            }

        }



        System.out.println(max_sum);




    }


}
