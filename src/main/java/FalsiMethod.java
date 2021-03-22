import java.text.DecimalFormat;

public class FalsiMethod implements FindingMethods {
    private double x0;
    private double rangeA;
    private double rangeB;

    private void calculate(MathFunction fun) {
        x0 = (rangeA * fun.calculate(rangeB) - rangeB * fun.calculate(rangeA)) / (fun.calculate(rangeB) - fun.calculate(rangeA));

        if(fun.calculate(x0) * fun.calculate(rangeA) < 0) {
            rangeB = x0;
        }
        else if(fun.calculate(x0) * fun.calculate(rangeB) < 0) {
            rangeA = x0;
        }
    }

    @Override
    public double accurateMethod (double a, double b, MathFunction fun, double epsilon) throws Exception {

        int iterations = 0;
        if( (fun.calculate(a) * fun.calculate(b)) > 0) {
            System.out.println("X0 does not exist!");
            throw new Exception("X0 does not exist!");
        }

        x0 = 0;
        rangeA = a;
        rangeB = b;
        while (Math.abs(rangeA - rangeB) > epsilon) {
            calculate(fun);
            iterations++;
        }
        System.out.println("Epsilon = " + new DecimalFormat("#0.00000000000").format(epsilon));
        System.out.println("x0 = " + new DecimalFormat("#0.00000000000").format(x0));
        System.out.println(("f(x0) = " + new DecimalFormat("#0.00000000000").format(fun.calculate(x0)) ));
        System.out.println("Number of iterations: " + iterations);
        return x0;
    }

    @Override
    public double iterationMethod (double a, double b, MathFunction fun, int iterations) throws Exception {
        if( (fun.calculate(a) * fun.calculate(b)) > 0) {
            System.out.println("X0 does not exist!");
            throw new Exception("X0 does not exist!");
        }

        x0 = 0;
        rangeA = a;
        rangeB = b;
        for (int i = 0; i < iterations; i++) {
            calculate(fun);
        }
        System.out.println("x0 = " + new DecimalFormat("#0.00000000000").format(x0));
        System.out.println(("f(x0) = " + new DecimalFormat("#0.00000000000").format(fun.calculate(x0)) ));
        System.out.println("Number of iterations: " + iterations);
        return x0;
    }

}

