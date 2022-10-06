public class Polynomials {

    // Returns polynomial with generic coefficients
    public static StringBuffer polyForm(int n) {
        char varCoefficient = 'a';
        int coefficientASCII = varCoefficient;
        StringBuffer polynomial = new StringBuffer();

        for (int i = n; i >= 0; i--) {
            if (i > 1) {
                polynomial.append(varCoefficient + "x^" + i + " + ");
            }
            else if (i == 1) {
                polynomial.append(varCoefficient + "x + ");
            }
            else if (i == 0) {
                polynomial.append(varCoefficient + "\n");
            }
            coefficientASCII += 1;
            varCoefficient = Character.toChars(coefficientASCII)[0];
        }

        return polynomial;
    }

    // Returns polynomial with coefficients
    public static StringBuffer polyCoefficients(int n, int[] coefficients) {
        StringBuffer polynomial = new StringBuffer();

        for (int i = 0; i <= n; i++) {
            // All positive coefficients not equal to 1, 0, or -1
            if (coefficients[i] != 0 && coefficients[i] != 1 && coefficients[i] != -1) {
                // First terms
                if (i >= 0 && i < (n - 1)) {
                    polynomial.append(coefficients[i] + "x^" + (n - i));
                }
                // Second to last term
                else if (i == (n - 1)) {
                    polynomial.append(coefficients[i] + "x + ");
                }
                // Constant term
                else if (i == n) {
                    polynomial.append(coefficients[i] + "\n");
                }
            }
            // Coefficients equal to 1 (don't print the 1)
            else if (coefficients[i] == 1) {
                // First terms
                if (i >= 0 && i < (n - 1)) {
                    polynomial.append("x^" + (n - i));
                }
                // Second to last term
                else if (i == (n - 1)) {
                    polynomial.append(" + " + "x");
                }
                // Last term
                else if (i == n) {
                    polynomial.append(" + " + coefficients[i] + "\n");
                }
            }
            // Coefficients equal to 0 (don't print anything)
            else if (coefficients[i] == 0) {
            }
            // Coefficients equal to -1 (don't print the 1 unless it's the last term)
            else if (coefficients[i] == -1) {
                // First terms
                if (i >= 0 && i < (n - 1)) {
                    polynomial.append(" - " + "x^" + (n - i));
                }
                // Second to last term
                else if (i == (n - 1)) {
                    polynomial.append(" - " + "x");
                }
                // Last term
                else if (i == n) {
                    polynomial.append(" - " + -coefficients[i] + "\n");
                }
            }
            // Negative coefficients
            else if (coefficients[i] < 0) {
                // First terms
                if (i >= 0 && i < (n - 1)) {
                    polynomial.append(" - " + "x^" + (n - i));
                }
                // Second to last term
                else if (i == (n - 1)) {
                    polynomial.append(" - " + "x ");
                }
                // Last term
                else if (i == n) {
                    polynomial.append(" - " + -coefficients[i] + "\n");
                }
            }
        }

        return polynomial;
    }

}