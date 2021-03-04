package project1;

public class Main {
    public static void main(String[] args){
        // check if input file exists
        if(args.length != 1){
            System.out.println("There must be exactly one argument(input file path) passed to the program!");
            return;
        }

        // Read input
        Input input = InputReader.read(args[0]);

        // UCS
        ShortestPathAlgorithm ucs = new UniformCostSearch(input);
        ucs.search();
        System.out.println("Uniform Cost Search: ");
        ucs.printPath();
        System.out.println("Cost of the Shortest Path: " + ucs.getPathActualCost());
        System.out.println("Number of nodes expanded: " + ucs.getVisitedNodeCount());

        System.out.println();

        // AStar
        ShortestPathAlgorithm aStar = new AStar(input);
        aStar.search();
        System.out.println("AStar Search: ");
        aStar.printPath();
        System.out.println("Cost of the Shortest Path: " + aStar.getPathActualCost());
        System.out.println("Number of nodes expanded: " + aStar.getVisitedNodeCount());

        System.out.println();

        // Compare results
        System.out.println("Result Comparison:");
        if(ucs.getPathActualCost() != aStar.getPathActualCost()){
            System.out.println("AStar found a suboptimal path to the destination");
            System.out.println("Extra cost of the path is: " + String.format("%.2f", (aStar.getPathActualCost() - ucs.getPathActualCost())));
        }else{
            System.out.println("AStar found an optimal path to the destination same as UCS");
        }

        if(ucs.getVisitedNodeCount() > aStar.getVisitedNodeCount()){
            System.out.println("AStar expanded " + (ucs.getVisitedNodeCount() - aStar.getVisitedNodeCount())
                    +" less number of nodes to get to the destination node");
        }else if(ucs.getVisitedNodeCount() < aStar.getVisitedNodeCount()){
            System.out.println("AStar expanded " + (ucs.getVisitedNodeCount() - aStar.getVisitedNodeCount())
                    +" more number of nodes to get to the destination node");
        }else{
            System.out.println("Both algorithms expanded the same " + ucs.getVisitedNodeCount()
                    +" number of nodes to get to the destination node");
        }
    }
}
