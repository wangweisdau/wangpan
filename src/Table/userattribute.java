package Table;

public class userattribute {
	Integer userattributeid;
	Integer prospaceid;
	Double prospacesize;
	String filelocation;
	String prodisktablename;
	Double usedspacesize;
	


	public Double getUsedspacesize() {
		return usedspacesize;
	}
	public void setUsedspacesize(Double usedspacesize) {
		this.usedspacesize = usedspacesize;
	}
	public String getProdisktablename() {
		return prodisktablename;
	}
	public void setProdisktablename(String prodisktablename) {
		this.prodisktablename = prodisktablename;
	}


	public Double getProspacesize() {
		return prospacesize;
	}
	public void setProspacesize(Double prospacesize) {
		this.prospacesize = prospacesize;
	}
	public Integer getUserattributeid() {
		return userattributeid;
	}
	public void setUserattributeid(Integer userattributeid) {
		this.userattributeid = userattributeid;
	}
	public Integer getProspaceid() {
		return prospaceid;
	}
	public void setProspaceid(Integer prospaceid) {
		this.prospaceid = prospaceid;
	}
	public String getFilelocation() {
		return filelocation;
	}
	public void setFilelocation(String filelocation) {
		this.filelocation = filelocation;
	}
	@Override
	public String toString() {
		return "userattribute [userattributeid=" + userattributeid + ", prospaceid=" + prospaceid + ", prospacesize="
				+ prospacesize + ", filelocation=" + filelocation + ", prodisktablename=" + prodisktablename + "]";
	}



	
	
	
}
