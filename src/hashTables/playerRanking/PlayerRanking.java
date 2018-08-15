package hashTables.playerRanking;

//import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class PlayerRanking {

    static ArrayList<Player> allPlayers = new ArrayList<>();
    private static HashMap<String, TreeSet<Player>> types = new HashMap<>();
    static StringBuilder answer = new StringBuilder();


    public static void main(String[] args) {

        InputReader reader = new InputReader();



        while(true){

            String commandO = reader.readLine();
            if(commandO.equals("end")){
                break;
            }

            //  String[] command = commandO.split(" ");

            switch (commandO){

                case "add":

                    String[] command = new String[5];
                    command[1] = reader.readLine();
                    command[2] = reader.readLine();
                    command[3] = reader.readLine();
                    command[4] = reader.readLine();
                    addPlayer(command);
                    continue;

                case "ranklist":

                    answer.append("\n");
                    int a = reader.readInt();
                    int b = reader.readInt();
                    List<Player> theList = new ArrayList<Player>(allPlayers);

                    for(int i = a-1; i <= b-1; i++){

                        if(i == b-1){

                            answer.append(i+1);
                            answer.append(". ");
                            answer.append(theList.get(i).getName());
                            answer.append("(");
                            answer.append(theList.get(i).getAge());
                            answer.append(")");
                            break;

                        }else {

                            answer.append(i+1);
                            answer.append(". ");
                            answer.append(theList.get(i).getName());
                            answer.append("(");
                            answer.append(theList.get(i).getAge());
                            answer.append(")");
                            answer.append("; ");
                        }
                    }

                    continue;

                case "find":

                    String temp = reader.readLine();
                    answer.append("\nType ");
                    answer.append(temp);
                    answer.append(": ");

                    find(temp);


            }



        }

        System.out.println(answer);

    }

    /*add Ivan Aggressive 20 1
    add Pesho Defensive 25 2
    add Georgi Neutral 30 3
    add Stamat Aggressive 22 2
    add Stamat Aggressive 40 1
    find Aggressive
    ranklist 1 5
    add Pencho Neutral 33 2
    find Neutral
    ranklist 1 3
    end*/

    public static void find(String input){

        if(!types.containsKey(input)){
            return;
        }

        short size = 0;

        for (Player a:
                types.get(input)) {
            if(size == 5){
                break;
            }
            //  answer.append(a);
            if(size == types.get(input).size()-1 || size == 4){
                answer.append(a.getName());
                answer.append("(");
                answer.append(a.getAge());
                answer.append(")");
                break;
            }
            answer.append(a.getName());
            answer.append("(");
            answer.append(a.getAge());
            answer.append(")");
            answer.append("; ");
            size++;

        }


    }


    public static void addPlayer(String[] input){


        Player newPlayer = new Player(input[1], input[2], convert(input[3]));



        allPlayers.add(convert(input[4])-1, newPlayer);

        answer.append("\nAdded player ");
        answer.append(input[1]);
        answer.append(" to position ");
        answer.append(input[4]);

        if(!types.containsKey(input[2])){
            types.put(input[2], new TreeSet<>());
        }

        TreeSet<Player> newTreeSet = types.get(input[2]);
        newTreeSet.add(newPlayer);
        if(newTreeSet.size() > 5){
            newTreeSet.pollLast();
        }
        types.put(input[2], newTreeSet);



    }

    public static class Player implements Comparable{

        private int age;
        private String name;
        private String type;

        public Player(String name, String type, int age){

            this.name = name;
            this.age = age;
            this.type = type;

        }

        public String getType() {
            return type;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(Object o) {

            Player player = (Player) o;

            if(!this.name.equals(player.name)){
                return this.name.compareTo(player.name);
            }

            int ageCompare = Integer.compare(this.age, player.age);

            return -ageCompare;

        }

        @Override
        public String toString() {
            return name + " " + type + " " + age ;
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
