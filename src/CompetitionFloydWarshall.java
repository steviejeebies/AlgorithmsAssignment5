/**
 * @author: Stephen Rowe (rowes@tcd.ie, ID: 14319662)
 */

/* A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
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
 * This class implements the competition using Floyd-Warshall algorithm
 */


/*
    DISCUSSION OF FLOYD WARSHALL ALGORITHM:
        The Floyd Warshall many-to-many shortest path algorithm does not require the data structures
        (i.e. CityMap and DirectedEdge) that the Dijkstra's implementation requires. The most significant
        space required is a 2D array that (when the algorithm is completed) lists the distance of any given
        vertex (x-axis) to any other vertex (y-axis). As a result, the space complexity Floyd-Warshall is
        O(V^2).

        The time complexities of operations, in order they are carried out:
            - validWalkingSpeed() - O(1), this is a series of constant comparisons and assignments, no loops
            - getMapFromFile() - O(E)* this reads each edge from the text file and assigns the relevant element
                                 of the 2D array the weight value.
            - initialiseArrayInfinite() - O(V^2), this method simply fills every element of the distTo[][] array with
                                the value of Infinity, with the exception of value on the diagonal of the matrix, which
                                are of value 0, since each vertex is a distance of 0 from itself.
            - Floyd Algorithm - O(V^3), as the algorithm consists of three nested for-loops
            - getMaxDistanceOnArray() - O(V^2), goes through the entire array and finds the highest value of the array
                                after all shortest paths have been calculated
            - timeRequiredforCompetition() - O(1), all operations in this method are constant

            * the actual time-complexity of getMapFromFile() is O(V^2 + E), because it calls initialiseArrayInfinite, but
              I split them up for explanation.

            The time complexity of the algorithm is O(V^3)

            One possible improvement to this algorithm that I didn't end up implementing is in the method
            initialiseArrayInfinite(). It may be possible to replace infinite with '0' on the array, and replace '0' on
            the array (for the distance between a node and itself) with some other value, maybe -infinite. What this means
            is that instead of initialiseArrayInfinite() being O(V^2), it could be O(V), as we do not need every value on
            the array, only the diagonal values. However, I figured that the time it would take to implement this, and
            modify all the comparisons in the program in order to allow for this while also producing the correct result,
            out-weighted the benefit of it, as the time complexity of the whole algorithm is O(V^3), and reducing this
            method only from O(V^2) to O(V) has very little impact. In terms of space-complexity, FW uses O(V^2) space,
            which cannot be improve.

            Within IntelliJ IDE, the time taken by FW to carry out all the tests was 4.7s, which was much higher than
            my implementation of Dijkstra (2.05s). The reasons for this is that I made some improvements to Dijkstra
            which helped the running time, in particular with respect to the fact that my Dijkstra algorithm can detect
            invalid city-maps very quickly (unlike FW, which runs through the entire process and is only able to detect
            errors at the end). I was not able to implement many improvements to FW as it is such a simple,
            straight-forward algorithm.
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CompetitionFloydWarshall {
    double distTo[][];

    // Variables that are used by timeRequiredforCompetition()
    int slowestWalkingSpeed;
    double longestDistanceBetweenTwoVertices;

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */
    CompetitionFloydWarshall (String filename, int sA, int sB, int sC){
        longestDistanceBetweenTwoVertices = -1;

        if(validWalkingSpeed(sA, sB, sC))
        {
            getMapFromFile(filename);

            int i, j, k;
            int V = distTo.length;
            for (k = 0; k < V; k++) {
                for (i = 0; i < V; i++) {
                    for (j = 0; j < V; j++) {
                        if (distTo[i][j] > distTo[i][k] + distTo[k][j])
                            distTo[i][j] = distTo[i][k] + distTo[k][j];
                    }
                }
            }

            longestDistanceBetweenTwoVertices = getMaxDistanceOnArray();
        }
    }

    public int timeRequiredforCompetition(){
        if(longestDistanceBetweenTwoVertices == -1) return -1;

        double timeRequired = Math.ceil(((longestDistanceBetweenTwoVertices*1000) / slowestWalkingSpeed));
        return (int) timeRequired;
    }

    private boolean validWalkingSpeed(int sA, int sB, int sC)
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
     * @return int: minimum minutes that will pass before the three contestants can meet
     */

    private void getMapFromFile(String fileName) {
        try {
            File file = new File(fileName);    //creates a new file instance
            Scanner scan = new Scanner(file);
            int numVertices = scan.nextInt();
            scan.nextInt(); // discard the num of edges value included in text file, we don't need it
            distTo = new double[numVertices][numVertices];
            initialiseArrayInfinite(distTo);
            while ((scan.hasNext())) {
                distTo[scan.nextInt()][scan.nextInt()] = scan.nextDouble();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    private void initialiseArrayInfinite(double[][] array) {
        int length = array.length;
        for(int i = 0; i < length; i++) {
            for(int j = 0; j < length; j++){
                array[i][j] = (i == j) ? 0 : Double.POSITIVE_INFINITY;
            }
        }
    }

    private double getMaxDistanceOnArray() {
        double returnValue = -1;
        double value;
        int length = distTo.length;

        for(int i = 0; i < length; i++) {
            for(int j = 0; j < length; j++) {
                value = distTo[i][j];
                if(value == Double.POSITIVE_INFINITY) return -1;
                if(value > returnValue) returnValue = value;
            }
        }
        return returnValue;
    }
}