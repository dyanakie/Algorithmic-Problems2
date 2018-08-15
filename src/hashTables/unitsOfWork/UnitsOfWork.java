package hashTables.unitsOfWork;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class UnitsOfWork {

    private static TreeSet<Unit> allUnits = new TreeSet<>();
    private static HashMap<String, TreeSet<Unit>> types = new HashMap<>();
    private static HashMap<String, Unit> unitNames = new HashMap<>();
    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) {

        InputReader reader = new InputReader();
        String input = "";

        while(!input.equals("end")){

            input = reader.readLine();

            switch (input) {
                case "end":
                    break;

                case "add":

                    String name = reader.readLine();
                    String type = reader.readLine();
                    String attack = reader.readLine();

                    if(unitNames.containsKey(name)){
                        answer.append("\nFAIL: ");
                        answer.append(name);
                        answer.append(" already exists!");


                    }else {

                        String[] commandA = new String[4];
                        commandA[0] = input;
                        commandA[1] = name;
                        commandA[2] = type;
                        commandA[3] = attack;
                        addPlayer(commandA);

                        answer.append("\nSUCCESS: ");
                        answer.append(name);
                        answer.append(" added!");
                    }

                    break;

                case "remove":

                    String name2 = reader.readLine();
                    String[] commandA = new String[4];
                    commandA[0] = input;
                    commandA[1] = name2;

                    if(!unitNames.containsKey(commandA[1])){
                        answer.append("\nFAIL: ");
                        answer.append(commandA[1]);
                        answer.append(" could not be found!");

                    }else{

                        removePlayer(commandA[1]);
                        answer.append("\nSUCCESS: ");
                        answer.append(commandA[1]);
                        answer.append(" removed!");

                    }


                    break;

                case "find":

                    String name3 = reader.readLine();
                    answer.append("\nRESULT: ");

                    if(types.containsKey(name3)){
                        TreeSet<Unit> temp = types.get(name3);
                        int size = 0;

                        for (Unit a:
                                temp) {
                            if(size == temp.size()-1 || size == 9){
                                answer.append(a);
                                break;

                            }else{
                                answer.append(a);
                                answer.append(", ");

                            }

                            size++;
                        }

                    }

                    break;

                case "power":

                    int power = convert(reader.readLine()) ;

                    answer.append("\nRESULT: ");

                    int size = 0;

                    for (Unit a:
                            allUnits) {
                        if(size == allUnits.size()-1 || size == power-1){
                            answer.append(a);
                            break;

                        }else{
                            answer.append(a);
                            answer.append(", ");

                        }

                        size++;
                    }



                    break;



            }

        }

        System.out.print(answer);


    }

    public static void removePlayer(String name){


        Unit temp = unitNames.get(name);

        unitNames.remove(name);
        allUnits.remove(temp);

        String type = temp.getType();
        TreeSet<Unit> a = types.get(type);
        a.remove(temp);
        types.put(type, a);


    }

    public static void addPlayer(String[] input){


        Unit newUnit = new Unit(input[1], input[2], convert(input[3]));

        if (!types.containsKey(input[2])) {
            types.put(input[2], new TreeSet<>());
        }

        TreeSet<Unit> temp = types.get(input[2]);
        temp.add(newUnit);
        allUnits.add(newUnit);
        unitNames.put(input[1], newUnit);
        types.put(input[2], temp);



    }

    public static class Unit implements Comparable{

        String name;
        String type;
        int attack;

        public Unit(String name, String type, int attack){
            this.name = name;
            this.type = type;
            this.attack = attack;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public int getAttack() {
            return attack;
        }

        @Override
        public String toString() {
            return getName()+"["+getType()+"]"+"("+getAttack()+")";
        }

        @Override
        public int compareTo(Object o) {
            Unit unit = (Unit) o;
            int attackCompare = Integer.compare(this.attack, unit.attack);
            if (attackCompare != 0) {
                return -attackCompare;
            }
            return this.name.compareTo(unit.name);
        }
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

    private static int convert(String s) {
        int value = 0;
        for (int i = 0; i < s.length(); i++) {
            value = value * 10 + (s.charAt(i) - '0');
        }
        return value;
    }

}
