/**
 * @author: Stephen Rowe (rowes@tcd.ie, ID: 14319662)
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CompetitionTests {

    @Test
    public void testDijkstraConstructor() {
        // Testing all of the files included in the input zip folder
        CompetitionDijkstra ourDijkstraVersion;
        ourDijkstraVersion = new CompetitionDijkstra("input-A.txt", 70, 70, 70);
        assertEquals(ourDijkstraVersion.timeRequiredForCompetition(), -1);
        ourDijkstraVersion = new CompetitionDijkstra("input-B.txt", 60,80,50);
        assertEquals(ourDijkstraVersion.timeRequiredForCompetition(), 10000);
        ourDijkstraVersion = new CompetitionDijkstra("input-C.txt", 60, 70, 70);
        assertEquals(ourDijkstraVersion.timeRequiredForCompetition(), -1);
        ourDijkstraVersion = new CompetitionDijkstra("input-D.txt", 50,80,60);
        assertEquals(ourDijkstraVersion.timeRequiredForCompetition(), 38);
        ourDijkstraVersion = new CompetitionDijkstra("input-E.txt", 70, 70, 70);
        assertEquals(ourDijkstraVersion.timeRequiredForCompetition(), 20);
        ourDijkstraVersion = new CompetitionDijkstra("input-F.txt", 70, 70, 70);
        assertEquals(ourDijkstraVersion.timeRequiredForCompetition(), -1);
        ourDijkstraVersion = new CompetitionDijkstra("input-G.txt", 70, 70, 70);
        assertEquals(ourDijkstraVersion.timeRequiredForCompetition(), -1);
//        ourDijkstraVersion = new CompetitionDijkstra("input-H.txt", 70, 70, 70);
//        assertEquals(ourDijkstraVersion.timeRequiredForCompetition(), -1);
        ourDijkstraVersion = new CompetitionDijkstra("input-I.txt", 72,70,65);
        assertEquals(ourDijkstraVersion.timeRequiredForCompetition(), 185);
        ourDijkstraVersion = new CompetitionDijkstra("input-J.txt", 70, 70, 70);
        assertEquals(ourDijkstraVersion.timeRequiredForCompetition(), -1);
        ourDijkstraVersion = new CompetitionDijkstra("input-K.txt", 51,70,88);
        assertEquals(ourDijkstraVersion.timeRequiredForCompetition(), 314);
        ourDijkstraVersion = new CompetitionDijkstra("input-L.txt", 63, 77, 95);
        assertEquals(ourDijkstraVersion.timeRequiredForCompetition(), 127);
        ourDijkstraVersion = new CompetitionDijkstra("input-M.txt", 70, 70, 70);
        assertEquals(ourDijkstraVersion.timeRequiredForCompetition(), 215);
        ourDijkstraVersion = new CompetitionDijkstra("input-N.txt", 70, 70, 70);
        assertEquals(ourDijkstraVersion.timeRequiredForCompetition(), 115);
        ourDijkstraVersion = new CompetitionDijkstra("tinyEWD.txt", 70, 70, 70);
        assertEquals(ourDijkstraVersion.timeRequiredForCompetition(), 27);
        ourDijkstraVersion = new CompetitionDijkstra("1000EWD.txt", 70, 70, 70);
        assertEquals(ourDijkstraVersion.timeRequiredForCompetition(), 20);
    }

    @Test
    public void testFWConstructor() {
        CompetitionFloydWarshall ourFloydWarshallVersion;
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-A.txt", 70, 70, 70);
        assertEquals(ourFloydWarshallVersion.timeRequiredforCompetition(), -1);
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-B.txt", 60,80,50);
        assertEquals(ourFloydWarshallVersion.timeRequiredforCompetition(), 10000);
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-C.txt", 60, 70, 70);
        assertEquals(ourFloydWarshallVersion.timeRequiredforCompetition(), -1);
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-D.txt", 50,80,60);
        assertEquals(ourFloydWarshallVersion.timeRequiredforCompetition(), 38);
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-E.txt", 70, 70, 70);
        assertEquals(ourFloydWarshallVersion.timeRequiredforCompetition(), 20);
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-F.txt", 70, 70, 70);
        assertEquals(ourFloydWarshallVersion.timeRequiredforCompetition(), -1);
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-G.txt", 70, 70, 70);
        assertEquals(ourFloydWarshallVersion.timeRequiredforCompetition(), -1);
//        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-H.txt", 70, 70, 70);
//        assertEquals(ourFloydWarshallVersion.timeRequiredforCompetition(), -1);
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-I.txt", 72,70,65);
        assertEquals(ourFloydWarshallVersion.timeRequiredforCompetition(), 185);
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-J.txt", 70, 70, 70);
        assertEquals(ourFloydWarshallVersion.timeRequiredforCompetition(), -1);
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-K.txt", 51,70,88);
        assertEquals(ourFloydWarshallVersion.timeRequiredforCompetition(), 314);
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-L.txt", 63, 77, 95);
        assertEquals(ourFloydWarshallVersion.timeRequiredforCompetition(), 127);
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-M.txt", 70, 70, 70);
        assertEquals(ourFloydWarshallVersion.timeRequiredforCompetition(), 215);
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-N.txt", 70, 70, 70);
        assertEquals(ourFloydWarshallVersion.timeRequiredforCompetition(), 115);
        ourFloydWarshallVersion = new CompetitionFloydWarshall("tinyEWD.txt", 70, 70, 70);
        assertEquals(ourFloydWarshallVersion.timeRequiredforCompetition(), 27);
        ourFloydWarshallVersion = new CompetitionFloydWarshall("1000EWD.txt", 70, 70, 70);
        assertEquals(ourFloydWarshallVersion.timeRequiredforCompetition(), 20);
    }

    @Test
    public void TestInvalidWalkingSpeeds()
    {
        // For Dijkstra
        CompetitionDijkstra ourDijkstraVersion;
        ourDijkstraVersion = new CompetitionDijkstra("input-B.txt", 0, 0, 0);
        assertEquals(ourDijkstraVersion.timeRequiredForCompetition(), -1);
        ourDijkstraVersion = new CompetitionDijkstra("input-B.txt", 2000, 60, 2000);
        assertEquals(ourDijkstraVersion.timeRequiredForCompetition(), -1);
        ourDijkstraVersion = new CompetitionDijkstra("input-B.txt", -50, -50, -50);
        assertEquals(ourDijkstraVersion.timeRequiredForCompetition(), -1);

        // For Floyd-Warshall
        CompetitionFloydWarshall ourFloydWarshallVersion;
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-B.txt", 0, 0, 0);
        assertEquals(ourFloydWarshallVersion.timeRequiredforCompetition(), -1);
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-B.txt", 2000, 60, 2000);
        assertEquals(ourFloydWarshallVersion.timeRequiredforCompetition(), -1);
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-B.txt", -50, -50, -50);
        assertEquals(ourFloydWarshallVersion.timeRequiredforCompetition(), -1);
    }

}
