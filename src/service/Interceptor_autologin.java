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
import Table.userattribute;

public class Interceptor_autologin implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Interceptor_specialpath) throws Exception {

		System.out.println("拦截1生效");

		WebApplicationContext getbean = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());

		HttpSession session = request.getSession();
		String username0 = (String) session.getAttribute("SharedWarehouseUserName");
		
		
		
		String res = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/"));
		System.out.println("拦截的请求为"+res);
		
		if("/changepassword.do".equals(res)) {
			if ( "true".equals( session.getAttribute("InSetStatus"))) 
				return true;
		}
		
		
		
		
		
		

		if (username0 != null &&  !"true".equals( session.getAttribute("InSetStatus"))) {
			return true;
		} else {

			Cookie[] cookies = request.getCookies();

			if (cookies == null) {
				// request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,
				// response);
				response.sendRedirect(request.getContextPath() + "/hello.do");
				
			} else {
				for (Cookie cookie : cookies) {
					if ("userhistory".equals(cookie.getName())) {

						String[] split = cookie.getValue().split("//");

						String username = split[0];
						String password = split[1];

						mapper usercheckbean = getbean.getBean("map", mapper.class);
						disktableuser disktableuser = getbean.getBean("disktableuser", disktableuser.class);
						disktableuser.setUsername(username);
						disktableuser.setPassword(password);

						disktableuser userCheckSelect = usercheckbean.userCheckSelect(disktableuser);


						if (userCheckSelect == null) {


							response.sendRedirect(request.getContextPath() + "/hello.do");
							return false;
							
						} else {


							request.getSession().setAttribute("SharedWarehouseUserName", username);
							request.getSession().setAttribute("SharedWarehousePassWord", password);
							disktableuser user1 = getbean.getBean("disktableuser", disktableuser.class);
							user1.setUsername(username);
							user1.setPassword(password);
							

							userattribute selectuserattributebyuser = usercheckbean.selectuserattributebyuser(user1);
							String url0 = selectuserattributebyuser.getFilelocation();
							String prodisktablename = selectuserattributebyuser.getProdisktablename();

							Double sumspace = selectuserattributebyuser.getProspacesize();
							

							Double usedspacesize = selectuserattributebyuser.getUsedspacesize();
							Integer userattributeid = selectuserattributebyuser.getUserattributeid();



							session.setAttribute("url", url0);
							session.setAttribute("tablename", prodisktablename);
							session.setAttribute("sumspace", sumspace);
							session.setAttribute("usedspace", usedspacesize);
							session.setAttribute("attributeid", userattributeid);
							return true;
						}
					} 
					
					/*else {
						// request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,
						// response);

						response.sendRedirect(request.getContextPath() + "/hello.do");

					}*/

				}
				
				
				
				response.sendRedirect(request.getContextPath() + "/hello.do");
				
			}

		}
		System.out.println("执行被拦截器终止,准备跳转");
		return false;
	}

}
