
public class BisectionMethod implements FindingMethods {

    @Override
    public double accurateMethod(double a, double b, MathFunction fun, double epsilon) throws Exception {
        if(fun.calculate(a) * fun.calculate(b) > 0) {
            System.out.println("X0 does not exist!");
            throw new Exception("X0 does not exist!");
        }

        double x0 = 0;
        while(Math.abs(a - b) > epsilon) {
            x0 = (a + b) / 2;

            if(fun.calculate(x0) * fun.calculate(a) < 0) {
                b = x0;
            }
            else if(fun.calculate(x0) * fun.calculate(b) < 0) {
                a = x0;
            }
        }
        return x0;
    }

    @Override
    public double iterationMethod(double a, double b, MathFunction fun, int iterations) throws Exception {
        if((fun.calculate(a) * fun.calculate(b)) > 0) {
            System.out.println("X0 does not exist!");
            throw new Exception("X0 does not exist!");
        }

        double x0 = 0;
        for(int i = 0; i < iterations; i++) {
            x0 = (a + b) / 2;

            if(fun.calculate(x0) * fun.calculate(a) < 0) {
                b = x0;
            }
            else if(fun.calculate(x0) * fun.calculate(b) < 0)  {
                a = x0;
            }
        }
        return x0;
    }
}
