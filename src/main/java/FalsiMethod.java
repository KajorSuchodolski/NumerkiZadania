
public class FalsiMethod implements FindingMethods {

    @Override
    public double accurateMethod (double a, double b, double epsilon) throws Exception {
        Functions functions = new Functions();
        if( (functions.sinFunction(a) * functions.sinFunction(b)) > 0) {
            System.out.println("X0 does not exist!");
//            return;
            throw new Exception("X0 does not exist!");
        }

        double x0 = 0;
        while (Math.abs(a - b) > epsilon) {
            x0 = (a * functions.sinFunction(b) - b * functions.sinFunction(a)) / (functions.sinFunction(b) - functions.sinFunction(a));

            if(functions.sinFunction(x0) * functions.sinFunction(a) < 0) {
                b = x0;
            }
            else if(functions.sinFunction(x0) * functions.sinFunction(b) < 0) {
                a = x0;
            }
        }
        return x0;
    }

    @Override
    public double iterationMethod (double a, double b, int iterations) throws Exception {
        Functions functions = new Functions();
        if( (functions.sinFunction(a) * functions.sinFunction(b)) > 0) {
            System.out.println("X0 does not exist!");
//            return;
            throw new Exception("X0 does not exist!");
        }

        double x0 = 0;
        for (int i = 0; i < iterations; i++) {
            x0 = (a * functions.sinFunction(b) - b * functions.sinFunction(a)) / (functions.sinFunction(b) - functions.sinFunction(a));

            if(functions.sinFunction(x0) * functions.sinFunction(a) < 0) {
                b = x0;
            }
            else if(functions.sinFunction(x0) * functions.sinFunction(b) < 0) {
                a = x0;
            }
        }
        return x0;
    }

}

