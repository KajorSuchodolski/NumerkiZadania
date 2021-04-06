import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Gaussian {
    private double matrix[][];   // lewa strona
    private int width;               // ilosc wspolczynnikow
    private double eps = 1e-12;

    public Gaussian (String inputfile) throws Exception {
        StringBuilder builder = new StringBuilder();

        // Wczytanie z pliku do zmiennej tekstowej
        try (Stream<String> stream = Files.lines(Paths.get(inputfile), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] rows = builder.toString().split("\n");

        this.width = rows[0].split(", ").length;
        this.matrix = new double [rows.length][this.width];

//      Konwersja na tablicę typu double
        for (int i = 0; i < rows.length; i++) {
            String[] splitRow = rows[i].split(", ");

            for (int num = 0; num < splitRow.length; num++) {
                this.matrix[i][num] = Double.parseDouble(splitRow[num]);
            }
        }
        System.out.println(this.getMatrix());

        if ((width - 1) != matrix.length) {
            throw new Exception("Błędne wymiary macierzy"); // ilosc wierszy musi byc rowna w macierzy lewej i prawej
        }
    }

    public double[] solve () throws Exception {

        double[] output = new double [width - 1];


        for (int row = 0; row < width - 2; row++) {
            for (int col = row + 1; col < width - 1; col ++) {
                if( Math.abs(matrix[row][row]) < eps ) {
                    throw new Exception("Gauss nie zadziała");
                }
                double multiplier = -matrix[col][row] / matrix[row][row];
                for (int k = row; k <= width - 1; k++) {
                    matrix[col][k] += multiplier * matrix[row][k];
                    System.out.println(getMatrix());

                }
            }
        }

        double s;

        for (int row = width - 2; row >= 0; row--) {

            s = matrix[row][width - 1];

            for(int col = width - 2; col >= row + 1; col-- ) {
                s -= matrix[row][col] * output[col];
            }
            if(Math.abs(matrix[row][row]) < eps) {
                throw new Exception("Gauss nie zadziala");
            }
            output[row] = s / matrix[row][row];
        }
        return output;
    }

    public void switchRows (int rowA, int rowB) {
        for (int i = 0; i < width + 1; i++) {
            double tmp = matrix[rowA][i];
            matrix[rowA][i] = matrix[rowB][i];
            matrix[rowB][i] = tmp;
        }
    }

    public void addRows (int rowA, int rowB, double multiplier) {
        for (int i = 0; i < width + 1; i++) {
            matrix[rowA][i] += matrix[rowB][i] * multiplier;
        }
    }

    public void multiplyRow (int row, double multiplier) {
        for (int i = 0; i < width + 1; i++) {
            matrix[row][i] *= multiplier;
        }
    }

    public String getMatrix () {
        StringBuilder outp = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            int j;
            for (j = 0; j < width - 1; j++) {
                outp.append(matrix[i][j]).append(" ");
            }
            outp.append("| ").append(matrix[i][j]).append("\n");
        }
        return outp.toString();
    }
}
