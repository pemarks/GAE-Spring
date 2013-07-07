package net.magus.example.web.interceptors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class LoginInterceptor implements HandlerInterceptor {
	private static Logger logger = Logger.getLogger(LoginInterceptor.class);
	
	private UserService userServ;	
	
	@PostConstruct
	public void init(){
		userServ = UserServiceFactory.getUserService();
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		boolean result = false;
		
		logger.debug("Checking to see if the user is logged in");
		
		if(!userServ.isUserLoggedIn()){
			logger.debug("The user is not logged in");
			response.sendRedirect(userServ.createLoginURL(request.getRequestURI()));		
			result = false;
		}else{
			logger.debug("The user is logged in");
			result = true;
		}
		
		return result;
	}

}
