package graphs.truck;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class Main {

    static HashMap<Integer, Node> graph = new HashMap<>();
    static HashSet<Integer> usedNodes = new HashSet<>();
    static PriorityQueue<Edge> availableEdges = new PriorityQueue<>();
    static int maxHeight = Integer.MAX_VALUE;

    public static void main(String[] args) {

        InputReader reader = new InputReader();


        int n = reader.readInt();
        int m = reader.readInt();

        for(int i = 1; i <= n; i++){
            graph.put(i, new Node(i));
        }


        for(int i = 0; i < m; i++){

            int a = reader.readInt();
            int b = reader.readInt();
            int distance = reader.readInt();

            //   availableEdges.add(new Edge(a, b, distance));

            graph.get(a).addEdge(graph.get(b), distance);
            graph.get(b).addEdge(graph.get(a), distance);


        }

        Node start = graph.get(1);

        dfs(start.value);


        System.out.println(maxHeight);


    }

    public static void dfs(int currentNode){

        usedNodes.add(currentNode);

        for (Edge a:
                graph.get(currentNode).edges ) {
            if(usedNodes.contains(a.getB())){
                continue;
            }
            availableEdges.offer(a);
        }

        Edge maxEdge = new Edge(0, 0, 0);

        while(!availableEdges.isEmpty()){

            if(usedNodes.contains(availableEdges.peek().getB())){
                availableEdges.poll();
                continue;
            }

            maxEdge = availableEdges.poll();
            if(maxHeight > maxEdge.getDistance()){
                maxHeight = maxEdge.getDistance();
            }
            break;

        }

        if(maxEdge.getDistance() == 0 && maxEdge.getA() == 0){
            return;
        }

        dfs(maxEdge.getB());

    }


    static class Node{

        private int value;
        private ArrayList<Edge> edges;

        public Node(int value){
            this.value = value;
            edges = new ArrayList<>();
        }

        public void addEdge(Node other, int distance){
            Edge newEdge = new Edge(this.value, other.value, distance);
            edges.add(newEdge);
        }

        public ArrayList<Edge> getEdges() {

            return edges;

        }
    }

    static class Edge implements Comparable<Edge>{

        int a;
        int b;
        int distance;

        public Edge(int a, int b, int distance){
            this.a = a;
            this.b = b;
            this.distance = distance;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public int compareTo(Edge o) {
            return o.distance - this.distance;
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