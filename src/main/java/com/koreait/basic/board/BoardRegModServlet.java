package com.koreait.basic.board;

import com.koreait.basic.Utils;
import com.koreait.basic.board.model.BoardDTO;
import com.koreait.basic.board.model.BoardEntity;
import com.koreait.basic.dao.BoardDAO;
import com.koreait.basic.user.model.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/board/regmod")
public class BoardRegModServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int iboard = Utils.getParameterInt(req, "iboard");
        HttpSession hs = req.getSession();
        UserEntity loginUser = (UserEntity) hs.getAttribute("loginUser");
        String title = "글 쓰기";
        if(loginUser == null) {
            res.sendRedirect("/user/login");
            return;
        }

        if (iboard != 0) {
            title = "글 수정";
            if (req.getAttribute("data") == null) {
                BoardDTO dto = new BoardDTO();
                dto.setIboard(iboard);
                req.setAttribute("data", BoardDAO.detailBoardList(dto));
            }
        }
        Utils.displayView(title, "board/regmod", req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String title = req.getParameter("title");
        String ctnt = req.getParameter("ctnt");
        int writer = Utils.loginUserPk(req);
        int result = 0;

        BoardEntity param = new BoardEntity();
        param.setTitle(title);
        param.setCtnt(ctnt);
        param.setWriter(writer);

        int iboard = Utils.getParameterInt(req, "iboard");
        if(iboard > 0) {
            param.setIboard(iboard);
            result = BoardDAO.updBoard(param);
        } else {
            result = BoardDAO.insBoardWithPk(param);
        }
        String err = null;
        switch (result) {
            case 1:
                res.sendRedirect("/board/detail?iboard=" + param.getIboard());
                break;
            default:
                err = "글 등록에 실패했습니다";
                req.setAttribute("err", err);
                req.setAttribute("data", param);
                doGet(req, res);
                break;
        }
        res.sendRedirect("/board/list");
    }
}