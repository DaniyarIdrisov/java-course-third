package ru.kpfu.itis.group903.daniyar.idrisov.semester.work.services.access;

import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.services.interfaces.AccessInterface;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.utils.Helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainAccess implements AccessInterface {

    @Override
    public void gates(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        Map<String, Object> root = new HashMap<>();
        Helper.render(request, response, "main.ftl", root);

    }

}
