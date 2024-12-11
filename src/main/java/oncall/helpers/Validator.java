package oncall.helpers;

public class Validator {
    private final static String DELIMITER = ",";
    
    public void checkMonth(String input) {
        String monthString = input.split(DELIMITER)[0];
        try {
            int month = Integer.parseInt(monthString);
            if (month < 1 || month > 12) {
                throw new IllegalArgumentException(ErrorMessages.INVALID.getMessage());
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessages.INVALID.getMessage());
        }
    }
}