package Table;

import java.sql.Date;

public class disktablePro {
	
	private Integer id;
	
	private String realname;
	private String filename;
	private String randname;
	private Date time;
	private long filesize;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getRandname() {
		return randname;
	}
	public void setRandname(String randname) {
		this.randname = randname;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	
	

	
	

}
