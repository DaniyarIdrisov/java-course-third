package ru.kpfu.itis.group903.daniyar.idrisov.semester.work.dao.interfaces;

import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.models.User;

public interface UserDAO extends DAO<User> {

    User login(String login, String password);

    User profile(String login);

}
