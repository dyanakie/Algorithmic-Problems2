package recursion.portals;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;


public class Portals {

    static int highest = 0;

    public static void teleport(String[][] matrix, int x, int y, int power, boolean[][] matrixCheck){

        matrixCheck[x][y] = true;

        if(power > highest){
            highest = power;
        }

        if(matrix[x][y].equals("#")){
            return;
        }

        int tempPower = Integer.parseInt(matrix[x][y]);

        if(x + tempPower < matrix.length && !matrixCheck[x+tempPower][y] && !matrix[x+tempPower][y].equals("#")){

            power += tempPower;
            teleport(matrix, x+tempPower, y, power,matrixCheck);
            power -= tempPower;
        }

        if(x - tempPower >=0 && !matrixCheck[x-tempPower][y] && !matrix[x-tempPower][y].equals("#")){

            power += tempPower;
            teleport(matrix, x-tempPower, y, power, matrixCheck);
            power -= tempPower;
        }

        if(y + tempPower < matrix[x].length && !matrixCheck[x][y+tempPower]&& !matrix[x][y+tempPower].equals("#")){

            power += tempPower;
            teleport(matrix, x, y+tempPower, power, matrixCheck);
            power -= tempPower;

        }

        if(y - tempPower >= 0 && !matrixCheck[x][y-tempPower]&& !matrix[x][y-tempPower].equals("#")){

            power += tempPower;
            teleport(matrix, x, y-tempPower, power, matrixCheck);
            power -= tempPower;
        }


        if(power +tempPower > highest){
            if(x + tempPower < matrix.length && !matrix[x+tempPower][y].equals("#")|| x - tempPower >=0 && !matrix[x-tempPower][y].equals("#")||y + tempPower < matrix[x].length && !matrix[x][y+tempPower].equals("#") || y - tempPower >= 0 && !matrix[x][y-tempPower].equals("#")) {
                highest = tempPower + power;
            }
            //  highest = tempPower + power;
        }

        matrixCheck[x][y] = false;

    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int x = Integer.parseInt(input[0]);
        int y = Integer.parseInt(input[1]);

        String[] input2 = br.readLine().split(" ");
        int n = Integer.parseInt(input2[0]);
        int m = Integer.parseInt(input2[1]);

        String[][] matrix = new String[n][m];

        for(int i = 0; i < n; i++){

            String[] temp = br.readLine().split(" ");

            for(int r = 0; r < temp.length; r++){

                matrix[i][r] = temp[r];

            }

        }

        boolean[][] matrixCheck = new boolean[n][m];

        teleport(matrix, x, y, 0, matrixCheck);

        System.out.println(highest);



    }
}
