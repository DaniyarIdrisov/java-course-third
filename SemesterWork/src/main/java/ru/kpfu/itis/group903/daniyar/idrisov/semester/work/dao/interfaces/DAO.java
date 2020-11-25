package ru.kpfu.itis.group903.daniyar.idrisov.semester.work.dao.interfaces;

import java.util.List;

public interface DAO<T> {

    T get(long id);

    List<T> getAll();

    void save(T x);

}
