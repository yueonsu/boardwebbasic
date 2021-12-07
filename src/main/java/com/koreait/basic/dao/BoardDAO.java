package com.koreait.basic.dao;

import com.koreait.basic.DbUtils;
import com.koreait.basic.board.model.BoardDTO;
import com.koreait.basic.board.model.BoardEntity;
import com.koreait.basic.board.model.BoardVO;
import com.koreait.basic.user.model.UserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    public static int insBoardWithPk(BoardEntity param) {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "INSERT INTO t_board (title, ctnt, writer)" +
                " VALUES (?, ?, ?) ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, param.getTitle());
            ps.setString(2, param.getCtnt());
            ps.setInt(3, param.getWriter());
            result = ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if(rs.next()) {
                param.setIboard(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return result;
    }

    public static BoardVO detailBoardList(BoardDTO param) {
        BoardVO vo = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT A.iboard, A.ctnt, A.title, A.writer, A.rdt, A.hit, B.nm AS writerNm" +
                " FROM t_board A" +
                " INNER JOIN t_user B" +
                " ON A.writer = B.iuser" +
                " WHERE iboard = ? ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            rs = ps.executeQuery();
            if(rs.next()) {
                vo = BoardVO.builder()
                        .iboard(param.getIboard())
                        .ctnt(rs.getString("ctnt"))
                        .title(rs.getString("title"))
                        .writer(rs.getInt("writer"))
                        .rdt(rs.getString("rdt"))
                        .hit(rs.getInt("hit"))
                        .writerNm(rs.getString("writerNm"))
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return vo;
    }

    public static int getMaxPageNum(BoardDTO param) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT CEIL(COUNT(*) / ?) FROM t_board ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getRowCnt());
            rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return 0;
    }

    public static List<BoardVO> selBoardList() {
        List<BoardVO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT A.iboard, A.title, A.writer, A.rdt, A.hit, B.nm AS writerNm" +
                " FROM t_board A" +
                " INNER JOIN t_user B" +
                " ON A.writer = B.iuser" +
                " ORDER BY iboard DESC ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                BoardVO vo = BoardVO.builder()
                        .iboard(rs.getInt("iboard"))
                        .title(rs.getString("title"))
                        .writer(rs.getInt("writer"))
                        .rdt(rs.getString("rdt"))
                        .hit(rs.getInt("hit"))
                        .writerNm(rs.getString("writerNm"))
                        .build();
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return list;
    }

    public static void boardHitUp(BoardDTO entity) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "UPDATE t_board SET hit = hit+1 WHERE iboard = ? ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, entity.getIboard());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
    }

    public static int updBoard(BoardEntity entity) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "UPDATE t_board " +
                " SET title = ?, ctnt = ?" +
                " WHERE iboard = ? AND writer = ? ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, entity.getTitle());
            ps.setString(2, entity.getCtnt());
            ps.setInt(3, entity.getIboard());
            ps.setInt(4, entity.getWriter());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }

    public static int delBoard(BoardEntity param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM t_board WHERE iboard = ? AND writer = ? ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            ps.setInt(2, param.getWriter());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }
}
