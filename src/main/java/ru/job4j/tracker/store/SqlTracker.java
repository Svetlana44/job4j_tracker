/* Во-первых, метод update() возвращает boolean, это нужно для того, чтобы узнать произошло обновление или нет.
 Во-вторых, чтобы узнать произошло само обновление мы используем метод executeUpdate(),
  если это метод возвращает 0, то значит оно не произошло, поэтому мы проверяем, что результат больше 0.

Также важно запомнить, что методы execute(), executeUpdate() и executeQuery() интерфейса PreparedStatement не принимают никаких аргументов,
 в отличие от одноименных методов Statement. Они выполняют указанный при создании объекта SQL-запрос с подставленными аргументами. */

package ru.job4j.tracker.store;

import ru.job4j.tracker.*;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {

    private Connection cn;

    public SqlTracker() {
        init();
    }

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    private void init() {
        try (InputStream in = SqlTracker.class.getClassLoader()
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

    public void statementExecute(String statementStr) {
        try (PreparedStatement preparedStatement = cn.prepareStatement(statementStr)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {

        String creatTableSql = "CREATE TABLE IF NOT EXISTS test("
                + "id serial primary key,"
                + "name varchar(20)"
                + "); COMMIT;";
        statementExecute(creatTableSql);

    }

    @Override
    public void close() throws SQLException {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        String addItem = "INSERT INTO test(name) values(?); COMMIT;";
        try (PreparedStatement preparedStatement = cn.prepareStatement(addItem)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        String updateStr = "UPDATE test SET name = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = cn.prepareStatement(updateStr)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setInt(2, id);

            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void delete(int id) {
        String delStr = "DELETE FROM test WHERE id=?;";
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
                "SELECT * FROM test;")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    items.add(new Item(resultSet.getInt("id"),
                            resultSet.getString("name")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
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
                "SELECT * FROM test WHERE name=?;"
        )) {
            preparedStatement.setString(1, key);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    items.add(new Item(resultSet.getInt("id"), resultSet.getString("name")));
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
                "SELECT * FROM test WHERE id=?;"
        )) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    item = new Item(resultSet.getInt("id"), resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;

    }

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
                    new ShowAllItemAction(output),
                    new FindItemByIdAction(output),
                    new FindItemsByNameAction(output),
                    new ExitAction(output)
            );
            new StartUI(output).init(input, tracker, actions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}