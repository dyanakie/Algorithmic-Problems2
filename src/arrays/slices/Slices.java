package arrays.slices;

import java.io.*;


public class Slices {



    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        String[] o = br.readLine().split(" ");
        int columns = Integer.parseInt(o[0]);
        int rows = Integer.parseInt(o[1]);
        int depth = Integer.parseInt(o[2]);

        int count = 0;


        int[][][] cube = new int[depth][rows][columns];


        int local_count = 0;
        int local_count2 = 0;

        int total_cube_count = 0;


        for(int r = 0; r < rows; r++){


            // String A = br.readLine().trim();
            String[] S = br.readLine().split(" ");


            int d = 0;
            int column = 0;


            for(int c = 0; c < S.length; c++){

                if(S[c].equals("|")){
                    column = 0;
                    d++;
                    continue;
                }


                total_cube_count += Integer.parseInt(S[c]);
                cube[d][r][column] = Integer.parseInt(S[c]);
                column++;
            }

        }

        local_count = 0;
        local_count2 = 0;


        for(int d = 0; d < cube.length-1; d++){               // proverka po depth

            for(int r = 0; r < cube[d].length; r++) {
                for (int c = 0; c < cube[d][r].length; c++) {
                    local_count += cube[d][r][c];
                }
            }

            if(local_count == total_cube_count/2) {
                count++;
            }

        }



        local_count = 0;
        local_count2 = 0;

        for(int col = 0; col < columns-1; col++) {               // proverka po koloni
            for (int d = 0; d < cube.length; d++) {

                for (int r = 0; r < cube[d].length; r++) {
                    local_count += cube[d][r][col];
                }


            }
            if (local_count == total_cube_count/2) {
                count++;
            }


        }

        local_count = 0;


        for(int r = 0; r < rows-1; r++) {               // proverka po rows

            for (int d = 0; d < depth; d++) {
                for (int c = 0; c < columns; c++) {
                    local_count += cube[d][r][c];
                }
            }

            if (local_count == total_cube_count / 2) {
                count++;


            }
        }


        if(rows == 1 && depth == 1){
            System.out.println("0");
        }else {

            System.out.println(count);
        }



    }

}
