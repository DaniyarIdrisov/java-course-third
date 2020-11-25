package ru.kpfu.itis.group903.daniyar.idrisov.semester.work.services.access;

import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.dao.UserDAOImpl;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.models.User;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.services.interfaces.AccessInterface;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.utils.Helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginAccess implements AccessInterface {

    @Override
    public  void gates(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");
        if (user != null)  {
            System.out.println("---------------------------------");
            System.out.println("Пользователь авторизован! LoginServlet");
            response.sendRedirect("/profile");
            return;
        }

        if (Helper.checkCookie(request, (new UserDAOImpl()))) {
            System.out.println("---------------------------------");
            System.out.println("Пользователь авторизован! LoginServlet");
            response.sendRedirect("/profile");
        } else {
            System.out.println("---------------------------------");
            System.out.println("Пользователь не авторизован! LoginServlet");
            Map<String, Object> root = new HashMap<>();
            Helper.render(request, response, "login.ftl", root);
        }

    }

}
