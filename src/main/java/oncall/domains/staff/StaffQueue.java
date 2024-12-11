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

    public String getAssignedStaff(boolean isHoliday, CallInfo lastInfo) {
        String assignedStaff;
        if (isHoliday) {
            assignedStaff = this.getNameAtNextIndex(lastInfo, "holiday");
            return assignedStaff;
        }
        assignedStaff = this.getNameAtNextIndex(lastInfo, "weekday");
        return assignedStaff;
    }

    private String getNameAtNextIndex(CallInfo lastInfo, String type) {
        if (type == "weekday") {
            String nextName = this.weekdayNames[this.nextWeekdayIndex];
            if (lastInfo.isSameName(nextName)) {
                return this.weekdayNames[this.nextWeekdayIndex + 1];
            }
            if (this.nextWeekdayIndex + 1 == this.weekdayNames.size()) {
                this.nextWeekdayIndex = 0;
                return nextName;
            }
            this.nextWeekdayIndex += 1;
            return nextName;
        }
        if (type == "holiday") {
            String nextName = this.holidayNames[this.nextHolidayIndex];
            if (lastInfo.isSameName(nextName)) {
                return this.holidayNames[this.nextHolidayIndex + 1];
            }
            if (this.nextHolidayIndex + 1 == this.holidayNames.size()) {
                this.nextHolidayIndex = 0;
                return nextName;
            }
            this.nextHolidayIndex += 1;
            return nextName;
        }
    }
}