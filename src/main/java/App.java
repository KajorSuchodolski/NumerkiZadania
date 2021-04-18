import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;

import javax.xml.crypto.Data;
import java.io.File;
import java.sql.SQLOutput;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {

        try {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Choose function: ");
            System.out.println("1. ");
            System.out.println("2. ");
            System.out.println("3. ");
            System.out.println("4. ");
            System.out.println("5. ");

            int fun = scanner.nextInt();

            MathFunction function = switch(fun) {
                case 1 -> new LinearFunction();
                case 2 -> new AbsoluteFunction();
                case 3 -> new PolynomialFunction();
                case 4 -> new TrygonometricFunction();
                case 5 -> new CompositionFunction();
                default -> throw new NullPointerException("Unexpected value of function!");
            };

            System.out.println("Type a: ");
            int a = scanner.nextInt();
            System.out.println("Type b: ");
            int b = scanner.nextInt();
            System.out.println("Type number of nodes: ");
            int n = scanner.nextInt();

            //
            JavaPlot plot = new JavaPlot("C:\\gnuplot\\bin\\gnuplot.exe");

            Newton newton = new Newton(a, b, n, function);

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
            nodesStyle.setLineWidth(2);

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
