import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


public class Graph {
    //initialization of variables
    int idGraph;
    File chosenGraph;
    int nbVertices;
    int nbEdges;
    int totalWeight;
    boolean cycle;
    ArrayList<Vertex> listOfVertices = new ArrayList<>();

    //-------------------------GETTERS & SETTERS---------------------------------\\
    public void setCycle(boolean cycle) {
        this.cycle = cycle;
    }

    public boolean isCycle() {
        return cycle;
    }

    public void setIdGraph(int idGraph) {
        this.idGraph = idGraph;
    }

    public int getIdGraph() {
        return idGraph;
    }

    public void setNbEdges(int nbEdges) {
        this.nbEdges = nbEdges;
    }

    public int getNbEdges() {
        return nbEdges;
    }

    public void setNbVertices(int nbVertices) {
        this.nbVertices = nbVertices;
    }

    public int getNbVertices() {
        return nbVertices;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public ArrayList<Vertex> getListOfVertices() {
<<<<<<< HEAD
        return this.listOfVertices;
=======
        return listOfVertices;
>>>>>>> ffb54710bdbce894fc8e149a86d35ea9dce22ce7
    }
    //---------------------------------------------------------------

    //function used to select the right graph from a user choice
    public boolean setChosenGraph(int choice) {
        switch (choice) {
            case 1:
                this.chosenGraph = new File("./txt/1.txt");
                return true;
            case 2:
                this.chosenGraph = new File("./txt/2.txt");
                return true;
            case 3:
                this.chosenGraph = new File("./txt/3.txt");
                return true;
            case 4:
                this.chosenGraph = new File("./txt/4.txt");
                return true;
            case 5:
                this.chosenGraph = new File("./txt/5.txt");
                return true;
            case 6:
                this.chosenGraph = new File("./txt/6.txt");
                return true;
            case 7:
                this.chosenGraph = new File("./txt/7.txt");
                return true;
            case 8:
                this.chosenGraph = new File("./txt/8.txt");
                return true;
            case 9:
                this.chosenGraph = new File("./txt/9.txt");
                return true;
            case 10:
                this.chosenGraph = new File("./txt/10.txt");
                return true;
            case 11:
                this.chosenGraph = new File("./txt/11.txt");
                return true;
            case 12:
                this.chosenGraph = new File("./txt/12.txt");
                return true;
            case 13:
                this.chosenGraph = new File("./txt/13.txt");
                return true;
            default:
                return false;
        }
    }


    //function used to fill the Arrays
    public void fillGraph(File chosenGraph) throws FileNotFoundException {
        Scanner sc = new Scanner(chosenGraph);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            setNbVertices(n); //setup the number of vertices
            n = sc.nextInt();
            setNbEdges(n); //setup the number of edges
            int[][] numbers = new int[this.getNbEdges()][3]; //declaring an array in which we are going to put all the edges
            for (int i = 0; i < numbers.length; i++) { //for loop that fills the array with all the edges
                numbers[i][0] = sc.nextInt();
                numbers[i][1] = sc.nextInt();
<<<<<<< HEAD
                numbers[i][2] = sc.nextInt();
=======
                numbers[i][2] = sc.nextInt(); //here is my last error, "noSuchElementException"
>>>>>>> ffb54710bdbce894fc8e149a86d35ea9dce22ce7
            }

            for (int j = 0; j < this.getNbVertices(); j++) { //for loop that fills the list of Vertices
                Vertex v = new Vertex();
                v.setVertexID(j); //Here, we attribute the value of the loop to the vertex, because we know how many vertices we have
                this.listOfVertices.add(v);
            }


            for (int i = 0 ; i < this.listOfVertices.size() ; i++) //For loop that goes through all the vertices one by one
            {
<<<<<<< HEAD
                for (int j = 0; j < this.getNbEdges(); j++) {//For loop that will fill every edges
                    Edge e = new Edge(); //creation of temp values
                    Vertex parent = new Vertex();
                    Vertex child = new Vertex();
                    if (this.listOfVertices.get(i).getVertexID() == numbers[j][1]) { //If the vertexId is the same as the second number on the line (Vertex receiving)
                        e.setEdgeParent(this.listOfVertices.get(numbers[j][1]));    //We set the parent vertex to the number contained in the file at position 2 of its line
                        e.setEdgeChild(this.listOfVertices.get(numbers[j][0])); //We set the child vertex which is at the rank where we currently are (because it is sending)
                        e.setEdgeWeight(numbers[j][2]); //We set the weight thanks to the line we currently are in the file at position 3

                        this.listOfVertices.get(i).listOfIngoingEdges.add(this.getListOfVertices().get(i).getListOfIngoingEdges().size(), e); //We finally add the new edge to the list of edges of the current vertex
                    }
                }
=======
                Edge e = new Edge(); //creation of temp values
                Vertex parent = new Vertex();
                Vertex child = new Vertex();

                for (int j = 0 ; j < this.getNbEdges() ; j++) //For loop that will fill every edges
                    if (this.listOfVertices.get(i).getVertexID() == numbers[j][1]){ //If the vertexId is the same as the second number on the line (Vertex receiving)
                        e.setEdgeParent(this.listOfVertices.get(numbers[j][1]));    //We set the parent vertex to the number contained in the file at position 2 of its line
                        e.setEdgeChild(this.listOfVertices.get(i)); //We set the child vertex which is at the rank where we currently are (because it is sending)
                        e.setEdgeWeight(numbers[j][2]); //We set the weight thanks to the line we currently are in the file at position 3

                        this.listOfVertices.get(i).listOfIngoingEdges.add(e); //We finally add the new edge to the list of edges of the current vertex
                    }
>>>>>>> ffb54710bdbce894fc8e149a86d35ea9dce22ce7
            }

        }
    }


    //main used to try things
    public static void main(String[] args) throws FileNotFoundException {
        Graph g = new Graph();
<<<<<<< HEAD
        Scanner sc = new Scanner(System.in);
        System.out.println("Give the number of the graph you want to see, between 1 to 13, anything else to exit");
        int choice = sc.nextInt();
        while (1 <= choice && choice <= 13){
            g.setChosenGraph(choice);
            g.fillGraph(g.chosenGraph);

            System.out.println("---------------------------------------");
            for (int i = 0; i < g.getNbVertices(); i++) {
                System.out.println("Vertex "+g.listOfVertices.get(i).getVertexID() + " ");
                if (g.getListOfVertices().get(i).getListOfIngoingEdges().isEmpty()){
                    System.out.println("This vertex is a source");
                }
                for (int j = 0; j < g.getListOfVertices().get(i).getListOfIngoingEdges().size(); j++) {
                    System.out.println("Receives from : " + g.getListOfVertices().get(i).getListOfIngoingEdges().get(j).getEdgeChild().getVertexID() + " with a weight of : " + g.getListOfVertices().get(i).getListOfIngoingEdges().get(j).getEdgeWeight());
                }
            }
            System.out.println("-------------------------");
            System.out.println("Give another number between 1 and 13 if you want to see another graph, or anything else to exit");
            choice = sc.nextInt();
        }
        System.out.println("Bye !");
    }
}
=======

        g.setChosenGraph(2);
        g.fillGraph(g.chosenGraph);

        for (int i = 0; i < g.getNbVertices(); i++) {
            for (int j = 0; j < g.listOfVertices.get(i).listOfIngoingEdges.size(); j++) {
                System.out.print(" " + g.listOfVertices.get(i).listOfIngoingEdges.get(j).getEdgeParent() + " ");
            }
            System.out.println();
        }
    }
}





>>>>>>> ffb54710bdbce894fc8e149a86d35ea9dce22ce7
