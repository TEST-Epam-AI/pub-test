package com.nguyen.app;

import org.assertj.core.api.Assertions;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.times;

public class CalculatorMockTest {
    private static ICalculator calculatorMock;

    @BeforeClass
    public static void initialize() {
        calculatorMock = Mockito.mock(Calculator.class);
    }

    @Test
    public void testSum() {
        Mockito.when(calculatorMock.sum(1, 2)).thenReturn((double) 1 + 2);
        double result = calculatorMock.sum(1, 2);
        Assertions.assertThat(result).isEqualTo(3);
    }

    @Test
    public void testSub() {
        Mockito.when(calculatorMock.sub(1, 2)).thenReturn((double) 1 - 2);
        double result = calculatorMock.sub(1, 2);
        Assertions.assertThat(result).isEqualTo(-1);
    }

    @Test
    public void testMul() {
        Mockito.when(calculatorMock.mul(1, 2)).thenReturn((double) 1 * 2);
        double result = calculatorMock.mul(1, 2);
        Assertions.assertThat(result).isEqualTo(2);
    }

    @Test
    public void testMod_success() {
        Mockito.when(calculatorMock.mod(1, 2)).thenReturn((double) 1 % 2);
        double result = calculatorMock.mod(1, 2);
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test(expected = ArithmeticException.class)
    public void testMod_fail() {
        Mockito.when(calculatorMock.mod(anyDouble(), 0)).thenThrow(ArithmeticException.class);
        calculatorMock.mod(anyDouble(), 0);
        Mockito.verify(calculatorMock, times(1)).mod(anyDouble(), anyDouble());
    }

    @Test
    public void testDiv_success() {
        Mockito.when(calculatorMock.div(1, 2)).thenReturn((double) 1 / 2);
        double result = calculatorMock.div(1, 2);
        Assertions.assertThat(result).isEqualTo(0.5);
    }

    @Test
    public void testDiv_fail() {
        Mockito.doThrow(new ArithmeticException("Cannot div for 0")).when(calculatorMock).div(anyDouble(), 0);
        try {
            calculatorMock.div(anyDouble(), 0);
        } catch (ArithmeticException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("Cannot div for 0");
        }
    }
}
