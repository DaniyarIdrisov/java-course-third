package ru.kpfu.itis.group903.daniyar.idrisov.semester.work.servlets;

import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.dao.UserDAOImpl;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.models.User;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.services.access.LoginAccess;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.utils.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    private UserDAOImpl userDAOImpl;

    @Override
    public void init() throws ServletException {
        userDAOImpl = new UserDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        System.out.println("---------------------------------");
        System.out.println("Пароль пользователя: " + password);
        String checkbox = request.getParameter("checkbox");
        password = Helper.encrypt(password);
        System.out.println("---------------------------------");
        System.out.println("Хэшированный пароль пользователя: " + password);

        if (login != null && !"".equals(login) && password != null && !"".equals(password)) {
            User user = userDAOImpl.login(login, password);
            if (user != null) {
                session.setAttribute("current_user", user);
                if (checkbox != null) {
                    response.addCookie(new Cookie("current_user", user.getLogin()));
                }
                System.out.println("---------------------------------");
                System.out.println("Пользователь вошел в аккуант!");
                response.sendRedirect("/profile");
            } else {
                System.out.println("---------------------------------");
                System.out.println("Пользователь не вошел в аккуант!");
                response.sendRedirect("/login");
            }
        }
        else {
            System.out.println("---------------------------------");
            System.out.println("Пользователь не вошел в аккуант!");
            response.sendRedirect("/login");
        }

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        (new LoginAccess()).gates(request, response);
    }

}

