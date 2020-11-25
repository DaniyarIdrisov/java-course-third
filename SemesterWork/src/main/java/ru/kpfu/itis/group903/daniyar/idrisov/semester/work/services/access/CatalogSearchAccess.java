package ru.kpfu.itis.group903.daniyar.idrisov.semester.work.services.access;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.dao.ItemDAOImpl;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.models.Item;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.services.interfaces.AccessInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CatalogSearchAccess implements AccessInterface {

    @Override
    public void gates(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String query = request.getParameter("query");

        List<Item> items = (new ItemDAOImpl()).getByLikePattern(query);

        JSONArray jsonArray = new JSONArray();

        for (Item item: items) {
            jsonArray.put(new JSONObject(item));
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("objects", jsonArray);

        response.setContentType("text/json");
        response.getWriter().write(jsonObject.toString());

    }

}
