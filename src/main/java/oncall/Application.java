package oncall;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        Validator validator = new Validator();
        String monthInput = this.getMonthInput(inputView, validator);
    }

    private String getMonthInput(InputView inputView, Validator validator) {
        while (true) {
            try {
                String monthInput = inputView.readMonth();
                validator.checkMonth(monthInput);
                return monthInput;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
