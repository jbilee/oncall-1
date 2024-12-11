package oncall.helpers;

import java.util.ArrayList;
import java.util.List;

public class CalendarHandler {
    private final List<String> days = List.of("월", "화", "수", "목", "금", "토", "일");

    public List<String> getDaysOfMonth(int size, String firstDay) {
        int startIndex = this.days.indexOf(firstDay);
        List<String> startingSubArray = this.days.stream().skip(startIndex).toList();
        List<String> endingSubArray = this.days.stream().limit(startIndex).toList();
        List<String> subArray = new ArrayList<>();

        while (subArray.size() < size) {
            subArray.addAll(startingSubArray);
            subArray.addAll(endingSubArray);
        }

        return subArray;
    }
}