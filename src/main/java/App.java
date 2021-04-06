
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

            double [] dupa = gaussian.solve();

            System.out.println(dupa[0]);
            System.out.println(dupa[1]);
            System.out.println(dupa[2]);

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
