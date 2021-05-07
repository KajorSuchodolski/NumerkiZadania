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

            MathFunction function = null;

            System.out.println("Choose function");
            function = TUIMethods.chooseFunction();

            Integrals integrals = new Integrals();

            System.out.println("Type number of nodes: ");
            int n = scanner.nextInt();
            System.out.println("Do you want to use Gaussian method? [y/n]");
            double res = (scanner.next().charAt(0) == 'y') ? integrals.GaussChebyshev(function,  n) : integrals.Simpson(function, -1, 1, eps);

            System.out.println("Integral value: " + res);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
