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


    public void checkDay(String input) {
        String dayString = input.split(DELIMITER)[1];
        List<String> days = List.of("월", "화", "수", "목", "금", "토", "일");
        if (!days.contains(dayString)) {
            throw new IllegalArgumentException(ErrorMessages.INVALID.getMessage());
        }
    }
}