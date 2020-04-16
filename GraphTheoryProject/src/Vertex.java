import java.util.ArrayList;

public class Vertex {
    int vertexID;
    int vertexRank;
    int nbInGoingEdges = 0;








    ArrayList<Edge> listOfIngoingEdges = new ArrayList<>();

    //-------------------------GETTERS & SETTERS---------------------------------\\
    public void setVertexID(int vertexID) {
        this.vertexID = vertexID;
    }
    public int getVertexID(){
        return this.vertexID;
    }

    public void setVertexRank(int vertexRank) {
        this.vertexRank = vertexRank;
    }
    public int getVertexRank(){
        return this.vertexRank;
    }

    public int getInGoingEdges() {
        return nbInGoingEdges;
    }
    public void setInGoingEdges(int inGoingEdges) {
        this.nbInGoingEdges = inGoingEdges;
    }

    public ArrayList<Edge> getListOfIngoingEdges(){
        return this.listOfIngoingEdges;
    }

}
