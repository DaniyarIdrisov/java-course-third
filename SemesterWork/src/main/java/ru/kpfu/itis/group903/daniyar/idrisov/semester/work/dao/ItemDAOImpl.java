package ru.kpfu.itis.group903.daniyar.idrisov.semester.work.dao;

import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.dao.interfaces.ItemDAO;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.models.Item;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    //language=SQL
    private static final String SQL_GET_ITEM_BY_ID = "select * from items where id = ?";
    private static final String SQL_GET_ALL_ITEMS = "select * from items";
    private static final String SQL_SAVE_ITEM = "insert into items(label, description, photo_path) values (?,?,?)";
    private static final String SQL_GET_ITEM_BY_LABEL = "select * from items where label like ?";

    @Override
    public Item get(long id) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_GET_ITEM_BY_ID);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Item(
                        rs.getLong(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getLong(5)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public List<Item> getAll() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_GET_ALL_ITEMS);
            ResultSet rs = ps.executeQuery();
            List<Item> items = new ArrayList<>();
            while (rs.next()) {
                items.add(
                        new Item(
                                rs.getLong(1), rs.getString(2), rs.getString(3),
                                rs.getString(4), rs.getLong(5)
                        ));
            }
            return items;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public void save(Item x) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    SQL_SAVE_ITEM, Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, x.getLabel());
            ps.setString(2, x.getDescription());
            ps.setString(3, x.getPhotoPath());

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
    public List<Item> getByLikePattern(String pattern) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL_GET_ITEM_BY_LABEL);
            ps.setString(1, "%" + pattern + "%");
            ResultSet rs = ps.executeQuery();
            List<Item> items = new ArrayList<>();
            while (rs.next()) {
                items.add(new Item(
                        rs.getLong(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getLong(5)
                ));
            }
            return items;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }


}
