public class StringCalculator {
    public int add(String numbers){
        if(numbers.isEmpty()) return 0;
        else if(numbers.endsWith(",")) throw new WrongDelimiterException("Wrong delimiter");
        else {
            int sumOfNumbers = 0;
            String[] numbersArray = numbers.split(",");
            for(String number:numbersArray){
                if(!isNumeric(number)) throw new WrongDelimiterException("Wrong delimiter");
                else sumOfNumbers += Integer.parseInt(number);
            }
            return sumOfNumbers;
        }
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
