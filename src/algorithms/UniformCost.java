package algorithms;

import common.Node;
import common.PSA;
import common.Problem;

import java.util.*;

/**
 * Created by zahra on 11/2/2016 AD.
 */


public class UniformCost {

    static class PathComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {

            if (o1.getPathcost() > o2.getPathcost())
                return 1;
            if (o1.getPathcost() < o2.getPathcost())
                return -1;
            return 0;
        }
    }

    public ArrayList<Node> solution(Node node) {
        ArrayList<Node> path = new ArrayList<>();

        System.out.println("soll");
        while (node != null) {
            path.add(node);
            node = node.parent;
        }

        return path;
    }

    public ArrayList<Node> graphUniformCost(Problem problem) {


        Node initialNode = new Node();
        initialNode.parent = null;
        initialNode.state = 0;

        Node node = initialNode;
        if (problem.goal_test(node.state))
            return solution(node);


        Comparator<Node> comparator = new PathComparator();
        PriorityQueue frontier = new PriorityQueue<Node>(10, comparator);
        frontier.add(node);

        Queue explored = new LinkedList<>();

        while (true) {

            if (frontier.size() == 0)
                return null;

            node = (Node) frontier.remove();
            explored.add(node);

            for (int i = 0; i < problem.actions(node.state).size(); i++) {

                Node child = PSA.child_node(problem, node, problem.actions(node.state).get(i));


                // existence in f & e
                boolean isExist = false;

                for (Object n : frontier) {
                    if (((Node) n).state == child.state)
                        isExist = true;
                }

                //
                for (Object m : explored) {
                    if (((Node) m).state == child.state)
                        isExist = true;
                }

                if (!isExist) {
                    if (problem.goal_test(child.state)) {
                        return solution(child);
                    } else {
                        frontier.add(child);
                    }
                }


            }

        }

    }

    public ArrayList<Node> treeUniformCost(Problem problem) {

        Node initialNode = new Node();
        initialNode.parent = null;
        initialNode.state = 0;

        Node node = initialNode;
        if (problem.goal_test(node.state))
            return solution(node);


        Comparator<Node> comparator = new PathComparator();
        PriorityQueue frontier = new PriorityQueue<Node>(10, comparator);
        frontier.add(node);

        Queue explored = new LinkedList<>();

        while (true) {
            if (frontier.size() == 0)
                return null;

            node = (Node) frontier.remove();


            for (int i = 0; i < problem.actions(node.state).size(); i++) {
                Node child = PSA.child_node(problem, node, problem.actions(node.state).get(i));

                // existence in f & e
                boolean isExist = false;

                for (Object n : frontier) {
                    if (((Node) n).state == child.state)
                        isExist = true;
                }

                if (!isExist) {
                    if (problem.goal_test(child.state)) {
                        return solution(child);
                    } else {
                        frontier.add(child);
                    }
                }

            }

        }

    }

}
