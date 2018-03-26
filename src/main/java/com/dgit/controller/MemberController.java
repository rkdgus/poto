package com.dgit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dgit.domain.MemberVO;
import com.dgit.domain.PotoVO;
import com.dgit.service.MemberService;
import com.dgit.service.PotoService;
import com.dgit.util.MediaUtils;
import com.dgit.util.UploadFileUtil;


@Controller
@RequestMapping("/member/*")
public class MemberController{
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService service;
	
	@Autowired
	PotoService potoService;
	
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
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
		}else{
			member.setUsername(vo.getUsername());
			member.setUserpw("");
		}
		
		
		model.addAttribute("login",member);

	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logoutGet(HttpSession session){
         session.invalidate();
         return "redirect:/";
	}          
	            
	@RequestMapping(value="/galley",method=RequestMethod.GET)
	public String galleyGet(HttpSession session,Model model){           
		MemberVO vo = (MemberVO)session.getAttribute("login");
		String userid=vo.getUserid();
		List<PotoVO> list=potoService.allPoto(userid);
		for(PotoVO p:list){
			p.setTitle(p.getFullName().substring(51));                                                  
		}
		model.addAttribute("img", list);
		
         return "member/galley";
	}
	
	@RequestMapping(value="/galley",method=RequestMethod.POST)
	public String galleyPost(HttpSession session, List<MultipartFile> imageFile,PotoVO vo) throws IOException, Exception{
		logger.info("galleyPost =================================================post");
		if(imageFile != null && imageFile.get(0).getSize() > 0){
			String[] files = new String[imageFile.size()];
			for(int i = 0; i < imageFile.size(); i++){
				logger.info(imageFile.get(i).getOriginalFilename());
				String savedName = UploadFileUtil.uploadFile(uploadPath, 
							imageFile.get(i).getOriginalFilename(), 
							imageFile.get(i).getBytes());
				files[i] = savedName;
				vo.setFullName(files[i]);
				potoService.insertPoto(vo);
			}
			
		}
         return "redirect:/member/galley";
	}
	
	
	
	@RequestMapping(value = "displayFile", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<byte[]> displayFile(String filename) {
		ResponseEntity<byte[]> entity = null;
		logger.info("displayFile : " + filename);
		InputStream in = null;
		try {
			String formatName = filename.substring(filename.lastIndexOf(".") + 1);
			MediaType type = MediaUtils.getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(type);

			in = new FileInputStream(uploadPath + filename);

			entity = new ResponseEntity<>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	
	@RequestMapping(value="/deleteImg/{pno}",method=RequestMethod.GET)
	public String deleteGet(@PathVariable("pno")int pno,String filename){
			System.gc();
			File file = new File(uploadPath+filename);
			File file1 = new File(uploadPath+filename.substring(0,12)+filename.substring(14));
			
			file1.delete();
			file.delete();
			
         potoService.deletePoto(pno);
         return "redirect:/member/galley";
	}
	
	
	
	
	
	
	
	
	
	
	
}
                              