
import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;

import java.io.File;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        double[][] tab = {
                {2, 3},
                {4, 5},
                {6, 7}
        };
        //System.out.println(tab.length);
        try {
            Gaussian gaussian = new Gaussian("src\\main\\java\\input.txt");
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
