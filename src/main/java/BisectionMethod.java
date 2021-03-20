
public class BisectionMethod implements FindingMethods {

    @Override
    public double accurateMethod(double a, double b, int fun, double epsilon) throws Exception {
        Functions functions = new Functions();
        if(functions.chooseFunction(a, fun) * functions.chooseFunction(b, fun) > 0) {
            System.out.println("X0 does not exist!");
            throw new Exception("X0 does not exist!");
        }


        double x0 = 0;
        while(Math.abs(a - b) > epsilon) {
            x0 = (a + b) / 2;

            if(functions.chooseFunction(x0, fun) * functions.chooseFunction(a, fun) < 0) {
                b = x0;
            }
            else if(functions.chooseFunction(x0, fun) * functions.chooseFunction(b, fun) < 0) {
                a = x0;
            }
        }
        return x0;
    }

    @Override
    public double iterationMethod(double a, double b, int fun, int iterations) throws Exception {
        Functions functions = new Functions();
        if((functions.chooseFunction(a, fun) * functions.chooseFunction(b, fun)) > 0) {
            System.out.println("X0 does not exist!");
            throw new Exception("X0 does not exist!");
        }

        double x0 = 0;
        for(int i = 0; i < iterations; i++) {
            x0 = (a + b) / 2;

            if(functions.chooseFunction(x0, fun) * functions.chooseFunction(a, fun) < 0) {
                b = x0;
            }
            else if(functions.chooseFunction(x0, fun) * functions.chooseFunction(b, fun) < 0)  {
                a = x0;
            }
        }
        return x0;
    }
}
