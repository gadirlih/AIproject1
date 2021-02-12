package project1;

public class UniformCostSearch extends ShortestPathAlgorithm {
    public UniformCostSearch(Input input) {
        super(input);
    }

    @Override
    protected double heuristicCost(Node node) {
        return 0;
    }
}
