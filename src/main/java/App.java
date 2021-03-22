
import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;

import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {


        MathFunction fun;
        FindingMethods find;

        System.out.println("Choose function: (1-4)\n");
        System.out.println("1. 2^x - 3");
        System.out.println("2. 3*sin(x)");
        System.out.println("3. 2*tan(x) + 7");
        System.out.println("4. x^3 - 2x^2 - x + 30\n");
        System.out.println("Choose: ");

        Scanner scanner = new Scanner(System.in);

        int choose = scanner.nextInt();

        switch(choose) {
            case 1:
                fun = new ExpFunction();
                break;
            case 2:
                fun = new SinusFunction();
                break;
            case 3:
                fun = new TangensFunction();
                break;
            case 4:
                fun = new PolynomialFunction();
                break;
            default:
                throw new IllegalStateException("Unexpected value!");
        }

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

        if(choose == 1) {
            System.out.println("Type epsilon: ");
            epsilon = scanner.nextDouble();
        }

        else {
            isIteration = true;
            System.out.println("Type number of iteration: ");
            iteration = scanner.nextInt();

        }

        System.out.println("Type a: ");
        double a = scanner.nextDouble();
        System.out.println("Type b: ");
        double b = scanner.nextDouble();


        try {

            // Zrobiłem tak duży zakres i dzielę przez 10, aby między każdymi dwoma sąsiadującymi liczbami calkowitymi
            // na wykresie na osi x bylo wyznaczane 10 punktow dla dokladnosci wykresu
            // dzieki temu na odleglosci <-10; 10> mamy 200 punktow
            JavaPlot plot = new JavaPlot("C:\\gnuplot\\bin\\gnuplot.exe");
            double[][] tab = new double[200][2];
            for (int i = 0; i < tab.length; i++) {
                tab[i][0] = (double) (i / 10) - 10;
                tab[i][1] = fun.calculate((double) (i / 10) - 10);
            }

            // tu trzeba po prostu podstawic to, co wybierze uzytkownik
            // w miejscu pointZero[0][1] = fun.calculate(x) dalem wartosc tego miejsca zerowego, aby zobaczyc jak bardzo
            // niedokladny jest wynik, mysle ze to pomoze
            double x;
            double[][] pointZero = new double[1][2];

            if(isIteration) {
                x = find.iterationMethod(a, b, fun, iteration);
            } else {
                x = find.accurateMethod(a, b, fun, epsilon);
            }

            pointZero[0][0] = x;
            pointZero[0][1] = fun.calculate(x);

            // styl funkcji
            PlotStyle lineStyle = new PlotStyle();
            lineStyle.setStyle(Style.LINES);
            lineStyle.setLineWidth(1);

            // styl punktu wskazujacego obliczone miejsce zerowe
            PlotStyle pointStyle = new PlotStyle();
            pointStyle.setStyle(Style.POINTS);
            pointStyle.setPointSize(2);
            pointStyle.setLineWidth(1);

            // To nizej jest juz w zasadzie tez samym stylem i rysowaniem na wykresie w zaleznosci od tego
            // co jest wyzej
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
            plot.set("title", " 'Wykres funkcji'");

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
