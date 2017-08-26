package service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
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

@Controller
public class LoginController {

	@RequestMapping("/hello.do")
	public ModelAndView hellocontroller(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();


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
			modelAndView.getModel().put("msg", "用户名或密码输入错误，请重新输入");
			modelAndView.setViewName("login");

		} else {
			
			if ("false".equals(checkSelect.getActivation()) ) {
				modelAndView.getModel().put("msg", "账户名未激活！请前往邮箱激活");
				modelAndView.setViewName("login");
            return modelAndView;
			} 
			
			
			if ("ok".equals(autologin)) {
				System.out.println("选择“免登录”,装配cookie");
				Cookie cook = new Cookie("userhistory", username + "//" + password);
				cook.setMaxAge(7 * 24 * 60 * 60);
				response.addCookie(cook);
			}
			session.setAttribute("SharedWarehouseUserName", username);
			session.setAttribute("SharedWarehousePassWord", password);

			
			disktableuser user1 = webApplicationContext.getBean("disktableuser", disktableuser.class);
			user1.setUsername(username);
			user1.setPassword(password);
			

			userattribute selectuserattributebyuser = map.selectuserattributebyuser(user1);

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
			model.getModel().put("msg", "两次密码输入不一致，请重新注册");
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
				model.getModel().put("msg", "该邮箱已被使用，请更换!");
				model.setViewName("register");
				return model;
			}
			
			if ( (!againemail) && againname) {
				model.getModel().put("msg", "该用户名已被使用，请更换");
				model.setViewName("register");
				return model;
			}
			
			if ( againemail && againname) {
				model.getModel().put("msg", "邮箱和用户名均已被使用，请更换！");
				model.setViewName("register");
				return model;
			}
			
		}
		
		String activationcode=UUID.randomUUID()+"";

		disktableuser newuser = webApplicationContext.getBean("disktableuser", disktableuser.class);
		newuser.setUsername(newusername);
		newuser.setPassword(newpassword);
		newuser.setEmail(email);
        newuser.setActivationcode(activationcode);
		System.out.println(newuser);

		map.insertNewUser(newuser);
		
		

		Properties param = webApplicationContext.getBean("db", Properties.class);
		userattribute newuserattribute = webApplicationContext.getBean("userattribute", userattribute.class);
		System.out.println(param.getProperty("personalspacesize"));
		newuserattribute.setProspacesize(Double.parseDouble(param.getProperty("personalspacesize")));
		newuserattribute.setUserattributeid(newuser.getUserattributeid());

		String uuidname = UUID.randomUUID().toString();
		uuidname = "pertab"+uuidname.replace("-", "").substring(18);

		
		Map<String, Object> mapset = new HashMap<>();
		mapset.put("tablename", uuidname);
		map.createdisktablePro(mapset);
		map.createdisktablePro2(mapset);
		
		
		String url = param.getProperty("personaldisklocation") + "/" + uuidname;
		File file = new File(url);
		if (!file.exists()) {
			file.mkdirs();
		}
		newuserattribute.setFilelocation(url);
		newuserattribute.setProdisktablename(uuidname);

		map.insertuserattritute(newuserattribute);
		


		JavaMailSenderImpl sender = webApplicationContext.getBean("mailSender",JavaMailSenderImpl.class);
		SimpleMailMessage mail = webApplicationContext.getBean("simplemailmessage", SimpleMailMessage.class);
		mail.setTo(email);//收件人邮箱地址
        mail.setSubject("共享仓库账号激活邮件");//主题
        

        
        
        

        Properties prop=webApplicationContext.getBean("db",Properties.class);
//    
        mail.setText("尊敬的用户("+newusername+")，您好：\r \n"
        		+ "欢迎来到共享仓库，请点击或复制链接：http://"+prop.getProperty("domainname")+"/activecheck.do?email="+email+"&activationcode="+activationcode+"\r\n"
        		+"进行邮箱激活,若非本人操作，请忽略此邮件。");
        sender.send(mail);

        

		

		
		model.getModel().put("msg", "注册成功，请前往邮箱进行账号激活，激活即可登录！");
		model.setViewName("login");

		return model;
	}

	
	
	@RequestMapping("/activecheck.do")
	public ModelAndView ActiveCheck(@RequestParam("activationcode") String activationcode,@RequestParam("email") String email, HttpServletRequest request ) {
		ModelAndView model=new ModelAndView();
		if(activationcode==null || email==null) {
			model.getModel().put("msg","请求链接错误,请联系管理员！");
			model.setViewName("activeresult");
			return model;
		}
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		disktableuser checkagain = webApplicationContext.getBean("disktableuser", disktableuser.class);
		
		checkagain.setEmail(email);
		checkagain.setActivationcode(activationcode);
		
		mapper map = webApplicationContext.getBean("map", mapper.class);
		int activecheck = map.activecheck(checkagain);
		if (activecheck>0) {
			model.getModel().put("msg","恭喜你，共享仓库激活成功！");
			model.setViewName("activeresult");
			return model;
		}else {
			model.getModel().put("msg","激活失败,请检查链接是否正确，或联系管理员！");
			model.setViewName("activeresult");
			return model;
		}
		
		

		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
