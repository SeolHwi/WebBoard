package com.exam.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.exam.dao.BoardDao;
import com.exam.dto.BoardDto;

public class WriteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/write.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		int KIND_ID = Integer.parseInt(req.getParameter("KIND_ID"));
		String TITLE = req.getParameter("TITLE");
		String USER_ID = req.getParameter("USER_ID");
		String CONTENT = req.getParameter("CONTENT");
		
		BoardDao boardDao = BoardDao.getInstance();
		BoardDto boardDto = new BoardDto();
		boardDto.setKIND_ID(KIND_ID);
		boardDto.setBOARD_ID(boardDao.nextval() + 1);
		boardDto.setTITLE(TITLE);
		boardDto.setUSER_ID(USER_ID);
		boardDto.setCONTENT(CONTENT);
		
		int result = boardDao.write(boardDto);
		resp.sendRedirect("board.do");
	}

}
