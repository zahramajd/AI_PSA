package problems;

/**
 * Created by zahra on 11/8/2016 AD.
 */
public class Coordinate {

    int state;
    int x;
    int y;

    public Coordinate(int x,int y){
        this.state=100*x+y;
        this.x=x;
        this.y=y;
    }
}
