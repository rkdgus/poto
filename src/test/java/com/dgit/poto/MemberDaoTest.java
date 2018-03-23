package com.dgit.poto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dgit.domain.MemberVO;
import com.dgit.persistence.MemberDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class MemberDaoTest {
	
	@Autowired
	private MemberDao dao;
	
/*	@Test
	public void testCreate() throws Exception{
		MemberVO vo = new MemberVO();
		
		vo.setUserid("1");
		vo.setUserpw("123");
		vo.setUsername("123");
		dao.insertMember(vo);
		System.out.println(vo);
	}
	*/
/*	@Test
	public void testRead() throws Exception{
		int bno = 1;
		
		System.out.println(dao.read(bno));
	}
	
	@Test
	public void testListAll() throws Exception{
		dao.listAll();
		System.out.println(dao.listAll());
	}
	
	@Test
	public void testUpdate() throws Exception{
		BoardVO vo = new BoardVO("제목이다", "test123", "나");
		vo.setBno(1);
		dao.update(vo);
		
	}
	
	@Test
	public void testdelete() throws Exception{
		dao.delete(1);
		
	}
	
	@Test
	public void testListPage() throws Exception{
		dao.listPage(3);
		
	}*/
	
	/*@Test
	public void testListCliteria() throws Exception{
		Criteria criteria = new Criteria();
		criteria.setPage(1);
		criteria.setPerPageNum(20);
		dao.listCriteria(criteria);
		
	}*/
	
	/*@Test
	public void testListSearchCount() throws Exception{
		SearchCriteria cri = new SearchCriteria();
		cri.setSearchType("t");
		cri.setKeyword("안녕");
		dao.listSearchCount(cri);
		
	}*/
	/*@Test
	public void testListSearch() throws Exception{
		SearchCriteria cri = new SearchCriteria();
		cri.setSearchType("t");
		dao.listSearchCount(cri);
		
	}*/
}
