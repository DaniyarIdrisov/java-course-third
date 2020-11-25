package ru.kpfu.itis.group903.daniyar.idrisov.semester.work.dao;

import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.dao.interfaces.UserDAO;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.models.User;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    //language=SQL
    private static final String SQL_GET_USER_BY_LOGIN_AND_PASSWORD = "select * from users where login = ? and password = ?";
    private static final String SQL_GET_ALL_USERS = "select * from users";
    private static final String SQL_SAVE_USER = "insert into users(login, password, last_name, first_name, email, address) values (?,?,?,?,?,?)";
    private static final String SQL_GET_USER_BY_LOGIN = "select * from users where login = ?";

    @Override
    public User get(long id) {
        try {
            Connection connection= DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_GET_USER_BY_LOGIN_AND_PASSWORD);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public List<User> getAll() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_GET_ALL_USERS);
            ResultSet rs = ps.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(new User(
                        rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public void save(User x) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SAVE_USER , Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, x.getLogin());
            ps.setString(2, x.getPassword());
            ps.setString(3, x.getLastName());
            ps.setString(4, x.getName());
            ps.setString(5, x.getEmail());
            ps.setString(6, x.getAddress());
            int updRows = ps.executeUpdate();
            if (updRows == 0) {
                //Если ничего не было изменено, значит возникла ошибка
                //Возбуждаем соответсвующее исключений
                throw new SQLException();
            }
                //Достаём созданное Id пользователя
            try (ResultSet set = ps.getGeneratedKeys();) {
                //Если id  существет, обновляем его у модели.
                if (set.next()) {
                    x.setId(set.getLong(1));
                } else {
                    //Модель сохранилась, но не удаётся получить сгенерированный id
                    //Возбуждаем соответвующее исключение
                    throw new SQLException();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public User login(String login, String password) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_GET_USER_BY_LOGIN_AND_PASSWORD);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public User profile(String login) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_GET_USER_BY_LOGIN);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

}
