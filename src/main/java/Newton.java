public class Newton {

    private double [][] nodes;
    private MathFunction mathFunction;

    public Newton(double a, double b, int n, MathFunction mathFunction) {
        this.nodes = chebyshev(a, b, n);
        this.mathFunction = mathFunction;
    }

    public double [][] chebyshev( double a, double b, int n) {
        double [][] nodes = new double[n][2];

        for(int i = 0; i < n; i++) {
            nodes[i][0] = Math.cos(Math.PI*(2*i + 1) / (2*n + 1));
            nodes[i][0] = 0.5 * (nodes[i][0] * (b - a) + (a + b));
            nodes[i][1] = mathFunction.calculate(nodes[i][0]);
        }

        return nodes;
    }

    public double [] factors(int a, int b, int n) {

        return null;
    }



    public double[] getNodes() {
        return nodes;
    }
}
