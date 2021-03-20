
public class App {

    public static void main( String[] args ) throws Exception {

        Functions functions = new Functions();
        BisectionMethod bisectionMethod = new BisectionMethod();
        FalsiMethod falsiMethod = new FalsiMethod();

        System.out.println(functions.horner(2));

        bisectionMethod.accurateMethod(0.5, 2, 1,0.000000000001);
        bisectionMethod.iterationMethod(0.5, 2, 1, 1000);
        falsiMethod.iterationMethod(3, 4, 1,1);
    }
}
