public class BisectionMethod implements FindingMethods {

    /*
    *
    * TUTAJ TRZEBA WPASC NA JAKIS SPANIALY POMYSL JAK WSTAWIC FUNCKJE
    * (COS ALA GENERYCZNOSC)
    *
    *
    */

    @Override
    public void accurateMethod(double a, double b, double epsilon) {


        double [] factors = {3, -3, 0, 0};

        Functions functions = new Functions();
        if(functions.horner(a, factors)*functions.horner(b, factors) > 0) {
            System.out.println("X0 does not exist!");
            return;
        }

        double x0 = 0;

        while(Math.abs(a - b) > epsilon) {

            x0 = (a+b)/2;


            if(functions.horner(x0, factors) * functions.horner(a, factors) < 0) {

                b = x0;
            }
            else if(functions.horner(x0, factors) * functions.horner(b, factors) < 0)  {

                a = x0;
            }
        }

        System.out.println(x0);
    }

    @Override
    public void iterationMethod(double a, double b, int iterations) {


        double [] factors = {3, -3, 0, 0};

        Functions functions = new Functions();
        if(functions.horner(a, factors)*functions.horner(b, factors) > 0) {
            System.out.println("X0 does not exist!");
            return;
        }

        double x0 = 0;

        for(int i = 0; i < iterations; i++) {

            x0 = (a+b)/2;

            if(functions.horner(x0, factors) * functions.horner(a, factors) < 0) {

                b = x0;
            }
            else if(functions.horner(x0, factors) * functions.horner(b, factors) < 0)  {

                a = x0;
            }

        }
        System.out.println(x0);
    }
}
