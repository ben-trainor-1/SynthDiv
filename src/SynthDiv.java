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


        // Division
        // Store working zeros in new array and print them out
        System.out.println("Finding rational zeros..."); // Counter variable for rational zeros list

        // Calculate possible rational zeros by passing the integer coefficients to calcRationalZeros()
        ArrayList<Float> possibleRationalZeros = SynthDiv.calcRationalZeros(coefficientInts);

        // Create new ArrayList<Float> to hold the results
        ArrayList<Float> results = new ArrayList<Float>(0);

        // Try each possible zero with synthetic division
        for (int i = 0; i < possibleRationalZeros.size(); i++) {
            // Try next value of k
            k = possibleRationalZeros.get(i);
            // Pass the degree, k, and coefficients to synthDiv()
            if (SynthDiv.synthDiv(degree, k, coefficientInts) == 0) {
                results.add(possibleRationalZeros.get(i));
            }
        }
        System.out.println(Colors.ANSI_RESET);
        if (!(results.size() >= 1)) {
            System.out.println("There are no rational zeros.");
        }
        else {
            System.out.println("The rational zeros are:" + Colors.ANSI_GREEN);
            for (int i = 0; i < results.size(); i++) {
                if (i < results.size() - 1) {
                    System.out.print(results.get(i) + ", ");
                }
                else {
                    System.out.println(results.get(i) + Colors.ANSI_RESET);
                }
            }
        }
        

        in.close();
        inTwo.close();

    }


    // Calculate possible rational zeros
    public static ArrayList<Float> calcRationalZeros(int[] coefficients) {

        // Calculate possible rational zeros and store them in an array of integers
        ArrayList<Float> possibleRationalZeros = new ArrayList<Float>(0);
        ArrayList<Float> p = new ArrayList<Float>(0);
        ArrayList<Float> q = new ArrayList<Float>(0);
        float constantTerm, leadingCoefficient;
        constantTerm = coefficients[coefficients.length - 1];
        leadingCoefficient = coefficients[0];
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
        for (float b = 1; b <= Math.abs(leadingCoefficient); b++) {
            if (Math.abs(leadingCoefficient) % b == 0) {
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

        return possibleRationalZeros;

    }
    

    // Performs synthetic division
    // Returns value of remainder
    public static float synthDiv(int degree, float k, int[] coefficients) {
        float r = 0;
        
        for (int i = 0; i <= degree; i++) {
            r += coefficients[i];
            r *= k;
        }
        
        return r;
    }


}
