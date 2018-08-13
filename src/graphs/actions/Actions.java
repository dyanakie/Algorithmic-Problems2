package graphs.actions;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Actions {

    private static HashMap<Integer, Node> graph = new HashMap<>();
    private static TreeSet<Integer> sources = new TreeSet<>();

    public static void main(String[] args) {


        InputReader reader = new InputReader();

        int n = reader.readInt();
        int m = reader.readInt();

        for(int i = 0; i < n; i++){
            graph.put(i, new Node(i));
            sources.add(i);
        }

        for(int i = 0; i < m; i++){

            int a = reader.readInt();
            int b = reader.readInt();

            graph.get(a).outgoingEdge(graph.get(b));
            sources.remove(b);

        }

        while(sources.size()>0){

            int current = sources.first();
            sources.remove(current);

            System.out.println(current);

            if(graph.get(current).outgoing == null){
                continue;
            }

            for (Node a :
                    graph.get(current).outgoing) {

                a.removeIncoming(graph.get(current));

                if (a.incoming.size() == 0) {
                    sources.add(a.value);
                }

            }


        }


    }

    static class Node{

        private int value;
        private ArrayList<Node> incoming;
        private ArrayList<Node> outgoing;

        public Node(int value){
            this.value = value;
            incoming = new ArrayList<>();
            outgoing = new ArrayList<>();
        }

        public void outgoingEdge(Node b){

            this.outgoing.add(b);
            b.incoming.add(this);

        }

        public void removeIncoming(Node a){
            incoming.remove(a);
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
