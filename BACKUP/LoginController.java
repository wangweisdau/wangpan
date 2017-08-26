package service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import Mapper.mapper;
import Table.disktableuser;
import Table.userattribute;
import sun.security.util.Password;

@Controller
public class LoginController {

	@RequestMapping("/hello.do")
	public ModelAndView hellocontroller(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();

		System.out.println("鏈夎闂�");
		HttpSession session = request.getSession();
		if (session.getAttribute("SharedWarehouseUserName") == null) {
			modelAndView.setViewName("login");
		} else {
			modelAndView.setViewName("index");
		}

		return modelAndView;

	}

	@RequestMapping(value="login.do" , method= RequestMethod.POST )
	public ModelAndView logincontroller(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam("username") String username, @RequestParam("password") String password) {

		String autologin = request.getParameter("autologin");

		ModelAndView modelAndView = new ModelAndView();

		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		mapper map = webApplicationContext.getBean("map", mapper.class);
		
		disktableuser user = webApplicationContext.getBean("disktableuser", disktableuser.class);
		
		user.setUsername(username);
		user.setPassword(password);
		System.out.println(user);
		disktableuser checkSelect = map.userCheckSelect(user);

		
		
		if (checkSelect == null ) {
			modelAndView.getModel().put("msg", "鏂囦欢鍚嶆垨瀵嗙爜閿欒锛岃閲嶆柊杈撳叆锛�");
			modelAndView.setViewName("login");

		} else {

			if ("ok".equals(autologin)) {
				System.out.println("鎵цcook");
				Cookie cook = new Cookie("userhistory", username + "//" + password);
				cook.setMaxAge(7 * 24 * 60 * 60);
				response.addCookie(cook);
			}
			session.setAttribute("SharedWarehouseUserName", username);
			session.setAttribute("SharedWarehousePassWord", password);

			
			disktableuser user1 = webApplicationContext.getBean("disktableuser", disktableuser.class);
			user1.setUsername(username);
			user1.setPassword(password);
			
			System.out.println(user1);	
			userattribute selectuserattributebyuser = map.selectuserattributebyuser(user1);
			System.out.println("瑙傚療");
			String url0 = selectuserattributebyuser.getFilelocation();
System.out.println(url0);
//			Map<String, Object> mapset = new HashMap<>();
			String prodisktablename = selectuserattributebyuser.getProdisktablename();
//			mapset.put("tablename", prodisktablename);

			Double sumspace = selectuserattributebyuser.getProspacesize();
			System.out.println("鐧婚檰鎴愭�荤┖闂达細"+sumspace);
			Double usedspacesize = selectuserattributebyuser.getUsedspacesize();
			Integer userattributeid = selectuserattributebyuser.getUserattributeid();
//			Double usedspace =map.selectdisktableProsize(mapset);


			
			System.out.println("鐧婚檰鎴愬墿浣欑┖闂达細"+usedspacesize);

			session.setAttribute("url", url0);
			session.setAttribute("tablename", prodisktablename);
			session.setAttribute("sumspace", sumspace);
			session.setAttribute("usedspace", usedspacesize);
			session.setAttribute("attributeid", userattributeid);

			modelAndView.setViewName("index");

		}

		return modelAndView;

	}

	@RequestMapping(value="register", method= RequestMethod.POST)
	public ModelAndView register(@RequestParam("email") String email,   @RequestParam("username") String newusername, @RequestParam("password") String newpassword, @RequestParam("password1") String newpassword1, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		mapper map = webApplicationContext.getBean("map", mapper.class);
		
		
		if(!newpassword.equals(newpassword1)) {
			model.getModel().put("msg", "涓ゆ瀵嗙爜椹跺叆涓嶄竴鑷达紝璇烽噸鏂拌緭鍏ワ紒");
			model.setViewName("register");
			return model;
		}
		
		disktableuser checkagain = webApplicationContext.getBean("disktableuser", disktableuser.class);
		checkagain.setEmail(email);
		checkagain.setUsername(newusername);
		checkagain.setPassword(newpassword);
		List<disktableuser> registercheck = map.registercheck(checkagain);
		
		boolean againname=false;
		boolean againemail=false;
		for (disktableuser checkresult : registercheck) {
			if(newusername.equals(checkresult.getUsername())) {
				againname=true;
			}
			if(email.equals(checkresult.getEmail())) {
				againemail=true;
			}
		}
		if (againemail || againname) {
			if (againemail && (!againname)) {
				model.getModel().put("msg", "璇ラ偖绠卞凡琚敞鍐岃繃锛岃鏇存崲閭!");
				model.setViewName("register");
				return model;
			}
			
			if ( (!againemail) && againname) {
				model.getModel().put("msg", "璇ョ敤鎴峰悕宸茶娉ㄥ唽杩囷紝璇锋洿鎹㈢敤鎴峰悕锛�");
				model.setViewName("register");
				return model;
			}
			
			if ( againemail && againname) {
				model.getModel().put("msg", "閭鍜岀敤鎴峰悕鍧囪娉ㄥ唽杩囷紝璇锋洿鎹紒");
				model.setViewName("register");
				return model;
			}
			
		}
		
		

		disktableuser newuser = webApplicationContext.getBean("disktableuser", disktableuser.class);
		newuser.setUsername(newusername);
		newuser.setPassword(newpassword);
		newuser.setEmail(email);

		System.out.println(newuser);

		map.insertNewUser(newuser);

		Properties param = webApplicationContext.getBean("db", Properties.class);
		userattribute newuserattribute = webApplicationContext.getBean("userattribute", userattribute.class);
		System.out.println(param.getProperty("personalspacesize"));
		newuserattribute.setProspacesize(Double.parseDouble(param.getProperty("personalspacesize")));

		String uuidname = UUID.randomUUID().toString();
		uuidname = "pertab"+uuidname.replace("-", "").substring(18);

		String url = param.getProperty("personaldisklocation") + "\\" + uuidname;
		File file = new File(url);
		if (!file.exists()) {
			file.mkdirs();
		}
		newuserattribute.setFilelocation(url);
		newuserattribute.setProdisktablename(uuidname);

		map.insertuserattritute(newuserattribute);
		System.out.println("鎵ц鍒版");

		Map<String, Object> mapset = new HashMap<>();
		mapset.put("tablename", uuidname);
		map.createdisktablePro(mapset);

		
		model.getModel().put("msg", "娉ㄥ唽鎴愬姛璇风櫥褰�");
		model.setViewName("login");

		return model;
	}

}
