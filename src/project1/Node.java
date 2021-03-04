package project1;

public class Node{
    private final int mVertexID; // vertex ID
    private final int mSquareID; // vertex square ID
    private double mBackwardCost; // backward cost | incoming edge cost

    public Node(int vertexID, int squareID, double backwardCost){
        this.mVertexID = vertexID;
        this.mSquareID = squareID;
        this.mBackwardCost = backwardCost;
    }

    public int getVertexID() {
        return mVertexID;
    }

    public int getSquareID() {
        return mSquareID;
    }

    public double getBackwardCost() {
        return mBackwardCost;
    }

    public void setBackwardCost(double backwardCost) {
        this.mBackwardCost = backwardCost;
    }

    public Node copy(){
        return new Node(mVertexID, mSquareID, mBackwardCost);
    }
}
