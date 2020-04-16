public class Edge {
    int edgeWeight;
    Vertex edgeParent;
    Vertex edgeChild;

    //-------------------------GETTERS & SETTERS---------------------------------\\
    public int getEdgeWeight(){
        return this.edgeWeight;
    }

    public Vertex getEdgeParent(){
        return this.edgeParent;
    }

    public Vertex getEdgeChild(){
        return this.edgeChild;
    }

    public void setEdgeWeight(int newWeight){
        this.edgeWeight = newWeight;
    }

    public void setEdgeParent(Vertex newParent){this.edgeParent = newParent;
    }

    public void setEdgeChild(Vertex newChild){
        this.edgeChild = newChild;
    }
}
