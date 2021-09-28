package ch.zuehlke.fullstack.hackathon.model;

public final class Field {
    private final FieldType type;
    private final String name;

    public Field(FieldType type) {
        this.type = type;
        this.name = "";
    }

    public Field(FieldType type, String name) {
        this.type = type;
        this.name = name;
    }

    public boolean moveToIsPossible() {
        return switch (type) {
            case EMPTY, FLAG -> true;
            case BORDER, PLAYER -> false;
        };
    }

    public boolean isFlag() {
        return type == FieldType.FLAG;
    }

    public FieldType type() {
        return type;
    }

    public String name() {
        return name;
    }
}
