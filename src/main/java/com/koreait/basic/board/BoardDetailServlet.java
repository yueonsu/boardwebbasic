package com.koreait.basic.board;

import com.koreait.basic.Utils;
import com.koreait.basic.board.model.BoardDTO;
import com.koreait.basic.board.model.BoardEntity;
import com.koreait.basic.board.model.BoardVO;
import com.koreait.basic.dao.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/detail")
public class BoardDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int iboard = Utils.getParameterInt(req, "iboard");
        String title = "detail";

        BoardDTO param = new BoardDTO();
        param.setIboard(iboard);

        int iuser = Utils.loginUserPk(req);
        BoardVO vo = BoardDAO.detailBoardList(param);

        if(vo.getWriter() != iuser) {
            BoardDAO.boardHitUp(param);
        }

        req.setAttribute("data", vo);
        Utils.displayView(title, "board/detail", req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}