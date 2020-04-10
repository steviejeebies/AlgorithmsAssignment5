/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CompetitionDijkstra {
    static class DirectedEdge {
        private int from;
        private int to;
        private double weight;

        DirectedEdge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int getFrom() { return this.from; }
        public int getTo() { return this.to; }
        public double getWeight() { return this.weight; }
    }

    static class CityMap {
        LinkedList<DirectedEdge>[] adjacent;
        int numIntersections;
        int numStreets;

        int sourceNodeDijkstra;
        double distTo[];
        DirectedEdge edgeTo[];
        PriorityQueue<Integer> vertexMinDistPQ;

        CityMap(int size) {
            adjacent = (LinkedList<DirectedEdge>[]) new LinkedList [size];
            numIntersections = size;
            numStreets = 0;
        }

        // This method will be called every time a new source vertex/intersection
        // is examined with Dijkstra's algorithms.
        public void initialiseDijkstra(int sourceNode) {
            sourceNodeDijkstra = sourceNode;
            distTo = new double[numIntersections];
            edgeTo = new DirectedEdge[numIntersections];
            vertexMinDistPQ = new PriorityQueue<Integer>(numIntersections, new VertexComparator());
        }

        public void addEdge(DirectedEdge newEdge) {
            int from = newEdge.getFrom();
            if(adjacent[from] == null) adjacent[from] = new LinkedList<DirectedEdge>();
            adjacent[from].add(newEdge);
            numStreets++;
        }

        // This comparator is used by our vertexMinDistPQ to get the vertex of
        // shortest distance from the currently examined vertex. 
        class VertexComparator implements Comparator<Integer>{
            @Override
            public int compare(Integer v1, Integer v2) {
                if (distTo[v1] < distTo[v2])
                    return 1;
                else if (distTo[v1] > distTo[v2])
                    return -1;
                return 0;
            }
        }

        public int getNumIntersections() { return numIntersections; }
        public int getNumStreets() { return numStreets; }
    }

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */
    CompetitionDijkstra (String filename, int sA, int sB, int sC){
        PriorityQueue<DirectedEdge> myPQ = new PriorityQueue<DirectedEdge>();
        int slowestWalkingSpeed = sA;
        if(sB < slowestWalkingSpeed) slowestWalkingSpeed = sB;
        if(sC < slowestWalkingSpeed) slowestWalkingSpeed = sC;
        CityMap ourCityMap = getMapFromFile(filename);
        int numIntersections = ourCityMap.getNumIntersections();

        int longestDistanceBetweenTwoVertices = -1;

        // For every intersections in the map, we need to find the shortest distance
        // from that intersection to every other intersection. In other words, many-to-many
        // shortest paths, but while using Dijkstra (which is a Single Source algorithm).
        // As a result, we have to iterate through the vertices/intersections, carrying out
        // Dijkstra's algorithm on each one. We'll find the intersection furthest away from
        // this one, and if the distance between these two intersections is the largest of
        // all possible combinations, then we save this distance for later.
        for(int i = 0; i < numIntersections; i++)
        {


            for (int distIter = 0; distIter < numIntersections; distIter++)
                distTo[distIter] = Integer.MAX_VALUE;

            distTo[i] = 0;


        }

    }


    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){
        //TODO

        return -1;
    }

    public static CityMap getMapFromFile(String fileName) {
        CityMap newCityMap;
        try {
            File file = new File(fileName);    //creates a new file instance
            FileReader ourFileReader = new FileReader(file);   //reads the file
            BufferedReader ourBufferedReader = new BufferedReader(ourFileReader);

            // get number of vertices, and create a new CityMap object
            newCityMap = new CityMap(Integer.parseInt(ourBufferedReader.readLine()));
            int numEdges = Integer.parseInt(ourBufferedReader.readLine());
            int i = 0;
            String str;
            while ((str = ourBufferedReader.readLine()) != null) {
                String[] arrOfStr = str.split(" "); // split line into three (string) numbers
                // parse as ints and pass as parameters to create new edges on our city map
                DirectedEdge newEdge = new DirectedEdge(
                        Integer.parseInt(arrOfStr[0]),
                        Integer.parseInt(arrOfStr[1]),
                        Double.parseDouble(arrOfStr[2]));
                newCityMap.addEdge(newEdge);
            }
            ourFileReader.close();    //closes the stream and release the resources
            return newCityMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String [] args) {
        CityMap ourCityMap = getMapFromFile("input-B.txt");
    }

}
