import java.awt.*;
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
                this.chosenGraph = new File("./txt/B2_graph1.txt");
                return true;
            case 2:
                this.chosenGraph = new File("./txt/B2_graph2.txt");
                return true;
            case 3:
                this.chosenGraph = new File("./txt/B2_graph3.txt");
                return true;
            case 4:
                this.chosenGraph = new File("./txt/B2_graph4.txt");
                return true;
            case 5:
                this.chosenGraph = new File("./txt/B2_graph5.txt");
                return true;
            case 6:
                this.chosenGraph = new File("./txt/B2_graph6.txt");
                return true;
            case 7:
                this.chosenGraph = new File("./txt/B2_graph7.txt");
                return true;
            case 8:
                this.chosenGraph = new File("./txt/B2_graph8.txt");
                return true;
            case 9:
                this.chosenGraph = new File("./txt/B2_graph9.txt");
                return true;
            case 10:
                this.chosenGraph = new File("./txt/B2_graph10.txt");
                return true;
            case 11:
                this.chosenGraph = new File("./txt/B2_graph11.txt");
                return true;
            case 12:
                this.chosenGraph = new File("./txt/B2_graph12.txt");
                return true;
            case 13:
                this.chosenGraph = new File("./txt/B2_graph13.txt");
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
        Graph graph = new Graph(); //Graph on which we will do our operations
        graph.setChosenGraph(idGraph);
        graph.fillGraph(graph.getChosenGraph());

        ArrayList<Vertex> sourcesTemp = new ArrayList<>(); //List that contains all the sources at one iteration

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
        System.out.print("   ");//putting spaces in the top left of the table
        for (int i = 0; i < this.getListOfVertices().size(); i++){//for loop to print all the columns
            System.out.printf("%-3s",this.getListOfVertices().get(i).getVertexID());
        }
        System.out.print("\n");//go to first line
        for (int i = 0; i < this.getListOfVertices().size(); i++){//for loop that goes line by line

            System.out.printf("%-3s", this.getListOfVertices().get(i).getVertexID());//print the line value

            for (int j = 0; j < this.getListOfVertices().size(); j++){//for loop that goes for each column in the line
                boolean isChild = false;
                for (int k = 0; k < this.getListOfVertices().get(i).getListOfOutgoingEdges().size(); k++){//for loop that goes through the outgoing edges of the line, checking if an edge between the line and column exists
                    if(this.getListOfVertices().get(i).getListOfOutgoingEdges().get(k).getEdgeParent().getVertexID() == this.getListOfVertices().get(j).getVertexID()){
                        isChild = true;
                    }
                }
                if (isChild == true){ System.out.printf("%-3s", "1"); } //if an edg exists, we print "1", if not, we print "-"

                else{ System.out.printf("%-3s", "-"); }
            }
            System.out.print("\n");// go to next line
        }
    }

    public void displayValueMatrix(){
        System.out.print("   "); //putting spaces in the top left of the table
        for (int i = 0; i < this.getListOfVertices().size(); i++){ //for loop to print all the columns
            System.out.printf("%-3s",this.getListOfVertices().get(i).getVertexID());
        }
        System.out.print("\n"); //go to first line
        for (int i = 0; i < this.getListOfVertices().size(); i++){ //for loop that goes line by line

            System.out.printf("%-3s",this.getListOfVertices().get(i).getVertexID()); //print the line value

            for (int j = 0; j < this.getListOfVertices().size(); j++){ //for loop that goes for each column in the line
                boolean isChild = false; //value to check if there's an edge between the line and the column
                int weight = 0; //value of the weight of the edge if one exists bewteen the line and column
                for (int k = 0; k < this.getListOfVertices().get(i).getListOfOutgoingEdges().size(); k++){ //for loop that goes through the outgoing edges of the line, checking if an edge between the line and column exists
                    if(this.getListOfVertices().get(i).getListOfOutgoingEdges().get(k).getEdgeParent().getVertexID() == this.getListOfVertices().get(j).getVertexID()){
                        isChild = true;
                        weight = this.getListOfVertices().get(i).getListOfOutgoingEdges().get(k).getEdgeWeight();
                    }
                }
                if (isChild == true){ System.out.printf("%-3s", weight); } //putting the value if an edge exists, if no, we put a "-"

                else{ System.out.printf("%-3s","-"); }
            }
            System.out.print("\n"); //go to next line
        }

    }

    /** Method to fill the ranks of the different vertices **/
    /** Can be used only if the given graph has no cycle **/
    public void setVerticesRank(int idGraph) throws FileNotFoundException {
        Graph graph = new Graph(); //Graph which we will use in order to delete its vertices and find the ranks
        graph.setChosenGraph(idGraph);
        graph.fillGraph(graph.getChosenGraph());
        ArrayList<Vertex> sourcesTemp = new ArrayList<>(); //List that contains all the sources at one iteration

        for (int i = 0; i < graph.getListOfVertices().size(); i++) //We update the list of sources
        {
            if (graph.getListOfVertices().get(i).isSource())
            {
                sourcesTemp.add(graph.getListOfVertices().get(i));
            }
        }

        for (int i = 0; i < this.getListOfVertices().size(); i++) //Loop that contains the algorithm
        {
            for (int k = 0; k < sourcesTemp.size(); k++) //For every sources (through all current sources)
            {
                for (int l = 0; l < this.getListOfVertices().size(); l++) //For each vertex (through each vertex of the real graph)
                {
                    if (this.getListOfVertices().get(l).getVertexID() == sourcesTemp.get(k).getVertexID())
                    {
                        this.getListOfVertices().get(l).setVertexRank(i); //We set the rank of the vertex with the iteration of the algorithm
                    }
                }
            }

            for (int j = 0 ; j < graph.getListOfVertices().size() ; j++) //We need to remove all the edges from the vertices we will delete
            {
                for (int k = 0; k < sourcesTemp.size(); k++) //This way, we go through all the current sources
                {
                    for (int l = 0; l < graph.getListOfVertices().get(j).getListOfIngoingEdges().size() ; l++) // We go through every INGOING edges of each vertex
                    {
                        if (graph.getListOfVertices().get(j).getListOfIngoingEdges().get(l).getEdgeChild().getVertexID() == sourcesTemp.get(k).getVertexID()) //If the ID of the vertex we currently are is the same as the child of the edge we currently are
                        {
                            graph.getListOfVertices().get(j).getListOfIngoingEdges().remove(l); //We remove the edge at the index of j >> the edge which is coming from a source

                        }
                    }
                }
            }

            for (int n = 0 ; n < sourcesTemp.size() ; n++) //For each sources we have
            {
                for (int m = 0; m < graph.getListOfVertices().size(); m++) //Here we go through all the vertices of the temporary graph
                {
                    if (graph.getListOfVertices().get(m).getVertexID() == sourcesTemp.get(n).getVertexID())  //If the vertex is a source we remove from the temporary graph
                    {
                        graph.getListOfVertices().remove(m); // We remove the vertex
                    }
                }
            }


             if (sourcesTemp.isEmpty()) //If there are no more sources, we stop here
             {
                 break;
             } else {
                 sourcesTemp.removeAll(sourcesTemp); //Reinitialize the array of sources
             }

            for (int j = 0; j < graph.getListOfVertices().size(); j++) //We update the list of sources
            {
                graph.getListOfVertices().get(j).setSource();
                if (graph.getListOfVertices().get(j).isSource())
                {
                    sourcesTemp.add(graph.getListOfVertices().get(j));
                }
            }
        }
    }

    public boolean isScheduling(int choice) throws FileNotFoundException {

        /** We check if th graph has a single entry point  **/
        int cpt = 0;
        for (int i = 0 ; i < this.getListOfVertices().size() ; i++)
        {
            if (this.getListOfVertices().get(i).isSource()) // If we have a source, we increment the cpt
                cpt++;

            if (cpt > 1) //If we have more than one source
                return false;
        }

        /** We check if the graph has a single exit point **/
        cpt = 0; // We reinitialize cpt because we will use ut one more time
        for (int i = 0 ; i < this.getListOfVertices().size() ; i++)
        {
            if (this.getListOfVertices().get(i).getListOfOutgoingEdges().isEmpty()) //If we have an exit point
                cpt++;

            if (cpt > 1) //If we have more than one exit point
                return false;
        }

        /** We check if the graph has a cycle **/
        if (this.isCyclic(choice)) //If the graph has a cycle, then we return false
            return false;

        /** We check if the weight is the same for all the outgoing edges and if there is no negative weight **/
        for (int i = 0 ; i < this.getListOfVertices().size() ; i++) //We go through all the vertices
        {
            for (int j = 0 ; j < this.getListOfVertices().get(i).getListOfOutgoingEdges().size() ; j++) //We go through the list of the outgoing edges
            {
                if (this.getListOfVertices().get(i).getListOfOutgoingEdges().get(j).getEdgeWeight() < 0) //If the weight of an outgoing edge is < 0
                    return false;

                if (j == this.getListOfVertices().get(i).getListOfOutgoingEdges().size() - 1) //If we reached the last edge -> because we start at rank 0 in the list we need to put '- 1'
                {
                    break;
                }
                else
                {
                    if (this.getListOfVertices().get(i).getListOfOutgoingEdges().get(j).getEdgeWeight() != this.getListOfVertices().get(i).getListOfOutgoingEdges().get(j + 1).getEdgeWeight()) //If the following edge weight of the vertex has not the same weight as the previous one
                        return false;
                }

            }
        }

        /** We check if all the outgoing edges of the source have a weight of 0 **/
        for (int i = 0 ; i < this.getListOfVertices().size() ; i++) //We go through all the vertices
        {
            if(this.getListOfVertices().get(i).isSource())
            {
                for (int j = 0 ; j < this.getListOfVertices().get(i).getListOfOutgoingEdges().size() ; j++) //We go through all the outgoing edges of the source
                {
                    if (this.getListOfVertices().get(i).getListOfOutgoingEdges().get(j).getEdgeWeight() != 0) //If one outgoing edge weight's is not 0
                        return false;
                }
            }
        }

        return true; //If all the conditions above are full filled
    }
}
