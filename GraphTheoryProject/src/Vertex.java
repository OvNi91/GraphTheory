import java.util.ArrayList;

public class Vertex {
    int vertexID;
    int vertexRank;
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

    public ArrayList<Edge> getListOfIngoingEdges(){
        return this.listOfIngoingEdges;
    }

}
