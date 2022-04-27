package com.exam.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.exam.dao.BoardDao;
import com.exam.dto.BoardDto;

public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String BOARD_ID = req.getParameter("BOARD_ID");
		BoardDao boardDao = BoardDao.getInstance();
		BoardDto boardDto = new BoardDto();
		boardDto = boardDao.selectByID(BOARD_ID);
		
		req.setAttribute("view", boardDto);
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view.jsp");
		rd.forward(req, resp);
	}
}
