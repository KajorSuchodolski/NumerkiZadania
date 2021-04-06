
// Program wykonany przez:
// Michał Dzieciuchowicz    229874
// Radosław Zyzik           230049

// Zadanie 2 - Rozwiązywanie układów równań metodą eliminacji Gaussa

public class App {

    public static void main(String[] args) throws Exception {
        try {
            Gaussian gaussian = new Gaussian("src\\main\\java\\input.txt");

            System.out.println("Obliczanie rozwiązań układu równań o poniższej formie macierzowej:");
            System.out.println(gaussian.getMatrix());

            double [] result = gaussian.solve();
            for (int i = 0; i < result.length; i++) {
                System.out.println("X" + (i + 1) + " = " + result[i]);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
