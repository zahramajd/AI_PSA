package common;

import java.util.ArrayList;

/**
 * Created by zahra on 11/2/2016 AD.
 */
public interface Problem {

    int initialState =0;

    boolean goal_test(int state);

    ArrayList<Integer> actions(int state);

    int result(int state, int actions);

    float step_cost(int state1, int state2);

    float heuristic(int state);

}
