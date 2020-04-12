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
 * This class implements the competition using Floyd-Warshall algorithm
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
        if(longestDistanceBetweenTwoVertices == -1)
            return -1;

        double timeRequired = (longestDistanceBetweenTwoVertices*1000) / slowestWalkingSpeed;
        return (int) Math.ceil(timeRequired);
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
        int i = 0;
        int j;
        int length = distTo.length;

        while(i < length) {
            j = 0;
            while (j < length) {
                value = distTo[i][j++];
                if(value == Double.POSITIVE_INFINITY) return -1;
                if(value > returnValue) returnValue = value;
            }
            i++;
        }
        return returnValue;
    }
}