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
        double[] distTo;
        boolean[] visited;
        int numVisited;
        // DirectedEdge edgeTo[];
        PriorityQueue<Integer> vertexMinDistPQ;

        CityMap(int size) {
            adjacent = new LinkedList[size];
            numIntersections = size;
            numStreets = 0;
            numVisited = 0;
        }

        public void addEdge(DirectedEdge newEdge) {
            int from = newEdge.getFrom();
            if(adjacent[from] == null) adjacent[from] = new LinkedList<>();
            adjacent[from].add(newEdge);
            numStreets++;
        }

        public void visitVertex(int vertex) {
            visited[vertex] = true;
            numVisited++;
        }

        public boolean stillHasVertexesToVisit() {
            return (numVisited < numIntersections);
        }

        // This method will be called every time a new *source* vertex/intersection
        // is used for Dijkstra's algorithms.
        public void initialiseDijkstra(int sourceNode) {
            sourceNodeDijkstra = sourceNode;

            // restart the minPQ from scratch, no nodes on it yet.
            vertexMinDistPQ = new PriorityQueue<>(new VertexComparator());

            // initialise all the distances in the distTo[] array as ~infinite,
            // except for distTo[sourceNode], which will be 0
            distTo = new double[numIntersections];
            for (int distIter = 0; distIter < numIntersections; distIter++) {
                distTo[distIter] = (distIter == sourceNodeDijkstra) ? 0 : Integer.MAX_VALUE;
                vertexMinDistPQ.add(distIter);
            }

            // no vertices have been visited yet.
            visited = new boolean[numIntersections];
            numVisited = 0;

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
        // public int getNumStreets() { return numStreets; }
    }

    // Variables that are used by timeRequiredforCompetition()
    int slowestWalkingSpeed;
    double longestDistanceBetweenTwoVertices;


    /**
      * @param filename: A filename containing the details of the city road network
      * @param sA, sB, sC: speeds for 3 contestants
      */
    CompetitionDijkstra (String filename, int sA, int sB, int sC){
        // We need to get the slowest walking speed of the three contestants
        System.out.println(filename);
        if(detectWalkingSpeedError(sA, sB, sC))
            longestDistanceBetweenTwoVertices = -1;
        else {
            slowestWalkingSpeed = sA;
            if (sB < slowestWalkingSpeed) slowestWalkingSpeed = sB;
            if (sC < slowestWalkingSpeed) slowestWalkingSpeed = sC;

            // initialise a city map, based on the contents of the text file
            CityMap ourCityMap = getMapFromFile(filename);

            longestDistanceBetweenTwoVertices = dijkstraLongestDistance(ourCityMap);
        }
    }

    public boolean detectWalkingSpeedError(int sA, int sB, int sC)
    {
        if(sA < 50 || sA > 100) return true;
        if(sB < 50 || sB > 100) return true;
        if(sC < 50 || sC > 100) return true;
        return false;
    }

    public double dijkstraLongestDistance(CityMap ourCityMap) {
        int numIntersections = ourCityMap.getNumIntersections();
        double longestDistanceBetweenTwoVertices = -1;

        /* For every intersections in the map, we need to find the shortest distance
         * from that intersection to every other intersection. In other words, many-to-many
         * shortest paths, but while using Dijkstra (which is a Single Source algorithm).
         * As a result, we have to iterate through the vertices/intersections, carrying out
         * Dijkstra's algorithm on each one. We'll find the intersection furthest away from
         * this one, and if the distance between these two intersections is the largest of
         * all possible combinations, then we save this distance for later. */

        for (int vertexSource = 0; vertexSource < numIntersections; vertexSource++) {
            // start a new version of the Dijkstra algorithm from scratch
            ourCityMap.initialiseDijkstra(vertexSource);
            int nextVertexToVisit = vertexSource;

            while(ourCityMap.stillHasVertexesToVisit()) {
                // if we have already visited this vertex, then we need to get the
                // next min-distance vertex on the PQ.
                while (ourCityMap.visited[nextVertexToVisit]) {
                    if (ourCityMap.vertexMinDistPQ.isEmpty()) break;
                    nextVertexToVisit = ourCityMap.vertexMinDistPQ.poll();
                }

                /* if the min-distance vertex on the minPQ is of a distance of Integer.MAX_VALUE,
                 * then this means that there is no route that exists between the source vertex
                 * and this vertex, which means that this is an invalid city map. */

                if (ourCityMap.distTo[nextVertexToVisit] == Integer.MAX_VALUE) return -1;

                // mark this vertex as visited
                ourCityMap.visitVertex(nextVertexToVisit);

                // Relaxing all edges starting from the 'nextVertexToVisit' vertex
                if(ourCityMap.adjacent[nextVertexToVisit] != null) {
                    for (DirectedEdge e : ourCityMap.adjacent[nextVertexToVisit]) {
                        int w = e.getTo();

                        if (ourCityMap.distTo[w] > ourCityMap.distTo[nextVertexToVisit] + e.getWeight()) {
                            ourCityMap.distTo[w] = ourCityMap.distTo[nextVertexToVisit] + e.getWeight();

                            // As the distance to w has definitely been shortened if we are in this if-statement,
                            // then we can throw the new value for w on the MinPQ (assuming we haven't already
                            // visited and relaxed all the vertices of w).
                            if (!ourCityMap.visited[w]) ourCityMap.vertexMinDistPQ.add(w);
                            //ourCityMap.edgeTo[w] = e;
                        }
                    }
                }
            }

            // finally, we need to iterate through the finalised shortest distance paths between the current
            // source and every other vertex, and if any of the distances are greater than the current
            // longestDistanceBetweenTwoVertices, then we update that value.
            for (int j = 0; j < numIntersections; j++) {
                if (ourCityMap.distTo[j] > longestDistanceBetweenTwoVertices)
                    longestDistanceBetweenTwoVertices = ourCityMap.distTo[j];
            }
        }

        return longestDistanceBetweenTwoVertices;
    }

    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){
        if(longestDistanceBetweenTwoVertices == -1)
            return -1;

        double timeRequired = (longestDistanceBetweenTwoVertices*1000) / slowestWalkingSpeed;
        return (int) Math.ceil(timeRequired);
    }

    public static CityMap getMapFromFile(String fileName) {
        CityMap newCityMap;
        try {
            File file = new File(fileName);    //creates a new file instance
            Scanner scan = new Scanner(file);
            newCityMap = new CityMap(scan.nextInt());
            int numEdges = scan.nextInt();
            while ((scan.hasNext())) {
                DirectedEdge newEdge = new DirectedEdge(
                        scan.nextInt(), // from
                        scan.nextInt(), // to
                        scan.nextDouble()); // weight
                newCityMap.addEdge(newEdge);
            }
            return newCityMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
