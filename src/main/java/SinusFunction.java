public class SinusFunction implements MathFunction {
    public double calculate(final double x) {
        return Math.sin(x - 1.5) * 3;
    }
}
