import java.util.Scanner;

public class TUIMethods {
    static MathFunction chooseFunction() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Linear:  ");
        System.out.println("2. Absolute: ");
        System.out.println("3. Polynomial: ");
        System.out.println("4. Trygonometric: ");

        int fun = scanner.nextInt();

        MathFunction function;
        switch(fun) {
            case 1 -> {
                System.out.println("Linear function y = ax + b");
                System.out.println("Insert a");
                double a = scanner.nextDouble();
                System.out.println("Insert b");
                double b = scanner.nextDouble();
                function = new LinearFunction(a, b);
            }
            case 2 -> function = new AbsoluteFunction();
            case 3 -> {
                System.out.println("Polynomial function with many factors");
                System.out.println("How many factors do you need?");
                int noOfFactors = scanner.nextInt();
                double[] factors = new double [noOfFactors];
                for(int i = 0; i < noOfFactors; i++) {
                    System.out.println("Insert factor number " + (i + 1));
                    factors[i] = scanner.nextDouble();
                }
                function = new PolynomialFunction(factors);
            }
            case 4 -> {
                System.out.println("Cosinus function a * cos(bx + c)");
                System.out.println("Insert a");
                double a = scanner.nextDouble();
                System.out.println("Insert b");
                double b = scanner.nextDouble();
                System.out.println("Insert c");
                double c = scanner.nextDouble();
                function = new TrygonometricFunction(a, b, c);
            }
            default -> throw new NullPointerException("Unexpected value of function!");
        };
        return function;
    }
}