package ru.kpfu.itis.group903.daniyar.idrisov.semester.work.services.access;

import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.dao.ItemDAOImpl;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.models.Item;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.models.User;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.services.interfaces.AccessInterface;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.utils.Helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogAccess implements AccessInterface {

    @Override
    public void gates(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();

        List<Item> cart = (List<Item>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }

        Map<String, Object> root = new HashMap<>();
        List<Item> items = (new ItemDAOImpl()).getAll();
        root.put("items", items);

        Helper.render(request, response, "catalog.ftl", root);

    }
}
