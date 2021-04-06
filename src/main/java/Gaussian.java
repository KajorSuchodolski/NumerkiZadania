import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Gaussian {
    private double matrix[][];   // lewa strona
    private int width;           // ilosc wspolczynnikow
    private double epsilon = 1e-8;

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

        if ((width - 1) != matrix.length) {
            throw new Exception("Błędne wymiary macierzy"); // ilosc wierszy musi byc rowna w macierzy lewej i prawej
        }
    }

    private void partialChoice(int x) {
        for (int y = x + 1; y < width -1; y++) {
            if (Math.abs(matrix[y][x]) > epsilon) {
                for (int i = 0; i < width; i++) {
                    double tmp = matrix[y][i];
                    matrix[y][i] = matrix[x][i];
                    matrix[x][i] = tmp;
                }
                return;
            }
        }
    }

    public double[] solve () throws Exception {
        double[] output = new double [width - 1];
        for (int x = 0; x < width - 2; x++) {
            if( Math.abs(matrix[x][x]) < epsilon) {
                // Czesciowy wybor elementu podstawowego
                partialChoice(x);
            }
            for (int y = x + 1; y < width - 1; y ++) {
                double multiplier = -1 * (matrix[y][x] / matrix[x][x]);
                for (int z = x; z < width; z++) {
                    matrix[y][z] += multiplier * matrix[x][z];
                }
            }
        }

        double s = 0;
        for (int x = width - 2; x >= 0; x--) {
            if(Math.abs(matrix[x][x]) < epsilon) {
                if (Math.abs(matrix[x][x+1]) < epsilon) {
                    throw new Exception("Układ nieoznaczony");
                } else {
                    throw new Exception("Układ sprzeczny");
                }
            }
            s = matrix[x][width - 1];

            for(int y = width - 2; y >= x + 1; y-- ) {
                s -= matrix[x][y] * output[y];
            }
            output[x] = s / matrix[x][x];
        }
        return output;
    }

    public String getMatrix () {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            int j;
            for (j = 0; j < width - 1; j++) {
                output.append(matrix[i][j]).append(" ");
            }
            output.append("| ").append(matrix[i][j]).append("\n");
        }
        return output.toString();
    }
}
