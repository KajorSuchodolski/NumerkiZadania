
import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;

public class App {

    public static void main( String[] args ) throws Exception {

        try {
            MathFunction fun = new PolynomialFunction();
            FindingMethods find = new BisectionMethod();

            // Zrobiłem tak duży zakres i dzielę przez 10, aby między każdymi dwoma sąsiadującymi liczbami calkowitymi
            // na wykresie na osi x bylo wyznaczane 10 punktow dla dokladnosci wykresu
            // dzieki temu na odleglosci <-10; 10> mamy 200 punktow
            JavaPlot plot = new JavaPlot();
            double [][] tab = new double [200][2];
            for (int i = 0; i < tab.length; i++) {
                tab[i][0] = (double) (i / 10) - 10;
                tab[i][1] = fun.calculate((double) (i / 10) - 10);
            }

            // tu trzeba po prostu podstawic to, co wybierze uzytkownik
            // w miejscu pointZero[0][1] = fun.calculate(x) dalem wartosc tego miejsca zerowego, aby zobaczyc jak bardzo
            // niedokladny jest wynik, mysle ze to pomoze
            double [][] pointZero = new double [1][2];
            double x = find.iterationMethod(-5, 0, fun, 150);
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

            funcPlot.setTitle("Funkcja fajna");
            pointZeroPlot.setTitle("Miejsce zerowe");

            funcPlot.setPlotStyle(lineStyle);
            pointZeroPlot.setPlotStyle(pointStyle);

            plot.setKey(JavaPlot.Key.OUTSIDE);
            plot.set("zeroaxis", "");
            plot.set("xzeroaxis", "");
            plot.set("xlabel", "'x'");
            plot.set("ylabel", "'f(x)'");
            plot.set("title", "'Wykres fajnej funkcji'");

            plot.addPlot(funcPlot);
            plot.addPlot(pointZeroPlot);

            plot.newGraph();
            plot.plot();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
