package project1;

import java.util.ArrayList;
import java.util.HashMap;

public class Input {
    private final HashMap<Integer, ArrayList<Node>> mAdjacencyList;
    private final HashMap<Integer, Integer> mVertexSquare;
    private final int mStartVertex;
    private final int mDestinationVertex;

    public Input(HashMap<Integer, ArrayList<Node>> adjacencyList, HashMap<Integer, Integer> vertexSquare, int startVertex, int destinationVertex) {
        this.mAdjacencyList = adjacencyList;
        this.mVertexSquare = vertexSquare;
        this.mStartVertex = startVertex;
        this.mDestinationVertex = destinationVertex;
    }

    public HashMap<Integer, ArrayList<Node>> getAdjacencyList() {
        return mAdjacencyList;
    }

    public HashMap<Integer, Integer> getVertexSquare() {
        return mVertexSquare;
    }

    public int getStartVertex() {
        return mStartVertex;
    }

    public int getDestinationVertex() {
        return mDestinationVertex;
    }
}
