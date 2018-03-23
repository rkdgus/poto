package com.dgit.persistence;

import com.dgit.domain.MemberVO;

public interface MemberDao {
	public MemberVO readWithPW(MemberVO vo);
	public MemberVO readMember(String userid);
	public void insertMember(MemberVO vo);
}
