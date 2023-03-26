package testTask;

import testTask.models.QueryLine;
import testTask.models.WaitingTimeLine;

import java.util.ArrayList;
import java.util.List;

public class AnalyticalTool {

    public static final char WAITING_TIME_LINE_IDENTIFIER = 'C';
    public static final char QUERY_LINE_IDENTIFIER = 'D';
    private static final int FIRST_DATA_INDEX = 1;
    private static final String LINES_SEPARATOR = "\n";
    public static final String SPECIAL_QUERY_VALUE = "*";
    public static final String DEFAULT_RESULT = "-";
    private final List<QueryLine> queries;
    private final List<WaitingTimeLine> waitingTimeLines;

    public AnalyticalTool() {
        queries = new ArrayList<>();
        waitingTimeLines = new ArrayList<>();
    }

    public String evaluateOuterData(String data) {
        initializeData(data.split(LINES_SEPARATOR));
        int timeSum = 0;
        int timeCounter = 0;
        StringBuilder outer = new StringBuilder();
        for (QueryLine queryLine: queries) {
            for (WaitingTimeLine waitingTimeLine: waitingTimeLines) {
                if (isLinesMatches(queryLine, waitingTimeLine)) {
                    timeSum += waitingTimeLine.getWaitingTimeInMinutes();
                    timeCounter++;
                }
            }
            outer.append(timeSum == 0 ? DEFAULT_RESULT : String.valueOf(timeSum / timeCounter))
                    .append("\n");
            timeSum = 0;
            timeCounter = 0;
        }
        return outer.toString();
    }

    private void initializeData(String[] lines) {
        for (int i = FIRST_DATA_INDEX; i < lines.length; i++) {
            switch (lines[i].charAt(0)) {
                case WAITING_TIME_LINE_IDENTIFIER:
                    waitingTimeLines.add(new WaitingTimeLine(lines[i]));
                    break;
                case QUERY_LINE_IDENTIFIER:
                    queries.add(new QueryLine(lines[i]));
                    break;
                default:
            }
        }
    }

    private boolean isLinesMatches(QueryLine queryLine, WaitingTimeLine waitingTimeLine) {
        boolean servicesMatcher = isServiceOrTypeMatches(queryLine.getServiceAndCategory(),
                waitingTimeLine.getServiceAndCategory());
        boolean questionsMatcher = isServiceOrTypeMatches(queryLine.getQuestionTypeCategoryAndSubcategory(),
                waitingTimeLine.getQuestionTypeCategoryAndSubcategory());
        boolean responseTypeMatcher = queryLine.getResponseType() == waitingTimeLine.getResponseType();
        boolean datesMatcher = (queryLine.getToDate() == null
                && waitingTimeLine.getDate().isAfter(queryLine.getFromDate()))
                || (waitingTimeLine.getDate().isAfter(queryLine.getFromDate())
                && waitingTimeLine.getDate().isBefore(queryLine.getToDate()));
        return servicesMatcher && questionsMatcher && responseTypeMatcher && datesMatcher;
    }

    private boolean isServiceOrTypeMatches(String queryServiceOrType, String waitingServiceOrType) {
        return queryServiceOrType.equals(SPECIAL_QUERY_VALUE)
                || waitingServiceOrType.startsWith(queryServiceOrType);
    }
}
