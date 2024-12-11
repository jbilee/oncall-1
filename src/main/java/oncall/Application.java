package oncall;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        Validator validator = new Validator();
        String monthInput = this.getMonthInput(inputView, validator);
        String weekdayNames = this.getWeekdayNames(inputView, validator);
        String holidayNames = this.getHolidayNames(inputView, validator);
    }

    private String getMonthInput(InputView inputView, Validator validator) {
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

    private String getWeekdayNames(InputView inputView, Validator validator) {
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

    private String getHolidayNames(InputView inputView, Validator validator) {
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
