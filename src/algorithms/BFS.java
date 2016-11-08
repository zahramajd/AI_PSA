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
public class BFS {

//    public Queue<Node> frontier;
//    public Queue<Node> explored;


    public ArrayList<Node> solution(Node node) {
        // TODO: return arraylist
        ArrayList<Node> path = new ArrayList<>();
        return path;
    }

    public ArrayList<Node> graphBFS(Problem problem) {

        Node initialNode = problem.initialState;
        if (problem.goal_test(initialNode.state))
            return solution(initialNode);


        Queue frontier = new LinkedList<>();
        frontier.add(initialNode);

        Queue explored = new LinkedList<>();

        while (true) {
            if (frontier.size() == 0)
                return null;

            Node node = (Node) frontier.remove();
            explored.add(node);

            for (int i = 0; i < problem.actions(node.state).size(); i++) {

                Node child = PSA.child_node(problem, node, problem.actions(node.state).get(i));

                // existence in f & e
                boolean isExist = false;

                for (Object n : frontier) {
                    if ( ((Node) n).state == child.state)
                        isExist = true;
                }
                //
                for (Object m : explored) {
                    if (((Node) m).state == child.state)
                        isExist = true;
                }

                if(!isExist)
                {
                    if (problem.goal_test(child.state))
                        return solution(child);
                }

                frontier.add(child);

            }

        }

    }

    public ArrayList<Node> treeBFS(Problem problem) {

        Node node = problem.initialState;
        if (problem.goal_test(node.state))
            return solution(node);


        Queue frontier = new LinkedList<>();
        frontier.add(node);


        while (true) {
            if (frontier.size() == 0)
                return null;

            node = (Node) frontier.remove();


            for (int i = 0; i < problem.actions(node.state).size(); i++) {
                Node child = PSA.child_node(problem, node, problem.actions(node.state).get(i));

                // existence in f & e
                boolean isExist = false;

                for (Object n : frontier) {
                    if ( ((Node) n).state == child.state)
                        isExist = true;
                }

                if(!isExist)
                {
                    if (problem.goal_test(child.state))
                        return solution(child);
                }

                frontier.add(child);

            }

        }

    }

}
