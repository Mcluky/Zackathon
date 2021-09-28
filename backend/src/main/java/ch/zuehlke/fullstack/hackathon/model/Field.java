package ch.zuehlke.fullstack.hackathon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonIgnore
    public boolean moveToIsPossible() {
        return switch (type) {
            case EMPTY, FLAG -> true;
            case BORDER, PLAYER -> false;
        };
    }

    @JsonIgnore
    public boolean isFlag() {
        return type == FieldType.FLAG;
    }

    @JsonProperty
    public FieldType type() {
        return type;
    }

    @JsonProperty
    public String name() {
        return name;
    }
}
