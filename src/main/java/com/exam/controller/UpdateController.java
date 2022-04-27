package com.exam.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.exam.dao.BoardDao;
import com.exam.dto.BoardDto;

public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String BOARD_ID = req.getParameter("BOARD_ID");
		BoardDao boardDao = BoardDao.getInstance();
		BoardDto boardDto = new BoardDto();
		boardDto = boardDao.selectByID(BOARD_ID);
		
		req.setAttribute("update", boardDto);
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/update.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String BOARD_ID = req.getParameter("BOARD_ID");
		String TITLE = req.getParameter("TITLE");
		String USER_ID = req.getParameter("USER_ID");
		String CONTENT = req.getParameter("CONTENT");
		
		BoardDao boardDao = BoardDao.getInstance();
		BoardDto boardDto = new BoardDto();
		boardDto.setBOARD_ID(Integer.parseInt(BOARD_ID));
		boardDto.setTITLE(TITLE);
		boardDto.setUSER_ID(USER_ID);
		boardDto.setCONTENT(CONTENT);
		
		boardDao.update(boardDto);
		resp.sendRedirect("board.do");
	}
}
