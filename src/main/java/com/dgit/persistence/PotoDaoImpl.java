package com.dgit.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dgit.domain.PotoVO;

@Repository
public class PotoDaoImpl implements PotoDao{
	private static final String namespace = "com.dgit.mapper.PotoMapper";
	@Autowired
	private SqlSession session;

	@Override
	public List<PotoVO> allPoto(String userid) {
		
		return session.selectList(namespace+".allPoto",userid);
	}

	@Override
	public void insertPoto(PotoVO vo) {
		// TODO Auto-generated method stub
		session.insert(namespace+".insertPoto",vo);
	}

	@Override
	public void deletePoto(int pno) {
		// TODO Auto-generated method stub
		session.delete(namespace+".deletePoto",pno);
	}

}
