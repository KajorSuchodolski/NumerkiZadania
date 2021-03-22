public class ExpFunction implements MathFunction {
    public double calculate (final double x) {
        // 2^x - 3
        double result = 2;
        if (x == (int) x) {
            for (int i = 1; i < x; i++){
                result *= 2;
            }
            return result - 3;
        }
        return Math.pow(2, x) - 3;
    }
}
