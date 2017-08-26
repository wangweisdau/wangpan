package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import Mapper.mapper;
import Table.disktable;
import Table.disktableuser;



@Controller
public class SetController {

	@RequestMapping("setrequest.do")
	public ModelAndView deleteFileManager(HttpServletRequest request){
WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		mapper map = webApplicationContext.getBean("map",mapper.class);
		
		List<disktable> selectall = map.selectall();
		
		
		ModelAndView modelAndView = new ModelAndView();
		
				
		
		modelAndView.addObject("user_data", selectall);

		modelAndView.setViewName("set");

		return modelAndView;
		
	}
	
	
	@RequestMapping("addadmin.do")
	public String AddAdmin(@RequestParam("newadmin") String newadmin, HttpServletRequest request,HttpSession session) {
		
WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());

		mapper map = webApplicationContext.getBean("map",mapper.class);
		
		disktableuser disktableuser = webApplicationContext.getBean("disktableuser",disktableuser.class);
		disktableuser.setUsername(newadmin);
		disktableuser selectadmin = map.selectadmin(disktableuser);

		if (selectadmin==null) {
			session.setAttribute("statustemp", "被推荐用户名输入错误，请重新输入！");
			return "redirect:/setrequest.do";
		}
		
		
if ("false".equals(selectadmin.getAdmin()) ) {
	int a=map.addnewadmin(disktableuser);
	System.out.println(a);
	session.setAttribute("statustemp", "恭喜"+newadmin+"成为管理员!");
	
}else {
	session.setAttribute("statustemp", newadmin+"已是管理员，无需推荐！");
}
		
			
		
		return "redirect:/setrequest.do";
		
	}
	
}
