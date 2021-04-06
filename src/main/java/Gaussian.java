import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Gaussian {
    private double coefficients[][];   // lewa strona
    private double values[];          // prawa strona     wyniki poszczegolnych rownan (to co jest za znakiem =)
    private int coefNum;               // ilosc wspolczynnikow

    public Gaussian (String inputfile) throws Exception {
        StringBuilder builder = new StringBuilder();

        // Wczytanie z pliku do zmiennej tekstowej
        try (Stream<String> stream = Files.lines(Paths.get(inputfile), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] rows = builder.toString().split("\n");

        this.coefNum = rows[0].split(", ").length;
        this.coefficients = new double [rows.length][this.coefNum];
        this.values = new double [rows.length];

        // Konwersja na tablicę typu double
        for (int i = 0; i < rows.length; i++) {
            String[] splitRow = rows[i].split("\\| ");

            this.values[i] = Double.parseDouble(splitRow[1]);

            String[] coeffStr = splitRow[0].split(", ");
            for (int c = 0; c < coeffStr.length; c++) {
                this.coefficients[i][c] = Double.parseDouble(coeffStr[c]);
            }
        }
        System.out.println(this.getMatrix());

        if (coefficients.length != values.length || coefNum != coefficients.length) {
            throw new Exception("Błędne wymiary macierzy"); // ilosc wierszy musi byc rowna w macierzy lewej i prawej
        }
    }

    double[] solve () {
        return null;
    }

    void switchRows (int rowA, int rowB) {
        for (int i = 0; i < coefNum; i++) {
            double tmp = coefficients[rowA][i];
            coefficients[rowA][i] = coefficients[rowB][i];
            coefficients[rowB][i] = tmp;

            tmp = values[rowA];
            values[rowA] = values[rowB];
            values[rowB] = tmp;
        }
    }

    void addRows (int rowA, int rowB, double multiplier) {
        for (int i = 0; i < coefNum; i++) {
            coefficients[rowA][i] += coefficients[rowB][i] * multiplier;
        }
        values[rowA] += values[rowB] * multiplier;
    }

    void multiplyRow (int row, double multiplier) {
        for (int i = 0; i < coefNum; i++) {
            coefficients[row][i] *= multiplier;
        }
        values[row] *= multiplier;
    }

    String getMatrix () {
        StringBuilder outp = new StringBuilder();
        for (int i = 0; i < coefficients.length; i++) {
            for (int j = 0; j < coefNum; j++) {
                outp.append(coefficients[i][j]).append(" ");
            }
            outp.append("| ").append(values[i]).append("\n");
        }
        return outp.toString();
    }
}
