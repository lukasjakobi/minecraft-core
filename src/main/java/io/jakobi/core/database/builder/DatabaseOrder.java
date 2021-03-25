package io.jakobi.core.database.builder;

public enum DatabaseOrder {

    ASC("ASC"),
    DESC("DESC");

    private final String name;

    DatabaseOrder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
