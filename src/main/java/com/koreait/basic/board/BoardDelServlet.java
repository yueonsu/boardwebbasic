package com.koreait.basic.board;

import com.koreait.basic.Utils;
import com.koreait.basic.board.model.BoardDTO;
import com.koreait.basic.board.model.BoardEntity;
import com.koreait.basic.dao.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/board/del")
public class BoardDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int iboard = Utils.getParameterInt(req, "iboard");
        HttpSession hs = req.getSession();
        int writer = Utils.loginUserPk(req);

        BoardEntity param = new BoardEntity();
        param.setIboard(iboard);
        param.setWriter(writer);

        int result = BoardDAO.delBoard(param);
        res.sendRedirect("/board/list");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}