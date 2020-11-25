package ru.kpfu.itis.group903.daniyar.idrisov.semester.work.services.access;

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

public class CartAccess implements AccessInterface {

    @Override
    public void gates(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");
        if (user != null) {
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            Map<String, Object> root = new HashMap<>();
            root.put("items", cart);
            List<Long> numbers = new ArrayList<>();
            long sum = 0;

            if (cart != null) {
                for (Item item : cart) {
                    System.out.println("Id товаров:" + item.getId());
                    numbers.add(item.getId());
                    sum = sum + item.getPrice();
                }
            }

            System.out.println("Сумма заказа:" + sum);


            root.put("numbers", numbers);
            root.put("sum", sum);
            System.out.println("---------------------------------");
            System.out.println("Пользователь авторизован! CartServlet");
            Helper.render(request, response, "cart.ftl", root);
        }
        else {
            System.out.println("---------------------------------");
            System.out.println("Пользователь не авторизован! CartServlet");
            response.sendRedirect("/login");
        }

    }

}
