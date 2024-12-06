package com.nguyen.app;

public class Calculator implements ICalculator {
    @Override
    public double sum(double a, double b) {
        return a + b;
    }

    @Override
    public double sub(double a, double b) {
        return a - b;
    }

    @Override
    public double mul(double a, double b) {
        return a * b;
    }

    @Override
    public double mod(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot mod for 0");
        }
        return a % b;
    }

    @Override
    public double div(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot div for 0");
        }
        return a / b;
    }
}
