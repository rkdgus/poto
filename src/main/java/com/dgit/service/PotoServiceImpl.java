package com.dgit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgit.domain.PotoVO;
import com.dgit.persistence.PotoDao;

@Service
public class PotoServiceImpl implements PotoService{

	
	@Autowired
	PotoDao dao;
	
	@Override
	public List<PotoVO> allPoto(String userid) {
		// TODO Auto-generated method stub
		return dao.allPoto(userid);
	}

	@Override
	public void insertPoto(PotoVO vo) {
		// TODO Auto-generated method stub
		dao.insertPoto(vo);
	}

	@Override
	public void deletePoto(int pno) {
		// TODO Auto-generated method stub
		dao.deletePoto(pno);
	}

}
