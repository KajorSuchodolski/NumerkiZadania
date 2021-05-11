public class Integrals {

    public double multiplyByWeight(MathFunction function, double x) {
        return function.calculate(x) * (1 / Math.sqrt(1 - x * x));
    }

    public double GaussChebyshev(MathFunction function, int nodes) {
        double res = 0;
        for (int i = 1; i <= nodes; i++) {
            double weight = Math.PI / nodes;
            double node = - Math.cos(((2 * i - 1) * Math.PI) / (2 * nodes));
            res += weight * function.calculate(node);
        }
        return res;
    }

    public double CalcSimpson (MathFunction function, double a, double b, double eps, boolean withWeight) {
        double res = 0;
        int m = 1;       // ilosc podzialow
        double prev = 0; // wartość poprzedniej iteracji
        do {
            prev = res;
            m *= 2;
            double h = (b - a) / m;

            if (withWeight) {
                res = multiplyByWeight(function, a) + multiplyByWeight(function, b);
                for (int i = 1; i <= m / 2 - 1; i++) {
                    res += 2 * multiplyByWeight(function, a + (2 * i) * h);
                    res += 4 * multiplyByWeight(function, a + (2 * i + 1) * h);
                }
            } else {
                res = function.calculate(a) + function.calculate(b);
                for (int i = 1; i <= m / 2 - 1; i++) {
                    res += 2 * function.calculate(a + (2 * i) * h);
                    res += 4 * function.calculate(a + (2 * i + 1) * h);
                }
            }
            res *= h;
        } while (Math.abs(res - prev) > eps);
        return res / 3;
    }

    public double NewtonCotes (MathFunction function, double eps, boolean withWeight) {
        double a = 0;
        double b = 0.5;
        double diff = 0; // różnica między iteracjami - do warunku końcowego
        double res = 0;
        do {
            diff = CalcSimpson(function, a, b, eps, withWeight);
            res += diff;
            a = b;
            b += (1 - b) / 2;
        } while (Math.abs(diff) > eps);

        a = -0.5;
        b = 0;
        do {
            diff = CalcSimpson(function, a, b, eps, withWeight);
            res += diff;
            b = a;
            a -= (1 - Math.abs(b)) / 2;
        } while (Math.abs(diff) > eps);

        return res;
    }
}
