
public class App {

    public static void main( String[] args ) throws Exception {

        Functions functions = new Functions();
        BisectionMethod bisectionMethod = new BisectionMethod();
        FalsiMethod falsiMethod = new FalsiMethod();

        double [] dupa = {3, -3, 0, 0};

        System.out.println(functions.horner(2, dupa));

        bisectionMethod.accurateMethod(0.5, 2, 0.000000000001);
        bisectionMethod.iterationMethod(0.5, 2, 1000);
        falsiMethod.iterationMethod(3, 4, 1);

    }
}
