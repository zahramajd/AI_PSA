package problems.PathFinding;

import algorithms.Astar;
import algorithms.Bidirectional;
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

    // array of obstacles
    ArrayList<Obstacle> obstacles;

    //
    int m, n;

    public Pathfinding(int m, int n, ArrayList<Obstacle> obstacles) {

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

        this.obstacles = obstacles;

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
            case "bd":
                this.solveBD();
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


    class Cell {

        Integer value;
        boolean a, b, c, d;

        Cell(Integer value, boolean a, boolean b, boolean c, boolean d) {
            this.value = value;
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        public String top() {
            return a ? "__" : "  ";
        }

        @Override
        public String toString() {
            return (value == null ? "." : value) + (b ? "|" : " ");
        }
    }

    boolean checkObs(Coordinate from, Coordinate to) {
        for (Obstacle o : obstacles) {
            if ((o.a.getX() == from.getX() && o.a.getY() == from.getY() && o.b.getX() == to.getX() && o.b.getY() == to.getY()) ||
                    (o.a.getX() == to.getX() && o.a.getY() == to.getY() && o.b.getX() == from.getX() && o.b.getY() == from.getY()))
                return true;
        }
        return false;
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

        Cell[][] map = new Cell[this.n][this.m];

        for (int i = 1; i <= this.n; i++) {
            for (int j = 1; j <= this.m; j++) {
                boolean da = checkObs(new Coordinate(j,i), new Coordinate(j, i - 1));
                boolean db = checkObs(new Coordinate(j,i), new Coordinate(j + 1, i));
                boolean dc = checkObs(new Coordinate(j,i), new Coordinate(j, i + 1));
                boolean dd = checkObs(new Coordinate(j,i), new Coordinate(j - 1, i));
                map[i - 1][j - 1] = new Cell(null, da, db, dc, dd);
            }
        }

        int s = 0;
        for (Coordinate c : pathCoordinate) {
            map[c.getX() - 1][c.getY() - 1].value = s++;
        }

        System.out.println("-----------------------------");

        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++)
                System.out.print(map[i][j]);

            System.out.print("\r\n");
        }

        System.out.println("-----------------------------");

    }

    private void solveBD() {

        Bidirectional b = new Bidirectional();
        ArrayList<Node> pathNodes = b.graphBD(this);

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
                System.out.println("x : " + c.getX() + " y : " + c.getY());
                if ((c.getX() == destination.getX()) && (c.getY() == destination.getY()))
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


        for (Coordinate c1 : states) {
            if (c1.state == state1) {
                for (Coordinate c2 : states) {
                    if (c2.state == state2)
                        return (float) ((float) pow(abs(c1.getX() - c2.getX()), 2) + pow(abs(c1.getY() - c2.getY()), 2));
                }
            }
        }
        return 0;
    }

    @Override
    public float heuristic(int state) {

        for (Coordinate c : states) {
            if (c.state == state)
                return (float) ((float) pow(abs(c.getX() - n), 2) + pow(abs(c.getY() - m), 2));
        }
        return 0;
    }

    @Override
    public int getGoalState() {
        return destination.state;
    }


    public ArrayList<Integer> find_possible_actions(Coordinate c) {

        // TODO: obstacles
        ArrayList<Integer> possibleActions = new ArrayList<>();
        // check up
        if ((c.getY() >= 2) && !check_obstacles(c, 1))
            possibleActions.add(new Integer(1));
        // check right
        if ((c.getX() < n) && !check_obstacles(c, 2))
            possibleActions.add(new Integer(2));
        // check down
        if ((c.getY() < m) && !check_obstacles(c, 3))
            possibleActions.add(new Integer(3));
        // check left
        if ((c.getX() >= 2) && !check_obstacles(c, 4))
            possibleActions.add(new Integer(4));


        return possibleActions;
    }

    public int find_result(Coordinate c, int action) {

        int xResult = 0;
        int yResult = 0;

        switch (action) {
            // up
            case 1:
                xResult = c.getX();
                yResult = c.getY() - 1;
                break;
            // right
            case 2:
                xResult = c.getX() + 1;
                yResult = c.getY();
                break;
            // down
            case 3:
                xResult = c.getX();
                yResult = c.getY() + 1;
                break;
            // left
            case 4:
                xResult = c.getX() - 1;
                yResult = c.getY();
                break;
        }

        boolean isExist = false;
        for (Coordinate co : states) {
            if ((co.getX() == xResult) && (co.getY() == yResult)) {
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

    public boolean check_obstacles(Coordinate from, int action) {


        Coordinate to = new Coordinate();

        switch (action) {
            // up
            case 1:
                to.setX(from.getX());
                to.setY(from.getY() - 1);
                break;
            // right
            case 2:
                to.setX(from.getX() + 1);
                to.setY(from.getY());
                break;
            // down
            case 3:
                to.setX(from.getX());
                to.setY(from.getY() + 1);
                break;
            // left
            case 4:
                to.setX(from.getX() - 1);
                to.setY(from.getY());
                break;
        }

        for (Obstacle o : obstacles) {

            if(o.a.equals(from) && o.b.equals(to))
                return true;

            if(o.a.equals(to) && o.b.equals(from))
                return true;

        }
        return false;

    }
}
