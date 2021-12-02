package com.koreait.basic;

import com.koreait.basic.user.model.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Utils {
    public static void displayView(String title, String page, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("title", title);
        req.setAttribute("page", page);
        disForward(req, res, "layout");
    }
    public static void disForward(HttpServletRequest req, HttpServletResponse res, String jsp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/" + jsp + ".jsp").forward(req, res);
    }

    public static int getParameterInt(HttpServletRequest req, String str) {
        String val = req.getParameter(str);
        return parseStringToInt(val, 0);
    }

    public static int parseStringToInt(String val, int defVal) {
        try {
            return Integer.parseInt(val);
        } catch (Exception e) {
            return defVal;
        }
    }

    public static UserEntity loginUser(HttpServletRequest req) {
        HttpSession hs = req.getSession();
        return (UserEntity) hs.getAttribute("loginUser");
    }
    public static int loginUserPk(HttpServletRequest req) {
        UserEntity loginUser = loginUser(req);
        if(loginUser ==  null) {
            return 0;
        }
        return loginUser.getIuser();
    }
}
