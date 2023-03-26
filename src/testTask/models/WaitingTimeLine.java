package testTask.models;

import testTask.models.Line;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WaitingTimeLine extends Line {
    private static final int DATE_INDEX = 4;
    private static final int TIME_INDEX = 5;
    private final LocalDate date;
    private final int waitingTimeInMinutes;

    public WaitingTimeLine(String line) {
        super(line);
        date = LocalDate.parse(lineDataArray[DATE_INDEX], DateTimeFormatter.ofPattern(DATE_PATTERN));
        waitingTimeInMinutes = Integer.parseInt(lineDataArray[TIME_INDEX]);
    }

    public LocalDate getDate() {
        return date;
    }

    public int getWaitingTimeInMinutes() {
        return waitingTimeInMinutes;
    }
}
