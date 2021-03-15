public class FalsiMethod implements FindingMethods {


    @Override
    public void accurateMethod( double a, double b, double epsilon) {

        
        
    }

    @Override
    public void iterationMethod( double a, double b, int iteration) {

        Functions functions = new Functions();
        if(functions.sinFunction(a)*functions.sinFunction(b) > 0) {
            System.out.println("X0 does not exist!");
            return;
        }

        double x0 = 0;

        for(int i = 0; i < iteration; i++) {

            x0 = (a * functions.sinFunction(b) - b * functions.sinFunction(a)) /
                    (functions.sinFunction(b) - functions.sinFunction(a));


            if(functions.sinFunction(x0) * functions.sinFunction(a) < 0) {

                b = x0;
            }
            else if(functions.sinFunction(x0) * functions.sinFunction(b) < 0)  {

                a = x0;
            }
        }

        System.out.println(x0);
        }


}

