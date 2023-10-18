public class StringCalculator {
    public int add(String numbers){
        if(numbers.isEmpty()) return 0;
        else if(numbers.endsWith(",")) throw new WrongDelimiterException("Wrong delimiter");
        if(numbers.startsWith("//")){
            String[] Array = numbers.split("\n");
            if(Array.length>2) throw new WrongDelimiterException("Wrong delimiter");
            String delimiter = String.valueOf(Array[0].charAt(2));
            if(Array[1].endsWith(delimiter)) throw new WrongDelimiterException("Wrong delimiter");
            numbers = Array[1].replace(delimiter,",");
        }
        else numbers = numbers.replace("\n",",");
        int sumOfNumbers = 0;
        String[] numbersArray = numbers.split(",");
        for(String number:numbersArray){
            if(!isNumeric(number)) throw new WrongDelimiterException("Wrong delimiter");
            sumOfNumbers += Integer.parseInt(number);
        }
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
