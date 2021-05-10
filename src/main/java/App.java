import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        double eps = 0.0001;

        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Choose function");
            MathFunction function = TUIMethods.chooseFunction();

            Integrals integrals = new Integrals();

            System.out.println("Type number of nodes: ");
            int n = scanner.nextInt();

            System.out.println("Value for Gassian-Chebyshev: " + integrals.GaussChebyshev(function,  n));
            System.out.println("Value for Simpson: " + integrals.getSimpsonLimit(function, eps));

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
