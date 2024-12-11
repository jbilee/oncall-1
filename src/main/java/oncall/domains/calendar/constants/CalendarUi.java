package oncall.domains.calendar.constants;

public enum CalendarUi {
    MONTH("월 "),
    DATE("일 "),
    HOLIDAY("(휴일)");

    private final String text;

    CalendarUi(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}