
import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;

import java.io.File;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        try {
            Gaussian gaussian = new Gaussian("src\\main\\java\\input.txt");

            double [] result = gaussian.solve();
            for (int i = 0; i < result.length; i++) {
                System.out.println("X" + (i + 1) + " = " + result[i]);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
