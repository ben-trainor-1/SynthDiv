import java.util.ArrayList;
import java.util.Scanner;

public class SynthDiv {

    public static void main(String[] args) throws Exception {
        
        Scanner in = new Scanner(System.in);
        Scanner inTwo = new Scanner(System.in);
        String input;
        int degree;
        float k;

        // Prompt for degree of dividend
        System.out.println("What degree is the dividend?");
        degree = in.nextInt();
        // degree = 2;
        System.out.println("\nThe polynomial has the form: " + Polynomials.polyForm(degree));


        // Prompt for coefficients separated by commas
        // Store into String array
        // Parse Integers from String array and store into coefficientInts
        System.out.println("Input " + (degree + 1) + " coefficients separated by commas.");
        input = inTwo.nextLine();
        // input = "2,6,4";
        String coefficientStrings[] = input.split(",");
        int coefficientInts[] = new int[coefficientStrings.length];
        for (int i = 0; i < coefficientStrings.length; i++) {
            coefficientInts[i] = Integer.parseInt(coefficientStrings[i]);
        }
        System.out.println("\nDividend: " + Polynomials.polyCoefficients(degree, coefficientInts));


        // Calculate possible rational zeros and store them in an array of integers
        ArrayList<Float> possibleRationalZeros = new ArrayList<Float>(0);
        ArrayList<Float> p = new ArrayList<Float>(0);
        ArrayList<Float> q = new ArrayList<Float>(0);
        int constantTerm, leadingCoefficient;
        constantTerm = coefficientInts[coefficientInts.length - 1];
        leadingCoefficient = coefficientInts[0];
        
        // Find possible values of p and q

            // Factors of the constant term
            for (float a = 1; a <= constantTerm; a++) {
                System.out.println("Seeing if " + a + " is a factor of " + constantTerm);
                if (constantTerm % a == 0) {
                    p.add(a);
                }
            }

            // Factors of the leading coefficient
            for (float b = 1; b <= leadingCoefficient; b++) {
                System.out.println("Seeing if " + b + " is a factor of " + leadingCoefficient);
                if (leadingCoefficient % b == 0) {
                    q.add(b);
                }
            }

            System.out.println("\nNumber of possible factors: " + (p.size() * q.size() * 2));

        // Store possible combinations of p and q inside possibleRationalZeros
        for (int i = 0; i < p.size(); i++) {
            for (int j = 0; j < q.size(); j++) {
                // Prevent duplicate zeros
                if (!possibleRationalZeros.contains(p.get(i) / q.get(j))) {
                    possibleRationalZeros.add(p.get(i) / q.get(j));
                    possibleRationalZeros.add(-(p.get(i) / q.get(j)));
                }
            }
        }
        System.out.println("\nPossible rational zeros of polynomial: ");
        System.out.print(Colors.ANSI_PURPLE);
        for (int i = 0; i < possibleRationalZeros.size(); i++) {
            if (i < possibleRationalZeros.size() - 1) {
                System.out.print(possibleRationalZeros.get(i) + ", ");
            }
            else {
                System.out.println(possibleRationalZeros.get(i) + Colors.ANSI_RESET);
            }
        }


        // Division
        for (int i = 0; i < possibleRationalZeros.size(); i++) {
            k = possibleRationalZeros.get(i);
            System.out.println("\nTrying " + k + "...");
            if (SynthDiv.synthDiv(degree, k, coefficientInts) == 0) {
                System.out.print(Colors.ANSI_GREEN + k + " is a factor of " + Polynomials.polyCoefficients(degree, coefficientInts) + Colors.ANSI_RESET);
            }
            else {
                System.out.print(Colors.ANSI_RED + k + " is not a factor of " + Polynomials.polyCoefficients(degree, coefficientInts) + Colors.ANSI_RESET);
            }
        }

        in.close();
        inTwo.close();

    }

    // Performs synthetic division
    // Returns value of remainder
    public static int synthDiv(int degree, float k, int[] coefficients) {
        int r = 0;
        
        for (int i = 0; i <= degree; i++) {
            r += coefficients[i];
            r *= k;
        }
        
        return r;
    }


}
