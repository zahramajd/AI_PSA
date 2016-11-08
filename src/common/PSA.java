package common;

/**
 * Created by zahra on 11/2/2016 AD.
 */
public class PSA {

    public static Node child_node(Problem problem,Node parent,int action){

        Node child=new Node();
        child.state=problem.result(parent.state,action);
        child.parent=parent;
        child.actions=problem.actions(child.state);
        child.setPathcost(parent.getPathcost() +problem.step_cost(parent.state,action));

        return child;
    }

}
