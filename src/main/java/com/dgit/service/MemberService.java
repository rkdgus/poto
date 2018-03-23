package com.dgit.service;

import com.dgit.domain.MemberVO;

public interface MemberService {
	public MemberVO readWithPW(MemberVO vo);
	public MemberVO readMember(String userid);
	public void insertMember(MemberVO vo);
}
