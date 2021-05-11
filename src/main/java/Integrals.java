public class Integrals {

    public double multiplyByWeight(MathFunction function, double x) {
        return function.calculate(x) * (1 / Math.sqrt(1 - x * x));
    }

    public double GaussChebyshev(MathFunction function, int nodes) {
        double res = 0;
        for (int i = 1; i <= nodes; i++) {
            double node = Math.cos((2 * i - 1) * Math.PI / (2 * nodes));
            res += function.calculate(node);
        }
        return res * Math.PI / (nodes);
    }

    public double CalcSimpson (MathFunction function, double a, double b, double eps, boolean withWeight) {
        double res = 0;
        int m = 1;       // ilosc podzialow
        double prev = 0; // wartość poprzedniej iteracji
        do {
            prev = res;
            m *= 2;
            double h = (b - a) / m; // dlugosc podpodziału

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

    public double NewtonCotes (MathFunction function, int poczatek, int koniec, double eps, boolean withWeight) {
        double a = 0;
        double b = koniec / 2d;
        double diff = 0; // różnica między iteracjami - do warunku końcowego
        double res = 0;
        do {
            diff = CalcSimpson(function, a, b, eps, withWeight);
            res += diff;
            a = b;
            b += (koniec - b) / 2;
        } while (Math.abs(diff) > eps);

        a = poczatek / 2d;
        b = 0;
        do {
            diff = CalcSimpson(function, a, b, eps, withWeight);
            res += diff;
            b = a;
            a -= (koniec - Math.abs(b)) / 2;
        } while (Math.abs(diff) > eps);

        return res;
    }
}
