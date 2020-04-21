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
        return this.listOfVertices;
    }


    public File getChosenGraph() {
        return chosenGraph;
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

        for (int i = 0 ; i < this.getListOfVertices().size() ; i++)
        {
                this.getListOfVertices().get(i).setSource();
        }
    }

    public boolean isCyclic(int idGraph) throws FileNotFoundException{
        //------ Initialisation ------\\
        Graph graph = new Graph();
        graph.setChosenGraph(idGraph);
        graph.fillGraph(graph.getChosenGraph());

        ArrayList<Vertex> sourcesTemp = new ArrayList<>();

        for (int i = 0 ; i < graph.getListOfVertices().size() ; i++)
        {
            if (graph.getListOfVertices().get(i).isSource())  //If the vertex is a source we add it to the list of sources
            {
                sourcesTemp.add(graph.getListOfVertices().get(i));
            }
        }
        //------ Over ------\\

        //------ Beginning of the algorithm ------\\

        while (!sourcesTemp.isEmpty()) //Tant que la liste des sources n'est pas vide
        {

            /** No need to remove the outgoing edges because they'll be removed when we will remove the sources from
             the temporary graph we created
             **/

            for (int i = 0 ; i < graph.getListOfVertices().size() ; i++) { //We go through all the vertices in order to remove the right edges
                for (int k = 0; k < sourcesTemp.size(); k++) //This way, we go through all the current sources
                {
                    for (int j = 0; j < graph.getListOfVertices().get(i).getListOfIngoingEdges().size(); j++) // We go through every INGOING edges of each vertex
                    {
                            if (graph.getListOfVertices().get(i).getListOfIngoingEdges().get(j).getEdgeChild().getVertexID() == sourcesTemp.get(k).getVertexID()) //If the ID of the vertex we currently are is the same as the child of the edge we currently are
                            {
                                graph.getListOfVertices().get(i).getListOfIngoingEdges().remove(j); //We remove the edge at the index of j >> the edge which is coming from a source
                            }
                    }
                }

            }

            for (int i = 0 ; i < graph.getListOfVertices().size() ; i++)
            {
                if (graph.getListOfVertices().get(i).isSource())  //If the vertex is a source we remove from the temporary graph
                {
                    graph.getListOfVertices().remove(i); // We remove the vertex
                }
            }

            graph.setNbVertices(graph.getNbVertices() - sourcesTemp.size());
            //It would be good to remove the correct number of edges but I don't know how we can know it right now

            sourcesTemp.removeAll(sourcesTemp); //Reinitialize the array of sources

            for (int i = 0 ; i < graph.getListOfVertices().size() ; i++) //We find the new sources created
            {
                graph.getListOfVertices().get(i).setSource();
                if (graph.getListOfVertices().get(i).isSource())  //If the vertex is a source we add it to the list of sources
                {
                        sourcesTemp.add(graph.getListOfVertices().get(i));
                }
            }
        }

        if (graph.getListOfVertices().isEmpty()) //If the list containing all the vertices is empty, it means that there is not a cycle
        {
            return false;
        } else { //If the list of vertices isn't empty, it means there is a cycle
            return true;
        }
    }

    public void displayAdjacencyMatrix(){
        System.out.print("   ");
        for (int i = 0; i < this.getListOfVertices().size(); i++){
            System.out.print(this.getListOfVertices().get(i).getVertexID() + " ");
        }
        System.out.print("\n");
        for (int i = 0; i < this.getListOfVertices().size(); i++){
            if (i < 10){
                System.out.print(this.getListOfVertices().get(i).getVertexID() + "  ");
            }
            else{
                System.out.print(this.getListOfVertices().get(i).getVertexID() + " ");
            }
            for (int j = 0; j < this.getListOfVertices().size(); j++){
                boolean isChild = false;
                for (int k = 0; k < this.getListOfVertices().get(i).getListOfOutgoingEdges().size(); k++){
                    if(this.getListOfVertices().get(i).getListOfOutgoingEdges().get(k).getEdgeParent().getVertexID() == this.getListOfVertices().get(j).getVertexID()){
                        isChild = true;
                    }
                }
                if (isChild == true){
                    if (j < 10){
                        System.out.print("1 ");
                    }
                    else{
                        System.out.print("1  ");
                    }
                }
                else{
                    if (j < 10){
                        System.out.print("- ");
                    }
                    else{
                        System.out.print("-  ");
                    }
                }
            }
            System.out.print("\n");
        }
    }


    //main used to try things
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

            System.out.println("---------------------------------------");


            for (int i = 0; i < g.getNbVertices(); i++) {
                System.out.println("Vertex "+g.listOfVertices.get(i).getVertexID() + " ");
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
