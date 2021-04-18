public class Newton {

    private double [] nodes;

    public Newton(double a, double b, int n) {
        this.nodes = chebyshev(a, b, n);
    }

    public double [] chebyshev( double a, double b, int n) {
        double [] nodes = new double[n];

        for(int i = 0; i < n; i++) {
            nodes[i] = Math.cos(Math.PI*(2*i + 1) / (2*n + 1));
            nodes[i] = 0.5 * (nodes[i] * (b - a) + (a + b));
        }

        return nodes;
    }

    public double[] getNodes() {
        return nodes;
    }
}
