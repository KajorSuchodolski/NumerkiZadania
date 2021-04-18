import java.util.ArrayList;
import java.util.List;

public class Newton {

    final private double a;
    final private double b;
    final private int n;
    final private double [][] nodes;
    final private MathFunction mathFunction;

    public Newton(double a, double b, int n, MathFunction mathFunction) {

        this.a = a;
        this.b = b;
        this.n = n;
        this.mathFunction = mathFunction;
        this.nodes = chebyshev();


    }

    public double [][] chebyshev() {
        double [][] nodes = new double[n][2];

        for(int i = 1; i <= n; i++) {

            //TUTAJ MOZNA INACZEJ
            nodes[i - 1][0] = Math.cos(Math.PI*(2*i - 1) / (2*n));
            nodes[i - 1][0] = 0.5 * ((nodes[i - 1][0] * (b - a)) + (a + b));
            nodes[i - 1][1] = mathFunction.calculate(nodes[i - 1][0]);
        }

        return nodes;
    }


    //te factorsy to tak naprawde wartosci funkcji potrzebne do newtona
    public double [] factors() {

        double [] factors = new double[n+1];

        for(int i=0; i < n-1; i++) {

            factors[i] = mathFunction.calculate(nodes[i][0]);
        }

        for(int i = 0; i < n -1; i++) {
            for(int j = n - 1; j > i; j--) {
                factors[j] = (factors[j] - factors[j - 1]) / (nodes[j][0] - nodes[j - i -1][0]);
            }
        }

        return factors;
    }

    public double [][] interpolation() {

        List<Double> iX = new ArrayList<Double>();
        List<Double> iY = new ArrayList<Double>();


        double [] factors = factors();

        double mul = 0;
        double sum = 0;

        for(double x = a; x < b + 0.1; x += 0.1) {

            iX.add(x);
            for(int i = 0; i <= n; i++) {
                if(factors[i] != 0) {
                    mul = factors[i];

                    for(int j = 0; j < i; j++) {
                        mul = mul * (x - nodes[j][0]);
                    }
                    sum += mul;
                }
            }
            iY.add(sum);
            sum = 0;
        }

        double [][] interpolation = new double[iX.size()][2];

        for(int i = 0; i < iX.size() ; i++) {
            interpolation[i][0] = iX.get(i);
            interpolation[i][1] = iY.get(i);

        }

        return interpolation;

    }

    public double[][] getNodes() {
        return nodes;
    }
}
