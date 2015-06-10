package controller;

import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import model.user.Admin;
import facade.UserFacade;

@ManagedBean
public class AdminController {

	@EJB
	private UserFacade userFacade;
	private String username;
	private String password;
	private String adminError;
	@SessionScoped
	private Admin admin;
	private Map<String, Object> sessionMap;

	public String loginAdmin() {
		this.admin= this.userFacade.retrieveAdmin(this.username);
		if ((admin==null)||(!(admin.checkPassword(this.password)))){
			this.adminError= "Invalid Username\\Password";
			return "index";
		}
		setSession();
		return "navbar";
	}
	
	public String logout(){
		this.sessionMap.remove(this.username + " admin");
		return "index.xhtml";
	}
	
	private void setSession(){
		this.sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		this.sessionMap.put(this.username + " admin", this.admin);
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
