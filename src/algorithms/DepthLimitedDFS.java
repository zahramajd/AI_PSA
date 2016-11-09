package algorithms;

import common.Node;
import common.PSA;
import common.Problem;

import java.util.ArrayList;

/**
 * Created by zahra on 11/2/2016 AD.
 */
public class DepthLimitedDFS {


    ArrayList<Node> result = new ArrayList<Node>();


    public ArrayList<Node> solution(Node node) {
        ArrayList<Node> path = new ArrayList<>();

        while (node != null) {
            path.add(node);
            node = node.parent;
        }

        return path;
    }


    // tree

    public ArrayList<Node> treeDepthLimitedSearch(Problem problem, int limit) {

        Node initialNode=new Node();
        initialNode.parent=null;
        initialNode.state=0;

        return recursiveDLS(initialNode, problem, limit);
    }


    public ArrayList<Node> recursiveDLS(Node node, Problem problem, int limit) {

        boolean cutoff_occurred;

        if (problem.goal_test(node.state))
            return solution(node);
        else {
            if (limit == 0)
                return null;
            else {
                cutoff_occurred = false;
                for (int i = 0; i < problem.actions(node.state).size(); i++) {
                    Node child = PSA.child_node(problem, node, problem.actions(node.state).get(i));
                    result = recursiveDLS(child, problem, limit - 1);
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

    }

    // Graph

    ArrayList<Node> explored = new ArrayList<Node>();

    public ArrayList<Node> graphDepthLimitedSearch(Problem problem, int limit) {

        Node initialNode=new Node();
        initialNode.parent=null;
        initialNode.state=0;

        return graphRecursiveDLS(initialNode, problem, limit);
    }


    public ArrayList<Node> graphRecursiveDLS(Node node, Problem problem, int limit) {

        boolean cutoff_occurred;

        if (problem.goal_test(node.state))
            return solution(node);
        else {
            if (limit == 0)
                return null;
            else {
                cutoff_occurred = false;
                for (int i = 0; i < problem.actions(node.state).size(); i++) {
                    explored.add(node);
                    Node child = PSA.child_node(problem, node, problem.actions(node.state).get(i));
                    if (!explored.contains(child)) {
                        result = recursiveDLS(child, problem, limit - 1);
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
}
