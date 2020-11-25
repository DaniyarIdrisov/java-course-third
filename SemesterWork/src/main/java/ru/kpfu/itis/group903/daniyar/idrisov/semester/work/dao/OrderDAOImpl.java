package ru.kpfu.itis.group903.daniyar.idrisov.semester.work.dao;

import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.dao.interfaces.OrderDAO;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.models.Order;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    //language=SQL
    private static final String SQL_ORDER_BY_ID = "select * from orders where id = ?";
    private static final String SQL_ORDER_BY_CUSTOMER_ID = "select * from orders where customer_id = ?";
    private static final String SQL_SAVE_ORDER = "insert into orders(customer_id, item_id, item_name, price) values (?, ?, ?, ?)";
    private static final String SQL_GET_ALL_ORDERS = "select * from orders";

    @Override
    public Order get(long id) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_ORDER_BY_ID);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Order(
                        rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getString(4), rs.getLong(5)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public List<Order> getAll() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_GET_ALL_ORDERS);
            ResultSet rs = ps.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (rs.next()) {
                orders.add(new Order(rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getString(4), rs.getLong(5)));
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public void save(Order x) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_SAVE_ORDER, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, x.getCustomerId());
            ps.setLong(2, x.getItemId());
            ps.setString(3, x.getItemName());
            ps.setLong(4, x.getPrice());

            int updRows = ps.executeUpdate();
            if (updRows == 0) {
                throw new SQLException();
            }
            try (ResultSet set = ps.getGeneratedKeys()) {
                if (set.next()) {
                    x.setId(set.getLong(1));
                } else {
                    throw new SQLException();
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Order> getByCustomer(long id) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_ORDER_BY_CUSTOMER_ID);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (rs.next()) {
                orders.add(
                        new Order(
                                rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getString(4), rs.getLong(5)
                        ));
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

}
