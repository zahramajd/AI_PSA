package problems.Chess;


/**
 * Created by zahra on 11/10/2016 AD.
 */
public class ChessState {

    private static int _state_start = 0;
    int state = 0;

    public int[] queens = new int[8];

    public ChessState() {
        this.state = _state_start++;
        System.out.println(this.state);
        for (int i = 0; i < 8; i++)
            queens[i] = i;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ChessState))
            return false;

        ChessState o = (ChessState) obj;

        for (int i = 0; i < 8; i++) {
            if (o.queens[i] != this.queens[i])
                return false;
        }

        return true;
    }

    public boolean isGoal() {

        for (int i = 0; i < 7; i++) {

            for (int j = i + 1; j < 8; j++) {
                if (queens[i] == queens[j])
                    return false;
                if (Math.abs(queens[i] - queens[j]) == j - i)
                    return false;
            }

        }

        show();
        return true;
    }

    public void show() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if( this.queens[i]==j)
                    System.out.print(" Q");
                else System.out.print(" - ");


            }
            System.out.println(" ");
        }

    }
}

