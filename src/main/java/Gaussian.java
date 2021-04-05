public class Gaussian {
    private double coefficients[][];   // lewa strona
    private double values[];          // prawa strona     wyniki poszczegolnych rownan (to co jest za znakiem =)
    private int CoefNum;               // ilosc wspolczynnikow

    public Gaussian (double[][] coefficients, double[] values) throws Exception {
        if (coefficients.length == values.length) {
            this.coefficients = coefficients;
            this.values = values;
            this.CoefNum = coefficients[0].length;
        } else {
            throw new Exception("Błędne wymiary macierzy"); // ilosc wierszy musi byc rowna w macierzy lewej i prawej
        }
    }

    double[] solve () {
        return null;
    }

    void switchRows (int rowA, int rowB) {
        for (int i = 0; i < CoefNum; i++) {
            double tmp = coefficients[rowA][i];
            coefficients[rowA][i] = coefficients[rowB][i];
            coefficients[rowB][i] = tmp;

            tmp = values[rowA];
            values[rowA] = values[rowB];
            values[rowB] = tmp;
        }
    }

    void subtractRows (int rowA, int rowB, double multiplier) {
        for (int i = 0; i < CoefNum; i++) {
            coefficients[rowA][i] -= coefficients[rowB][i] * multiplier;
        }
        values[rowA] -= values[rowB] * multiplier;
    }

    void multiplyRow (int row, double multiplier) {
        for (int i = 0; i < CoefNum; i++) {
            coefficients[row][i] *= multiplier;
        }
        values[row] *= multiplier;
    }

    String getMatrix () {
        StringBuilder outp = new StringBuilder();
        for (int i = 0; i < coefficients.length; i++) {
            for (int j = 0; j < CoefNum; j++) {
                outp.append(coefficients[i][j]).append(" ");
            }
            outp.append("| ").append(values[i]).append("\n");
        }
        return outp.toString();
    }
}
