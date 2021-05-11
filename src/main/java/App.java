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

//            System.out.println("Choose function");
//            MathFunction function = TUIMethods.chooseFunction();
//
            Integrals integrals = new Integrals();
//
//            System.out.println("Type number of nodes: ");
//            int n = scanner.nextInt();
//
//            System.out.println("Value for Gassian-Chebyshev: " + integrals.GaussChebyshev(function,  n));
//            System.out.println("Value for Simpson with weight: " + integrals.getSimpsonLimit(function, eps, true));
//            System.out.println("Value for Simpson without weight: " + integrals.getSimpsonLimit(function, eps, false));

            PolynomialFunction function = new PolynomialFunction(new double[]{-1, 1, 1, 1});
            int n = 5;

            System.out.println("Value for Gassian-Chebyshev: " + integrals.GaussChebyshev(function, n));
            System.out.println("Value for Simpson with weight: " + integrals.NewtonCotes(function, -1, 1, eps, true));
            System.out.println("Value for Simpson without weight: " + integrals.NewtonCotes(function, -1, 1, eps, false));

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
