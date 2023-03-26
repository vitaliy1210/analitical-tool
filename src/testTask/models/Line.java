package testTask.models;

public abstract class Line {
    protected static final int SERVICE_AND_VARIATION_ID_INDEX = 1;
    protected static final int QUESTION_TYPE_CATEGORY_AND_SUBCATEGORY_ID_INDEX = 2;
    protected static final int RESPONSE_TYPE_INDEX = 3;
    protected static final String LINE_REGEX = " ";

    public static final String DATE_PATTERN = "dd.MM.yyyy";
    protected String[] lineDataArray;
    protected String serviceAndCategory;
    protected String questionTypeCategoryAndSubcategory;
    protected ResponseType responseType;

    public Line(String line) {
        lineDataArray = line.split(LINE_REGEX);
        serviceAndCategory = lineDataArray[SERVICE_AND_VARIATION_ID_INDEX];
        questionTypeCategoryAndSubcategory = lineDataArray[QUESTION_TYPE_CATEGORY_AND_SUBCATEGORY_ID_INDEX];
        responseType = ResponseType.valueOf(lineDataArray[RESPONSE_TYPE_INDEX]);
    }


    public String getServiceAndCategory() {
        return serviceAndCategory;
    }

    public String getQuestionTypeCategoryAndSubcategory() {
        return questionTypeCategoryAndSubcategory;
    }

    public ResponseType getResponseType() {
        return responseType;
    }
}
