package oncall.domains.calendar;

public class CallInfo {
    private final int date;
    private final String day;
    private final boolean isHoliday;

    public CallInfo(int date, String day, boolean isHoliday) {
        this.date = date;
        this.day = day;
        this.isHoliday = isHoliday;
    }
}