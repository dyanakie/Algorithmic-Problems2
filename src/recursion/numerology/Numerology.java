package recursion.numerology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Numerology {

    static int[] result = new int[10];

    public static int Calculate(int a, int b){

        return (a + b) * (a ^ b) % 10;
    }

    public static void Numerology(StringBuilder number){

        if(number.length() == 1){

            result[Integer.parseInt(number.toString())]++;
            return;

        }

        for(int i = 0; i < number.length()-1; i++){

            String old = number.toString();
            int a = Character.getNumericValue(number.charAt(i));
            int b = Character.getNumericValue(number.charAt(i+1));

            int c = Calculate(a, b);

            number = number.replace(i, i+2, Integer.toString(c));

            Numerology(number);

            number = new StringBuilder(old);



        }

    }

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String number = br.readLine();

        StringBuilder numb = new StringBuilder(number);
        Numerology(numb);

        for(int i = 0; i < result.length; i++){

            System.out.print(result[i] + " ");

        }

    }
}