package arrays.digits;

import java.io.*;
import java.util.InputMismatchException;

public class Digits {


    public static void main(String[] args) throws IOException{

        InputReader reader = new InputReader();
        OutputWriter writer = new OutputWriter();

        int N = reader.readInt();

        int[][] matrix = new int[N][N];

        for(int i = 0; i < N; i++){

            for(int c = 0; c < N; c++){
                matrix[i][c] = reader.readInt();
            }

        }

        int count = 0;

        for(int i = 0; i < N-4; i++){

            for(int n = 0; n < N; n++){

                if(matrix[i][n] == 1 && i+4 <= N && n - 2 >= 0){


                    if(matrix[i+1][n] == 1 && matrix[i+2][n] == 1 && matrix[i+3][n] == 1 && matrix[i+4][n] == 1 && matrix[i+1][n-1] == 1 && matrix[i+2][n-2] == 1){

                        count += 1;

                    }

                }

                if(matrix[i][n] == 2 && i + 4 < N && n - 1 >= 0 && n+1 < N){

                    if(matrix[i+1][n-1] == 2 && matrix[i+1][n+1]==2 && matrix[i+2][n+1]==2 && matrix[i+3][n]==2 && matrix[i+4][n]==2 && matrix[i+4][n-1] == 2 && matrix[i+4][n+1]==2){
                        count += 2;
                    }

                }

                if(matrix[i][n] == 3 && i + 4 < N  && n+2 < N){

                    if(matrix[i][n+1] == 3 && matrix[i][n+2] == 3 && matrix[i+1][n+2]==3 && matrix[i+2][n+1] ==3&& matrix[i+2][n+2]==3 && matrix[i+3][n+2] ==3 && matrix[i+4][n]==3 && matrix[i+4][n+1]==3 && matrix[i+4][n+2]==3){

                        count +=3;
                    }

                }

                if(matrix[i][n] == 4 && i + 4 < N  && n+2 < N){

                    if(matrix[i][n+2]==4 && matrix[i+1][n]==4 && matrix[i+1][n+2]==4 && matrix[i+2][n]==4 && matrix[i+2][n+1]==4 && matrix[i+2][n+2] == 4 && matrix[i+3][n+2]==4 && matrix[i+4][n+2]==4){
                        count += 4;
                    }
                }

                if(matrix[i][n] == 5 && i + 4 < N  && n+2 < N){

                    if(matrix[i][n+1]==5 && matrix[i][n+2]==5 && matrix[i+1][n]==5 && matrix[i+2][n] == 5 && matrix[i+2][n+1]==5 && matrix[i+2][n+2]==5 && matrix[i+3][n+2]==5 && matrix[i+4][n]==5 && matrix[i+4][n+1]==5 && matrix[i+4][n+2]==5 ){
                        count+= 5;
                    }
                }

                if(matrix[i][n] == 6 && i + 4 < N  && n+2 < N){

                    if(matrix[i][n+1] ==6 && matrix[i][n+2]==6 && matrix[i+1][n]==6 && matrix[i+2][n]==6 && matrix[i+2][n+1]==6 && matrix[i+2][n+2]==6 && matrix[i+3][n]==6 && matrix[i+3][n+2]==6 && matrix[i+4][n]==6 && matrix[i+4][n+1]==6 && matrix[i+4][n+2]==6){
                        count+=6;
                    }

                }

                if(matrix[i][n] == 7 && i + 4 < N  && n+2 < N){

                    if(matrix[i][n+1] == 7 && matrix[i][n+2] == 7 && matrix[i+1][n+2]==7 && matrix[i+2][n+1]== 7 && matrix[i+3][n+1] == 7 && matrix[i+4][n+1] == 7){
                        count+= 7;
                    }

                }

                if(matrix[i][n] == 8 && i + 4 < N  && n+2 < N){

                    if(matrix[i][n+1] == 8 && matrix[i][n+2] == 8 && matrix[i+1][n] == 8 && matrix[i+1][n+2] == 8 && matrix[i+2][n+1] == 8 && matrix[i+3][n] == 8 && matrix[i+3][n+2] == 8 && matrix[i+4][n] == 8 && matrix[i+4][n+1] == 8 && matrix[i+4][n+2] == 8){
                        count+=8;
                    }

                }

                if(matrix[i][n] == 9 && i + 4 < N  && n+2 < N){

                    if(matrix[i][n+1] == 9 && matrix[i][n+2] == 9 && matrix[i+1][n] == 9 && matrix[i+1][n+2] == 9 && matrix[i+2][n+1] == 9 && matrix[i+2][n+2] == 9 && matrix[i+3][n+2] == 9 && matrix[i+4][n] == 9 && matrix[i+4][n+1] == 9 && matrix[i+4][n+2] == 9){
                        count+= 9;
                    }

                }




            }


        }

        writer.printLine(count);
        writer.close();


    }
    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        InputReader() {
            this.stream = System.in;
        }

        int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        int readInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        long readLong() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        double readDouble() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.' && c != ',') {
                if (c == 'e' || c == 'E') {
                    return res * Math.pow(10, readInt());
                }
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.' || c == ',') {
                c = read();
                double m = 1;
                while (!isSpaceChar(c)) {
                    if (c == 'e' || c == 'E') {
                        return res * Math.pow(10, readInt());
                    }
                    if (c < '0' || c > '9') {
                        throw new InputMismatchException();
                    }
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
            return res * sgn;
        }

        String readLine() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
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
