import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Give the number of the graph you want to see, between 1 to 13, anything else to exit");
        int choice = sc.nextInt();
        while (1 <= choice && choice <= 13){
            Graph g = new Graph();
            g.setChosenGraph(choice);
            g.fillGraph(g.chosenGraph);


            if (g.isCyclic(choice)) {
                System.out.println("-> The graph has cycle <-");
            } else {
                System.out.println("-> The graph has no cycle <-");
            }

            g.setVerticesRank(choice);

            if (g.isScheduling(choice))
            {
                System.out.println(">> Graph is a correct graph scheduling <<");
            } else {
                System.out.println(">> The graph is not a correct graph scheduling <<");
            }

            System.out.println("---------------------------------------");


            for (int i = 0; i < g.getNbVertices(); i++) {
                System.out.println("Vertex " + g.listOfVertices.get(i).getVertexID() + " -> Rank : " + g.getListOfVertices().get(i).getVertexRank());
                if (g.getListOfVertices().get(i).isSource()){
                    System.out.println("This vertex is a source");
                }
                for (int j = 0; j < g.getListOfVertices().get(i).getListOfIngoingEdges().size(); j++) {
                    System.out.println("Receives from : " + g.getListOfVertices().get(i).getListOfIngoingEdges().get(j).getEdgeChild().getVertexID() + " with a weight of : " + g.getListOfVertices().get(i).getListOfIngoingEdges().get(j).getEdgeWeight());
                }

                for (int j = 0; j < g.getListOfVertices().get(i).getListOfOutgoingEdges().size(); j++) {
                    System.out.println("Sends to : " + g.getListOfVertices().get(i).getListOfOutgoingEdges().get(j).getEdgeParent().getVertexID() + " with a weight of : " + g.getListOfVertices().get(i).getListOfOutgoingEdges().get(j).getEdgeWeight());
                }
                System.out.println("-------------------------");
            }
            System.out.println("-------------------------");
            System.out.println("Give another number between 1 and 13 if you want to see another graph, or anything else to exit");
            choice = sc.nextInt();
        }
        System.out.println("Bye !");
    }
}

