package com.dgit.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthIntercepter extends HandlerInterceptorAdapter{
	private static final Logger logger = LoggerFactory.getLogger(HandlerInterceptorAdapter.class);
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
			logger.info("postHandler=============================================");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("preHandler=============================================");
		HttpSession session = request.getSession();
		if(session.getAttribute("login")==null){
			saveDest(request);
			response.sendRedirect(request.getContextPath()+"/user/login");
			
			return false;//원래 가야할 controller에 안감
		}
		return true;
	}

	private void saveDest(HttpServletRequest req){
		String uri = req.getRequestURI();
		String query = req.getQueryString();
		
		if(query == null||query.equals("null")){
			query="";
		}else{
			query="?"+query;
		}
		
		if(req.getMethod().equals("GET")){
			logger.info("dest : "+(uri+query));
			req.getSession().setAttribute("dest", uri+query);
		}
	}
	
}














