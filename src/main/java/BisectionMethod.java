import java.text.DecimalFormat;

public class BisectionMethod implements FindingMethods {
    private double x0;
    private double rangeA;
    private double rangeB;
    private double diff;

    private void calculate(MathFunction fun) {
        double newX0 = (rangeA + rangeB) / 2;
        diff = x0 - newX0;
        x0 = newX0;

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

        int iterations = 0;
        x0 = 0;
        rangeA = a;
        rangeB = b;
        do {
            calculate(fun);
            iterations++;
        } while (Math.abs(diff) > epsilon);
        System.out.println("Epsilon = " + new DecimalFormat("#0.00000000000").format(epsilon));
        System.out.println("x0 = " + new DecimalFormat("#0.00000000000").format(x0));
        System.out.println(("f(x0) = " + new DecimalFormat("#0.00000000000").format(fun.calculate(x0)) ));
        System.out.println("Number of iterations: " + iterations);
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

        System.out.println("x0 = " + new DecimalFormat("#0.00000000000").format(x0));
        System.out.println(("f(x0) = " + new DecimalFormat("#0.00000000000").format(fun.calculate(x0)) ));
        System.out.println("Number of iterations: " + iterations);
        return x0;
    }
}
