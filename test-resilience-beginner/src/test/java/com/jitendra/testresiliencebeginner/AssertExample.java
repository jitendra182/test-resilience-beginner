package com.jitendra.testresiliencebeginner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AssertExample {


    @Test
    void test1() {
//        System.out.println("some assertion method");
        int actual = 12 + 12;
        int expected = 24;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test2() {
//        System.out.println("some assertion method");
        int[] actual = {1, 2, 3 , 4, 5, 8};
        int[] expected = {1, 2, 3 , 4, 5, 6};
        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    void test3() {
        String actual ="myString";
        String expect = "myString";
        Assertions.assertSame(expect, actual);
    }

    @Test
    void test4() {
        String actual ="myString";
        String expect = new String("myString");
        Assertions.assertNotSame(expect, actual);
    }

    @Test
    void test5() {
        String actual = null;
        Assertions.assertNull(actual);
    }

    @Test
    void test6() {
        String actual = null;
        Assertions.assertNotNull(actual);
    }

    @Test
    void test7() {
        Boolean actual = false;
        Assertions.assertTrue(actual);
    }
    @Test
    void test8() {
        List<Integer>  actual = List.of(1,2,3,4,5);
        List<Integer>  expected = List.of(1,2,3,4,5);
        Assertions.assertIterableEquals(expected, actual );
    }

    @Test
    void test9() {
        List<Integer>  actual = List.of(1,2,3,4,5, 7);
        List<Integer>  expected = List.of(1,2,3,4,5);
        Assertions.assertIterableEquals(expected, actual );
    }


    @Test
    void test10() {

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, ()-> {
            int a =5;
            int b =0;
            int res =a / b;
        } );

    }

    @Test
    void test11() {
        Assertions.assertAll(()-> {
            List<Integer>  actual = List.of(1,2,3,4,5, 7);
            List<Integer>  expected = List.of(1,2,3,4,5);
        });
    }
}
