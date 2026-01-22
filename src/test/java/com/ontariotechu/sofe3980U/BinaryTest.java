package com.ontariotechu.sofe3980U;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class BinaryTest {

    // Constructor Tests 

    @Test
    public void normalConstructor() {
        Binary binary = new Binary("1001001");
        assertTrue(binary.getValue().equals("1001001"));
    }

    @Test
    public void constructorWithInvalidDigits() {
        Binary binary = new Binary("1001001211");
        assertTrue(binary.getValue().equals("0"));
    }

    @Test
    public void constructorWithInvalidChars() {
        Binary binary = new Binary("1001001A");
        assertTrue(binary.getValue().equals("0"));
    }

    @Test
    public void constructorWithNegativeSign() {
        Binary binary = new Binary("-1001001");
        assertTrue(binary.getValue().equals("0"));
    }

    @Test
    public void constructorWithZeroTailing() {
        Binary binary = new Binary("00001001");
        assertTrue(binary.getValue().equals("1001"));
    }

    @Test
    public void constructorEmptyString() {
        Binary binary = new Binary("");
        assertTrue(binary.getValue().equals("0"));
    }

    // Add Tests 

    @Test
    public void add() {
        Binary binary1 = new Binary("1000");
        Binary binary2 = new Binary("1111");
        Binary binary3 = Binary.add(binary1, binary2);
        assertTrue(binary3.getValue().equals("10111"));
    }

    @Test
    public void add2() {
        Binary binary1 = new Binary("1010");
        Binary binary2 = new Binary("11");
        Binary binary3 = Binary.add(binary1, binary2);
        assertTrue(binary3.getValue().equals("1101"));
    }

    @Test
    public void add3() {
        Binary binary1 = new Binary("11");
        Binary binary2 = new Binary("1010");
        Binary binary3 = Binary.add(binary1, binary2);
        assertTrue(binary3.getValue().equals("1101"));
    }

    @Test
    public void add4() {
        Binary binary1 = new Binary("0");
        Binary binary2 = new Binary("1010");
        Binary binary3 = Binary.add(binary1, binary2);
        assertTrue(binary3.getValue().equals("1010"));
    }

    @Test
    public void add5() {
        Binary binary1 = new Binary("0");
        Binary binary2 = new Binary("0");
        Binary binary3 = Binary.add(binary1, binary2);
        assertTrue(binary3.getValue().equals("0"));
    }

    // OR Tests

    @Test
    public void orSameLength() {
        Binary a = new Binary("1010");
        Binary b = new Binary("0101");
        Binary r = Binary.or(a, b);
        assertTrue(r.getValue().equals("1111"));
    }

    @Test
    public void orDifferentLength() {
        Binary a = new Binary("1");
        Binary b = new Binary("1000");
        Binary r = Binary.or(a, b);
        assertTrue(r.getValue().equals("1001"));
    }

    @Test
    public void orWithZero() {
        Binary a = new Binary("0");
        Binary b = new Binary("111000");
        Binary r = Binary.or(a, b);
        assertTrue(r.getValue().equals("111000"));
    }

    // AND Tests 

    @Test
    public void andSameLength() {
        Binary a = new Binary("1100");
        Binary b = new Binary("1010");
        Binary r = Binary.and(a, b);
        assertTrue(r.getValue().equals("1000"));
    }

    @Test
    public void andDifferentLength() {
        Binary a = new Binary("1");
        Binary b = new Binary("1000");
        Binary r = Binary.and(a, b);
        assertTrue(r.getValue().equals("0"));
    }

    @Test
    public void andAllOnes() {
        Binary a = new Binary("1111");
        Binary b = new Binary("1111");
        Binary r = Binary.and(a, b);
        assertTrue(r.getValue().equals("1111"));
    }

    // Multiply Tests 

    @Test
    public void multiplyByZero() {
        Binary a = new Binary("101101");
        Binary b = new Binary("0");
        Binary r = Binary.multiply(a, b);
        assertTrue(r.getValue().equals("0"));
    }

    @Test
    public void multiplyByOne() {
        Binary a = new Binary("111000");
        Binary b = new Binary("1");
        Binary r = Binary.multiply(a, b);
        assertTrue(r.getValue().equals("111000"));
    }

    @Test
    public void multiplyGeneralCase() {
        // 101 (5) * 11 (3) = 1111 (15)
        Binary a = new Binary("101");
        Binary b = new Binary("11");
        Binary r = Binary.multiply(a, b);
        assertTrue(r.getValue().equals("1111"));
    }
}
