package hashTables.cooking;

import java.io.*;
import java.util.*;


public class Cooking {


    public static double special = 0.0;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        //   String[] recipee = new String[n];
        HashMap<String, String[]> usedMap = new HashMap<>();
        HashMap<String, String[]> recipeeMap = new HashMap<>();
        ArrayList<String> recipeeNames = new ArrayList<>();

        for(int i = 0; i < n; i++){


            String[] temp = br.readLine().split(":");

            if(temp.length<3){
                continue;
            }

            if(recipeeMap.containsKey(temp[2].toLowerCase())){

                recipeeMap.put(temp[2].toLowerCase(), addRepeating(recipeeMap.get(temp[2].toLowerCase()), temp));

            }else{
                recipeeNames.add(temp[2].toLowerCase());
                recipeeMap.put(temp[2].toLowerCase(), temp);
            }


        }

        // izpolzvanite


        int m = Integer.parseInt(br.readLine());

        for(int i = 0; i < m; i++){

            String[] temp = br.readLine().split(":");

            if(temp.length<3){
                continue;
            }

            if(usedMap.containsKey(temp[2].toLowerCase())){

                usedMap.put(temp[2].toLowerCase(), addRepeating(usedMap.get(temp[2].toLowerCase()), temp));

            }else{
                usedMap.put(temp[2].toLowerCase(), temp);
            }

        }


        // Tuka stava veche compare-a

        for(int i = 0; i < recipeeNames.size(); i++){

            if(usedMap.containsKey(recipeeNames.get(i))){

                compare(recipeeMap.get(recipeeNames.get(i)), usedMap.get(recipeeNames.get(i)));

            }else{
                String[] temp = recipeeMap.get(recipeeNames.get(i));

                Double digit = Double.parseDouble(temp[0]);
                digit += 0.0001;
                String s = String.format("%.2f", digit);

                System.out.println(s+":"+temp[1]+":"+temp[2]);

            }


        }


         /*for(int i = 0; i < recipeeNames.size(); i++){

            String[] temp = recipeeMap.get(recipeeNames.get(i).toLowerCase());

            System.out.println(Arrays.toString(temp));

        }*/

        /*System.out.println("\n\n\n\n");
        System.out.println(special);*/










    }




















    public static String[] addRepeating(String[] previous, String[] current){


        /*System.out.println("ARRAYS:");
        System.out.println(Arrays.toString(previous));
        System.out.println(Arrays.toString(current));*/

        Double currentDouble = scoobyDo(Double.parseDouble(current[0]), current[1]);

        Double previousDouble = scoobyDo(Double.parseDouble(previous[0]), previous[1]);

        Double newDouble = currentDouble + previousDouble;

        newDouble = convert(newDouble, previous[1]);

        //   System.out.println(newDouble);

        //  String s = String.format("%.2f", newDouble);

        previous[0] = newDouble.toString();

        return previous;
    }

    public static void compare(String[] recipeeProduct, String[] addedProduct){


       /*Arrays.stream(recipeeProduct).forEach(x -> System.out.print(x + " "));
       Arrays.stream(addedProduct).forEach(x -> System.out.print(x + " "));*/

        if(recipeeProduct[1].equals(addedProduct[1])){

            if(Double.parseDouble(addedProduct[0]) >= Double.parseDouble(recipeeProduct[0])){
                return;
            }else{
                Double difference = Double.parseDouble(recipeeProduct[0] )-Double.parseDouble(addedProduct[0]);
                difference += 0.0001;
                String temp = String.format("%.2f", difference);
                //  System.out.println(temp + ":" + recipeeProduct[1]+":"+recipeeProduct[2]);
                System.out.println(temp + ":" + recipeeProduct[1]+":"+recipeeProduct[2]);
                return;
            }
        }

        Double addedProductMls = scoobyDo(Double.parseDouble(addedProduct[0]), addedProduct[1]);
        Double recipeeProductMls = scoobyDo(Double.parseDouble(recipeeProduct[0]), recipeeProduct[1]);

       /*System.out.println(addedProductMls);
       System.out.println(recipeeProductMls);*/

        if(addedProductMls >= recipeeProductMls){
            return;
        }

        Double missingQuantity = recipeeProductMls - addedProductMls;

        missingQuantity = convert(missingQuantity, recipeeProduct[1]);

        missingQuantity += 0.0001;

        String s = String.format("%.2f", missingQuantity);


        System.out.println(s+":" + recipeeProduct[1] + ":" + recipeeProduct[2]);
        // return s+":" + recipeeProduct[1] + ":" + recipeeProduct[2];
        return;




    }

    public static Double convert(Double quantity, String measurementUnit){


        Double quantityRevised = 0.0;

        measurementUnit = checkMeasurementUnit(measurementUnit);


        switch (measurementUnit){

            case "mls":
                quantityRevised = quantity;
                break;

            case "ls":
                quantityRevised = quantity/1000;
                break;

            case "tsps":
                quantityRevised = quantity/5;
                break;

            case "tbsps":
                quantity /= 3;
                quantityRevised = quantity/5;
                break;

            case "cups":
                quantity /= 48;
                quantityRevised = quantity/5;
                break;

            case "fl ozs":
                quantityRevised = quantity/5;
                quantityRevised /= 48;
                quantityRevised *= 8;
                break;

            case "pts":
                quantity /= 2;
                quantityRevised = quantity/48;
                quantityRevised /= 5;
                break;

            case "qts":
                quantity /= 2;
                quantity /= 2;
                quantityRevised = quantity/48;
                quantityRevised /= 5;
                break;

            case "gals":
                quantity /= 4;
                quantity /= 2;
                quantity /= 2;
                quantityRevised = quantity/48;
                quantityRevised /= 5;
                break;
        }

        return quantityRevised;

    }


    public static Double scoobyDo(Double quantity, String measurementUnit){

        //  String unit = measurementUnit;
        Double quantityRevised = 0.0;

        measurementUnit = checkMeasurementUnit(measurementUnit);




        switch (measurementUnit){

            case "mls":
                quantityRevised = quantity;
                break;

            case "ls":
                quantityRevised = quantity*1000;
                break;

            case "tsps":
                quantityRevised = quantity*5;
                break;

            case "tbsps":
                quantity *= 3;
                quantityRevised = quantity*5;
                break;

            case "cups":
                quantity *= 48;
                quantityRevised = quantity*5;
                break;

            case "fl ozs":
                quantity /= 8;
                quantityRevised = quantity*48;
                quantityRevised *= 5;
                break;

            case "pts":
                quantity *= 2;
                quantityRevised = quantity*48;
                quantityRevised *= 5;
                break;

            case "qts":
                quantity *= 2;
                quantity *= 2;
                quantityRevised = quantity*48;
                quantityRevised *= 5;
                break;

            case "gals":
                quantity *= 4;
                quantity *= 2;
                quantity *= 2;
                quantityRevised = quantity*48;
                quantityRevised *= 5;
                break;
        }

        quantityRevised += 0.0001;

        return quantityRevised;

    }

    public static String checkMeasurementUnit(String unit){

        if(unit.equals("tablespoons")){
            return "tbsps";
        }

        if(unit.equals("teaspoons")){
            return "tsps";
        }

        if(unit.equals("liters")){
            return "ls";
        }

        if(unit.equals("milliliters")){
            return "mls";
        }

        if(unit.equals("fluid ounces")){
            return "fl ozs";
        }

        if(unit.equals("gallons")){
            return "gals";
        }

        if(unit.equals("pints")){
            return "pts";
        }

        if(unit.equals("quarts")){
            return "qts";
        }

        return unit;

    }


}
