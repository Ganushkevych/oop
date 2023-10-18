import java.util.ArrayList;

public class StringCalculator {
    public int add(String numbers){
        if(numbers.isEmpty()) return 0;
        if(numbers.endsWith(",")) throw new WrongDelimiterException("Wrong delimiter");
        if(numbers.startsWith("//")){
            String[] Array = numbers.split("\n");
            if(Array.length>2) throw new WrongDelimiterException("Wrong delimiter");
            String delimiter;
            if(numbers.startsWith("//[")) delimiter = Array[0].substring(3, Array[0].length() - 1);
            else delimiter = String.valueOf(Array[0].charAt(2));
            if(Array[1].endsWith(delimiter)) throw new WrongDelimiterException("Wrong delimiter");
            numbers = Array[1].replace(delimiter,",");
        }
        else numbers = numbers.replace("\n",",");
        int sumOfNumbers = 0;
        ArrayList<Integer> negativeNumbersList = new ArrayList<>();
        String[] numbersArray = numbers.split(",");
        for(String number:numbersArray){
            if(!isNumeric(number)) throw new WrongDelimiterException("Wrong delimiter");
            if(Integer.parseInt(number)<0) negativeNumbersList.add(Integer.parseInt(number));
            if(Integer.parseInt(number)<=1000) sumOfNumbers += Integer.parseInt(number);
        }
        if(!negativeNumbersList.isEmpty()) throw new NegativeNumbersException("Negative numbers are not allowed",negativeNumbersList);
        return sumOfNumbers;
    }
    private static boolean isNumeric(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
