package testTask.models;

public enum ResponseType {
    P("First answer"),
    N("Next answer");
    final String typeName;
    ResponseType(String typeName){
        this.typeName = typeName;
    }
}
