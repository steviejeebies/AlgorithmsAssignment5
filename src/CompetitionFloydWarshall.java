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

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */
    CompetitionFloydWarshall (String filename, int sA, int sB, int sC){
        getMapFromFile(filename);
        int i, j, k;
        int V = distTo.length;
        for (k = 0; k < V; k++)
        {
            // Pick all vertices as source one by one
            for (i = 0; i < V; i++)
            {
                // Pick all vertices as destination for the
                // above picked source
                for (j = 0; j < V; j++)
                {
                    // If vertex k is on the shortest path from
                    // i to j, then update the value of dist[i][j]
                    if (distTo[i][k] + distTo[k][j] < distTo[i][j])
                        distTo[i][j] = distTo[i][k] + distTo[k][j];
                }
            }
        }

        // Print the shortest distance matrix
        printSolution(distTo, V);
    }

    void printSolution(double dist[][], int V)
    {
        System.out.println("The following matrix shows the shortest "+
                "distances between every pair of vertices");
        for (int i=0; i<V; ++i)
        {
            for (int j=0; j<V; ++j)
            {
                if (dist[i][j]==Integer.MAX_VALUE)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j]+"   ");
            }
            System.out.println();
        }
    }

    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){

        //TO DO
        return -1;
    }

    public void getMapFromFile(String fileName) {
        try {
            File file = new File(fileName);    //creates a new file instance
            Scanner scan = new Scanner(file);
            int numVertices = scan.nextInt();
            distTo = new double[numVertices][numVertices];
            initialiseArrayInfinite(distTo);
            while ((scan.hasNext())) {
                distTo[scan.nextInt()][scan.nextInt()] = scan.nextDouble();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void initialiseArrayInfinite(double[][] array) {
        int i = -1, j = -1;
        int length = array.length;
        while(++i < length) {
            while (++j < length) {
                array[i][j] = Integer.MAX_VALUE;
            }
        }
    }
}