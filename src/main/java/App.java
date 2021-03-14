public class App {

    public static void main( String[] args ) {

        Functions functions = new Functions();
        BisectionMethod bisectionMethod = new BisectionMethod();

        double [] dupa = {3, -3, 0, 0};

        System.out.println(functions.horner(2, dupa));

        bisectionMethod.accurateMethod(0.5, 2, 0.000000000001);
        bisectionMethod.iterationMethod(0.5, 2, 1000);

    }
}
