package oncall;

import oncall.domains.calendar.Calendar;
import oncall.domains.staff.StaffQueue;
import oncall.helpers.Validator;
import oncall.ui.InputView;
import oncall.ui.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        Validator validator = new Validator();
        String monthInput = getMonthInput(inputView, validator);
        String weekdayNames = getWeekdayNames(inputView, validator);
        String holidayNames = getHolidayNames(inputView, validator);

        StaffQueue staffQueue = new StaffQueue(weekdayNames, holidayNames);
        Calendar calendar = new Calendar(monthInput);
        calendar.assignStaff(staffQueue);
        calendar.printResults(new OutputView());
    }

    private static String getMonthInput(InputView inputView, Validator validator) {
        while (true) {
            try {
                String monthInput = inputView.readMonth();
                validator.checkMonth(monthInput);
                validator.checkDay(monthInput);
                return monthInput;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static String getWeekdayNames(InputView inputView, Validator validator) {
        while (true) {
            try {
                String nameInput = inputView.readWeekdayNames();
                validator.checkNames(nameInput);
                return nameInput;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static String getHolidayNames(InputView inputView, Validator validator) {
        while (true) {
            try {
                String nameInput = inputView.readHolidayNames();
                validator.checkNames(nameInput);
                return nameInput;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
