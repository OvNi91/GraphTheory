import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Graph {
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
        return listOfVertices;
    }
    //---------------------------------------------------------------
    public boolean setChosenGraph(int choice){
        switch (choice){
            case 1:
                this.chosenGraph = new File("C:/Users/gmaza/IdeaProjects/GraphTheory/GraphTheory/GraphTheoryProject/txt/1.txt");
                return true;
            case 2:
                this.chosenGraph = new File("C:/Users/gmaza/IdeaProjects/GraphTheory/GraphTheory/GraphTheoryProject/txt/2.txt");
                return true;
            case 3:
                this.chosenGraph = new File("C:/Users/gmaza/IdeaProjects/GraphTheory/GraphTheory/GraphTheoryProject/txt/3.txt");
                return true;
            case 4:
                this.chosenGraph = new File("C:/Users/gmaza/IdeaProjects/GraphTheory/GraphTheory/GraphTheoryProject/txt/4.txt");
                return true;
            case 5:
                this.chosenGraph = new File("C:/Users/gmaza/IdeaProjects/GraphTheory/GraphTheory/GraphTheoryProject/txt/5.txt");
                return true;
            case 6:
                this.chosenGraph = new File("C:/Users/gmaza/IdeaProjects/GraphTheory/GraphTheory/GraphTheoryProject/txt/6.txt");
                return true;
            case 7:
                this.chosenGraph = new File("C:/Users/gmaza/IdeaProjects/GraphTheory/GraphTheory/GraphTheoryProject/txt/7.txt");
                return true;
            case 8:
                this.chosenGraph = new File("C:/Users/gmaza/IdeaProjects/GraphTheory/GraphTheory/GraphTheoryProject/txt/8.txt");
                return true;
            case 9:
                this.chosenGraph = new File("C:/Users/gmaza/IdeaProjects/GraphTheory/GraphTheory/GraphTheoryProject/txt/9.txt");
                return true;
            case 10:
                this.chosenGraph = new File("C:/Users/gmaza/IdeaProjects/GraphTheory/GraphTheory/GraphTheoryProject/txt/10.txt");
                return true;
            case 11:
                this.chosenGraph = new File("C:/Users/gmaza/IdeaProjects/GraphTheory/GraphTheory/GraphTheoryProject/txt/11.txt");
                return true;
            case 12:
                this.chosenGraph = new File("C:/Users/gmaza/IdeaProjects/GraphTheory/GraphTheory/GraphTheoryProject/txt/12.txt");
                return true;
            case 13:
                this.chosenGraph = new File("C:/Users/gmaza/IdeaProjects/GraphTheory/GraphTheory/GraphTheoryProject/txt/13.txt");
                return true;
            default:
                return false;
        }
    }


    public void fillGraph(File chosenGraph) throws FileNotFoundException {
        Scanner sc = new Scanner(chosenGraph);
        while (sc.hasNext()){
            int n = sc.nextInt();
            setNbVertices(n);
            n = sc.nextInt();
            setNbEdges(n);
            int [][] numbers = new int[this.getNbVertices()][3];
            for (int i = 0; i < numbers.length; i++){
                numbers[i][0]=sc.nextInt();
                numbers[i][1]=sc.nextInt();
                numbers[i][2]=sc.nextInt();
            }
            for (int j = 0; j < this.getNbVertices() ; j++){
                Vertex v = new Vertex();
                int temp = numbers[j][0];
                v.setVertexID(temp);
                if (this.getListOfVertices().contains(v)){
                    continue;
                }
                else{
                    this.getListOfVertices().add(v);
                }
            }
            for (int k = 0; k < this.getNbVertices(); k++){
                Edge e = new Edge();
                Vertex parent = new Vertex();
                Vertex child = new Vertex();
                parent.setVertexID(numbers[k][0]);
                child.setVertexID(numbers[k][1]);
                e.setEdgeParent(parent);
                e.setEdgeChild(child);
                e.setEdgeWeight(numbers[k][2]);
                if (this.getListOfVertices().contains(parent)){
                    int index = this.getListOfVertices().indexOf(parent);
                    this.getListOfVertices().get(index).getListOfIngoingEdges().add(e);
                }
            }
        }

    }

    public static void main (String[] args) throws FileNotFoundException {
        Graph g = new Graph();
        g.setChosenGraph(1);
        g.fillGraph(g.chosenGraph);
        System.out.println(g.getListOfVertices());
    }


}
