package arrays.zigzag;

import java.io.*;
import java.util.StringTokenizer;


public class ZigZag {


    public static void main(String[] args) throws IOException {

        InputReader reader = new InputReader();

        int N = reader.readInt();
        int M = reader.readInt();


        int multi = 1;
        int current;
        long count = 0;
        boolean step = true;


        for(int i = 0; i < N; i++){

            current = multi;

            for(int r = 0; r < M; r++){


                if(step){
                    count += current;
                    if(i != 0 && i != N-1 && r != 0 && r != M-1){

                        count += current;
                    }
                    step = false;
                }else{
                    step = true;
                }
                current = current +3;
            }

            if(step){
                step = false;
            }else{
                step = true;
            }

            multi = multi+3;
        }


        System.out.println(count);



    }

    static class InputReader {
        private BufferedReader br;
        private StringTokenizer st;

        InputReader() {
            try {
                br = new BufferedReader(new InputStreamReader(System.in));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String readLine() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int readInt() {
            return Integer.parseInt(readLine());
        }
    }
}
