package io.jakobi.core.database;

import io.jakobi.core.database.builder.DatabaseOperation;
import io.jakobi.core.database.builder.DatabaseOrder;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseQueryBuilder {

    private static final int MAX_QUERY_RESULT_SIZE_LIMIT = 100;
    private static final DatabaseOrder DEFAULT_DATABASE_ORDER = DatabaseOrder.ASC;
    private static final String DEFAULT_SELECT = "*";
    private static final String DEFAULT_DATABASE_ORDER_COLUMN = "id";

    private static Connection connection;

    private String table, where;
    private List<String> select = Collections.singletonList(DEFAULT_SELECT);
    private int limit = MAX_QUERY_RESULT_SIZE_LIMIT;
    private DatabaseOrder databaseOrder = DEFAULT_DATABASE_ORDER;
    private String databaseOrderColumn = DEFAULT_DATABASE_ORDER_COLUMN;

    public DatabaseQueryBuilder() {

    }

    public DatabaseQueryBuilder select(String... select) {
        this.select = Arrays.asList(select);

        return this;
    }

    public DatabaseQueryBuilder from(String table) {
        this.table = table;

        return this;
    }

    public DatabaseQueryBuilder where(String format, String... values) {
        this.where = String.format(format, Arrays.stream(values));

        return this;
    }

    public DatabaseQueryBuilder orderBy(String column, DatabaseOrder databaseOrder) {
        this.databaseOrder = databaseOrder;
        this.databaseOrderColumn = column;

        return this;
    }

    public DatabaseQueryBuilder limit(int limit) {
        this.limit = limit;

        return this;
    }

    public String toString() {
        String format = DatabaseOperation.SELECT.getFormat();

        format = format.replace("%SEL%", this.select.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("-", "{", "}")));
        format = format.replace("%TAB%", this.table);
        format = format.replace("%WHE%", this.where);
        format = format.replace("%LIM%", String.valueOf(this.limit));
        format = format.replace("%ORD%", this.databaseOrder.getName());
        format = format.replace("%ORDC%", this.databaseOrderColumn);

        return format;
    }
}
