package ru.kpfu.itis.group903.daniyar.idrisov.semester.work.dao.interfaces;

import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.models.Item;

import java.util.List;

public interface ItemDAO extends DAO<Item> {

    List<Item> getByLikePattern(String pattern);

}
