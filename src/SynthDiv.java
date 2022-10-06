import java.util.ArrayList;
import java.util.Scanner;

public class SynthDiv {

    public static void main(String[] args) throws Exception {
        
        Scanner in = new Scanner(System.in);
        Scanner inTwo = new Scanner(System.in);
        String input;
        int degree = 0;
        float k;

        // Prompt for degree of dividend
        System.out.println("What degree is the dividend?");
        while (degree <= 1) {
            degree = in.nextInt();
            if (degree <= 1) {
                System.out.println("Please enter an integer larger than 1.");
            }
        }
        // degree = 2;
        System.out.println("The polynomial has the form: " + Colors.ANSI_YELLOW + Polynomials.polyForm(degree) + Colors.ANSI_RESET);


        // Prompt for coefficients separated by commas
        // Store into String array
        // Parse Integers from String array and store into coefficientInts
        System.out.println("Input " + (degree + 1) + " coefficients separated by commas.");
        input = inTwo.nextLine();
        // input = "1,-1,-12";
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
        float constantTerm, leadingCoefficient;
        constantTerm = coefficientInts[coefficientInts.length - 1];
        leadingCoefficient = coefficientInts[0];
        System.out.println(Colors.ANSI_BLUE + "Constant term: " + Colors.ANSI_RESET + constantTerm
                            + Colors.ANSI_BLUE + "\nLeading coefficient: " + Colors.ANSI_RESET + leadingCoefficient);
        
        
        // Find possible values of p and q
        // Factors of the constant term
        for (float a = 1; a <= Math.abs(constantTerm); a++) {
            if (Math.abs(constantTerm) % a == 0) {
                p.add(a);
            }
        }

        // Factors of the leading coefficient
        for (float b = 1; b <= leadingCoefficient; b++) {
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

        // Print out possible zeros
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
        // Store working zeros in new array and print them out
        
        System.out.println("Finding rational zeros...");
        int z = 0;
        ArrayList<Float> rationalZeros = new ArrayList<Float>(0);
        for (int i = 0; i < possibleRationalZeros.size(); i++) {
            k = possibleRationalZeros.get(i);
            if (SynthDiv.synthDiv(degree, k, coefficientInts).remainder == 0) {
                rationalZeros.add(possibleRationalZeros.get(i));
                System.out.print(Colors.ANSI_GREEN + rationalZeros.get(z) + " ");
                z++;
            }
        }
        System.out.println(Colors.ANSI_RESET);
        if (!(rationalZeros.size() >= 1)) {
            System.out.println("There are no rational zeros.");
        }

        in.close();
        inTwo.close();

    }

    // Performs synthetic division
    // Returns value of remainder
    public static SynthDivInstance synthDiv(int degree, float k, int[] coefficients) {
        SynthDivInstance result = new SynthDivInstance(degree);
        
        for (int i = 0; i <= degree; i++) {
            result.remainder += coefficients[i];
            result.newCoefficients[i] = result.remainder;
            result.remainder *= k;
        }
        
        return result;
    }


}
