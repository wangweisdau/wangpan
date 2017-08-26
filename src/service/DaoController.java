package service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import Mapper.mapper;
import Table.disktable;
import Table.keyfactory;
import Table.userattribute;

@Controller
public class DaoController {

	@RequestMapping("/upload.do")
	public String upload(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {


		
		request.setCharacterEncoding("Utf-8");

		String name = null;
		String personalupload = null;
		String style=null;

		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		ServletFileUpload bean = webApplicationContext.getBean("ServletFileUpload", ServletFileUpload.class);
		mapper map = webApplicationContext.getBean("map", mapper.class);

		List<FileItem> parseRequest = null;
		try {
			parseRequest = (List<FileItem>) bean.parseRequest(request);
		} catch (FileUploadException e) {

			System.out.println("请求内容出现异常");
			e.printStackTrace();
		}
		for (FileItem fileItem : parseRequest) {

			// 得到post方法中传递来的参数
			if (fileItem.isFormField()) {

				String fieldName = fileItem.getFieldName();
				if (fieldName == null) {
					System.out.println("post方法无参数传递");
				} else {
					System.out.println("寻找personalupload" + fieldName);
					
					
					if (fieldName.equalsIgnoreCase("personalupload")) {
						personalupload = fileItem.getString();
					}
					if(fieldName.equalsIgnoreCase("style")) {
 
						style=fileItem.getString();
					}
				}
				 System.out.println("执行到style"+fileItem.getString());
				System.out.println("是否私有：" + personalupload);
			}
		}

		
		if(style==null) {
			session.setAttribute("statustemp",  "未选择属性，请重传！<br/>");
			return "redirect:/uploadrequest.do";
		}
		
		
		
		
		for (FileItem fileItem : parseRequest) {
			// 得到上传文件的名字
			if (!fileItem.isFormField()) {

				name = fileItem.getName();
				long size = fileItem.getSize();

				name = name.substring(name.lastIndexOf("\\") + 1);

				System.out.println("文件大小：" + size);

				Properties prop = webApplicationContext.getBean("db", Properties.class);
				String url = null;
				String url0 = null;

				System.out.println("判断模式" + "ok".equals(personalupload));
				if ("ok".equals(personalupload)) {
					System.out.println("进入私有：" + personalupload);

					Double resspace = (Double) session.getAttribute("sumspace") - (Double) session.getAttribute("usedspace");

					if ((size / 1024 / 1024) > resspace) {
						session.setAttribute("statustemp", name + "上传失败！原因：私有空间不足！<br/>");
						return "redirect:/uploadrequest.do";

					} else {

						url0 = (String) session.getAttribute("url");
						System.out.println("执行到此" + url0);

						url = url0 + "/" + name;
					}
				}

				else {

					url0 = prop.getProperty("disklocation");
					url = url0 + "/" + name;
				}

				disktable disktable = webApplicationContext.getBean("disktable", disktable.class);
				disktable.setFilename(url);
				disktable.setRealname(name);
				String randurl = UUID.randomUUID() + url.substring(url.lastIndexOf("."));
				disktable.setRandname(url0 + "/" + randurl);
				disktable.setFilesize(((double) size) / 1024 / 1024);

				System.out.println("打印要插入的信息"+disktable);
				
				File ff = new File(url0);
				if (!ff.exists()) {
					ff.mkdirs();
				}

				InputStream inputStream = null;
				FileOutputStream fileOutputStream = null;
				try {
					inputStream = fileItem.getInputStream();
					fileOutputStream = new FileOutputStream(new File(url0, randurl));
					int len;
					byte[] buf = new byte[2048];

					while ((len = inputStream.read(buf)) > 0) {
						fileOutputStream.write(buf, 0, len);
					}

					fileOutputStream.close();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("文件流处理异常");
					session.setAttribute("statustemp", name + "上传失败！原因：文件流处理异常！<br/>");
					return "redirect:/uploadrequest.do";
					// e.printStackTrace();
				}
				
				
				
                    keyfactory  keyfactory = webApplicationContext.getBean("keyfactory", keyfactory .class);
					map.keyfactory(keyfactory);
					int id=keyfactory.getId();  //为新文件分配一个主键
					System.out.println("返回的id为"+id);
					System.out.println("返回的id为"+style);
					disktable.setId(id);
					
					
				if ("ok".equals(personalupload)) {
					userattribute userattribute = webApplicationContext.getBean("userattribute", userattribute.class);
					userattribute.setUserattributeid((Integer) session.getAttribute("attributeid"));
					Double usedsize = (Double) session.getAttribute("usedspace") + disktable.getFilesize();
					session.setAttribute("usedspace", usedsize);
					userattribute.setUsedspacesize(usedsize);
					map.updateusedspacesizebyid(userattribute);

					Map<String, Object> mapset = new HashMap<>();
					mapset.put("tablename", (String) session.getAttribute("tablename"));
					mapset.put("realname", disktable.getRealname().replaceAll("\\\\", "\\\\\\\\"));
					mapset.put("filename", disktable.getFilename().replaceAll("\\\\", "\\\\\\\\"));
					mapset.put("randname", disktable.getRandname().replaceAll("\\\\", "\\\\\\\\"));
					mapset.put("filesize", disktable.getFilesize());
					mapset.put("id",disktable.getId());
					map.insertdisktablePro(mapset);
					session.setAttribute("statustemp", "个人文件（" + name + "）上传成功！<br/> <a href=\"downloadfiletype.do?type=personal&style=personalall\">去个人空间查看</a> <br/>");
				}

				else {

					map.insert(disktable);
					Map<String,Object> mapset1=new HashMap<>();
					mapset1.put("id", id);
					String[] split = style.split("_");
					mapset1.put("tablename", split[0]);
					mapset1.put("attribute", ","+split[1]);
					mapset1.put("value", ","+"'true'");
					map.insertstyle(mapset1);	
					session.setAttribute("statustemp", "共享文件(" + name + ")上传成功！<br/> <a href=\"downloadfiletype.do?type=enjoy&style=enjoyall\">去共享空间查看</a> <br/>");
				}

			}
		}
		return "redirect:/uploadrequest.do";
	}

	//// @RequestMapping("/downloadrequest.do")
	// public ModelAndView showbeforedownload(HttpServletRequest request,HttpSession
	//// session) {
	//
	// WebApplicationContext webApplicationContext =
	//// WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
	//
	// mapper map = webApplicationContext.getBean("map", mapper.class);
	//
	// List<disktable> selectall = map.selectall();
	//
	// Map<String,Object> mapset=new HashMap<>();
	// mapset.put("tablename",(String) session.getAttribute("tablename"));
	// List<disktable> selectallpro=map.selectallpro(mapset);
	//
	//
	// ModelAndView modelAndView = new ModelAndView();
	//
	// modelAndView.addObject("user_table", selectall);
	// modelAndView.addObject("user_table_pro", selectallpro);
	//
	// modelAndView.setViewName("downloaddivide");
	//
	// return modelAndView;
	// }

	@RequestMapping("delete.do")
	public String deleteFile(HttpServletRequest request, HttpSession session, @RequestParam("type") String type, @RequestParam("id") String id) {

		boolean delstatus_database = false;
		boolean delstatus_disk = false;

		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());

		mapper map = webApplicationContext.getBean("map", mapper.class);

		disktable orcal = null;

		if ("personal".equals(type)) {
			Map<String, Object> mapset = new HashMap<>();
			mapset.put("tablename", (String) session.getAttribute("tablename"));
			mapset.put("id", Integer.parseInt(id));
			orcal = map.select(mapset);
		} else {

			Map<String, Object> mapset = new HashMap<>();
			mapset.put("tablename", "disktable");
			mapset.put("id", Integer.parseInt(id));
			orcal = map.select(mapset);
		}

		System.out.println(orcal);
		
		
		
		String shareflag = orcal.getShareflag();
		if ("true".equals(shareflag) && "personal".equals(type)) {
			delstatus_disk = true;
		} 

		if ("true".equals(shareflag) && "enjoy".equals(type)){
			File file = new File(orcal.getRandname());
			if (file.delete()) {
				delstatus_disk = true;
			}

		}

		if (!"true".equals(shareflag) && "enjoy".equals(type)){
			String[] split = shareflag.split("&");
			System.out.println(split[0]);
			Map<String, Object> mapset = new HashMap<>();
			mapset.put("tablename", split[0]);
			mapset.put("id", id);
			disktable select = map.select(mapset);
			if (select!=null) {
				delstatus_disk = true;
				Map<String, Object> mapset1 = new HashMap<>();
				mapset1.put("tablename", (String) session.getAttribute("tablename"));
				mapset1.put("id", Integer.parseInt(id));
				map.updateProflag(mapset1);
				
			}else {
				File file = new File(orcal.getRandname());
				if (file.delete()) {
					delstatus_disk = true;
				}
			}
			
			
		}
		
		
		
		int delnum = 0;

		if ("personal".equals(type)) {
			Map<String, Object> mapset1 = new HashMap<>();
			mapset1.put("tablename", (String) session.getAttribute("tablename"));
			mapset1.put("id", Integer.parseInt(id));
			delnum = map.deletepro(mapset1);
		} else {
			delnum = map.delete(Integer.parseInt(id));
			
			
			
			
		}

		if (delnum == 1) {
			delstatus_database = true;

		} else {
			System.out.println("数据库删除异常");
			System.out.println("删除条数为" + delnum);

		}

		if (delstatus_database && delstatus_disk) {
			session.setAttribute("statustemp", orcal.getRealname() + "删除完成<br/>");
		} else {
			session.setAttribute("statustemp", orcal.getRealname() + "删除时出现异常，数据库或硬盘文件丢失，请审查");

		}

		if ("personal".equals(type)) {
			return "redirect:/downloadfiletype.do?type=personal&style=personalall";
		} else {

			return "redirect:/setrequest.do";
		}
	}

}
