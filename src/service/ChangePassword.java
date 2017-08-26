package service;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import Mapper.mapper;
import Table.disktableuser;

@Controller
public class ChangePassword {

	String useremail=null;
	
	@RequestMapping("/sendemail.do")
	public ModelAndView SendEmail(@RequestParam("username") String username, @RequestParam("email") String email, HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		JavaMailSenderImpl sender = webApplicationContext.getBean("mailSender",JavaMailSenderImpl.class);
		SimpleMailMessage mail = webApplicationContext.getBean("simplemailmessage", SimpleMailMessage.class);
	       
		
		mapper map = webApplicationContext.getBean("map",mapper.class);
		disktableuser disktableuser = webApplicationContext.getBean("disktableuser",disktableuser.class);
		disktableuser.setEmail(email);
		disktableuser emailcheck = map.emailcheck(disktableuser);
		
		
		
		if(emailcheck==null) {
		modelAndView.getModel().put("msg", "输入的邮箱不存在，请重新输入！");
		modelAndView.setViewName("forgetpassword");
		return modelAndView;
		}
		

		    mail.setTo(email);//收件人邮箱地址
	        mail.setSubject("共享仓库密码修改确认邮件");//主题
	        
	       int random= (int)((Math.random()*9+1)*100000);  
	       
	       
	       System.out.println("当前时间"+System.currentTimeMillis());
	       
	       
	       
	        session.setAttribute("checknum", random);
	        session.setAttribute("checknum_otime",System.currentTimeMillis());
	        
	        
	        mail.setText("尊敬的用户"+emailcheck.getUsername()+"，您好：\r \n"
	        		+ "欢迎继续使用共享仓库，您的验证码为：【"+random+"】，五分钟内有效，请马上进行验证。	\r\n" + 
	        		"若非本人操作，请忽略此邮件。");
	        sender.send(mail);

	        
		System.out.println(" 更换密码邮件发送成功.. ");


		
		

		modelAndView.getModel().put("msg", "验证码已发至邮箱，请前往邮箱获取！");
		modelAndView.setViewName("changepasswordverifycode");
		return modelAndView;

	}
	
	@RequestMapping("/checkverifycode.do")
	public ModelAndView Checkverifycode(@RequestParam("checknum") String checknum,HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		
		
		String checknum_sever =((int) session.getAttribute("checknum"))+"";
         long otime = (long) session.getAttribute("checknum_otime");
         
         
         
         
		System.out.println("验证码"+checknum_sever);

	       if((System.currentTimeMillis()-otime)>300000) {
	    	   System.out.println("验证码已经过期");
	    	   modelAndView.getModel().put("msg", "验证码已经过期,请重新提交");
	    	   modelAndView.setViewName("login");
	    	   session.removeAttribute("checknum");
	    	   session.removeAttribute("checknum_otime");
	    	   return modelAndView;
	       }
	       
	       if (!checknum_sever.equals(checknum)) {
	    	   System.out.println("验证码填写错误，请重新填写");
	    	   modelAndView.getModel().put("msg", "验证码填写错误，请重新填写");
	    	   modelAndView.setViewName("changepasswordverifycode");
	    	   return modelAndView;
		}
	       
	       System.out.println("验证码填写验证成功");
    	   session.removeAttribute("checknum");
    	   session.removeAttribute("checknum_otime");
    	   session.setAttribute("InSetStatus", "true");
    	   
	       modelAndView.getModel().put("msg", "邮箱验证成功，请设置新密码：");
    	   modelAndView.setViewName("changepassword");
    	   return modelAndView;
	       
         
	
		
	}
	
	
	
	@RequestMapping("/changepassword.do")
	public ModelAndView Changepassword(@RequestParam("newpassword") String newpassword,@RequestParam("newpassword1") String newpassword1,HttpServletRequest request,HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		
		if(!newpassword.equals(newpassword1)) {
			modelAndView.getModel().put("msg", "两次密码输入不一致，请重新输入");
			modelAndView.setViewName("changepassword");
			return modelAndView;
		}
		
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		mapper map = webApplicationContext.getBean("map",mapper.class);
		
		disktableuser disktableuser = webApplicationContext.getBean("disktableuser", disktableuser.class);
		disktableuser.setEmail((String)session.getAttribute("email"));
		disktableuser.setPassword(newpassword);

		
		System.out.println("执行到此");
/*		if (!"true".equals((String)session.getAttribute("InSetStatus"))) {
			modelAndView.getModel().put("msg", "本次验证超时，请重新验证邮箱！");
			modelAndView.setViewName("login");
			return modelAndView;
		}*/
		
		
		
		map.updatepassword(disktableuser);
		
		session.removeAttribute("InSetStatus");
		
		modelAndView.getModel().put("msg", "密码修改成功");
		modelAndView.setViewName("result");
		return modelAndView;
   	 
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
