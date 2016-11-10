package problems.PathFinding;

/**
 * Created by zahra on 11/8/2016 AD.
 */
public class Coordinate {

    public int state;
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.state = 100 * x + y;
        this.setX(x);
        this.setY(y);
    }

    public Coordinate() {

    }

    @Override
    public String toString() {
        return getX() + "," + getY();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        Coordinate c = (Coordinate) obj;
        return this.x == c.x && this.y == c.y;
    }
}
