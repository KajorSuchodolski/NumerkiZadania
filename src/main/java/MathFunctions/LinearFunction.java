package MathFunctions;

import MathFunctions.MathFunction;

public class LinearFunction implements MathFunction {

    private double a, b;
    public LinearFunction(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double calculate( double x) {
        return a * x + b;
    }
}
