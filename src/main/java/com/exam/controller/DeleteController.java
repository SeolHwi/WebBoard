package com.exam.controller;

import java.io.IOException;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.exam.dao.BoardDao;

public class DeleteController extends HttpServlet {

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
		boardDao.del(Integer.parseInt(BOARD_ID));

		resp.sendRedirect("board.do");
	}


}
