package oncall.helpers;

import oncall.ui.constants.ErrorMessages;

import java.util.Arrays;
import java.util.List;

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

    public void checkNames(String input) {
        List<String> names = Arrays.stream(input.split(DELIMITER)).toList();
        names.forEach(name -> {
            if (name.length() > 5) {
                throw new IllegalArgumentException(ErrorMessages.INVALID.getMessage());
            }
            if (names.indexOf(name) != names.lastIndexOf(name)) {
                throw new IllegalArgumentException(ErrorMessages.INVALID.getMessage());
            }
        });
    }
}