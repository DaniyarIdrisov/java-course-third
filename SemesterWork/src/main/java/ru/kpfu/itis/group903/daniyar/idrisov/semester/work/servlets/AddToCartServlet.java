package ru.kpfu.itis.group903.daniyar.idrisov.semester.work.servlets;

import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.dao.ItemDAOImpl;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.models.Item;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddToCart")
public class AddToCartServlet extends HttpServlet {

    private ItemDAOImpl itemDAOImpl;

    @Override
    public void init() {

        itemDAOImpl = new ItemDAOImpl();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");
        if (user == null) {
            System.out.println("---------------------------------");
            System.out.println("Пользователь не авторизован!");
            response.sendRedirect("/login");
            return;
        }

        List<Item> cart = (List<Item>) session.getAttribute("cart");
        long id = Long.parseLong(request.getParameter("id"));
        System.out.println("---------------------------------");
        System.out.println("Id товара:" + id);

        for (Item i : cart) {
            if (i.getId() == id) {
                System.out.println("---------------------------------");
                System.out.println("Товар уже есть в корзине!");
                response.sendRedirect("/catalog");
                return;
            }
        }
        System.out.println("---------------------------------");
        System.out.println("Товар успешно добавлен в корзину!");
        cart.add(itemDAOImpl.get(id));
        session.setAttribute("cart", cart);
        response.sendRedirect("/catalog");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
