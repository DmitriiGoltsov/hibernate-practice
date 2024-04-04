package org.goltsov;

import entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class HibernateRunnerTest {

    @Test
    void checkReflectionAPI() throws SQLException, IllegalAccessException {

        User user = User.builder()
                .build();

        String expected = """
                insert
                into
                public.users
                (age, birth_day, name, surname, username)
                values
                (?, ?, ?, ?, ?)
                """;

        String sql = """
                insert 
                into 
                %s
                (%s)
                values
                (%s)
                """;

        String tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
                .map(tableAnnotation -> tableAnnotation.schema() + "." + tableAnnotation.name())
                .orElse(user.getClass().getName());

        Field[] declaredFields = user.getClass().getDeclaredFields();

        String columnName = Arrays.stream(declaredFields)
                .sorted(Comparator.comparing(Field::getName))
                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
                        .map(Column::name)
                        .orElse(field.getName())) // в реальных приложениях всё чуть сложнее. Требуется учитывать стратегии наименования.
                .collect(Collectors.joining(", "));

        String columnValues = Arrays.stream(declaredFields)
                .map(field -> "?")
                .collect(Collectors.joining(", "));

        String formattedSQL = sql.formatted(tableName, columnName, columnValues);

        assertThat(formattedSQL).isEqualTo(expected);

        Connection connection = null; // TO DO
        PreparedStatement preparedStatement = connection.prepareStatement(formattedSQL);

        int index = 1;
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            preparedStatement.setObject(index, declaredField.get(user));
            index++;
        }

        System.out.println("PreparedStatement " + preparedStatement);
    }

}