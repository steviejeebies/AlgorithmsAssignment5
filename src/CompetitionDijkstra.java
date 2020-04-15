/**
 * @author: Stephen Rowe (rowes@tcd.ie, ID: 14319662)
 */

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

/*
    DISCUSSION OF DIJKSTRA'S ALGORITHM:

    Dijkstra's algorithm is a one-to-many shortest path algorithm, and as a result, I had to implement the program
    so that it carried out the algorithm on a given source node, find the distance between this source node and every
    other node, then move on to the next source node (until we have gone through them all).

    The time complexity of a many-to-many implementation of Dijkstra's algorithm is O(V^3logV). The implementation
    is shared between this class and the CityMap class.

    We have vertices, which we can just treat as indexes (since they are just numbers), and we have all of the edges,
    for each of which we create an instance of DirectedEdge and add it to our CityMap class. A small variation I made
    in this program is that the priority queue for vertices does not implement decreaseKey() when when we have shortened
    the distance to an edge that is already on the queue. Instead, if a vertex is already on the queue, I leave it there,
    and also add the vertex to the queue again with respect to the lower distance (unless the vertex that would be added
    to the PQ is one that we have already visited and processed). This way, I can filter out values popped from the
    priority queue if we have already visited this node with an if-statement, resulting in little or no detriment to
    the time-complexity to the algorithm. Adding to the priotity queue is where the 'logV' comes in with respect to the
    time-complexity, as every vertex will be added to the priority queue.

    Method time-complexity (some of these methods are in CityMap.java)
    - validWalkingSpeed() - O(1), same as FW
    - addEdge() - O(1) - add an edge read from the text file to city map
    - restartDijkstra() - O(V) - have to reset the Dijkstra related arrays entirely for each vertex that we are using
    as source node.
    - getClosestVertex() - O(logV) removes the min value from the PQ, and marks it as visited on visited[].
    - dijkstraLongestDistance - O(V^3(logV)) - this has the structure of Dijkstra's algorithms, calling the relevant
    methods from City Map in order to either add or remove a vertex from the priority queue, while also iterating
    through all of the adjacent edges to a vertex to see if they provide a shorter path.
    - findCurrentLongestDistanceBetweenTwoVertices() - O(V), this iterates through distTo[] and finds the longest
    shortest path.
    - timeRequiredForCompetition() - O(N), same as FW implementation

    The improvements that I made to this algorithm is that the algorithm is able to detect very quickly if the map is
    invalid, meaning that there is no wasted time spent on a map that cannot be used on A Contest To Meet. For example,
    the method getClosestVertex() checks for all scenarios that a vertex would be invalid (e.g. infinite distance to the
    vertex that was popped, therefore there is no possible route between this vertex and the source vertex). It will
    usually be able to detect these issues for the vast majority of cases within the first iteration of Dijkstra (i.e.
    with the first source vertex), but there are exceptions (such as, if there is a route from 'a' to 'z', but no route
    from 'z' to 'a', as the edges are directed).




    
 */

import java.util.*;

public class CompetitionDijkstra {
     // Variables that are used by timeRequiredforCompetition()
    int slowestWalkingSpeed;
    double longestDistanceBetweenTwoVertices;

    /**
      * @param filename: A filename containing the details of the city road network
      * @param sA, sB, sC: speeds for 3 contestants
      */
    CompetitionDijkstra (String filename, int sA, int sB, int sC){
        longestDistanceBetweenTwoVertices = -1;
        if(validWalkingSpeed(sA, sB, sC)) {
            // initialise a city map, based on the contents of the text file
            CityMap ourCityMap = new CityMap(filename);
            // start Dijkstra algorithm
            longestDistanceBetweenTwoVertices = dijkstraLongestDistance(ourCityMap);
        }
    }

    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition() {
        if(longestDistanceBetweenTwoVertices == -1) return -1;

        double timeRequired = Math.ceil(((longestDistanceBetweenTwoVertices*1000) / slowestWalkingSpeed));
        return (int) timeRequired;
    }

    /**
     * Checks that the average walking speeds inputted are valid based on the Assignment instructions,
     * and finds the slowest walking contestant of the three.
     * @param sA, sB, sC: speeds for 3 contestants
     * @return true if, for any walking speed s, 50 <= s <= 100
     */
    public boolean validWalkingSpeed(int sA, int sB, int sC)
    {
        if((sA >= 50 && sA <= 100) && (sB >= 50 && sB <= 100) && (sC >= 50 && sC <= 100))
        {
            slowestWalkingSpeed = sA;
            if (sB < slowestWalkingSpeed) slowestWalkingSpeed = sB;
            if (sC < slowestWalkingSpeed) slowestWalkingSpeed = sC;
            return true;
        }
        return false;
    }

    /**
     * Many-to-many implementation of Dijkstra's algorithm. Out of all the shortest
     * paths, this method returns the *longest* shortest-path from any node to any
     * other node on the city map.
     *
     * @param ourCityMap: A city map that has already been constructed from a text file
     * @return length of the longest shortest-path from any node to any other node
     */
    public double dijkstraLongestDistance(CityMap ourCityMap) {
        int numIntersections = ourCityMap.getNumIntersections();
        int nextVertexToVisit;
        LinkedList<DirectedEdge> edgesStartingFromThisVertex;
        double currentLongestDistance = -1;

        /* For every intersections in the map, we need to find the shortest distance
         * from that intersection to every other intersection. In other words, many-to-many
         * shortest paths, but while using Dijkstra (which is a Single Source algorithm).
         * As a result, we have to iterate through the vertices/intersections, carrying out
         * Dijkstra's algorithm on each one. We'll find the intersection furthest away from
         * this one, and if the distance between these two intersections is the largest of
         * all possible combinations, then we save this distance for later. */

        for (int vertexSource = 0; vertexSource < numIntersections; vertexSource++) {
            // start a new version of the Dijkstra algorithm from scratch
            ourCityMap.restartDijkstra(vertexSource);

            while(ourCityMap.stillHasVertexesToVisit()) {
                edgesStartingFromThisVertex = ourCityMap.getClosestVertex();
                /* if vertexesWithEdges is null here, then one of the following three scenarios have happened:
                 * (1) The vertex that was taken from the PQ has a distance of infinity from the source node, meaning
                 *     that there is no possible path from source to this node.
                 * (2) There are no edges that start from this vertex (i.e. if a contestant starts at this
                 *     intersection, then they can't leave it
                 * (3) There's nothing left on the PQ, in which case not enough vertexes were added to the PQ.
                 * In all three cases, if this scenario happens while calculating the Dijkstra shortest path, then
                 * this map is invalid for A Content to Meet, and we can end the program early. */

                if(edgesStartingFromThisVertex == null) return -1;

                nextVertexToVisit = edgesStartingFromThisVertex.get(0).getFrom();   /* this is just a quick way of getting the
                                                                         * number of the vertex that we just took off
                                                                         * the PQ. I did it this way because
                                                                         * getClosestVertex() returns a linked list,
                                                                         * not an Int */

                // Relaxing all edges starting from the 'nextVertexToVisit' vertex
                for (DirectedEdge e : edgesStartingFromThisVertex) {
                    int w = e.getTo();

                    if (ourCityMap.distTo[w] > ourCityMap.distTo[nextVertexToVisit] + e.getWeight()) {
                        ourCityMap.distTo[w] = ourCityMap.distTo[nextVertexToVisit] + e.getWeight();

                        /* As the distance to w has definitely been shortened if we are in this if-statement,
                         * then we can throw the new value for w on the MinPQ (assuming we haven't already
                         * visited and relaxed all the vertices of w). */
                        if (!ourCityMap.visited[w]) ourCityMap.addToPQ(w);
                    }
                }
            }
            currentLongestDistance = ourCityMap.findCurrentLongestDistanceBetweenTwoVertices(currentLongestDistance);
            if(currentLongestDistance == -1) return -1;
        }
        return currentLongestDistance;
    }
}
