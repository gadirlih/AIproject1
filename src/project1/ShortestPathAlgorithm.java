package project1;

import java.util.*;

public abstract class ShortestPathAlgorithm {

    private final HashMap<Integer, ArrayList<Node>> mAdjacencyList;
    private final HashMap<Integer, Double> mShortestDistances;
    private final HashMap<Integer, Integer> mVertexParents;
    private final PriorityQueue<Node> mPriorityQueue;
    private final int mStartVertex;
    private final int mDestinationVertex;
    private final HashMap<Integer, Integer> mVertexSquare;
    private int mVisitedNodeCount = 0;
    private double mPathActualCost = 0;
    private final ArrayList<Integer> mShortestPath;

    public ShortestPathAlgorithm(Input input){
        this.mAdjacencyList = input.getAdjacencyList();
        this.mStartVertex = input.getStartVertex();
        this.mDestinationVertex = input.getDestinationVertex();
        this.mShortestDistances = new HashMap<>();
        this.mVertexParents = new HashMap<>();
        this.mVertexSquare = input.getVertexSquare();
        this.mPriorityQueue = new PriorityQueue<>(new NodeComparator());
        this.mShortestPath = new ArrayList<>();
    }

    public void search(){
        // start node and queue initialization
        Node startNode = new Node(mStartVertex, mVertexSquare.get(mStartVertex), 0);
        mPriorityQueue.add(startNode);
        mShortestDistances.put(startNode.getVertexID(), totalCost(startNode));

        while(!mPriorityQueue.isEmpty()){
            Node currentNode = mPriorityQueue.poll();

            // if the current path is more than the already existing shortest path to the node than continue
            if(totalCost(currentNode) > mShortestDistances.getOrDefault(currentNode.getVertexID(), Double.MAX_VALUE))
                continue;

            mVisitedNodeCount++;
            // if current node == goal then exit!
            if(currentNode.getVertexID() == mDestinationVertex) {
                prepareShortestPath();
                mPathActualCost = currentNode.getEdgeCost();
                break;
            }

            for (int i = 0; i < mAdjacencyList.getOrDefault(currentNode.getVertexID(), new ArrayList<>()).size(); i++)
            {
                Node adjacentNode = mAdjacencyList.get(currentNode.getVertexID()).get(i);

                // check if the cost of the adjacent node is shorter than the previous cost
                if (currentNode.getEdgeCost() + totalCost(adjacentNode) < mShortestDistances.getOrDefault(adjacentNode.getVertexID(), Double.MAX_VALUE))
                {
                    // update shortest distance to the adjacent node
                    mShortestDistances.put(adjacentNode.getVertexID(), currentNode.getEdgeCost() + totalCost(adjacentNode));

                    Node copy = adjacentNode.copy();
                    copy.setEdgeCost(currentNode.getEdgeCost() + adjacentNode.getEdgeCost());
                    // add adjacent node to the queue
                    mPriorityQueue.add(copy);

                    // update the parent of the adjacent node to the current node
                    mVertexParents.put(adjacentNode.getVertexID(), currentNode.getVertexID());
                }
            }
        }
    }

    private void prepareShortestPath(){
        int vertexID = mDestinationVertex;
        while(vertexID != mStartVertex){
            mShortestPath.add(vertexID);
            vertexID = mVertexParents.get(vertexID);
        }
        mShortestPath.add(mStartVertex);
    }


    public void printPath(){
        System.out.print("Path: ");
        for(int i = mShortestPath.size() - 1; i > 0; i--) {
            System.out.print(mShortestPath.get(i) + " -> ");
        }
        System.out.println(mShortestPath.get(0));
    }

    protected double totalCost(Node node){
        return heuristicCost(node) + node.getEdgeCost();
    }

    protected abstract double heuristicCost(Node node);

    class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return Double.compare(totalCost(o1), totalCost(o2));
        }
    }

    public int getVisitedNodeCount() {
        return mVisitedNodeCount;
    }

    public double getPathActualCost() {
        return mPathActualCost;
    }
}
