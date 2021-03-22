
public class BisectionMethod implements FindingMethods {
    private double x0;
    private double rangeA;
    private double rangeB;

    private void calculate(MathFunction fun) {
        x0 = (rangeA + rangeB) / 2;

        if(fun.calculate(x0) * fun.calculate(rangeA) < 0) {
            rangeB = x0;
        }
        else if(fun.calculate(x0) * fun.calculate(rangeB) < 0) {
            rangeA = x0;
        }
    }

    @Override
    public double accurateMethod(double a, double b, MathFunction fun, double epsilon) throws Exception {
        if(fun.calculate(a) * fun.calculate(b) > 0) {
            System.out.println("X0 does not exist!");
            throw new Exception("X0 does not exist!");
        }

        x0 = 0;
        rangeA = a;
        rangeB = b;
        while(Math.abs(a - b) > epsilon) {
            calculate(fun);
        }
        return x0;
    }

    @Override
    public double iterationMethod(double a, double b, MathFunction fun, int iterations) throws Exception {
        if((fun.calculate(a) * fun.calculate(b)) > 0) {
            System.out.println("X0 does not exist!");
            throw new Exception("X0 does not exist!");
        }

        x0 = 0;
        rangeA = a;
        rangeB = b;
        for(int i = 0; i < iterations; i++) {
            calculate(fun);
        }
        return x0;
    }
}
