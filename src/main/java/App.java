import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;

import javax.xml.crypto.Data;
import java.io.File;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {

        try {

            JavaPlot plot = new JavaPlot("C:\\gnuplot\\bin\\gnuplot.exe");

            MathFunction function = new AbsoluteFunction();

            Newton newton = new Newton(-10, 10, 50, new AbsoluteFunction());

            double [][] y = new double[100][2];
            for(int i = 0; i < y.length; i++) {
                double tmpX = -5 + ((i * 1.0) / 10);
                y[i][0] = tmpX;
                y[i][1] = function.calculate(tmpX);
            }

            double [][] interpolation = newton.interpolation();


            //
            PlotStyle functionStyle = new PlotStyle();
            functionStyle.setStyle(Style.LINES);
            functionStyle.setLineWidth(1);

            PlotStyle interStyle = new PlotStyle();
            interStyle.setStyle(Style.LINES);
            interStyle.setLineWidth(1);

            PlotStyle nodesStyle = new PlotStyle();
            nodesStyle.setStyle(Style.POINTS);
            nodesStyle.setLineWidth(1);

            //

            DataSetPlot functionPlot = new DataSetPlot(y);
            DataSetPlot interPlot = new DataSetPlot(interpolation);
            DataSetPlot nodePlot = new DataSetPlot(newton.getNodes());


            //
            functionPlot.setPlotStyle(functionStyle);
            interPlot.setPlotStyle(interStyle);
            nodePlot.setPlotStyle(nodesStyle);

            //DO POPRAWY TO
            plot.setKey(JavaPlot.Key.OUTSIDE);
            plot.set("xlabel", "'x'");
            plot.set("ylabel", "'f(x)'");
            plot.set("title", "'Funkcja'");


            for(int i = 0; i < newton.getNodes().length; i++) {
                System.out.println(newton.getNodes()[i][0] + " " + newton.getNodes()[i][1]);
            }

            //
            plot.addPlot(functionPlot);
            plot.addPlot(interPlot);
            plot.addPlot(nodePlot);

            //
            plot.newGraph();
            plot.plot();

        }

        catch(Exception e) {
            System.out.println(e);
        }

    }
}
