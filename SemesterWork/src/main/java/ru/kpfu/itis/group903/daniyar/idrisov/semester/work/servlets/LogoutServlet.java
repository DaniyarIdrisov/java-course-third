package ru.kpfu.itis.group903.daniyar.idrisov.semester.work.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LogoutServlet")
public class LogoutServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.removeAttribute("current_user");

        session.invalidate();

        Cookie cookie = new Cookie("current_user", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        System.out.println("---------------------------------");
        System.out.println("Пользователь вышел из аккунта!");
        response.sendRedirect("/login");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
