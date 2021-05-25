import MathFunctions.MathFunction;

public class Integral {

    private final double eps;
    private final MathFunction inner;
    private final MathFunction outer;
    private final MathFunction legendre;

    public Integral(double eps, MathFunction inner, MathFunction outer, MathFunction legendre) {
        this.eps = eps;
        this.inner = inner;
        this.outer = outer;
        this.legendre = legendre;
    }

    public double getWeight(double x) {
        return (1 / Math.sqrt(1 - x * x));
    }

    public double GaussChebyshev(int nodes) {
        double res = 0;
        if (outer != null) {
            for (int i = 1; i <= nodes; i++) {
                double node = Math.cos((2 * i - 1) * Math.PI / (2 * nodes));
                res += outer.calculate(inner.calculate(node)) * legendre.calculate(node);
            }
            //return outer.calculate(inner.calculate(x)) * legendre.calculate(x) * getWeight(x);
        } else {
            for (int i = 1; i <= nodes; i++) {
                double node = Math.cos((2 * i - 1) * Math.PI / (2 * nodes));
                res += inner.calculate(node) * legendre.calculate(node);
            }
        }
        return res * Math.PI / (nodes);
    }

    private double calculate(double x) {
        if (outer != null) {
            return outer.calculate(inner.calculate(x)) * legendre.calculate(x);
        } else {
            return inner.calculate(x) * legendre.calculate(x);
        }
    }

    public double NewtonCotes (int beginning, int end) {
        double a = 0;
        double b = end / 2d;
        double diff = 0; // różnica między iteracjami - do warunku końcowego
        double res = 0;

        do {
            diff = CalcSimpson(a, b);
            res += diff;
            a = b;
            b += Math.abs(end - b) / 2;
        } while (Math.abs(diff) > eps);

        a = beginning / 2d;
        b = 0;
        do {
            diff = CalcSimpson(a, b);
            res += diff;
            b = a;
            a -= Math.abs(beginning - b) / 2;
        } while (Math.abs(diff) > eps);

        return res;
    }

    public double CalcSimpson (double a, double b) {
        double res = 0;
        int m = 1;       // ilosc podzialow
        double prev = 0; // wartość poprzedniej iteracji

        do {
            prev = res;
            m *= 2;
            double h = (b - a) / m; // dlugosc podpodziału

            res = calculate(a) + calculate(b);
            for(int i = 1; i <= m / 2 - 1; i++) {
                res += 2 * calculate(a + (2 * i) * h);
                res += 4 * calculate(a + (2 * i + 1) * h);
            }

            res *= h;
        } while(Math.abs(res - prev) > eps);

        return res / 3;
    }
}