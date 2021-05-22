package MathFunctions;

import MathFunctions.MathFunction;

public class AbsoluteFunction implements MathFunction {

    @Override
    public double calculate(double x) {
        return Math.abs(x);
    }
}
