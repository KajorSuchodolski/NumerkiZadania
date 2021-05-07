public class Integrals {

    private double multiplyByWeight(MathFunction function, double x) {
        return function.calculate(x) / Math.sqrt(1 - x * x);
    }

    public double GaussChebyshev(MathFunction function, int nodes) {
        double res = 0;
        for (int i = 0; i <= nodes; i++) {
            double weight = Math.PI / nodes;
            double node = - Math.cos(((2 * i - 1) * Math.PI) / (2 * nodes));
            res += weight * function.calculate(node);
        }
        return res;
    }

    public double Simpson (MathFunction function, double a, double b, double eps) {
        double res = 0;
        int range = 1;
        double tmp;
        do {
            range *= 2;
            double len = (b - a) / range;
            tmp = res;
            res = multiplyByWeight(function, a) + multiplyByWeight(function, b);
            for (int i = 1; i < range / 2; i++) {
                res += 4 * multiplyByWeight(function, a + (2 * i - 1) * len);
                res += 2 * multiplyByWeight(function, a + (2 * i) * len);
            }
            res *= len / 3;
        } while (Math.abs(tmp - res) > eps);
        return res;
    }

    public double getSimpsonLimit (MathFunction function, double eps) {
        double a = 0;
        double b = 0.5;
        double tmp, res = 0;
        do {
            tmp = Simpson(function, a, b, eps);
            res += tmp;
            a = b;
            b += (1 - b) / 2;
        } while (Math.abs(tmp) > eps);

        a = -0.5;
        b = 0;
        do {
            tmp = Simpson(function, a, b, eps);
            res += tmp;
            b = a;
            a -= (1 - Math.abs(b)) / 2;
        } while (Math.abs(tmp) > eps);

        return res;
    }
}
