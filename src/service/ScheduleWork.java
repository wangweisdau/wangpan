package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service

public class ScheduleWork {

	
	public void cleansession() {

		System.out.println("定时器工作中");
		}
	
	
	
	/*public void cleansession(HttpServletRequest request,HttpSession session) {
		long otime=0;
		String checknum_sever=null;
		System.out.println("定时器工作中");

		checknum_sever =((int) session.getAttribute("checknum"))+"";
        otime = (long) session.getAttribute("checknum_otime");
		
        if (checknum_sever.isEmpty() || otime!=0) {
        	if(((System.currentTimeMillis()-otime)/1000/60)>1) {
        		session.removeAttribute("checknum");
 	    	   session.removeAttribute("checknum_otime");
        	}
        	if(((System.currentTimeMillis()-otime)/1000/60)>10) {
        		session.removeAttribute("InSetStatus");
 	    	   
        	}
		}
        
        
        
	}*/
	
	
}
