package service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import Mapper.mapper;
import Table.disktableuser;

public class Interceptor_specialpath implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		System.out.println("执行拦截器2");
		String res = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/"));
		System.out.println("拦截的请求为"+res);
		
		if ("/setrequest.do".equals(res)) {
			HttpSession session = request.getSession();
			WebApplicationContext ContextUtils = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
			disktableuser disktableuser = ContextUtils.getBean("disktableuser",disktableuser.class);
			disktableuser.setUsername((String) session.getAttribute("SharedWarehouseUserName"));
			mapper mapper = ContextUtils.getBean("map", mapper.class);
			disktableuser selectadmin = mapper.selectadmin(disktableuser);

			if ("true".equals(selectadmin.getAdmin())) {
				return true;
			}else {
				response.sendRedirect(request.getContextPath() + "/404result.do");
				return false;
			}
			
		}
		
		if("/exit.do".equals(res)) {
			HttpSession session = request.getSession();
			
			session.removeAttribute("SharedWarehouseUserName");
			session.removeAttribute("SharedWarehousePassWord");
			session.removeAttribute("url");
			session.removeAttribute("tablename");
			session.removeAttribute("sumspace");
			session.removeAttribute("usedspace");
			session.removeAttribute("attributeid");
			
			Cookie cook = new Cookie("userhistory","null");
					
					cook.setMaxAge(0);
			response.addCookie(cook);
			response.sendRedirect(request.getContextPath() + "/indexstart.jsp");	
			return false;
		
		}
		
		
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
