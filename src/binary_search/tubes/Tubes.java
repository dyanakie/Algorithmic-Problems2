package binary_search.tubes;

import java.io.*;


public class Tubes {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] tubes = new int[N];
        int max_tube = 0;

        for (int i = 0; i < N; i++) {

            tubes[i] = Integer.parseInt(br.readLine());
            if(tubes[i] > max_tube){
                max_tube = tubes[i];
            }

        }


        int down_limit = 1;
        int upper_limit = max_tube;
        int middle = (upper_limit+down_limit)/2;
        int tube_count = 0;
        int max_lenght = 0;

        while (down_limit <= upper_limit) {

            tube_count = 0;

            for (int i = 0; i < tubes.length; i++) {

                if (tubes[i] != 0 && middle != 0) {
                    tube_count += tubes[i] / middle;
                } else {
                    tube_count += 0;
                }



            }

            if (tube_count < M) {

                upper_limit = middle - 1;


            }else if (tube_count >= M) {


                down_limit = middle + 1;
                max_lenght = middle;



            }


            middle = (upper_limit + down_limit)/2;

        }


        System.out.println(max_lenght);


    }
}
