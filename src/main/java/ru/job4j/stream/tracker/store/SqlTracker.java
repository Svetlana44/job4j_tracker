/* Во-первых, метод update() возвращает boolean, это нужно для того, чтобы узнать произошло обновление или нет.
 Во-вторых, чтобы узнать произошло само обновление мы используем метод executeUpdate(),
  если это метод возвращает 0, то значит оно не произошло, поэтому мы проверяем, что результат больше 0.

Также важно запомнить, что методы execute(), executeUpdate() и executeQuery() интерфейса PreparedStatement не принимают никаких аргументов,
 в отличие от одноименных методов Statement. Они выполняют указанный при создании объекта SQL-запрос с подставленными аргументами. */

package ru.job4j.stream.tracker.store;

import ru.job4j.stream.tracker.Store;
import ru.job4j.stream.tracker.Item;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlTracker implements Store {

    private Connection cn;

    public SqlTracker() {
        init();
    }

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    private void init() {
        try (InputStream in = new FileInputStream("db/liquibase.properties")) {
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

    public void statementExecute(String statementStr) {
        try (PreparedStatement preparedStatement = cn.prepareStatement(statementStr)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

/*    public void createTable() {

        String creatTableSql = "CREATE TABLE IF NOT EXISTS items("
                + "id serial primary key,"
                + "name text,"
                + "created timestamp"
                + ");";
        statementExecute(creatTableSql);

    }  */

    private Item newItem(ResultSet resultSet) throws SQLException {
        Timestamp timestamp = resultSet.getTimestamp(3);

        return new Item(resultSet.getInt("id"),
                resultSet.getString("name"),
                timestamp.toLocalDateTime());
    }

    @Override
    public void close() throws SQLException {
        if (cn != null) {
            cn.close();
        }
    }

    /* Метод добавления должен устанавливать Item id, сгенерированное БД
    Время создания заявки не должно изменяться*/
    @Override
    public Item add(Item item) {
        String addItem = "INSERT INTO items(name,created) values(?,?);";
        try (PreparedStatement preparedStatement = cn.prepareStatement(addItem, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, item.getName());

            LocalDateTime currentDateTime = LocalDateTime.now();
            /* Преобразует в java.sql.Timestamp */
            Timestamp timestamp = Timestamp.valueOf(currentDateTime);
            preparedStatement.setTimestamp(2, timestamp);
            preparedStatement.execute();
            try (ResultSet key = preparedStatement.getGeneratedKeys()) {
                if (key.next()) {
                    item.setId(key.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        String updateStr = "UPDATE items SET name = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = cn.prepareStatement(updateStr)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setInt(2, id);
            if (preparedStatement.executeUpdate() != 1) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void delete(int id) {
        String delStr = "DELETE FROM items WHERE id=?;";
        try (PreparedStatement preparedStatement = cn.prepareStatement(delStr)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement preparedStatement = cn.prepareStatement(
                "SELECT * FROM items;")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    items.add(newItem(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement preparedStatement = cn.prepareStatement(
                "SELECT * FROM items WHERE name=?;"
        )) {
            preparedStatement.setString(1, key);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    items.add(newItem(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Item findById(int id) {
        Item item = new Item();
        try (PreparedStatement preparedStatement = cn.prepareStatement(
                "SELECT * FROM items WHERE id=?;"
        )) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    item = newItem(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;

    }

/* запуск из   StartUI производится.
  public static void main(String[] args) {


        SqlTracker sqlTracker = new SqlTracker();
        sqlTracker.createTable();

        Output output = new ConsoleOutput();

        Input input = new ValidateInput(
                output, new ConsoleInput()
        );
        try (Store tracker = new SqlTracker()) {
            List<UserAction> actions = List.of(
                    new CreateAction(output),
                    new ReplaceAction(output),
                    new DeleteAction(output),
                    /*                    new FindAllAction(output),*/
/*                    new ShowAllItemAction(output),
                    new FindItemByIdAction(output),
                    new FindItemsByNameAction(output),
                    new ExitAction(output)
            );
            new StartUI(output).init(input, tracker, actions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}