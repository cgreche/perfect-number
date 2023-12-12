package org.example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
    References:
        - https://en.wikipedia.org/wiki/Perfect_number
        - https://oeis.org/A000396
        - https://web.archive.org/web/20141006120722/http://amicable.homepage.dk/perfect.htm
        - https://cronodon.com/Programming/Perfect_Numbers.html
 */

public class PerfectNumber {

    static BigInteger FIRST_PERFECT_NUMBER = BigInteger.valueOf(6);

    /**
     * Test n as perfect number
     *
     * @param n Number to be tested
     * @return true if n is a perfect number; false otherwise.
     */
    public static boolean test(BigInteger n) {
        // Optimization: If n is less than the first Perfect Number, return false
        if(n.compareTo(FIRST_PERFECT_NUMBER) < 0)
            return false;

        // Optimization: Assuming only even numbers are Perfect Numbers
        if(!n.and(BigInteger.ONE).equals(BigInteger.ZERO))
            return false;

        // A faster way to get all divisors of a number compare until the square root of the number
        BigInteger nSqrt = n.sqrt(); //(BigInteger.valueOf(2));
        BigInteger sum = BigInteger.valueOf(1);
        for(BigInteger i = BigInteger.valueOf(2); i.compareTo(nSqrt) <= 0; i = i.add(BigInteger.ONE)) {
            if(n.remainder(i).equals(BigInteger.ZERO)) {
                sum = sum.add(i);
                BigInteger div = n.divide(i);
                if(!i.equals(div))
                    sum = sum.add(div);
            }
        }

        return sum.equals(n);
    }

    public static boolean test(long n) {
        return test(BigInteger.valueOf(n));
    }

    public static boolean test(String text) {
        return test(new BigInteger(text));
    }

    /**
     * Find all perfect numbers between n1 and n2
     *
     * @param n1 Lower bound to start searching for perfect numbers
     * @param n2 Upper bound to stop searching for perfect numbers
     * @return List containing the perfect numbers found
     */
    public static List<BigInteger> findBetween(BigInteger n1, BigInteger n2) {
        ArrayList<BigInteger> perfects = new ArrayList<>();

        if(!n1.and(BigInteger.ONE).equals(BigInteger.ZERO))
            n1 = n1.add(BigInteger.ONE);

        // Optimization: Assuming perfect numbers are always even (no proven odd perfect numbers were found)
        for(; n1.compareTo(n2) <= 0; n1 = n1.add(BigInteger.TWO)) {
            if(test(n1))
                perfects.add(n1);
        }

        return Collections.unmodifiableList(perfects);
    }

}
