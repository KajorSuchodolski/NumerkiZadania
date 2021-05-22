import MathFunctions.MathFunction;
import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Do you want to use complex function? If yes, insert 1");
            int complex = scanner.nextInt();

            MathFunction inner = null;
            MathFunction outer = null;

            if (complex == 1) {
                System.out.println("Choose inner function");
                inner = TUIMethods.chooseFunction();
                System.out.println("Choose outer function");
                outer = TUIMethods.chooseFunction();
            } else {
                System.out.println("Choose function");
                inner = TUIMethods.chooseFunction();
            }

            System.out.println("Defining range <a;b>");
            System.out.println("Type a: ");
            int a = scanner.nextInt();
            System.out.println("Type b: ");
            int b = scanner.nextInt();
            System.out.println("Type number of nodes: ");
            int n = scanner.nextInt();

 //         JavaPlot plot = new JavaPlot("C:\\gnuplot\\bin\\gnuplot.exe");
            JavaPlot plot = new JavaPlot();

//            Newton newton = new Newton(a, b, n, inner, outer);
//
//            int range = Math.abs(b - a) * 100 + 1;
//            double [][] y = new double[range][2];
//            for(int i = 0; i < range; i++) {
//                double tmpX = a + i * 0.01;
//                y[i][0] = tmpX;
//                if (outer == null) {
//                    y[i][1] = inner.calculate(tmpX);
//                } else {
//                    y[i][1] = outer.calculate(inner.calculate(tmpX));
//                }
//            }
//
//            double [][] interpolation = newton.interpolation();
//
//            PlotStyle functionStyle = new PlotStyle();
//            functionStyle.setStyle(Style.LINES);
//            functionStyle.setLineWidth(1);
//
//            PlotStyle interStyle = new PlotStyle();
//            interStyle.setStyle(Style.LINES);
//            interStyle.setLineWidth(1);
//
//            PlotStyle nodesStyle = new PlotStyle();
//            nodesStyle.setStyle(Style.POINTS);
//            nodesStyle.setLineWidth(2);
//
//            DataSetPlot functionPlot = new DataSetPlot(y);
//            DataSetPlot interPlot = new DataSetPlot(interpolation);
//            DataSetPlot nodePlot = new DataSetPlot(newton.getNodes());
//
//            functionPlot.setPlotStyle(functionStyle);
//            functionPlot.setTitle("Function");
//            interPlot.setPlotStyle(interStyle);
//            interPlot.setTitle("Interpolation");
//            nodePlot.setPlotStyle(nodesStyle);
//            nodePlot.setTitle("Chebyshev nodes");
//
//            plot.setKey(JavaPlot.Key.OUTSIDE);
//            plot.set("xlabel", "'x'");
//            plot.set("ylabel", "'f(x)'");
//            plot.set("title", "'Graph'");
//
//            for(int i = 0; i < newton.getNodes().length; i++) {
//                System.out.println("x" + (i+1) + ": " + newton.getNodes()[i][0] + " y" + (i+1) + ": " + newton.getNodes()[i][1]);
//            }
//
//            plot.addPlot(functionPlot);
//            plot.addPlot(interPlot);
//            plot.addPlot(nodePlot);
//            plot.newGraph();
//            plot.plot();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
