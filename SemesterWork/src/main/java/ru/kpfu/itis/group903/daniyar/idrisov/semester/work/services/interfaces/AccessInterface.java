package ru.kpfu.itis.group903.daniyar.idrisov.semester.work.services.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AccessInterface {

    void gates(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
