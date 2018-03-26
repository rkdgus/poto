package com.dgit.service;

import java.util.List;

import com.dgit.domain.PotoVO;

public interface PotoService {

	public List<PotoVO> allPoto(String userid);

	public void insertPoto(PotoVO vo);

	public void deletePoto(int pno);
}
