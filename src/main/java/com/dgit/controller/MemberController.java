package com.dgit.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dgit.domain.MemberVO;
import com.dgit.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController{
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService service;
	
	@RequestMapping(value="/userForm",method=RequestMethod.GET)
	public String userFormGet() throws Exception{
		logger.info("userForm =================================================get");
		return "member/userForm";
	}
	
	@RequestMapping(value="/userForm",method=RequestMethod.POST)
	public String userFormPost(MemberVO vo) throws Exception{
		logger.info("userForm =================================================post");
		service.insertMember(vo);
		return "redirect:/member/login";         
	}
	
	@RequestMapping(value="/userForm/{userid}",method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> idFormGet(@PathVariable("userid")String userid) throws Exception{
		ResponseEntity<String> entity = null;
		logger.info("userForm =================================================get");
		MemberVO vo =service.readMember(userid);

		if(vo!=null){
			entity = new ResponseEntity<String>("FAIL",HttpStatus.OK);
		}else{
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
			                                      
		}
		return entity;                                     
	}   
	
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginFormGet() throws Exception{
		logger.info("userForm =================================================get");
		return "member/loginForm";
	}
	
	@RequestMapping(value="/loginPost",method=RequestMethod.POST)
	public void loginFormPost(Model model,MemberVO vo) throws Exception{
		logger.info("loginFormPost =================================================post");
		

		MemberVO member = service.readWithPW(vo);
		System.out.println(member);
		if(member == null){
			logger.info("user  없음 ...........");
			logger.info("loginPOST         return ...........");
			return;
		}
		member.setUsername(vo.getUsername());
		member.setUserpw("");
		
		model.addAttribute("login",member);

	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logoutGet(HttpSession session){
         session.invalidate();
         return "redirect:/";
	}
}
                              