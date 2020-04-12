import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class CityMap {
    // City map related variables/arrays
    LinkedList<DirectedEdge>[] adjacentEdges;
    int numIntersections;
    int numStreets;

    // Dijkstra related variables/arrays
    int sourceVertexDijkstra;
    double[] distTo;
    boolean[] visited;
    int numVisited;
    PriorityQueue<Integer> vertexMinDistPQ;
    // DirectedEdge edgeTo[];       // we don't need to retain this list of edges for this program

    CityMap(String filename) {
        getMapFromFile(filename);
        numVisited = 0;
    }

    public void addEdge(DirectedEdge newEdge) {
        int from = newEdge.getFrom();
        if (adjacentEdges[from] == null) adjacentEdges[from] = new LinkedList<DirectedEdge>();
        adjacentEdges[from].add(newEdge);
        numStreets++;
    }

    // This method will be called every time a new *source* vertex/intersection
    // is used for Dijkstra's algorithms.
    public void restartDijkstra(int sourceVertex) {
        sourceVertexDijkstra = sourceVertex;

        // restart the minPQ from scratch, no nodes on it yet.
        vertexMinDistPQ = new PriorityQueue<>(new VertexComparator());

        // initialise all the distances in the distTo[] array as ~infinite,
        // except for distTo[sourceNode], which will be 0
        distTo = new double[numIntersections];
        for (int distIter = 0; distIter < numIntersections; distIter++)
            distTo[distIter] = Double.POSITIVE_INFINITY;
        distTo[sourceVertexDijkstra] = 0;

        // no vertices have been visited yet.
        visited = new boolean[numIntersections];
        numVisited = 0;
        addToPQ(sourceVertex);
    }

    public LinkedList<DirectedEdge> getClosestVertex(){
        while(true) {
            Integer minDistanceVertex = vertexMinDistPQ.poll();
            if(minDistanceVertex == null) return null;  // if PQ empty
            if(distTo[minDistanceVertex] == Double.POSITIVE_INFINITY) return null; // if no path from source to this vertex
            if(visited[minDistanceVertex]) continue;    // if node already visited

            // if sucessfully found min vertex
            visited[minDistanceVertex] = true;
            numVisited++;
            return adjacentEdges[minDistanceVertex];
        }
    }

    public boolean stillHasVertexesToVisit() {
        return (numVisited < numIntersections);
    }

    public void addToPQ(int value){
        if(vertexMinDistPQ != null) vertexMinDistPQ.add(value);
    }

    public double updateLongestDistanceBetweenTwoVertices(double currentLongestDistance) {
        if(distTo == null) return -1;
        for (int j = 0; j < numIntersections; j++) {
            if (distTo[j] > currentLongestDistance)
                currentLongestDistance = distTo[j];
        }
        if(currentLongestDistance == Double.POSITIVE_INFINITY) return -1;
        return currentLongestDistance;
    }

    // This comparator is used by our vertexMinDistPQ to get the vertex of
    // shortest distance from the currently examined vertex.
    class VertexComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer v1, Integer v2) {
            if (distTo[v1] > distTo[v2])
                return 1;
            else if (distTo[v1] < distTo[v2])
                return -1;
            return 0;
        }
    }

    public int getNumIntersections() { return numIntersections; }
    // public int getNumStreets() { return numStreets; }        // unused method

    public void getMapFromFile(String fileName) {
        try {
            File file = new File(fileName);    //creates a new file instance
            Scanner scan = new Scanner(file);
            numIntersections = scan.nextInt();
            adjacentEdges = (LinkedList<DirectedEdge>[]) new LinkedList[numIntersections];
            scan.nextInt();  // discard the num of edges value included in text file, we don't need it
            while ((scan.hasNext())) {
                DirectedEdge newEdge = new DirectedEdge(
                        scan.nextInt(), // from
                        scan.nextInt(), // to
                        scan.nextDouble()); // weight
                addEdge(newEdge);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}