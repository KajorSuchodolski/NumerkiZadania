
public class Functions {

    public double horner(final double x) {
        double [] factors = {3, -3, 0, 0};
        double y = factors[0];

        for(int i = 1; i < factors.length; i++) {
            y = y * x + factors[i];
        }
        return y;
    }

    public double exp(final double x, double a, double b) {
        if(a < 0) {
            throw new ArithmeticException("a must be greater than 0!");
        }

        double y = a;
        for(int i = 1; i < x ; i++) {
            y *= y;
        }
        return y + b;
    }

    public double sinFunction(final double x) {
        return Math.sin(x) * 3;
    }

    public double tanFunction(final double x) {
        return Math.tan(x) * 2 + 7;
    }

    public double chooseFunction (final double x, int i) throws Exception {
        return switch (i) {
            case 1 -> sinFunction(x);
            case 2 -> tanFunction(x);
            case 3 -> horner(x);
            default -> throw new Exception("Out of scope");
        };
    }
}
