package ru.job4j.tracker.store;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class SqlTrackerTest {
    private Connection cn;

    public void init() {
        try (
                InputStream in = SqlTracker.class.getClassLoader()
                        .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void statementExecute() {
        init();
        try (PreparedStatement preparedStatement = cn.prepareStatement("SELECT * FROM test;")) {
            boolean actual = preparedStatement.execute();
            Assertions.assertThat(actual).isTrue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void close() throws SQLException {
        init();
        Assertions.assertThat(cn.isClosed()).isFalse();
        cn.close();
        Assertions.assertThat(cn.isClosed()).isTrue();
    }

    @Test
    public void add() {
        init();
        Item item = new Item("item33");
        SqlTracker sqlTracker = new SqlTracker();
        Item actual = sqlTracker.add(item);

        Assertions.assertThat(actual).isEqualTo(item);
    }

    @Test
    public void replace() {
        init();
        Item item = new Item("item333");
        SqlTracker sqlTracker = new SqlTracker();
        Item addedItem = sqlTracker.add(item);
        int id = addedItem.getId();

        boolean actual = sqlTracker.replace(id, new Item("replacesItem"))
                && (sqlTracker.findById(id).getName().equals("replacesItem"));
        Assertions.assertThat(actual).isTrue();
    }

    @Test
    public void delete() {
        init();
        Item item = new Item("itemDel333");
        SqlTracker sqlTracker = new SqlTracker();
        Item addedItem = sqlTracker.add(item);
        boolean actual = sqlTracker.findByName("itemDel333").equals(item);
        Assertions.assertThat(actual).isFalse();
    }

    @Test
    public void findAll() {
        init();
        String delStr = "DELETE FROM test;";
        try (PreparedStatement preparedStatement = cn.prepareStatement(delStr)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Item item1 = new Item("insertedItem1");
        Item item2 = new Item("insertedItem2");
        SqlTracker sqlTracker = new SqlTracker();
        Item addedItem1 = sqlTracker.add(item1);
        Item addedItem2 = sqlTracker.add(item2);

        boolean result = true;
        for (Item item : sqlTracker.findAll()) {
            if (!(item.equals(addedItem1) || item.equals(addedItem2))) {
                result = false;
            }
        }
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void findByName() {
        init();
        String delStr = "DELETE FROM test;";
        try (PreparedStatement preparedStatement = cn.prepareStatement(delStr)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Item item1 = new Item("findedItem1");
        Item item2 = new Item("findedItem1");
        SqlTracker sqlTracker = new SqlTracker();
        Item addedItem1 = sqlTracker.add(item1);
        Item addedItem2 = sqlTracker.add(item2);

        boolean result = true;
        for (Item item : sqlTracker.findByName("findedItem1")) {
            if (!(item.equals(addedItem1) || item.equals(addedItem2))) {
                result = false;
            }
        }
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void findById() {
        init();
        String delStr = "DELETE FROM test;";
        try (PreparedStatement preparedStatement = cn.prepareStatement(delStr)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Item item1 = new Item("findedIdItem1");
        SqlTracker sqlTracker = new SqlTracker();
        Item addedItem1 = sqlTracker.add(item1);

        Assertions.assertThat(sqlTracker.findById(addedItem1.getId())).isEqualTo(addedItem1);
    }
}