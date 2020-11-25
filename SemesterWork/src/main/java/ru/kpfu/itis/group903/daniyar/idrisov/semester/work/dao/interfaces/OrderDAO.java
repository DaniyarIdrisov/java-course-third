package ru.kpfu.itis.group903.daniyar.idrisov.semester.work.dao.interfaces;

import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.models.Order;

import java.util.List;

public interface OrderDAO extends DAO<Order> {

    List<Order> getByCustomer(long id);

}
