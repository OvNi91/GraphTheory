import java.util.ArrayList;

public class Vertex {
    int vertexID;
    int vertexRank;
    ArrayList<Edge> listOfIngoingEdges = new ArrayList<>();
    ArrayList<Edge> listOfOutgoingEdges = new ArrayList<>();
    boolean source;








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

    public ArrayList<Edge> getListOfOutgoingEdges() {
        return listOfOutgoingEdges;
    }

    public boolean isSource() {
        return source;
    }
    public void setSource() {

        if (listOfIngoingEdges.isEmpty())
        {
            this.source = true;
        }
        else
        {
            this.source = false;
        }
    }



}
