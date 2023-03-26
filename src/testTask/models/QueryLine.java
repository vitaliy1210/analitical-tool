package testTask.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class QueryLine extends Line {
    private static final int DATES_INDEX = 4;
    public static final String DATES_SEPARATOR = "-";
    private LocalDate fromDate;
    private LocalDate toDate;

    public QueryLine(String line) {
        super(line);
        parseDates(lineDataArray[DATES_INDEX]);
    }

    private void parseDates(String dates) {
        String[] splitDates = dates.split(DATES_SEPARATOR);
        int fromIndex = 0, toIndex = 1;
        fromDate = LocalDate.parse(splitDates[fromIndex], DateTimeFormatter.ofPattern(DATE_PATTERN));
        if (splitDates.length == 1) {
            return;
        }
        toDate = LocalDate.parse(splitDates[toIndex], DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }
}
