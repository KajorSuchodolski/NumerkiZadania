
import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;

import java.io.File;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {

        try {

            JavaPlot plot = new JavaPlot("C:\\gnuplot\\bin\\gnuplot.exe");

            MathFunction function = new PolynomialFunction();

            double [][] y = new double[100][2];
            for(int i = 0; i < y.length; i++) {
                double tmpX = -5 + ((i * 1.0) / 10);
                y[i][0] = tmpX;
                y[i][1] = function.calculate(tmpX);
            }

            PlotStyle functionStyle = new PlotStyle();
            functionStyle.setStyle(Style.LINES);
            functionStyle.setLineWidth(1);

            DataSetPlot functionPlot = new DataSetPlot(y);

            functionPlot.setPlotStyle(functionStyle);

            plot.setKey(JavaPlot.Key.OUTSIDE);
            plot.set("xlabel", "'x");
            plot.set("ylabel", "'y");
            plot.set("title", "'Funkcja");

            plot.addPlot(functionPlot);

            plot.newGraph();
            plot.plot();
        }

        catch(Exception e) {
            System.out.println(e);
        }

    }
}
