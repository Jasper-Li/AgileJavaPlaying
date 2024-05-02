package org.example.chapter10;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

import static java.lang.System.out;
import static java.util.FormatProcessor.FMT;
import static org.junit.jupiter.api.Assertions.*;

public class Chapter10Test {
    @Test
    void immutibility() {
        BigDecimal old = new BigDecimal(10);
        var newValue = old.add(new BigDecimal(10));
        assertEquals(new BigDecimal(20), newValue);
        assertEquals(new BigDecimal(10), old);
    }

    @Test
    void test2() {
        var value1 = new BigDecimal("10.00");
        var value2 = new BigDecimal("1");
        assertNotEquals(value1, value2);
        assertEquals(value1, value2.multiply(value1));
        assertEquals(value2, value1.multiply(new BigDecimal("0.1"), new MathContext(1)));
    }
    @Test
    void test3() {
        assertNotEquals(0.9f, 0.005f*2.0f);
        assertNotEquals(0.9, 0.005*2.0);
    }
    public class CompilerError1 {
        float x = (float)0.01;
    }
    public class CompilerError2 {
        double x = 0.01;
    }

    @Test
    void test5() {
        String string = "0xDEAD";
        var value = Integer.decode(string);
        String hexRepr = STR."0x\{Integer.toHexString(value)}";
        assertEquals(hexRepr.toLowerCase(), string.toLowerCase());
        out.print(STR."\{string} in octal is: 0\{Integer.toOctalString(value)}");
    }

    @Test
    void test8() {
        int[] values = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Set<Integer> expected = Set.of(3, 6, 9);
        assertEquals(expected, dividedBy3(values));
        assertEquals(expected, dividedBy3_2(values));

    }
    private Set<Integer> dividedBy3(int... values) {
        Set<Integer> result = new HashSet<>();
        for(var value: values) {
            if (value%3 == 0) {
                result.add(value);
            }
        }
        return result;
    }
    private Set<Integer> dividedBy3_2(int... values) {
        Set<Integer> result = new HashSet<>();
        final float tolarance = 0.1f;
        for(var value: values) {
            var buf = value/3.0f  - value/3;
            if(Math.abs(buf) < tolarance) {
                result.add(value);
            }
        }
        return result;
    }

    @Disabled
    @Test
    void test_10() {
        float x1 = 1;
//        float x2 = 1.0;
        float x3 = (int)1.0;
    }

    @Test
    void testFrom11To17() {
        assertEquals(1, (int)1.9);
        assertEquals(2.0, Math.rint(1.9));
        assertEquals(2.0, Math.rint(1.5));
        assertEquals(2.0, Math.rint(2.5));

        int x = 5, y=10;
        assertEquals(42, x*5 + y++ * 7 / 4);
        assertEquals(5, x);
        assertEquals(11, y);

        x=5; y=10;
        assertEquals(300, ++x * 5 * y++);
        assertEquals(6, x);
        assertEquals(11, y);

        x=5; y=10;
        assertEquals(275, x++ * 5 * ++y);
        assertEquals(6, x);
        assertEquals(11, y);

        x=5; y=10;
        assertEquals(52, ++x + 5 * 7 + ++y);
        assertEquals(6, x);
        assertEquals(11, y);

//        assertEquals(42, ++y++  % ++x++)
        x=5; y=10;
        assertTrue(x*7==35 || y++==0);
        assertEquals(5, x);
        assertEquals(10, y);

        x=5; y=10;
        assertEquals(66, ++x * ++y);
        assertEquals(6, x);
        assertEquals(11, y);

        x=5; y=10;
        assertEquals(50, x++ * y++);
        assertEquals(6, x);
        assertEquals(11, y);

//        assertTrue(true && x*7);
//        assertTrue(x*2 == y- || ++y==10);
        x=5; y=10;
        assertFalse(x*2 == -y || ++y==10);
        assertEquals(5, x);
        assertEquals(11, y);

        assertEquals(34, 17<<1);

        assertEquals(-2, ~1);

        assertEquals(0b11, 0b111 >>1);
        assertEquals(0b11, 0b111 >>>1);
        int v = -10;
        out.println(STR."\{Integer.toBinaryString(v)}");
        var vBinary = Integer.toBinaryString(v);
        int v1 = -10 >> 2;
        out.println(STR."\{-10} >> 2 is %x\{v1}, \n\{Integer.toBinaryString(v1)}");
        int v2 = -10 >>> 2;
        out.println(FMT."\{-10} >>> 2 is %x\{v2}, \n%32s\{Integer.toBinaryString(v2)}");
    }

    private int randomValue(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1)+min;
    }
    private int randomTo50() {
        return randomValue(1, 50);
    }
    @Test
    void test18RandomGenerator() {
        final int GENERATE_COUNT = 50_0000;
        class NumberCount{
            Map<Integer, Double> numberFrequency = new HashMap<>();
            NumberCount(List<Integer> numbers)  {
                Map<Integer, Integer> map = new HashMap<>();
                for(var number : numbers) {
                    var value = map.getOrDefault(number, 0);
                    map.put(number, value+1);
                }
                for(var entry: map.entrySet()) {
                    numberFrequency.put(entry.getKey(), (double)entry.getValue()/GENERATE_COUNT);
                }
            }

        }
        List<Integer> numbers = new ArrayList<>(GENERATE_COUNT);
        for(int i =0; i<GENERATE_COUNT; ++i) {
            numbers.add(randomTo50());
        }
        final int value_min = 1;
        final int value_max= 50;
        final int value_count = 50;
        var numberCount = new NumberCount(numbers);
        var numberFrequency = numberCount.numberFrequency;
        var keySet = numberFrequency.keySet();
        final double tolarance = 0.005;
        assertEquals(value_count, keySet.size());
        for(int i = value_min; i<= value_max; ++i) {
            assertTrue(keySet.contains(i));
            assertEquals(0.02, numberFrequency.get(i), tolarance);
//            out.println(STR."\{i} -> \{numberFrequency.get(i)}");
        }

    }
    private boolean are2MembersSwapped(List<Integer> left, List<Integer>right) {
        final var leftSize = left.size();
        final var rightSize = right.size();
        if(leftSize != rightSize) {
            return false;
        }
        List<Integer> mismatchedIndexes = new ArrayList<>();
        for(int i =0; i<leftSize; ++i) {
            if(left.get(i) != right.get(i)) {
                mismatchedIndexes.add(i);
            }
        }
        if(mismatchedIndexes.size() != 2) {
            out.println(STR."expect mismatched size(\{mismatchedIndexes.size()}) == 2.");
            return false;
        }
        final int firstIndex = mismatchedIndexes.get(0);
        final int secondIndex = mismatchedIndexes.get(1);
        boolean result = left.get(firstIndex) == right.get(secondIndex) && left.get(secondIndex) == right.get(firstIndex);
        if(!result) {

            String msg = STR."""
            expect firstIndex \{firstIndex}, secondIndex \{secondIndex},
            \{left.get(firstIndex)} == \{right.get(secondIndex)}
            \{left.get(secondIndex)} == \{right.get(firstIndex)}
            """;
        }
        return result;
    }
    @Test
    void test19AreListsSwapped() {
        record Check(List<Integer> left, List<Integer> right, boolean areSwapped){}
        Check[] checks = new Check[]{
            new Check(
                List.of(1, 2, 3),
                List.of(1, 2),
                false
            ),
            new Check(
                List.of(1, 2, 3),
                List.of(1, 2, 3),
                false
            ),
            new Check(
                List.of(1, 2, 3),
                List.of(4, 2, 6),
                false
            ),
            new Check(
                List.of(1, 2, 3),
                List.of(3, 2, 6),
                false
            ),
            new Check(
                List.of(1, 2, 3),
                List.of(3, 2, 1),
               true
            ),
        };
        for(var check : checks) {
            assertEquals(check.areSwapped, are2MembersSwapped(check.left, check.right));
        }
    }
    private List<Integer> swap2Numbers(final List<Integer> values) {
        final int MIN=1;
        final int MAX=100;
        final int COUNT = MAX - MIN +1;
        final int indexesCount = 2;
        List<Integer> indexes = new ArrayList<>(indexesCount);
        Random random = new Random();

       while(indexes.size() < 2)  {
            final var nextIndex = random.nextInt(COUNT);
            if(!indexes.contains(nextIndex)) {
                indexes.add(nextIndex);
            }
        }
        var firstIndex = indexes.get(0);
        var secondeIndex = indexes.get(1);
        var buf = values.get(firstIndex);
        values.set(firstIndex, values.get(secondeIndex));
        values.set(secondeIndex, buf);
        return values;
    }
    @Test
    void testSwap2Numbers() {
        final var values = create100Numbers();
        final var origin = new ArrayList<>(values);
        assertTrue(are2MembersSwapped(origin, swap2Numbers(values)));
    }
    private void swap2Numbers100Times(final List<Integer> values) {
        for(int i =0 ;i<100; ++i) {
            swap2Numbers(values);
        }
    }
    private List<Integer> create100Numbers() {
        List<Integer> values = new ArrayList<>(100);
        for(int i=1; i<=100; ++i) {
            values.add(i);
        }
        return values;
    }
    @Test
    void test19() {
        final var values = create100Numbers();
        swap2Numbers100Times(values);
        out.println("values after 100 times swap.");
        for(int i = 0; i<100; ++i) {
            if(i!=0 && i%10 == 0) out.println();
            out.print(FMT. "%3d\{values.get(i)} ");
        }

    }
    @Test
    void test20() {
        Random random1 = new Random(1L);
        Random random2 = new Random();
        assertNotEquals(random1.nextDouble(), random2.nextDouble(), 0.000001);
    }
    @Test
    void test21Exchange() {
        int a = 10;
        int b = 20;
        assertEquals(b, a^b^a);
        assertEquals(a, a^b^b);
    }

    private void checkBitwidth(int bitWidth) {
        if(bitWidth > 64){
            throw new RuntimeException("failed to get size");
        }
    }
    @Test
    void test22TypeSize() {
        char char1 = 0b1;
        int bitWidth=0;
        int count = 0;
        while(char1 != 0) {
            char1 <<= 1;
            checkBitwidth(++bitWidth);
        }
        assertEquals(16, bitWidth);

        byte b1 = 0b1;
        bitWidth=0;
        count = 0;
        while(b1 != 0) {
            b1 <<=1;
            checkBitwidth(++bitWidth);
        }
        assertEquals(8, bitWidth);

        short s1 = 0b1;
        bitWidth=0;
        count = 0;
        while(s1 != 0) {
            s1 <<=1;
            checkBitwidth(++bitWidth);
        }
        assertEquals(16, bitWidth);

        int i1 = 0b1;
        bitWidth=0;
        count = 0;
        while(i1 != 0) {
            i1 <<=1;
            checkBitwidth(++bitWidth);
        }
        assertEquals(32, bitWidth);

        long l1 = 0b1L;
        bitWidth=0;
        count = 0;
        while(l1 != 0) {
            l1 <<=1;
            checkBitwidth(++bitWidth);
        }
        assertEquals(64, bitWidth);
    }
}
