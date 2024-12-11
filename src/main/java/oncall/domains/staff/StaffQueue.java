package oncall.domains.staff;

import oncall.domains.calendar.CallInfo;
import oncall.ui.constants.ErrorMessages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StaffQueue {
    private List<String> weekdayNames = new ArrayList<>();
    private List<String> holidayNames = new ArrayList<>();
    private int nextWeekdayIndex = 0;
    private int nextHolidayIndex = 0;

    public StaffQueue(String weekNames, String weekendNames) {
        this.init(weekNames, weekendNames);
    }

    private void init(String weekNames, String weekendNames) {
        List<String> weekNamez = Arrays.stream(weekNames.split(",")).toList();
        List<String> weekendNamez = Arrays.stream(weekendNames.split(",")).toList();

        weekNamez.forEach(name -> {
            if (!weekendNamez.contains(name)) {
                throw new IllegalArgumentException(ErrorMessages.INVALID.getMessage());
            }
        });

        this.weekdayNames.addAll(weekNamez);
        this.holidayNames.addAll(weekendNamez);
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
        if (type.equals("weekday")) {
            int nextIndex = this.nextWeekdayIndex;
            String nextName = this.weekdayNames.get(nextIndex);
            boolean isSameName = lastInfo.isSameName(nextName);
            while (isSameName) {
                if (nextIndex + 1 == this.weekdayNames.size()) {
                    nextIndex = 0;
                }
                nextName = this.weekdayNames.get(nextIndex);
                isSameName = lastInfo.isSameName(nextName);
            }

            if (this.nextWeekdayIndex + 1 == this.weekdayNames.size()) {
                this.nextWeekdayIndex = 0;
                return nextName;
            }
            this.nextWeekdayIndex += 1;
            return nextName;
        }

        if (type.equals("holiday")) {
            int nextIndex = this.nextHolidayIndex;
            String nextName = this.holidayNames.get(nextIndex);
            boolean isSameName = lastInfo.isSameName(nextName);
            while (isSameName) {
                if (nextIndex + 1 == this.holidayNames.size()) {
                    nextIndex = 0;
                }
                nextName = this.holidayNames.get(nextIndex);
                isSameName = lastInfo.isSameName(nextName);
            }

            if (this.nextHolidayIndex + 1 == this.holidayNames.size()) {
                this.nextHolidayIndex = 0;
                return nextName;
            }
            this.nextHolidayIndex += 1;
            return nextName;
        }
        return "";
    }
}