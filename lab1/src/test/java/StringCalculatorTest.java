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
    @Test
    public void shouldReturnSumOfNumbers() {
        assertEquals(7, stringCalculator.add("1,2,4"));
        assertEquals(12, stringCalculator.add("1,2,4,5"));
    }
    @Test
    public void shouldReturnSumWithNewLineAsDelimiter() {
        assertEquals(12, stringCalculator.add("1,2\n4,5"));
        assertEquals(12, stringCalculator.add("1\n2\n4\n5"));
    }
    @Test
    public void shouldThrowExceptionOnFewDelimiter2() {
        try{
            stringCalculator.add("1,\n2,3");
            fail("Should be WrongDelimiterException");
        }catch (WrongDelimiterException ignored){}
    }
    @Test
    public void shouldReturnSumWithYoursDelimiter() {
        assertEquals(6, stringCalculator.add("//*\n1*2*3"));
        assertEquals(6, stringCalculator.add("//;\n1,2;3"));
        assertEquals(6, stringCalculator.add("//*\n1*2,3"));
    }
    @Test
    public void shouldThrowExceptionOnTwoDelimitersInARow2() {
        try{
            stringCalculator.add("//;\n1,;2,3");
            fail("Should be WrongDelimiterException");
        }catch (WrongDelimiterException ignored){}
    }
    @Test
    public void shouldThrowExceptionOnUnknownDelimiter2() {
        try{
            stringCalculator.add("//%\n1%2$3");
            fail("Should be WrongDelimiterException");
        }catch (WrongDelimiterException ignored){}
    }
    @Test
    public void shouldThrowExceptionOnDelimiterAtTheEnd2() {
        try{
            stringCalculator.add("//%\n1,2,3%");
            fail("Should be WrongDelimiterException");
        }catch (WrongDelimiterException ignored){}
    }
    @Test
    public void shouldThrowExceptionOnDelimiterAtTheBegin2() {
        try{
            stringCalculator.add("//^\n^1,2,3");
            fail("Should be WrongDelimiterException");
        }catch (WrongDelimiterException ignored){}
    }
    @Test
    public void shouldThrowExceptionOnWrongInput() {
        try{
            stringCalculator.add("/^\n^1,2,3");
            fail("Should be WrongDelimiterException");
        }catch (WrongDelimiterException ignored){}
    }
    @Test
    public void shouldThrowExceptionOnNegativeNumbers() {
        try{
            stringCalculator.add("-1,-2,3,4");
            fail("Should be NegativeNumbersException");
        }catch (NegativeNumbersException e){
            assertEquals(e.getMessage(),"Negative numbers are not allowed\n[-1, -2]");
        }
    }
    @Test
    public void shouldThrowExceptionOnNegativeNumbers2() {
        try{
            stringCalculator.add("//%\n-1%-2,3,4");
            fail("Should be NegativeNumbersException");
        }catch (NegativeNumbersException e){
            assertEquals(e.getMessage(),"Negative numbers are not allowed\n[-1, -2]");
        }
    }
    @Test
    public void shouldReturnSumAvoidingNumberThatMoreThanThousand() {
        assertEquals(1999, stringCalculator.add("1000,999,1001"));
    }
    @Test
    public void shouldReturnSumAvoidingNumberThatMoreThanThousand2() {
        assertEquals(1999, stringCalculator.add("//*\n1000*999,1001"));
    }
    @Test
    public void shouldReturnSumWithLongDelimiter() {
        assertEquals(6, stringCalculator.add("//[***]\n1***2***3"));
        assertEquals(6, stringCalculator.add("//[^^]\n1^^2,3"));
    }
}
