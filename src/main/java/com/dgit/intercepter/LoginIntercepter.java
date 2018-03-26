package com.dgit.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dgit.domain.MemberVO;

public class LoginIntercepter extends HandlerInterceptorAdapter{
	private static final Logger logger = LoggerFactory.getLogger(LoginIntercepter.class);
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	
		
		Object object = modelAndView.getModel().get("login");
		HttpSession session=request.getSession();
		if(object != null){
			MemberVO vo = (MemberVO) object;
			logger.info("userid : "+vo.getUserid());
			session.setAttribute("login", vo);
			
			Object dest = session.getAttribute("dest");
			String path = (dest!=null)?(String) dest:request.getContextPath();
			session.removeAttribute("dest");
			
			response.sendRedirect("galley");//home controller
		}                                                          
		logger.info("postHandler==========================");                                            
                       
	}                                                                                

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("preHandler==========================");
		HttpSession session = request.getSession();
		if(session.getAttribute("login")!=null){
			logger.info("이전 login 정보삭제 ");
			session.removeAttribute("login");
		}
		return true;
	}
	
}
