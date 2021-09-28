package ch.zuehlke.fullstack.hackathon.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Field) obj;
        return Objects.equals(this.type, that.type) &&
                Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name);
    }

    @Override
    public String toString() {
        return "Field[" +
                "type=" + type + ", " +
                "name=" + name + ']';
    }

}
