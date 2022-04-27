package com.exam.func;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.exam.dao.BoardDao;
import com.exam.dto.BoardDto;

public class BoardFunc {
	
	public List<Map> list() {
		BoardDao boardDao = BoardDao.getInstance();
		List<BoardDto> list = boardDao.selectList();
	
		List<Map> allList = new ArrayList<Map>();
		allList.clear();

		HashMap<String, Object> hm;
		SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.dd\nhh:mm");
		for (BoardDto b : list) {
			String stDate = "";
			Timestamp tDate = b.getDATE();
			if (tDate != null) {
				stDate = sdf.format(tDate);
			}
			hm = new HashMap<String, Object>();
			hm.put("KIND", b.getKIND());
			hm.put("BOARD_ID", b.getBOARD_ID());
			hm.put("TITLE", b.getTITLE());
			hm.put("USER_ID", b.getUSER_ID());
			hm.put("DATE", stDate);
			allList.add(hm);
		}
		
		return allList;
	}
	
	public List<Map> kind() {
		BoardDao boardDao = BoardDao.getInstance();
		List<BoardDto> list = boardDao.selectKind();
		
		List<Map> kindList = new ArrayList<Map>();
		kindList.clear();
		
		HashMap<String, Object> hm;
		for (BoardDto bd : list) {
			hm = new HashMap<String, Object>();
			hm.put("ID", bd.getID());
			hm.put("KIND", bd.getKIND());
			kindList.add(hm);
		}

		return kindList;
	}
}
