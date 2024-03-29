package ru.job4j.tracker.store;

import org.junit.jupiter.api.*;
import ru.job4j.stream.tracker.Item;
import ru.job4j.stream.tracker.store.SqlTracker;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class SqlTrackerTest {

    private static Connection connection;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = new FileInputStream("db/liquibase_test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @BeforeEach
    public void wipeTableBefor() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @AfterEach
    public void wipeTableAfter() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        LocalDateTime created = LocalDateTime.now();
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }

    /*my tests*/

    @Test
    public void close() throws SQLException {
        initConnection();
        assertThat(connection.isClosed()).isFalse();
        connection.close();
        assertThat(connection.isClosed()).isTrue();
        initConnection();
    }

    @Test
    public void add() {
        Item item = new Item("item33");
        SqlTracker sqlTracker = new SqlTracker(connection);
        Item actual = sqlTracker.add(item);

        assertThat(actual).isEqualTo(item);
    }

    @Test
    public void replace() {
        Item item = new Item("item333");
        SqlTracker sqlTracker = new SqlTracker(connection);
        Item addedItem = sqlTracker.add(item);
        int id = addedItem.getId();

        boolean actual = sqlTracker.replace(id, new Item("replacesItem"))
                && (sqlTracker.findById(id).getName().equals("replacesItem"));
        assertThat(actual).isTrue();
    }

    @Test
    public void delete() {
        Item item = new Item("itemDel333");
        SqlTracker sqlTracker = new SqlTracker(connection);
        sqlTracker.add(item);
        sqlTracker.delete(item.getId());
        int actualId = sqlTracker.findById(item.getId()).getId();
        assertThat(actualId).isNotEqualTo(item.getId()).isEqualTo(0);
    }

    @Test
    public void findAll() {
        try {
            wipeTableAfter();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Item item1 = new Item("insertedItem1");
        Item item2 = new Item("insertedItem2");
        SqlTracker sqlTracker = new SqlTracker(connection);
        Item addedItem1 = sqlTracker.add(item1);
        Item addedItem2 = sqlTracker.add(item2);

        boolean result = true;
        for (Item item : sqlTracker.findAll()) {
            if (!item.equals(addedItem1)
                    && !item.equals(addedItem2)) {
                result = false;
            }
        }
        try {
            wipeTableAfter();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertThat(result).isTrue();
    }

    @Test
    public void findByName() {
        String delStr = "DELETE FROM items;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(delStr)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Item item1 = new Item("findedItem1");
        Item item2 = new Item("findedItem1");
        SqlTracker sqlTracker = new SqlTracker(connection);
        Item addedItem1 = sqlTracker.add(item1);
        Item addedItem2 = sqlTracker.add(item2);

        boolean result = true;
        for (Item item : sqlTracker.findByName("findedItem1")) {
            if (!item.equals(addedItem1)
                    && !item.equals(addedItem2)) {
                result = false;
            }
        }
        assertThat(result).isTrue();
    }

    @Test
    public void findById() {
        String delStr = "DELETE FROM items;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(delStr)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Item item1 = new Item("findedIdItem1");
        SqlTracker sqlTracker = new SqlTracker(connection);
        Item addedItem1 = sqlTracker.add(item1);

        assertThat(sqlTracker.findById(addedItem1.getId())).isEqualTo(addedItem1);
    }
}