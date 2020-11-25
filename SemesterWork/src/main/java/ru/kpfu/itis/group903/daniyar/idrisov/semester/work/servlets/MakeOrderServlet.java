package ru.kpfu.itis.group903.daniyar.idrisov.semester.work.servlets;

import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.dao.ItemDAOImpl;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.dao.OrderDAOImpl;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.models.Item;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.models.Order;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "MakeOrderServlet")
public class MakeOrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Enumeration enumeration = request.getParameterNames();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");
        while (enumeration.hasMoreElements()) {
            String name = (String)enumeration.nextElement();
            if (name.startsWith("item")) {
                Long itemId = Long.parseLong(request.getParameter(name));
                Order order = new Order();
                Item item = (new ItemDAOImpl()).get(itemId);
                OrderDAOImpl orderDAO = new OrderDAOImpl();
                order.setCustomerId(user.getId());
                order.setItemId(item.getId());
                order.setItemName(item.getLabel());
                order.setPrice(item.getPrice());
                orderDAO.save(order);
            }

        }

        response.sendRedirect("/catalog");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
