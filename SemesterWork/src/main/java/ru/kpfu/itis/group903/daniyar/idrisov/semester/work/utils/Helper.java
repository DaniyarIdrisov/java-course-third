package ru.kpfu.itis.group903.daniyar.idrisov.semester.work.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.dao.UserDAOImpl;
import ru.kpfu.itis.group903.daniyar.idrisov.semester.work.models.User;

import javax.servlet.http.*;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class Helper {

    private static Configuration cfg = null;

    public static Configuration getConfig(HttpServletRequest req) {
        if (cfg == null) {
            cfg = new Configuration();
            cfg = new Configuration(Configuration.VERSION_2_3_30);
            cfg.setServletContextForTemplateLoading(req.getServletContext(), "/WEB-INF/templates");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        }
        return cfg;
    }

    public static void render(HttpServletRequest request, HttpServletResponse response, String path, Map<String, Object> root) throws IOException {

        Configuration cfg = Helper.getConfig(request);
        try {
            Template tmpl = cfg.getTemplate(path);
            try {
                response.setContentType("text/html");
                tmpl.process(root, response.getWriter());
            } catch (TemplateException e) {
                throw new IllegalStateException(e);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    public static boolean checkCookie(HttpServletRequest request, UserDAOImpl userDAOImpl) {

        Cookie[] cookies = request.getCookies();
        String cookieName = "current_user";
        Cookie cookie = null;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (cookieName.equals(c.getName())) {
                    cookie = c;
                    break;
                }
            }
        }
        if (cookie != null) {
            User user = userDAOImpl.profile(cookie.getValue());
            HttpSession session = request.getSession();
            session.setAttribute("current_user", user);
        }
        return cookie != null;

    }

    public static String encrypt(String password){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] digest = messageDigest.digest();
            password = DatatypeConverter.printHexBinary(digest).toUpperCase();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return password;
    }

}
