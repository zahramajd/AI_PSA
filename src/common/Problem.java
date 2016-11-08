package common;

import java.util.ArrayList;

/**
 * Created by zahra on 11/2/2016 AD.
 */
public interface Problem {

    public Node initialState=null;

    public boolean goal_test(int state);
    public ArrayList<Integer> actions(int state);
    public int result(int state,int actions);
    public float step_cost(int state1,int state2);
    public float heuristic(int state);

}
