
import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;

import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        MathFunction fun;
        FindingMethods find;
        String [] functions = {"2^x - 3", "3 sin(x)", "tan(x/4)", "x^3 - 2x^2 - x + 3", "2^(cos(x)) + sin(4x-2)"};

        System.out.println("Choose function: (1-5)\n");
        for (int i = 0; i < functions.length; i++) {
            System.out.println((i + 1) + ". " + functions[i]);
        }
        System.out.println("\nChoose: ");
        Scanner scanner = new Scanner(System.in);

        int choose = scanner.nextInt();
        String title = "'Wykres funkcji " + functions[choose - 1] + "'";

        fun = switch (choose) {
            case 1 -> new ExpFunction();
            case 2 -> new SinusFunction();
            case 3 -> new TangensFunction();
            case 4 -> new PolynomialFunction();
            case 5 -> new CompositionFunction();
            default -> throw new IllegalStateException("Unexpected value!");
        };

        System.out.println("Choose method: ");
        System.out.println("1. Bisection: ");
        System.out.println("2. Falsi: ");

        choose = scanner.nextInt();

        find = methodChoosing(choose);

        System.out.println("Choose how x0 will be found: ");
        System.out.println("1. By epsilon: ");
        System.out.println("2. By iteration: ");

        choose = scanner.nextInt();

        boolean isIteration = false;

        double epsilon = 0.1;
        int iteration = 0;

        if (choose == 1) {
            System.out.println("Type epsilon: ");
            epsilon = scanner.nextDouble();
        } else {
            isIteration = true;
            System.out.println("Type number of iteration: ");
            iteration = scanner.nextInt();
        }

        System.out.println("Type a: ");
        double a = scanner.nextDouble();
        System.out.println("Type b: ");
        double b = scanner.nextDouble();

        try {
            //JavaPlot plot = new JavaPlot("C:\\gnuplot\\bin\\gnuplot.exe");
            JavaPlot plot = new JavaPlot();

            double x;
            double[][] pointZero = new double[1][2];

            if(isIteration) {
                x = find.iterationMethod(a, b, fun, iteration);
            } else {
                x = find.accurateMethod(a, b, fun, epsilon);
            }

            pointZero[0][0] = x;
            pointZero[0][1] = fun.calculate(x);

            double[][] tab = new double[100][2];
            for (int i = 0; i < tab.length; i++) {
                double tmpX = x - 5 + ((i * 1.0) / 10);
                tab[i][0] = tmpX;
                tab[i][1] = fun.calculate(tmpX);
            }

            // styl funkcji
            PlotStyle lineStyle = new PlotStyle();
            lineStyle.setStyle(Style.LINES);
            lineStyle.setLineWidth(1);

            // styl punktu wskazujacego obliczone miejsce zerowe
            PlotStyle pointStyle = new PlotStyle();
            pointStyle.setStyle(Style.POINTS);
            pointStyle.setPointSize(2);
            pointStyle.setLineWidth(1);

            DataSetPlot funcPlot = new DataSetPlot(tab);
            DataSetPlot pointZeroPlot = new DataSetPlot(pointZero);

            funcPlot.setTitle("Funkcja");
            pointZeroPlot.setTitle("Miejsce zerowe");

            funcPlot.setPlotStyle(lineStyle);
            pointZeroPlot.setPlotStyle(pointStyle);

            plot.setKey(JavaPlot.Key.OUTSIDE);
            plot.set("zeroaxis", "");
            plot.set("xzeroaxis", "");
            plot.set("xlabel", "'x'");
            plot.set("ylabel", "'f(x)'");
            plot.set("title", title);

            plot.addPlot(funcPlot);
            plot.addPlot(pointZeroPlot);

            plot.newGraph();
            plot.plot();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static FindingMethods methodChoosing(int i) {
        return switch(i) {
            case 1 -> new BisectionMethod();
            case 2 -> new FalsiMethod();
            default -> null;
        };
    }
}
