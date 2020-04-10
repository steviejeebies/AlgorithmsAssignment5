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
    class DirectedEdge {
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

    class CityMap {
        LinkedList<DirectedEdge> [] adjacent;
        int index;

        CityMap(int size) {
            adjacent = (LinkedList<DirectedEdge>[]) new LinkedList [size];
            index = 0;
        }

        public void addEdge(int from, int to, double weight) {
            adjacent[from].add(new DirectedEdge(from, to, weight));
        }
    }

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */
    CompetitionDijkstra (String filename, int sA, int sB, int sC){

    }


    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){

        //TO DO
        return -1;
    }

    public static double[] getDoubleArrayFromTextFile(String fileName, int arraySize) {
        double[] returnArray = new double[arraySize];
        int i = 0; // array index
        try {
            File file = new File(fileName);    //creates a new file instance
            FileReader ourFileReader = new FileReader(file);   //reads the file
            BufferedReader ourBufferedReader = new BufferedReader(ourFileReader);
            while (i < arraySize)
                returnArray[i++] = Double.parseDouble(ourBufferedReader.readLine());
            ourFileReader.close();    //closes the stream and release the resources
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnArray;
    }

}
