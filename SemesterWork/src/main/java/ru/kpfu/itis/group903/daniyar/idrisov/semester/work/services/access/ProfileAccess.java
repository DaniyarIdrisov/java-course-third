package ru.kpfu.itis.group903.daniyar.idrisov.semester.work.services.access;

import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.dao.OrderDAOImpl;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.models.Order;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.models.User;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.services.interfaces.AccessInterface;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.utils.Helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileAccess implements AccessInterface {

    @Override
    public void gates(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");
        if (user != null) {
            Map<String, Object> root = new HashMap<>();
            root.put("user", user);
            List<Order> orders = (new OrderDAOImpl()).getByCustomer(user.getId());
            root.put("orders", orders);
            System.out.println("---------------------------------");
            System.out.println("Пользователь авторизован! ProfileServlet");
            Helper.render(request, response, "profile.ftl", root);
        } else {
            System.out.println("---------------------------------");
            System.out.println("Пользователь не авторизован! ProfileServlet");
            response.sendRedirect("/login");
        }

    }

}
