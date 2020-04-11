import org.junit.Test;

public class CompetitionTests {

    @Test
    public void testDijkstraConstructor() {
        CompetitionDijkstra ourDijkstraVersion;
        ourDijkstraVersion = new CompetitionDijkstra("input-A.txt", 100, 100, 100);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-B.txt", 100, 100, 100);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-C.txt", 60, 100, 70);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-D.txt", 100, 100, 100);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-E.txt", 100, 100, 100);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-F.txt", 100, 100, 100);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-G.txt", 100, 100, 100);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-H.txt", 100, 100, 100);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-I.txt", 100, 100, 100);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-J.txt", 100, 100, 100);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-K.txt", 100, 100, 100);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-L.txt", 100, 100, 100);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-M.txt", 100, 100, 100);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-N.txt", 100, 100, 100);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-F.txt", 100, 100, 100);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("input-G.txt", 100, 100, 100);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("tinyEWD.txt", 100, 100, 100);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
        ourDijkstraVersion = new CompetitionDijkstra("1000EWD.txt", 100, 100, 100);
        System.out.println(ourDijkstraVersion.timeRequiredforCompetition());
    }

    @Test
    public void testFWConstructor() {
        CompetitionFloydWarshall ourFloydWarshall;
        ourFloydWarshall = new CompetitionFloydWarshall("input-A.txt", 100, 100, 100);
    }

    //TODO - more tests

}
