import org.junit.Test;

public class CompetitionTests {

    @Test
    public void testDijkstraConstructor() {
        CompetitionDijkstra ourDijkstraVersion;
        ourDijkstraVersion = new CompetitionDijkstra("input-A.txt", 70, 70, 70);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-B.txt", 70, 70, 70);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-C.txt", 60, 70, 70);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-D.txt", 70, 70, 70);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-E.txt", 70, 70, 70);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-F.txt", 70, 70, 70);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-G.txt", 70, 70, 70);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-H.txt", 70, 70, 70);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-I.txt", 70, 70, 70);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-J.txt", 70, 70, 70);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-K.txt", 70, 70, 70);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-L.txt", 70, 70, 70);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-M.txt", 70, 70, 70);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-N.txt", 70, 70, 70);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("tinyEWD.txt", 70, 70, 70);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("1000EWD.txt", 70, 70, 70);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
    }

    @Test
    public void testFWConstructor() {
        CompetitionFloydWarshall ourFloydWarshallVersion;
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-A.txt", 70, 70, 70);
        System.out.println(ourFloydWarshallVersion.timeRequiredforCompetition());
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-B.txt", 70, 70, 70);
        System.out.println(ourFloydWarshallVersion.timeRequiredforCompetition());
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-C.txt", 70, 70, 70);
        System.out.println(ourFloydWarshallVersion.timeRequiredforCompetition());
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-D.txt", 70, 70, 70);
        System.out.println(ourFloydWarshallVersion.timeRequiredforCompetition());
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-E.txt", 70, 70, 70);
        System.out.println(ourFloydWarshallVersion.timeRequiredforCompetition());
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-F.txt", 70, 70, 70);
        System.out.println(ourFloydWarshallVersion.timeRequiredforCompetition());
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-G.txt", 70, 70, 70);
        System.out.println(ourFloydWarshallVersion.timeRequiredforCompetition());
        //ourFloydWarshallVersion = new CompetitionFloydWarshall("input-H.txt", 70, 70, 70);
        //System.out.println(ourFloydWarshallVersion.timeRequiredforCompetition());
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-I.txt", 70, 70, 70);
        System.out.println(ourFloydWarshallVersion.timeRequiredforCompetition());
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-J.txt", 70, 70, 70);
        System.out.println(ourFloydWarshallVersion.timeRequiredforCompetition());
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-K.txt", 70, 70, 70);
        System.out.println(ourFloydWarshallVersion.timeRequiredforCompetition());
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-L.txt", 70, 70, 70);
        System.out.println(ourFloydWarshallVersion.timeRequiredforCompetition());
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-M.txt", 70, 70, 70);
        System.out.println(ourFloydWarshallVersion.timeRequiredforCompetition());
        ourFloydWarshallVersion = new CompetitionFloydWarshall("input-N.txt", 70, 70, 70);
        System.out.println(ourFloydWarshallVersion.timeRequiredforCompetition());
        ourFloydWarshallVersion = new CompetitionFloydWarshall("tinyEWD.txt", 70, 70, 70);
        System.out.println(ourFloydWarshallVersion.timeRequiredforCompetition());
        ourFloydWarshallVersion = new CompetitionFloydWarshall("1000EWD.txt", 70, 70, 70);
        System.out.println(ourFloydWarshallVersion.timeRequiredforCompetition());
    }

    //TODO - more tests

}
