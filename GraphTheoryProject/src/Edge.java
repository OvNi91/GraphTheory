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

<<<<<<< HEAD
    public void setEdgeParent(Vertex newParent){this.edgeParent = newParent;
=======
    public void setEdgeParent(Vertex newParent){
        this.edgeParent = newParent;
>>>>>>> ffb54710bdbce894fc8e149a86d35ea9dce22ce7
    }

    public void setEdgeChild(Vertex newChild){
        this.edgeChild = newChild;
    }
}
