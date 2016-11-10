import problems.Chess.EightQueens;

public class Main {

    public static void main(String[] args) throws Exception {

//        // Scanner input = new Scanner(System.in);
//        Scanner input = new Scanner(new FileInputStream("input.txt"));
//
//       // System.out.println("m : ");
//        int m=input.nextInt();
//
//       // System.out.println("n : ");
//        int n=input.nextInt();
//
//        System.out.println(m);
//        Pathfinding pf=new Pathfinding(m,n);
//
//        pf.solve("dfs");

        EightQueens eq=new EightQueens();
        eq.solve("ldfs");


    }
}
