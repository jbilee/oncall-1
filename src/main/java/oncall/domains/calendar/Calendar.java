package oncall.domains.calendar;

import oncall.domains.staff.StaffQueue;
import oncall.helpers.CalendarHandler;
import oncall.ui.OutputView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Calendar {
    private List<CallInfo> dates = new ArrayList<>();
    private Map<String, List<Integer>> holidays = Map.of("1", List.of(1), "3", List.of(1), "5", List.of(5), "6", List.of(6), "8", List.of(15), "10", List.of(3, 9), "12", List.of(25));

    public Calendar(String input) {
        this.init(input);
    }

    private void init(String input) {
        CalendarHandler calendarHandler = new CalendarHandler();
        String[] values = input.split(",");
        int month = Integer.parseInt(values[0]);
        String firstDay = values[1];
        LocalDate localDate = LocalDate.of(2024, month, 1);
        int daysInMonth = localDate.lengthOfMonth();
        List<String> daysOfMonth = calendarHandler.getDaysOfMonth(daysInMonth, firstDay);
        for (int i = 1; i <= daysInMonth; i++) {
            boolean isHoliday = false;
            String day = daysOfMonth.remove(0);
            if (i == 1) {
                if (isDateHoliday(month, i) || isDayHoliday(day)) {
                    isHoliday = true;
                }
                CallInfo newCallInfo = new CallInfo(month, i, day, isHoliday);
                this.dates.add(newCallInfo);
                continue;
            }

            if (isDateHoliday(month, i) || isDayHoliday(day)) {
                isHoliday = true;
            }
            CallInfo newCallInfo = new CallInfo(month, i, day, isHoliday);
            this.dates.add(newCallInfo);
        }
    }

    private boolean isDateHoliday(int month, int date) {
        List<Integer> holidayDates = this.holidays.get(String.valueOf(month));
        return holidayDates.contains(date);
    }

    private boolean isDayHoliday(String day) {
        return day.equals("토") || day.equals("일");
    }

    public void assignStaff(StaffQueue staffQueue) {
        for (int i = 0; i < this.dates.size(); i++) {
            CallInfo curInfo = this.dates.get(i);
            CallInfo prevInfo;
            if (i == 0) {
                prevInfo = new CallInfo(0, 0, "", false);
                prevInfo.updateName("null");
                String assignedName = staffQueue.getAssignedStaff(curInfo.isHoliday, prevInfo);
                curInfo.updateName(assignedName);
                continue;
            }
            prevInfo = this.dates.get(i - 1);
            String assignedName = staffQueue.getAssignedStaff(curInfo.isHoliday, prevInfo);
            curInfo.updateName(assignedName);
        }
    }

    public void printResults(OutputView outputView) {
        this.dates.forEach(date -> {
            String info = date.getPrintableInfo();
            outputView.printInfo(info);
        });
    }
}