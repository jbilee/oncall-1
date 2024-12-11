package oncall.domains.calendar;

public class CallInfo {
    private final int date;
    private final String day;
    private final boolean isHoliday;
    private String staff;

    public CallInfo(int date, String day, boolean isHoliday) {
        this.date = date;
        this.day = day;
        this.isHoliday = isHoliday;
    }

    public updateName(String name) {
        this.staff = name;
    }
    
    public isSameName(String name) {
        return this.staff.equals(name);
    }
}