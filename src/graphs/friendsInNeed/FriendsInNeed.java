package graphs.friendsInNeed;

import java.io.*;
import java.util.*;

public class FriendsInNeed {

    private static int n;
    private static int INF = 999;



    public static void main (String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);    // total points
        int m = Integer.parseInt(input[1]);    // streets
        int h = Integer.parseInt(input[2]);    // hospitals

        String[] hospitals = br.readLine().split(" ");

        HashSet<Integer> hospitalsCheck = new HashSet<>();

        for(int i = 0; i < hospitals.length; i++){
            hospitalsCheck.add(Integer.parseInt(hospitals[i]));
        }

        int[][] matrix = new int[n+1][n+1];

        for(int i = 1; i < n+1; i++){
            for(int r = 1; r < n+1; r++){

                if(i == r) {
                    matrix[i][r] = 0;
                }else{
                    matrix[i][r] = INF;
                }

            }

        }

        for(int i = 0; i < m; i++){

            input = br.readLine().split(" ");

            matrix[Integer.parseInt(input[0])][Integer.parseInt(input[1])] = Integer.parseInt(input[2]);
            matrix[Integer.parseInt(input[1])][Integer.parseInt(input[0])] = Integer.parseInt(input[2]);

        }

        //  printMatrix(matrix);



        int[] hospitalsEveryDistance = new int[hospitals.length];

        for(int i = 0; i < hospitals.length; i++){
            int currentHospital = Integer.parseInt(hospitals[i]);

            int[] distances = findDistance(currentHospital, matrix);

            /*System.out.println("hospital: " + currentHospital);
            for(int e = 0; e < distances.length; e++){
                System.out.println(distances[e]);
            }*/


            for(int r = 0; r < distances.length; r++){

                if(hospitalsCheck.contains(r)){
                    distances[r] = 0;
                }

                hospitalsEveryDistance[i] += distances[r];
            }
        }

        int min = 10000000;

        for (int a:
                hospitalsEveryDistance) {
            if(a < min){
                min = a;
            }
        }

        System.out.println(min);



    }

    public static int[] findDistance(int currentHospital, int[][] matrix){

        int[] distances = new int[n+1];
        boolean[] used = new boolean[n+1];

        for(int i = 1; i < distances.length; i++){
            distances[i] = INF;
            used[i] = false;
        }

        distances[currentHospital] = 0;

        for(int i = 1; i < n+1; i++){

            int currentNode = chooseNode(distances, used);
            used[currentNode] = true;

            for(int r = 1; r < n+1; r++){

                if(!used[r] && matrix[currentNode][r] != INF && distances[r] > distances[currentNode] + matrix[currentNode][r]){
                    distances[r] = distances[currentNode] + matrix[currentNode][r];
                }

            }
        }

        return distances;



    }

    public static int chooseNode(int[] distances, boolean[] used){

        int min = Integer.MAX_VALUE;
        int index = -1;

        for(int i = 1; i < distances.length; i++){

            if(!used[i] && distances[i] < min){
                min = distances[i];
                index = i;
            }

        }

        return index;

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

}