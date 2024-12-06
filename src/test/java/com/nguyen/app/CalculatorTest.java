package com.nguyen.app;


import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class CalculatorTest {
    private static ICalculator calculator;

    @BeforeClass
    public static void initialize() {
        calculator = new Calculator();
    }

    @Before
    public void beforeTest() {
        System.out.println("Before test");
    }

    @After
    public void afterTest() {
        System.out.println("After test");
    }

    @Test
    public void testSum() {
        double result = calculator.sum(1, 5.78);
        assertEquals(6.78, result, 0);
    }

    @Test
    public void testSub() {
        double result = calculator.sub(5, 4);
        assertEquals(1, result, 0);
    }

    @Test
    public void testMul() {
        double result = calculator.mul(4, 14);
        assertEquals(56, result, 0);
    }

    @Test
    public void testDiv_Success() throws Exception {
        double result = calculator.div(8, 2);
        assertEquals(4, result, 0);
    }

    @Test
    public void testMod_Success() throws Exception {
        double result = calculator.mod(8, 2);
        assertEquals(0, result, 0);
    }

    @Test(expected = ArithmeticException.class)
    public void testDiv_Fail() {
        double result = calculator.div(10, 0);
    }

    @Test
    public void testMod_Fail() {
        try {
            double result = calculator.mod(10, 0);
        } catch (ArithmeticException ex) {
            assertThat(ex, instanceOf(ArithmeticException.class));
            assertEquals(ex.getMessage(), "Cannot mod for 0");
        }
    }
}
