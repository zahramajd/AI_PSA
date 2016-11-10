package problems.Chess;

import algorithms.Astar;
import algorithms.BFS;
import algorithms.DepthLimitedDFS;
import common.Node;
import common.Problem;

import java.util.ArrayList;

/**
 * Created by zahra on 11/2/2016 AD.
 */
public class EightQueens implements Problem {

    ChessState initialNode;
    ArrayList<ChessAction> chessActions;
    ArrayList<ChessState> chessStates;


    public EightQueens() {
        // initial
        this.initialNode = new ChessState();
        chessActions = new ArrayList<>();
        chessStates = new ArrayList<>();

        chessStates.add(initialNode);

        make_actions();
    }

    public void solve(String alg) {

        switch (alg) {
            case "bfs":
                this.solveBFS();
                break;
            case "ldfs":
                this.solveLDFS();
                break;
            case "a":
                this.solveA();
                break;
        }

    }

    private void solveBFS() {

        BFS bfs = new BFS();
        ArrayList<Node> pathNodes = bfs.treeBFS(this);



    }

    private void solveLDFS() {

        DepthLimitedDFS ldfs = new DepthLimitedDFS();
        ArrayList<Node> pathNodes = ldfs.graphDepthLimitedSearch(this, 8);
    }

    private void solveA() {

        Astar a = new Astar();
        ArrayList<Node> pathNodes = a.graphAstar(this);


    }

    @Override
    public boolean goal_test(int state) {

        ChessState s = null;

        for (ChessState i : this.chessStates)
            if (i.state == state)
                s = i;

        if (s == null)
            return false;

        return s.isGoal();
    }

    @Override
    public ArrayList<Integer> actions(int state) {

        ArrayList<Integer> possibleActions = new ArrayList<>();
        for (int i = 1; i <= 28; i++)
            possibleActions.add(new Integer(i));

        return possibleActions;
    }

    @Override
    public int result(int state, int action) {

        for (ChessState c : chessStates) {
            if (c.state == state) {
                return find_result(c, action);
            }
        }

        return -1;
    }

    @Override
    public float step_cost(int state1, int state2) {
        return 1;
    }

    @Override
    public float heuristic(int state) {
        // TODO: heuristic

        return 0;
    }

    public void make_actions() {

        int j = 2;
        for (int i = 0; i < 8; i++) {
            for (j = i; j < 8; j++) {
                if (i != j) {
                    ChessAction ca = new ChessAction(i, j);
                    chessActions.add(ca);
                }
            }
        }

    }

    public int find_result(ChessState c, int action) {


        ChessState chessResult = new ChessState();

        for (int i = 0; i < 8; i++)
            chessResult.queens[i] = c.queens[i];

        ChessAction ca = chessActions.get(action - 1);

        int temp = chessResult.queens[ca.b];
        chessResult.queens[ca.b] = chessResult.queens[ca.a];
        chessResult.queens[ca.a] = temp;

        for (ChessState co : chessStates) {
            if (co.equals(chessResult))
                return co.state;
        }

        this.chessStates.add(chessResult);
        return chessResult.state;
    }
}
