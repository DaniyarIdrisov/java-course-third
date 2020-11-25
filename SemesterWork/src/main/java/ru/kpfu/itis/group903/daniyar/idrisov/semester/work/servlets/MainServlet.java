package ru.kpfu.itis.group903.daniyar.idrisov.semester.work.servlets;

import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.dao.UserDAOImpl;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.services.access.MainAccess;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MainServlet")
public class MainServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        (new MainAccess()).gates(request, response);
    }


}
