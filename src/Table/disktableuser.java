package Table;

public class disktableuser {
	String username;
    String password;
    Integer id;
    Integer userattributeid;
    String email;
    String admin;
    String activation;
    String activationcode;





public String getActivation() {
		return activation;
	}
	public void setActivation(String activation) {
		this.activation = activation;
	}
	public String getActivationcode() {
		return activationcode;
	}
	public void setActivationcode(String activationcode) {
		this.activationcode = activationcode;
	}
public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
public Integer getUserattributeid() {
		return userattributeid;
	}
	public void setUserattributeid(Integer userattributeid) {
		this.userattributeid = userattributeid;
	}
public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
@Override
public String toString() {
	return "disktableuser [username=" + username + ", password=" + password + ", id=" + id + ", userattributeid=" + userattributeid + ", email=" + email + "]";
}




}
