package algorithms;

import common.Node;
import common.PSA;
import common.Problem;

import java.util.ArrayList;

/**
 * Created by zahra on 11/2/2016 AD.
 */
public class DFS {

    ArrayList<Node> result = new ArrayList<Node>();


    public ArrayList<Node> solution(Node node) {
        // TODO: return arraylist
        ArrayList<Node> path = new ArrayList<>();
        return path;
    }


    public ArrayList<Node> treeDepthLimitedSearch(Problem problem) {

        return recursiveDLS(problem.initialState, problem);
    }


    public ArrayList<Node> recursiveDLS(Node node, Problem problem) {

        boolean cutoff_occurred;

        if (problem.goal_test(node.state))
            return solution(node);
        else {
                cutoff_occurred = false;
                for (int i = 0; i < problem.actions(node.state).size(); i++) {
                    Node child = PSA.child_node(problem, node, problem.actions(node.state).get(i));
                    result = recursiveDLS(child, problem);
                    if (result.size() == 0)
                        cutoff_occurred = true;
                    else {
                        if (result != null)
                            return result;
                    }
                }
                if (cutoff_occurred)
                    return result;
                return null;

        }

    }

    // Graph

    ArrayList<Node> explored = new ArrayList<Node>();

    public ArrayList<Node> graphDepthLimitedSearch(Problem problem) {

        return graphRecursiveDLS(problem.initialState, problem);
    }


    public ArrayList<Node> graphRecursiveDLS(Node node, Problem problem) {

        boolean cutoff_occurred;

        if (problem.goal_test(node.state))
            return solution(node);
        else {
                cutoff_occurred = false;
                for (int i = 0; i < problem.actions(node.state).size(); i++) {
                    explored.add(node);
                    Node child = PSA.child_node(problem, node, problem.actions(node.state).get(i));
                    if (!explored.contains(child)) {
                        result = recursiveDLS(child, problem);
                        if (result.size() == 0)
                            cutoff_occurred = true;
                        else {
                            if (result != null)
                                return result;
                        }
                    }
                }
                if (cutoff_occurred)
                    return result;
                return null;

        }

    }
}
