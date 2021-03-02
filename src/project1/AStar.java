package project1;

public class AStar extends ShortestPathAlgorithm{
    private final int mDestinationVertexSquare; // destination node squareID

    public AStar(Input input) {
        super(input);
        this.mDestinationVertexSquare = input.getVertexSquare().get(input.getDestinationVertex());
    }

    // Find Euclidean distance between the current node and the destination node
    @Override
    protected double heuristicCost(Node node) {
        int diff = Math.abs(node.getSquareID() - mDestinationVertexSquare);
        int h = (diff / 10) * 10; // find the height
        int w = (diff % 10) * 10; // find the width
        return Math.sqrt(h * h + w * w);
    }
}
