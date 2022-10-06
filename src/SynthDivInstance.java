
public class SynthDivInstance {

    int remainder;
    int degree;
    int index = 0;
    float[] coefficients;
    float[] newCoefficients;

    public SynthDivInstance(int n) {
        degree = n;
        coefficients = new float[degree];
        newCoefficients = new float[degree - 1];
    }

    public void storeNextRemainder(float r) {
        newCoefficients[index] = r;
        index++;
    }
    
}
