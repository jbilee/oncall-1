package oncall.helpers;

public class CalendarHandler {
    private final static List<String> days = List.of("월", "화", "수", "목", "금", "토", "일");

    public List<String> getDaysOfMonth(int size, String firstDay) {
        int startIndex = this.days.indexOf(firstDay);
        List<String> startingSubArray = this.days.stream().skip(startIndex).toList();
        List<String> endingSubArray = this.days.stream().limit(startIndex).toList();
        List<String> subArray = new ArrayList<>();
        
        while (subArray.size() < size) {
            subArray.add(startingSubArray);
            subArray.add(endingSubArray);
        }
        
        return subArray;
    }
}