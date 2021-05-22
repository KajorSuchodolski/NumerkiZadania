import MathFunctions.MathFunction;

public class Integral {

    private final double eps;
    private final MathFunction inner;
    private final MathFunction outer;

    public Integral(double eps, MathFunction inner, MathFunction outer) {
        this.eps = eps;
        this.inner = inner;
        this.outer = outer;
    }

    private double calculate(double x) {
        if (outer != null) {
            return outer.calculate(inner.calculate(x));
        } else {
            return inner.calculate(x);
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
            b += (end - b) / 2;
        } while (Math.abs(diff) > eps);

        a = beginning / 2d;
        b = 0;
        do {
            diff = CalcSimpson(a, b);
            res += diff;
            b = a;
            a -= (end - Math.abs(b)) / 2;
        } while (Math.abs(diff) > eps);

        return res;
    }

    private double CalcSimpson (double a, double b) {
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