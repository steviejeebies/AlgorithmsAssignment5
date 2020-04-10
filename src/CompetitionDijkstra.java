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
        // City map related variables/arrays
        LinkedList<DirectedEdge>[] adjacent;
        int numIntersections;
        int numStreets;

        // Dijkstra related variables/arrays
        int sourceNodeDijkstra;
        double distTo[];
        boolean visited[];
        // DirectedEdge edgeTo[];
        PriorityQueue<Integer> vertexMinDistPQ;

        CityMap(int size) {
            adjacent = (LinkedList<DirectedEdge>[]) new LinkedList [size];
            numIntersections = size;
            numStreets = 0;
        }

        public void addEdge(DirectedEdge newEdge) {
            int from = newEdge.getFrom();
            if(adjacent[from] == null) adjacent[from] = new LinkedList<DirectedEdge>();
            adjacent[from].add(newEdge);
            numStreets++;
        }

        // This method will be called every time a new source vertex/intersection
        // is examined with Dijkstra's algorithms.
        public void initialiseDijkstra(int sourceNode) {
            sourceNodeDijkstra = sourceNode;

            // restart the minPQ from scratch, no nodes on it yet.
            vertexMinDistPQ = new PriorityQueue<Integer>(new VertexComparator());

            // initialise all the distances in the distTo[] array as ~infinite,
            // except for distTo[sourceNode], which will be 0
            distTo = new double[numIntersections];
            for (int distIter = 0; distIter < numIntersections; distIter++) {
                distTo[distIter] = (distIter == sourceNodeDijkstra) ? 0 : Integer.MAX_VALUE;
                vertexMinDistPQ.add(distIter);
            }

            // no vertices have been visited yet.
            visited = new boolean[numIntersections];

            //edgeTo = new DirectedEdge[numIntersections];
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

    // Variables that are used by timeRequiredforCompetition()
    int slowestWalkingSpeed;
    int longestDistanceBetweenTwoVertices;
    boolean invalidMap;
    /**
      * @param filename: A filename containing the details of the city road network
      * @param sA, sB, sC: speeds for 3 contestants
      */
    CompetitionDijkstra (String filename, int sA, int sB, int sC){
        // We need to get the slowest walking speed of the three contestants
        slowestWalkingSpeed = sA;
        if(sB < slowestWalkingSpeed) slowestWalkingSpeed = sB;
        if(sC < slowestWalkingSpeed) slowestWalkingSpeed = sC;

        // If the map we are given contains scenarios where the competition is impossible to
        // complete, this boolean will be used in timeRequiredforCompetition() to make sure
        // that we return '-1' to say that it is invalid. Until the Dijkstra shortest paths
        // are created from the text file, then we'll also return -1.
        invalidMap = true;

        // initialise a city map, based on the contents of the text file
        CityMap ourCityMap = getMapFromFile(filename);

        longestDistanceBetweenTwoVertices = dijkstraLongestDistance(ourCityMap);
    }

    public int dijkstraLongestDistance(CityMap ourCityMap)
    {
        int numIntersections = ourCityMap.getNumIntersections();

        // For every intersections in the map, we need to find the shortest distance
        // from that intersection to every other intersection. In other words, many-to-many
        // shortest paths, but while using Dijkstra (which is a Single Source algorithm).
        // As a result, we have to iterate through the vertices/intersections, carrying out
        // Dijkstra's algorithm on each one. We'll find the intersection furthest away from
        // this one, and if the distance between these two intersections is the largest of
        // all possible combinations, then we save this distance for later.
        for(int i = 0; i < numIntersections; i++)
        {
            // start a new version of the Dijkstra algorithm from scratch,
            // using 'i' as the source vertex
            ourCityMap.initialiseDijkstra(i);
            int nextVertexToVisit = i;

            // if we have already visited this vertex, then we need to get the
            // next min-distance vertex on the PQ.
            while(ourCityMap.visited[nextVertexToVisit])
            {
                if(ourCityMap.vertexMinDistPQ.isEmpty()); //TODO
                nextVertexToVisit = ourCityMap.vertexMinDistPQ.poll();
            }

            ourCityMap.visited[nextVertexToVisit]= true;

            // we need to relax all the edges coming from this vertex

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
