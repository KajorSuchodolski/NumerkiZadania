import MathFunctions.AbsoluteFunction;
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
//            System.out.println("Type a: ");
//            int a = scanner.nextInt();
//            System.out.println("Type b: ");
//            int b = scanner.nextInt();

            // TYMCZASOWO
            int a = -2;
            int b = 2;

 //         JavaPlot plot = new JavaPlot("C:\\gnuplot\\bin\\gnuplot.exe");
            JavaPlot plot = new JavaPlot();

            // public Legendre(int a, int b, int polynomialSize, MathFunction inner, MathFunction outer) {
            // outer moze byc nullem
            Legendre leg = new Legendre(a, b, 5, inner, outer);

            int range = Math.abs(b - a) * 100 + 1;
            double [][] y = new double[range][2];
            for(int i = 0; i < range; i++) {
                double tmpX = a + i * 0.01;
                y[i][0] = tmpX;
                if (outer == null) {
                    y[i][1] = inner.calculate(tmpX);
                } else {
                    y[i][1] = outer.calculate(inner.calculate(tmpX));
                }
            }

            double [][] approx = leg.getValues();

            PlotStyle functionStyle = new PlotStyle();
            functionStyle.setStyle(Style.LINES);
            functionStyle.setLineWidth(1);

            PlotStyle approxStyle = new PlotStyle();
            approxStyle.setStyle(Style.LINES);
            approxStyle.setLineWidth(1);

            DataSetPlot functionPlot = new DataSetPlot(y);
            DataSetPlot approxPlot = new DataSetPlot(approx);

            functionPlot.setPlotStyle(functionStyle);
            functionPlot.setTitle("Function");
            approxPlot.setPlotStyle(approxStyle);
            approxPlot.setTitle("Approximation");
            plot.setKey(JavaPlot.Key.OUTSIDE);
            plot.set("xlabel", "'x'");
            plot.set("ylabel", "'f(x)'");
            plot.set("title", "'Graph'");


            plot.addPlot(functionPlot);
            plot.addPlot(approxPlot);

            plot.newGraph();
            plot.plot();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
