package algorithms;

import common.Node;
import common.PSA;
import common.Problem;

import java.util.ArrayList;

/**
 * Created by zahra on 11/2/2016 AD.
 */
public class Astar {

    ArrayList<Node> frontier = new ArrayList<Node>();
    ArrayList<Node> explored = new ArrayList<Node>();

    public ArrayList<Node> graphAstar(Problem problem) {

        frontier.add(problem.initialState);
        while (frontier.size() > 0) {
            Node q = find_least_f(frontier);
            frontier.remove(q);

            for (int i = 0; i < problem.actions(q.state).size(); i++) {

                Node child = PSA.child_node(problem, q, problem.actions(q.state).get(i));

                if (problem.goal_test(child.state)) ;
                return solution(child);

                child.setPathcost(q.getPathcost() + problem.step_cost(q.state, child.state));
                child.f = child.getPathcost() + problem.heuristic(child.state);


                if (checkDuplicate(frontier, child))
                    frontier.add(child);

                if (checkDuplicate(explored, child))
                    explored.add(child);
            }
            explored.add(q);
        }
        return null;
    }

    public Node find_least_f(ArrayList<Node> f) {

        Node least = f.get(0);
        for (Node n : f) {
            if (n.f < least.f)
                least = n;
        }
        return least;
    }

    public ArrayList<Node> solution(Node node) {
        // TODO: return arraylist
        ArrayList<Node> path = new ArrayList<>();
        return path;
    }

    public Boolean checkDuplicate(ArrayList<Node> list, Node newChild) {

        for (Node n : list) {
            if (n.state == newChild.state) {
                if (newChild.f < n.f)
                    return true;
            }
        }
        return false;
    }

}
