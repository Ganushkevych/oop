import java.util.ArrayList;

public class NegativeNumbersException extends RuntimeException{
    public NegativeNumbersException(String text, ArrayList<Integer> negativeNumbersList){
        super(text+"\n"+negativeNumbersList);
    }
}