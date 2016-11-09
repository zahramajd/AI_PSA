package problems;

import algorithms.Astar;
import algorithms.DFS;
import algorithms.UniformCost;
import common.Node;
import common.Problem;

import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.pow;


public class Pathfinding implements Problem {


    // actions
    // 1:up  2:right 3:down 4:left

    Coordinate source;
    Coordinate destination;

    // array of states
    ArrayList<Coordinate> states;
    ArrayList<Coordinate> obstacles;

    //
    int m, n;

    public Pathfinding(int m, int n) {
        // TODO : get from user
        // TODO : get obstacles
        this.source = new Coordinate(1, 1);
        source.state = 0;

        this.destination = new Coordinate(m, n);
        this.m = m;
        this.n = n;

        // array of states
        states = new ArrayList<>();
        states.add(source);
        states.add(destination);

        obstacles = new ArrayList<>();

    }

    public void solve(String alg) {
        switch (alg) {
            case "dfs":
                this.solveDFS();
                break;
            case "uc":
                this.solveUC();
                break;
            case "a":
                this.solveA();
                break;
        }

    }

    private void solveDFS() {

        DFS dfs = new DFS();
        ArrayList<Node> pathNodes = dfs.graphDFS(this);

        ArrayList<Coordinate> pathCoordinate = new ArrayList<>();
        for (Node n : pathNodes) {
            for (Coordinate c : states) {
                if (c.state == n.state)
                    pathCoordinate.add(0, c);
            }
        }

        for (Coordinate c : pathCoordinate) {
            System.out.println(c);
        }
    }

    private void solveUC() {

        UniformCost uc = new UniformCost();
        ArrayList<Node> pathNodes = uc.graphUniformCost(this);

        ArrayList<Coordinate> pathCoordinate = new ArrayList<>();
        for (Node n : pathNodes) {
            for (Coordinate c : states) {
                if (c.state == n.state)
                    pathCoordinate.add(0, c);
            }
        }

        for (Coordinate c : pathCoordinate) {
            System.out.println(c);
        }

    }

    private void solveA() {

        Astar a = new Astar();
        ArrayList<Node> pathNodes = a.graphAstar(this);

        ArrayList<Coordinate> pathCoordinate = new ArrayList<>();
        for (Node n : pathNodes) {
            for (Coordinate c : states) {
                if (c.state == n.state)
                    pathCoordinate.add(0, c);
            }
        }

        for (Coordinate c : pathCoordinate) {
            System.out.println(c);
        }

    }

    @Override
    public boolean goal_test(int state) {

        System.out.println("goal test");

        for (Coordinate c : states) {
            if (c.state == state) {
                System.out.println("x : "+c.x+" y : "+c.y);
                if ((c.x == destination.x) && (c.y == destination.y))
                    return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Integer> actions(int state) {

        for (Coordinate c : states) {
            if (c.state == state)
                return find_possible_actions(c);
        }

        return new ArrayList<>();
    }

    @Override
    public int result(int state, int action) {

        for (Coordinate c : states) {
            if (c.state == state) {
                return find_result(c, action);
            }
        }

        return 0;
    }

    @Override
    public float step_cost(int state1, int state2) {

        // TODO: distance br should asked
        for (Coordinate c1 : states) {
            if (c1.state == state1) {
                for (Coordinate c2 : states) {
                    if (c2.state == state2)
                        return (float) ((float) pow(abs(c1.x - c2.x), 2) + pow(abs(c1.y - c2.y), 2));
                }
            }
        }
        return 0;
    }

    @Override
    public float heuristic(int state) {

        for (Coordinate c : states) {
            if (c.state == state)
                return (float) ((float) pow(abs(c.x - n), 2) + pow(abs(c.y - m), 2));
        }
        return 0;
    }


    public ArrayList<Integer> find_possible_actions(Coordinate c) {

        // TODO: obstacles
        ArrayList<Integer> possibleActions = new ArrayList<>();
        // check up
        if (c.y >= 2)
            possibleActions.add(new Integer(1));
        // check right
        if (c.x < n)
            possibleActions.add(new Integer(2));
        // check down
        if (c.y < m)
            possibleActions.add(new Integer(3));
        // check left
        if (c.x >= 2)
            possibleActions.add(new Integer(4));


        return possibleActions;
    }

    public int find_result(Coordinate c, int action) {

        int xResult = 0;
        int yResult = 0;

        switch (action) {
            // up
            case 1:
                xResult = c.x;
                yResult = c.y - 1;
                break;
            // right
            case 2:
                xResult = c.x + 1;
                yResult = c.y;
                break;
            // down
            case 3:
                xResult = c.x;
                yResult = c.y + 1;
                break;
            // left
            case 4:
                xResult = c.x - 1;
                yResult = c.y;
                break;
        }

        boolean isExist = false;
        for (Coordinate co : states) {
            if ((co.x == xResult) && (co.y == yResult)) {
                isExist = true;
                return co.state;
            }
        }

        if (!isExist) {
            Coordinate newC = new Coordinate(xResult, yResult);
            states.add(newC);
            return newC.state;
        }
        return -1;
    }
}
