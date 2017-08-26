package service;

import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import Mapper.mapper;
import Table.disktable;
import sun.misc.BASE64Encoder;

@Controller
public class DownloadController {

	@RequestMapping("downloadfiletype.do")
  public ModelAndView	DivideDownloadType(@RequestParam("style") String style, @RequestParam("type") String type,HttpSession session,HttpServletRequest request)  {
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		ModelAndView modelAndView = new ModelAndView();
		mapper map = webApplicationContext.getBean("map", mapper.class);
		
		List<disktable> selectresult=null;

		
		
		if ("enjoy".equals(type) && "enjoyall".equals(style)) {
			
			System.out.println(type);
			System.out.println(style);
			selectresult = map.selectall();
			modelAndView.setViewName("downloaddivide");		
			
			if(selectresult.isEmpty()) {
				modelAndView.getModel().put("msg", "无共享内容！");
			}else {
			modelAndView.getModel().put("msg", "推荐下载：");
			modelAndView.addObject("user_table", selectresult);	
			
			}
			return modelAndView;
		}
		
		
		Map<String,Object> mapset=new HashMap<>();
		String[] split = style.split("_");
		if (type.equals("enjoy") && !style.isEmpty()) {

		mapset.put("tablename", "disktable");
		mapset.put("attributetable", split[0]);
		mapset.put("attribute", split[1]);
		 selectresult = map.selectbystyle(mapset);
			modelAndView.setViewName("downloaddivide");
			
			if(selectresult.isEmpty()) {
				modelAndView.getModel().put("msg", "内容不存在！请选择其他类别！");
			}else {
			modelAndView.getModel().put("msg", "共有"+selectresult.size()+"记录如下：");
			modelAndView.addObject("user_table", selectresult);	
			
			}
			return modelAndView;
		}
		
		
		if ("personal".equals(type) && "personalall".equals(style)) {			
			mapset.put("tablename",(String) session.getAttribute("tablename"));
			List<disktable> selectresultpro=map.selectallpro(mapset);
			modelAndView.setViewName("download");

			if(selectresultpro.isEmpty()) {
				modelAndView.getModel().put("msg", "<br/><br/><br/> 个人空间内容为空! <br/> <a href=\"uploadrequest.do\">点击上传文件</a> <br/><br/><br/>");
						

			}else {
			modelAndView.getModel().put("msg", "共有"+selectresultpro.size()+"条个人记录如下：");
			modelAndView.addObject("user_table", selectresultpro);
			}
					
			return modelAndView;
		}
		
		
		

		
		modelAndView.setViewName("result");		
		return modelAndView;

		

	  
  }
	
	
	@RequestMapping("/downloadfile.do")
	public String download(@RequestParam("id") String id, @RequestParam("type") String type, HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "application/octet-stream");
		
		

		System.out.println(type);
		

		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());

		mapper mapper = webApplicationContext.getBean("map", mapper.class);
		
		Map<String,Object> mapset=new HashMap<>();
		mapset.put("id", Integer.parseInt(id));
		if ("enjoy".equals(type)) {
			mapset.put("tablename", "disktable");
		}else {
			if ("personal".equals(type)) {
				mapset.put("tablename", (String) session.getAttribute("tablename"));
			} else {
				System.out.println("下载类型非共享或个人,不能确定");
			}
		}
		
		disktable file = mapper.select(mapset);



		// 用于判断，根据不同的浏览器，转码成相应的下载文件名
		String filename = file.getRealname();
		String downloadfilename = "";
		String agent = request.getHeader("User-Agent"); // 它用于判断是什么浏览器
		if (agent.contains("MSIE")) {
			// IE浏览器
			downloadfilename = URLEncoder.encode(filename, "utf-8");

		} else if (agent.contains("Firefox")) {
			// 火狐浏览器
			BASE64Encoder base64Encoder = new BASE64Encoder();
			downloadfilename = "=?utf-8?B?" + base64Encoder.encode(filename.getBytes("utf-8")) + "?=";
		} else if (agent.contains("Chrome")) {
			// google浏览器
			downloadfilename = URLEncoder.encode(filename, "utf-8");
		} else {
			// 其它浏览器
			downloadfilename = URLEncoder.encode(filename, "utf-8");
		}

		response.setHeader("Content-Disposition", "attachement;filename=" + downloadfilename);

		FileInputStream inputStream = new FileInputStream(file.getRandname());
		ServletOutputStream outputStream = response.getOutputStream();

		int len = 0;
		byte[] buf = new byte[1024];
		while ((len = inputStream.read(buf)) > 0) {
			outputStream.write(buf, 0, len);
		}

		inputStream.close();

		return null;

	}
	
	
	
	
	
	@RequestMapping("sharefilerequest.do")
public ModelAndView sharefiletiaozhuan(@RequestParam("filename") String filename, @RequestParam("id") String id,HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.getModel().put("shareid",Integer.parseInt(id));
		modelAndView.getModel().put("filename",filename);
		modelAndView.setViewName("sharefileattribute");
		
	return modelAndView;
	
}	
	
	
	@RequestMapping("sharefile.do")
	public String sharefile(@RequestParam("shareid") Integer id,HttpSession session, HttpServletRequest request) {
		
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());

		mapper mapper = webApplicationContext.getBean("map", mapper.class);
		Map<String,Object> mapset=new HashMap<>();
		String tablename = ( String) session.getAttribute("tablename");
		mapset.put("tablename", tablename);
		mapset.put("tablenamepro", (String) session.getAttribute("tablename")+"&"+(String) session.getAttribute("SharedWarehouseUserName"));
		mapset.put("id", id);
		
		

		
		if (mapper.updateshareflag(mapset)<=0) {
			session.setAttribute("statustemp", "文件不能重复共享！");

			return "redirect:/downloadfiletype.do?type=personal&style=personalall";
		}
		
		
		
	int ss=mapper.shareuserfiletablebyid(mapset);
		System.out.println("分享换表结果："+ ss);
		
		
		
		String[] styles = request.getParameterValues("style");
		
		Map<String,Object> mapset1=new HashMap<>();
		
		
		
		String chars="";
		String results="";
		
		for (String string : styles) {
			String[] split = string.split("_");
			mapset1.put("tablename", split[0]);
			chars=chars+","+split[1];
			results=results+","+"'true'";
			
		}
		
		mapset1.put("id", id);
		mapset1.put("value", results);
		mapset1.put("attribute", chars);
		mapper.insertstyle(mapset1);
		
		
		session.setAttribute("statustemp", "共享成功！");

		return "redirect:/downloadfiletype.do?type=personal&style=personalall";	
		
		
		

		
	}
	
	
	
	
	
	
	
	
	
}










