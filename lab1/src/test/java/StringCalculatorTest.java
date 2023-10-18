import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {
    StringCalculator stringCalculator = new StringCalculator();
    @Test
    public void shouldReturnZeroOnEmptyString() {
        assertEquals(0, stringCalculator.add(""));
    }
    @Test
    public void shouldReturnNumberOnNumber() {
        assertEquals(1, stringCalculator.add("1"));
    }
    @Test
    public void shouldReturnSumOfTwoNumbers() {
        assertEquals(3, stringCalculator.add("1,2"));
    }
    @Test
    public void shouldThrowExceptionOnDelimiterAtTheEnd() {
        try{
            stringCalculator.add("1,2,");
            fail("Should be WrongDelimiterException");
        }catch (WrongDelimiterException ignored){}
    }
    @Test
    public void shouldThrowExceptionOnDelimiterAtTheBegin() {
        try{
            stringCalculator.add(",1,2");
            fail("Should be WrongDelimiterException");
        }catch (WrongDelimiterException ignored){}
    }
    @Test
    public void shouldThrowExceptionOnUnknownDelimiter() {
        try{
            stringCalculator.add("1%2");
            fail("Should be WrongDelimiterException");
        }catch (WrongDelimiterException ignored){}
    }
    @Test
    public void shouldThrowExceptionOnFewDelimiter() {
        try{
            stringCalculator.add("1,,2");
            stringCalculator.add("1,,,2");
            fail("Should be WrongDelimiterException");
        }catch (WrongDelimiterException ignored){}
    }
}