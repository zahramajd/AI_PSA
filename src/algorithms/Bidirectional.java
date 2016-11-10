package algorithms;

import common.Node;
import common.PSA;
import common.Problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by zahra on 11/2/2016 AD.
 */
public class Bidirectional {

    class BFS {
        Node initialNode = new Node();
        Queue frontier = new LinkedList<>();
        Queue explored = new LinkedList<>();
        Problem problem;

        BFS(Problem problem, int state) {
            initialNode.parent = null;
            initialNode.state = state;
            this.problem = problem;
            frontier.add(initialNode);
        }

        Node[] next() {
            if (frontier.size() == 0)
                return null;

            Node node = (Node) frontier.remove();
            explored.add(node);

            for (int i = 0; i < problem.actions(node.state).size(); i++) {

                Node child = PSA.child_node(problem, node, problem.actions(node.state).get(i));

                // existence in f & e
                boolean isExist = false;

                for (Object n : frontier)
                    if (((Node) n).state == child.state)
                        isExist = true;

                for (Object m : explored)
                    if (((Node) m).state == child.state)
                        isExist = true;

                if (!isExist) {
                    frontier.add(child);
                }

            }

            return (Node[]) frontier.toArray(new Node[frontier.size()]);
        }

    }


    public ArrayList<Node> graphBD(Problem problem) {

        BFS a = new BFS(problem, 0);
        BFS b = new BFS(problem, problem.getGoalState());

        while (true) {

            Node[] qa = a.next();
            Node[] qb = b.next();
            if(qa==null || qb==null){
                continue;
            }

            for (Node n1 : qa)
                for (Node n2 : qb)
                    if (n1.state == n2.state) {

                        ArrayList<Node> path = new ArrayList<>();

                        while (n1 != null) {
                            path.add(0, n1);
                            n1 = n1.parent;
                        }

                        n2 = n2.parent;

                        while (n2 != null) {
                            path.add(n2);
                            n2 = n2.parent;
                        }

                        return path;
                    }
        }
    }
}
