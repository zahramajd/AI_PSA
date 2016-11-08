package common;

import java.util.ArrayList;

/**
 * Created by zahra on 11/2/2016 AD.
 */
public class Node {

    public Node parent;
    public int state;
    private int pathcost=0;
    public ArrayList<Integer> actions;

    public float f=0;

    public int getPathcost() {
        return pathcost;
    }

    public void setPathcost(int pathcost) {
        this.pathcost = pathcost;
    }
}
