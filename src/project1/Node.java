package project1;

public class Node{
    private final int mVertexID; // vertex ID
    private final int mSquareID; // vertex square ID
    private double mEdgeCost = 0; // incoming edge cost

    public Node(int vertexID, int squareID, double edgeCost){
        this.mVertexID = vertexID;
        this.mSquareID = squareID;
        this.mEdgeCost = edgeCost;
    }

    public int getVertexID() {
        return mVertexID;
    }

    public int getSquareID() {
        return mSquareID;
    }

    public double getEdgeCost() {
        return mEdgeCost;
    }

    public void setEdgeCost(double edgeCost) {
        this.mEdgeCost = edgeCost;
    }

    public Node copy(){
        return new Node(mVertexID, mSquareID, mEdgeCost);
    }
}
