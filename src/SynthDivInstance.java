
public class SynthDivInstance {

    int remainder;
    int degree;
    float[] coefficients;
    float[] newCoefficients;

    public SynthDivInstance(int n) {
        degree = n;
        coefficients = new float[degree];
        newCoefficients = new float[degree - 1];
    }

    public void storeNextRemainder(int index, float r) {
        newCoefficients[index] = r;
    }
    
}
