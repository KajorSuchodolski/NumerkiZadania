import MathFunctions.LinearFunction;
import MathFunctions.MathFunction;
import MathFunctions.PolynomialFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Legendre {
    private double[][] approximationValues;
    private int a;
    private int b;
    private int n;

    private MathFunction inner;
    private MathFunction outer;

    // wspolczynniki dla kolejnych wielomianow legendre'a
    private double[][] legendreFactors;


    private double fallingPow(double x, double n) {
        double res = 1;
        for (int j = 0; j <= n - 1; j++) {
            res *= (x - j);
        }
        return res;
    }

    private double binomial(double x, double k) {
        if (k < 0) {
            return 0;
        } else {
            double factorial = 1;
            for (int i = 1; i <= k; i++) {
                factorial *= i;
            }
            return fallingPow(x, k) / factorial;
        }
    }


    public Legendre(int a, int b, int polynomialSize, MathFunction inner, MathFunction outer) {
        this.a = a;
        this.b = b;
        this.n = polynomialSize;
        this.inner = inner;
        this.outer = outer;
        this.legendreFactors = new double[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= i; k++) {
                // liczenie wspolczynnikow wielomianow legendre'a
                legendreFactors[i][k] = Math.pow(2, i) * binomial(i, k) * binomial((i + k -1)/2d, i);
            }
        }
    }

    public double getPolynomialValue(int n, double x) {
        double y = legendreFactors[n][this.n - n];
        for(int i = n; i >= 0; i--) {
            y = y * x + legendreFactors[n][i];
        }
        return y;
    }

    public double[][] getValues() {
        double[] ak = new double[n + 1];
        for (int k = 0; k <= n; k++) {
            // odwracanie kolejnosci wspolczynnikow legerendre'a aby byla zgodna z kolejnoscia funckji hornera
            double[] tmpArr = new double[k + 1];
            int index = 0;
            for (int i = k; i >= 0; i--) {
                tmpArr[index] = legendreFactors[k][i];
                index++;
            }

            Integral integral = new Integral(0.0001, inner, outer, new PolynomialFunction(tmpArr));
            ak[k] = (2 * k + 1) / 2d * integral.NewtonCotes(a, b);
        }

        // generowanie wartosci x i y wykresu
        int range = Math.abs(a - b) * 100 + 1;
        double[][] values = new double[range][2];
        for(int i = 0; i < range; i++) {
            double tmpX = a + i * 0.01;
            values[i][0] = tmpX;

            double y = 0;
            for (int k = 0; k <= n; k++) {
                y += ak[k] * getPolynomialValue(k, tmpX);
            }
            values[i][1] = y;
        }
        return values;
    }


}
