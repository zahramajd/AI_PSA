package algorithms;

import common.Node;
import common.Problem;

import java.util.ArrayList;

/**
 * Created by zahra on 11/2/2016 AD.
 */
public class IterativeDeepeningDFS {


    ArrayList<Node> result = new ArrayList<Node>();


    public ArrayList<Node> graphIterativeDeepingDFS(Problem problem) {

        for (int depth = 0; depth < 1000; depth++) {

            LDFS ddfs = new LDFS();
            result = ddfs.graphDFS(problem,depth);
            if (result == null)
                return result;
            if (result.size() > 0)
                return result;
        }
        return null;
    }

    public ArrayList<Node> treeIterativeDeepingDFS(Problem problem) {

        for (int depth = 0; depth < 1000; depth++) {

            LDFS ddfs = new LDFS();
            result = ddfs.treeDFS(problem, depth);
            if (result == null)
                return result;
            if (result.size() > 0)
                return result;
        }
        return null;
    }
}
