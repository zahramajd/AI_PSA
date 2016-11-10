package algorithms;

import common.Node;
import common.PSA;
import common.Problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class DFS {


    public ArrayList<Node> solution(Node node) {

        ArrayList<Node> path = new ArrayList<>();

        while (node != null) {
            path.add(node);
            node = node.parent;
        }

        return path;
    }

    public ArrayList<Node> graphDFS(Problem problem) {

        Node initialNode=new Node();
        initialNode.parent=null;
        initialNode.state=0;

        return recursiveGraphDFS(initialNode,problem);
    }

    private ArrayList<Node> recursiveGraphDFS(Node node,Problem problem){

        if (problem.goal_test(node.state))
            return solution(node);


        for (int i = 0; i < problem.actions(node.state).size(); i++) {

            Node child = PSA.child_node(problem, node, problem.actions(node.state).get(i));

           return recursiveGraphDFS(child,problem);
        }
        return null;
    }

    public ArrayList<Node> treeDFS(Problem problem) {

        Node initialNode=new Node();
        initialNode.parent=null;
        initialNode.state=0;


        Node node = initialNode;
        if (problem.goal_test(node.state))
            return solution(node);


        Stack frontier = new Stack();
        frontier.add(node);


        while (true) {
            if (frontier.size() == 0)
                return null;

            node = (Node) frontier.pop();


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
