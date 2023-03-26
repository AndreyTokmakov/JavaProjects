package Pairs_Algoritms;

final class ComplexNumber {
    private final double re;
    private final double im;

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        else if (null == obj || obj.getClass() != this.getClass())
            return false;
        else {
            ComplexNumber number = (ComplexNumber)obj;
            return this.re == number.re && this.im == number.im;
        }
    }

    @Override
    public int hashCode() {
        double result = 1307 * re + 3571;
        result += 1733 * im;
        return (int)result;
    }
}

public class ComplexNumberTests {


    public static void main(String[] args)
    {
        ComplexNumber a = new ComplexNumber(1, 1);
        ComplexNumber b = new ComplexNumber(1, 12);

        System.out.println(a.equals(b) + "| " + a.hashCode() + " =? " + b.hashCode());
    }
}
