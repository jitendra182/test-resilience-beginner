package com.jitendra.testresiliencebeginner;

import com.jitendra.testresiliencebeginner.basic.Calculator;
import org.junit.jupiter.api.*;

import java.util.concurrent.TimeUnit;

public class CalculatorTest {

    Calculator calculator;

    @BeforeEach
    void setUp() {
        System.out.println("BeforeEach Called");
        calculator = new Calculator();
    }

    @Test
    void multiplyTest() {
        int result = calculator.multiply(5, 4);
        Assertions.assertEquals(20, result);
        Assertions.assertEquals(25,  calculator.multiply(5,5));
    }

    @Test
    void divideTest() {
        Assertions.assertEquals( 5, calculator.divide(20,4));
    }

    @BeforeAll
    public static void initBeforeAll() {
        System.out.println("BeforeAll called");
    }

    @AfterEach
    public void afterEachMethod() {
        System.out.println("AfterEach called");
    }

    @AfterAll
    public static void initAfterAl() {
        System.out.println("AfterAll called");
    }


    @Test
    void testAsume() {
        boolean ass = 5 < 3;
        Assumptions.assumeTrue(ass);
    }

    @Test
    @Disabled
    void testDisabled() {
        System.out.println("test disabeld");
    }

    @Test()
    @Timeout(value = 42, unit = TimeUnit.MILLISECONDS)
    void testTimeout() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Test
    @DisplayName("My Custom Test")
    void displayNameTest() {
        System.out.println("displayNameTest called");
    }


    @Test
    @Tag("MyCustomTest")
    void tagTest() {
        System.out.println("MyCustomTest called");
    }
}
