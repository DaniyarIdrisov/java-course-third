package ru.kpfu.itis.group903.daniyar.idrisov.semester.work.servlets;

import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.dao.UserDAOImpl;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.models.User;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.services.access.RegistrationAccess;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.utils.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    private UserDAOImpl userDAOImpl;

    @Override
    public void init() throws ServletException {

        userDAOImpl = new UserDAOImpl();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String lastName = request.getParameter("lastName");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String check = request.getParameter("check");

        System.out.println("---------------------------------");
        System.out.println("Данные пользователя:");
        System.out.println("Логин: " + login);
        System.out.println("Пароль: " + password);
        System.out.println("Фамилия: " + lastName);
        System.out.println("Имя: " + name);
        System.out.println("Email: " + email);
        System.out.println("Домашний адрес: " + address);

        List<User> users = userDAOImpl.getAll();
        for (User user: users) {
            if (login.equals(user.getLogin())) {
                System.out.println("---------------------------------");
                System.out.println("Пользователь c таким логином уже сущетсвует!");
                response.sendRedirect("/registration");
                return;
            }
        }
        if (check.equals("yes")) {
            if (login != null && !"".equals(login) && password != null && !"".equals(password) && lastName != null && !"".equals(lastName) && name != null && !"".equals(name) && email != null && !"".equalsIgnoreCase(email) && address != null && !"".equalsIgnoreCase(address)) {
                User user = new User();
                password = Helper.encrypt(password);
                System.out.println("---------------------------------");
                System.out.println("Хэшированный пароль: " + password);
                user.setPassword(password);
                user.setLogin(login);
                user.setLastName(lastName);
                user.setName(name);
                user.setEmail(email);
                user.setAddress(address);

                userDAOImpl.save(user);
                System.out.println("---------------------------------");
                System.out.println("Пользователь успешно добавлен!");

                response.addCookie(new Cookie("current_user", user.getLogin()));
                session.setAttribute("current_user", user);
                response.sendRedirect("/profile");
            }
            else {
                System.out.println("---------------------------------");
                System.out.println("Пользователь не добавлен!");
                response.sendRedirect("/registration");
            }
        }
        else {
            System.out.println("---------------------------------");
            System.out.println("Пароли не совпадают!");
            response.sendRedirect("/registration");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        (new RegistrationAccess()).gates(request, response);
    }

}
