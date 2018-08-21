package data_structures.horse_matrix;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HorseMatrix {

    static int minPath = 100000;


    public static void main(String[] args) throws IOException {

        InputReader reader = new InputReader();

        int N = reader.readInt();
        char[][] matrix = new char[N][N];
        int x = 0;
        int y = 0;

        for (int i = 0; i < N; i++) {
            for (int r = 0; r < N; r++) {

                matrix[i][r] = reader.readLine().charAt(0);
                if (matrix[i][r] == 's') {
                    x = i;
                    y = r;
                }
            }

        }

        int[] rowMove = new int[]{-2, -2, -1, -1, 1, 1, 2, 2};
        int[] colMove = new int[]{-1, 1, -2, 2, -2, 2, -1, 1};

        Data newData = new Data(x, y, 1);

        Queue<Data> queue = new ArrayDeque<>();

        queue.add(newData);

        boolean exit = false;

        while(!queue.isEmpty() && !exit) {

            Data current = queue.poll();

            for(int i = 0; i < 8; i++){

                int row = current.getX() + rowMove[i];
                int col = current.getY() + colMove[i];

                if(!isValid(row, col, matrix)){
                    continue;
                }

                if(matrix[row][col] == 'e'){

                    System.out.println(current.getJumps());
                    exit = true;
                    break;
                }else{

                    //   printMatrix(matrix);
                    matrix[row][col] = 'x';
                    queue.offer(new Data(row, col, current.getJumps()+1));
                }


            }

        }

        if(!exit){
            System.out.println("No");
        }





        //   printMatrix(matrix);

    }

    public static boolean isValid(int x, int y, char[][] matrix){

        return x < matrix.length && x >= 0 && y < matrix[x].length && y >= 0 && matrix[x][y] != 'x';

    }

    public static void printMatrix(char[][] matrix){

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
