public class CompositionFunction implements MathFunction {
    public double calculate(final double x) {
        return Math.pow(2, Math.cos(x)) + Math.sin(4 * x - 2);
    }
}
