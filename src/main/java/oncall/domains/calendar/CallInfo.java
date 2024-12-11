package oncall.domains.calendar;

public class CallInfo {
    private final int month;
    private final int date;
    private final String day;
    private final boolean isHoliday;
    private String staff;

    public CallInfo(int month, int date, String day, boolean isHoliday) {
        this.month = month;
        this.date = date;
        this.day = day;
        this.isHoliday = isHoliday;
    }

    public void updateName(String name) {
        this.staff = name;
    }

    public boolean isSameName(String name) {
        return this.staff.equals(name);
    }

    public String getPrintableInfo() {
        if (this.isHoliday) {
            return this.month + CalendarUi.MONTH.getText() + this.date + CalendarUi.DATE.getText() + this.day + CalendarUi.HOLIDAY.getText() + " " + this.staff;
        }
        return this.month + CalendarUi.MONTH.getText() + this.date + CalendarUi.DATE.getText() + this.day + " " + this.staff;
    }
}