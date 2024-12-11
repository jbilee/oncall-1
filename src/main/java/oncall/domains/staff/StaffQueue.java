package oncall.domains.staff;

public class StaffQueue {
    private List<String> weekdayNames = new ArrayList<>();
    private List<String> holidayNames = new ArrayList<>();
    private int nextWeekdayIndex = 0;
    private int nextHolidayIndex = 0;

    public StaffQueue(String weekNames, String weekendNames) {
        this.init(weekNames, weekendNames);
    }

    private void init(String weekNames, String weekendNames) {
        List<String> weekNamez = weekNames.split(",").toList();
        List<String> weekendNamez = weekendNames.split(",").toList();

        weekNamez.forEach(name -> {
            if (!weekendNamez.contains(name)) {
                throw new IllegalArgumentException(ErrorMessages.INVALID.getMessage());
            }
        })

        this.weekdayNames.add(weekNamez);
        this.holidayNames.add(weekendNamez);
    }
}