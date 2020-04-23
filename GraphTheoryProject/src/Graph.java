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

    public File getChosenGraph() {
        return chosenGraph;
    }

    public ArrayList<Vertex> getListOfVertices() {
        return this.listOfVertices;
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
                numbers[i][2] = sc.nextInt();
            }

            for (int j = 0; j < this.getNbVertices(); j++) { //for loop that fills the list of Vertices
                Vertex v = new Vertex();
                v.setVertexID(j); //Here, we attribute the value of the loop to the vertex, because we know how many vertices we have
                this.listOfVertices.add(v);
            }


            for (int i = 0 ; i < this.listOfVertices.size() ; i++) //For loop that goes through all the vertices one by one
            {
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

                    Edge e2 = new Edge(); //creation of temp values

                    if (this.listOfVertices.get(i).getVertexID() == numbers[j][0]) //To increment the list of outgoing edges
                    {

                        e.setEdgeParent(this.listOfVertices.get(numbers[j][1]));    //We set the parent vertex to the number contained in the file at position 2 of its line
                        e.setEdgeChild(this.listOfVertices.get(numbers[j][0])); //We set the child vertex which is at the rank where we currently are (because it is sending)
                        e.setEdgeWeight(numbers[j][2]); //We set the weight thanks to the line we currently are in the file at position 3

                        this.listOfVertices.get(i).listOfOutgoingEdges.add(e); //Here we increment the list of outgoing edges
                    }
                }
            }

        }
    }
    public void FindShortestPath(int idGraph) throws FileNotFoundException {
        System.out.println("Code de Aurélien");
        Scanner sc = new Scanner(System.in);


        //The goal here will be to find the shortest path
        System.out.println("From which source do you want to go?");
        int choiceSource = sc.nextInt(); //We choose the source of the shortest path

        if (this.getListOfVertices().get(choiceSource).getListOfIngoingEdges().isEmpty()){
            System.out.println("This vertex is a source");
        }
        else{
            System.out.println("This vertex is not a source");
        }

        this.listOfVertices.get(choiceSource).vertexValue = 0;//We initialize the value of the source at 0
        int[][]shortestPath; //We initialize our table for the shortest path
        shortestPath = new int[this.nbVertices-1][this.nbVertices];


        for (int n = 0; n<this.nbVertices;n++){
            if (this.listOfVertices.get(n).vertexValue != 0){
                this.listOfVertices.get(n).vertexValue = 10000;//We initialize the value of the other vertices at 10000 (infinity)
            }
        }

        for (int m =0;m<this.nbVertices-1;m++){//nb lines
            for (int l = 0; l<this.nbVertices;l++){//nb columns
                if (l == choiceSource){
                    shortestPath[m][l] = 0;
                    System.out.print(shortestPath[m][l] + " ");
                }
                else {
                    shortestPath[m][l] = 10000; //We fill the initial table with 10000 (infinite values)
                    System.out.print(shortestPath[m][l] + " ");
                }
            }
            System.out.println();
        }


        for (int i = 0; i<this.nbVertices; i++){ //We will repeat the following interation for the number of vertices minus 1


            for (int j = 0; j<this.getListOfVertices().size(); j++){//We will repeat the following iteration for each vertex


                for (int k = 0; k<this.getListOfVertices().get(j).getListOfOutgoingEdges().size(); k++){//We will repeat the iteration for each predecessor of the vertex

                    //We set the value of the vertex as the minimum between the value of the parent + the weight of the edge and the actual value of the vertex
                    if (this.getListOfVertices().get(j).getListOfOutgoingEdges().get(k).getEdgeChild().vertexValue!=0) {
                        this.getListOfVertices().get(j).getListOfOutgoingEdges().get(k).getEdgeChild().vertexValue = Math.min(this.getListOfVertices().get(j).vertexValue + this.getListOfVertices().get(j).listOfOutgoingEdges.get(k).edgeWeight, this.getListOfVertices().get(j).getListOfOutgoingEdges().get(k).getEdgeChild().vertexValue);
                    }
                    else{
                        this.getListOfVertices().get(j).getListOfOutgoingEdges().get(k).getEdgeChild().vertexValue = this.getListOfVertices().get(j).vertexValue+this.getListOfVertices().get(j).listOfOutgoingEdges.get(k).edgeWeight;
                    }

                    //We fill the graph with the values we found
                    if(this.getListOfVertices().get(j).getListOfOutgoingEdges().get(k).getEdgeChild().vertexValue<=shortestPath[this.nbVertices-2][i]){
                        shortestPath[this.nbVertices-2][i] = this.getListOfVertices().get(j).getListOfOutgoingEdges().get(k).getEdgeChild().vertexValue;

                    }
                    System.out.println(shortestPath[j][k]+" ");
                }
            }
        }

        //We create an array that will store the final shortestPath
        int[] realShortPath;
        realShortPath = new int[this.nbVertices];

        System.out.println("6");
        //We fill this array with the last line of the table
        for (int o=0; o<=this.nbVertices-1;o++){
            realShortPath[o] = shortestPath[this.nbVertices-2][o];
            System.out.print(realShortPath[o]+" ");
        }
        System.out.println("7");
        System.out.println("The shortest path is : "+realShortPath);
    }

    //main used to try things
    public static void main(String[] args) throws FileNotFoundException {
        Graph g = new Graph();
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

                for (int j = 0; j < g.getListOfVertices().get(i).getListOfOutgoingEdges().size(); j++) {
                    System.out.println("Sends to : " + g.getListOfVertices().get(i).getListOfOutgoingEdges().get(j).getEdgeParent().getVertexID() + " with a weight of : " + g.getListOfVertices().get(i).getListOfOutgoingEdges().get(j).getEdgeWeight());
                }
                System.out.println("-------------------------");
            }

            g.FindShortestPath(g.getIdGraph());

            System.out.println("-------------------------");
            System.out.println("Give another number between 1 and 13 if you want to see another graph, or anything else to exit");
            choice = sc.nextInt();
        }
        System.out.println("Bye !");
    }
}
