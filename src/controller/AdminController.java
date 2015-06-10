package controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import model.user.Admin;
import facade.UserFacade;

@ManagedBean
public class AdminController {

	@EJB
	private UserFacade userFacade;

	private String username;
	private String password;
	private String adminError;
	private Admin admin;

	public String loginAdmin() {
		this.admin= this.userFacade.retrieveAdmin(username);
		if ((admin==null)||(!(admin.checkPassword(this.password)))){
			this.adminError= "Invalid Username\\Password";
			return "index";
		}
		return "navbar";
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String user) {
		this.username = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getAdminError() {
		return adminError;
	}

	public void setAdminError(String adminError) {
		this.adminError = adminError;
	}
}
