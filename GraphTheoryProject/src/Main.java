import javax.sound.midi.Soundbank;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public final static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {Runtime.getRuntime().exec("cls");}
            else{ Runtime.getRuntime().exec("clear");}
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Give the number of the graph you want to see, between 1 to 13, anything else to exit");
        int choice = sc.nextInt();
        while (1 <= choice && choice <= 13){
            Graph g = new Graph();
            g.setChosenGraph(choice);
            g.fillGraph(g.chosenGraph);

            System.out.println(">> ADJACENCY MATRIX <<");
            System.out.println();

            g.displayAdjacencyMatrix();
            System.out.println();

            System.out.println(">> VALUE MATRIX <<");
            System.out.println();
            g.displayValueMatrix();

            System.out.println();

            System.out.println("---------------------------------------");

            if (g.isCyclic(choice)) {
                System.out.println("-> The graph " + choice + " has cycle <-");
                System.out.println(">> The graph is not a correct graph scheduling <<");
            } else {
                if (g.isScheduling(choice))
                {
                    System.out.println(">> The graph " + choice + " has no cycle <<");
                    System.out.println(">> The graph is a correct graph scheduling <<");
                } else {

                    System.out.println(">> The graph " + choice + " has no cycle <<");
                    System.out.println(">> The graph is not a correct graph scheduling <<");
                    g.setVerticesRank(choice);
                }


            }


            System.out.println("---------------------------------------");
            g.setVerticesRank(choice);

           if (seeRanksInMain())
           {
               if (g.isCyclic(choice))
               {
                   System.out.println("The graph is cyclic, therefore it is irrelevant to see the ranks of the vertices");
               } else {
                   System.out.println(">> VERTICES RANKS <<");
                   for (int i = 0; i < g.getNbVertices(); i++) {
                       System.out.print("Vertex " + g.listOfVertices.get(i).getVertexID() + " -> Rank : " + g.getListOfVertices().get(i).getVertexRank());
                       if (g.getListOfVertices().get(i).isSource()){
                           System.out.print(" => This vertex is a source");
                       }
                       System.out.println();
                   }
               }

           }

            System.out.println("-------------------------");
            System.out.println("Give another number between 1 and 13 if you want to see another graph, or anything else to exit");
            choice = sc.nextInt();
        }
        System.out.println("Bye !");
    }



    public static boolean seeRanksInMain()
    {
        Scanner sc = new Scanner(System.in);
        int choice2;

        System.out.println("Would you like to see the ranks of the vertices ? \n" +
                "1 - Yes\n" +
                "2 - No ");
        choice2 = sc.nextInt();

        while (choice2 != 1 && choice2 != 2)
        {
            System.out.println("Would you like to see the ranks of the vertices ? \n" +
                    "1 - Yes\n" +
                    "2 - No ");
            choice2 = sc.nextInt();
        }

        if (choice2 == 1)
            return true;
        if (choice2 == 2)
            return false;
        return true;
    }
}

