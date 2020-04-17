import java.util.ArrayList;

public class Vertex {
<<<<<<< HEAD
    int vertexID;
    int vertexRank;
=======
    private int vertexID;
    private int vertexRank;









>>>>>>> ffb54710bdbce894fc8e149a86d35ea9dce22ce7
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

<<<<<<< HEAD
=======

>>>>>>> ffb54710bdbce894fc8e149a86d35ea9dce22ce7
    public ArrayList<Edge> getListOfIngoingEdges(){
        return this.listOfIngoingEdges;
    }

}
