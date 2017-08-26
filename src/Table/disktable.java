package Table;

import java.sql.Date;

public class disktable {
	
	private Integer id;
	
	private String realname;
	private String filename;
	private String randname;
	private Date time;
	private double filesize;
	private String shareflag;
	
	
	
	public String getShareflag() {
		return shareflag;
	}
	public void setShareflag(String shareflag) {
		this.shareflag = shareflag;
	}
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
	public double getFilesize() {
		return filesize;
	}
	public void setFilesize(double filesize) {
		this.filesize = filesize;
	}
	@Override
	public String toString() {
		return "disktable [id=" + id + ", realname=" + realname + ", filename=" + filename + ", randname=" + randname
				+ ", time=" + time + ", filesize=" + filesize + ", shareflag=" + shareflag + "]";
	}
	
	
	
	
	
	

	
	

}
