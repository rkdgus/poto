package com.dgit.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dgit.domain.MemberVO;

@Repository
public class MemberDaoImpl implements MemberDao{
	
	private static final String namespace = "com.dgit.mapper.MemberMapper";
	@Autowired
	private SqlSession session;

	@Override
	public MemberVO readWithPW(MemberVO vo) {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".readWithPW", vo);
	}

	@Override
	public MemberVO readMember(String userid) {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".readMember", userid);
	}

	@Override
	public void insertMember(MemberVO vo) {
		// TODO Auto-generated method stub
		session.insert(namespace+".insertMember",vo);
	}

}
