package ru.job4j.tracker.store;

import ru.job4j.tracker.*;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
        try (Statement statement = cn.createStatement()) {
            statement.execute(statementStr);
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
        String addItem = "INSERT INTO test(id,name) values(DEFAULT," + item.getName() + "); COMMIT;";
        statementExecute(addItem);
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        return false;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Item> findAll() {
        return null;
    }

    @Override
    public List<Item> findByName(String key) {
        return null;
    }

    @Override
    public Item findById(int id) {
        return null;
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