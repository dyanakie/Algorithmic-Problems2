package data_structures.horse_spread;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class HorseSpread {


    public static void main(String[] args) throws IOException {

        InputReader reader = new InputReader();

        int N = reader.readInt();
        int M = reader.readInt();
        int[][] matrix = new int[N][M];
        int x = reader.readInt();
        int y = reader.readInt();


        int[] rowMove = new int[]{-2, -2, -1, -1, 1, 1, 2, 2};
        int[] colMove = new int[]{-1, 1, -2, 2, -2, 2, -1, 1};

        Data newData = new Data(x, y, 2);

        Queue<Data> queue = new ArrayDeque<>();

        queue.add(newData);


        while(!queue.isEmpty()) {

            Data current = queue.poll();

            for(int i = 0; i < 8; i++){

                int row = current.getX() + rowMove[i];
                int col = current.getY() + colMove[i];

                if(!isValid(row, col, matrix)){
                    continue;
                }

                matrix[row][col] = current.getJumps();
                queue.offer(new Data(row, col, current.getJumps()+1));


            }

        }

        for(int i = 0; i < N; i++) {

            System.out.println(matrix[i][M/2]);
        }




    }


    public static boolean isValid(int x, int y, int[][] matrix){
        return x < matrix.length && x >= 0 && y < matrix[x].length && y >= 0 && matrix[x][y] == 0;

    }

    public static void printMatrix(int[][] matrix){

        for(int i = 0; i < matrix.length; i++){
            for(int r = 0; r < matrix[i].length; r++){
                System.out.print(matrix[i][r] + " ");
            }
            System.out.println();

        }
        System.out.println();

    }

    public static class Data{

        private int x;
        private int y;
        private int jumps;

        public Data(int x, int y, int jumps){
            this.x = x;
            this.y = y;
            this.jumps = jumps;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getJumps() {
            return jumps;
        }
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

    static class OutputWriter {
        private final PrintWriter writer;

        OutputWriter() {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        }

        void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0)
                    writer.print(' ');
                writer.print(objects[i]);
            }
        }

        void printLine(Object... objects) {
            print(objects);
            writer.println();
        }

        void close() {
            writer.close();
        }
    }
}