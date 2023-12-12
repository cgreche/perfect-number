import org.example.PerfectNumber;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class PerfectNumberTest {

    @Test
    public void testIsMagicNumber() {
        Assert.assertTrue(PerfectNumber.test(6));
        Assert.assertTrue(PerfectNumber.test(28));
        Assert.assertTrue(PerfectNumber.test(496));
        Assert.assertTrue(PerfectNumber.test(8128));
        Assert.assertTrue(PerfectNumber.test(33550336));
        Assert.assertTrue(PerfectNumber.test(8589869056L));
        Assert.assertTrue(PerfectNumber.test(137438691328L));

        /* Sloooooow
        Assert.assertTrue(MagicNumber.is(2305843008139952128L));
        Assert.assertTrue(MagicNumber.is("2658455991569831744654692615953842176"));
        Assert.assertTrue(MagicNumber.is("191561942608236107294793378084303638130997321548169216"));
        */
        Assert.assertTrue(PerfectNumber.test("8589869056"));
        Assert.assertFalse(PerfectNumber.test(43432433));
        Assert.assertFalse(PerfectNumber.test(9223372036854775807L));
        Assert.assertFalse(PerfectNumber.test("4343243243234"));
    }

    @Test
    public void testMagicNumberFromInvalidString() {
        try {
            PerfectNumber.test(new BigInteger("abc"));
            Assert.fail();
        } catch(NumberFormatException exception) {
        }

        try {
            PerfectNumber.test(new BigInteger("1.27"));
            Assert.fail();
        } catch(NumberFormatException exception) {

        }
    }

    @Test
    public void findPerfectNumbersBetween1And10000() {
        List<BigInteger> expected = Arrays.asList(
                BigInteger.valueOf(6),
                BigInteger.valueOf(28),
                BigInteger.valueOf(496),
                BigInteger.valueOf(8128));

        List<BigInteger> result = PerfectNumber.findBetween(BigInteger.valueOf(1), BigInteger.valueOf(10000));
        for(BigInteger i : result) {
            System.out.println(i.toString());
        }

        Assert.assertEquals(result, expected);
    }
}
