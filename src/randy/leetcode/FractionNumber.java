package randy.leetcode;

public class FractionNumber {
    final int numerator;
    final int denominator;

    public FractionNumber(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static FractionNumber of(int numerator, int denominator) {
        return new FractionNumber(numerator, denominator);
    }

    public static FractionNumber of(int n) {
        return new FractionNumber(n, 1);
    }

    public FractionNumber add(int n) {
        return FractionNumber.of(numerator + n * denominator, denominator);
    }

    public FractionNumber subtract(int n) {
        return FractionNumber.of(numerator - n * denominator, denominator);
    }

    public FractionNumber multiply(int n) {
        return FractionNumber.of(numerator * n, denominator);
    }

    public FractionNumber divide(int n) {
        return FractionNumber.of(numerator, n * denominator);
    }

    public FractionNumber add(FractionNumber fn) {
        return FractionNumber.of(numerator * fn.denominator + denominator * fn.numerator,
                denominator * fn.denominator);
    }

    public FractionNumber subtract(FractionNumber fn) {
        return FractionNumber.of(numerator * fn.denominator - denominator * fn.numerator,
                denominator * fn.denominator);
    }

    public FractionNumber multiply(FractionNumber fn) {
        return FractionNumber.of(numerator * fn.numerator, denominator * fn.denominator);
    }

    public FractionNumber divide(FractionNumber fn) {
        return FractionNumber.of(numerator * fn.denominator, denominator * fn.numerator);
    }

    public int getInteger() {
        checkDivisionByZero();
        return numerator / denominator;
    }

    public int getRemainder() {
        checkDivisionByZero();
        return numerator % denominator;
    }

    public double getDouble() {
        checkDivisionByZero();
        return 1.0 * numerator / denominator;
    }

    public void checkDivisionByZero() {
        if (denominator == 0) {
            throw new ArithmeticException("divided by zero");
        }
    }

    public boolean divisionByZero() {
        return denominator == 0;
    }

    @Override
    public String toString() {
        return "FractionNumber{" +
                "numerator=" + numerator +
                ", denominator=" + denominator +
                '}';
    }
}
