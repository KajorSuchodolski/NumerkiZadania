
import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;

public class App {

    public static void main( String[] args ) throws Exception {

        try {
            Functions functions = new Functions();
            BisectionMethod bisectionMethod = new BisectionMethod();
            FalsiMethod falsiMethod = new FalsiMethod();

            JavaPlot plot = new JavaPlot();
            double [][] tab = new double [200][2];
            for (int i = 0; i < tab.length; i++) {
                tab[i][0] = (double) i / 20;
                tab[i][1] = functions.sinFunction((double) i / 10);
            }
            double [][] pointZero = new double [1][2];
            pointZero[0][0] = 3;
            pointZero[0][1] = 0;

            double [][] xAxis = new double [2][2];
            xAxis[0][0] = 0;
            xAxis[0][1] = 0;
            xAxis[1][0] = tab.length / 20;
            xAxis[1][1] = 0;

            PlotStyle lineStyle = new PlotStyle();
            lineStyle.setStyle(Style.LINES);
            lineStyle.setLineWidth(1);

            PlotStyle axisStyle = new PlotStyle();
            axisStyle.setStyle(Style.LINES);
            axisStyle.setLineWidth(1);

            PlotStyle pointStyle = new PlotStyle();
            pointStyle.setStyle(Style.POINTS);
            pointStyle.setPointSize(2);
            pointStyle.setLineWidth(1);

            DataSetPlot funcPlot = new DataSetPlot(tab);
            DataSetPlot pointZeroPlot = new DataSetPlot(pointZero);
            DataSetPlot xAxisPlot = new DataSetPlot(xAxis);
            funcPlot.setTitle("Funkcja fajna");
            xAxisPlot.setTitle("Oś x");
            pointZeroPlot.setTitle("Miejsce zerowe");

            funcPlot.setPlotStyle(lineStyle);
            xAxisPlot.setPlotStyle(lineStyle);
            pointZeroPlot.setPlotStyle(pointStyle);

            plot.set("xlabel", "'x'");
            plot.set("ylabel", "'f(x)'");
            plot.set("title", "'Wykres fajnej funkcji'");

            plot.addPlot(funcPlot);
            plot.addPlot(xAxisPlot);
            plot.addPlot(pointZeroPlot);

            plot.newGraph();
            plot.plot();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
