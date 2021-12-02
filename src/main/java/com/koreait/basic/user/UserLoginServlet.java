package com.koreait.basic.user;

import com.koreait.basic.Utils;
import com.koreait.basic.dao.UserDAO;
import com.koreait.basic.user.model.LoginResult;
import com.koreait.basic.user.model.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/login")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Utils.displayView("login", "user/login", req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        String upw = req.getParameter("upw");

        UserEntity param = new UserEntity();
        param.setUpw(upw);
        param.setUid(uid);

        LoginResult lr = UserDAO.login(param);
        if(lr.getResult()==1) {
            HttpSession hs = req.getSession();
            hs.setAttribute("loginUser", lr.getLoginUser());
            res.sendRedirect("/board/list");
            return;
        }
        String err = null;
        switch (lr.getResult()) {
            case 0:
                err = "Failed Login";
                break;
            case 2:
                err = "Undefined User Id";
                break;
            case 3:
                err = "Undefined User Password";
                break;
        }
        req.setAttribute("err", err);
        doGet(req, res);
    }
}