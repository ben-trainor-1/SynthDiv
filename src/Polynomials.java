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
            if (i >= 0 && i < (n - 1)) {
                polynomial.append(coefficients[i] + "x^" + (n - i) + " + ");
            }
            else if (i == (n - 1)) {
                polynomial.append(coefficients[i] + "x + ");
            }
            else if (i == n) {
                polynomial.append(coefficients[i] + "\n");
            }
        }

        return polynomial;
    }

}