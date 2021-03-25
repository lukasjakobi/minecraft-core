package io.jakobi.core.database.builder;

public enum DatabaseOperation {

    SELECT("SELECT %SEL% FROM %TAB% WHERE %WHE% LIMIT %LIM% ORDER BY %ORDC% %ORD%"),
    UPDATE(""),
    DELETE(""),
    DROP("");

    private final String format;

    DatabaseOperation(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
