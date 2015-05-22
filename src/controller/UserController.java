package controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import model.user.Admin;
import model.user.Customer;
import facade.UserFacade;

@ManagedBean
public class UserController {

	@EJB
	private UserFacade userFacade;

	private String user;
	private String password;
	private String loginError;
	private Admin admin;
	private Customer customer;


	public String loginAdmin() {
		this.admin= this.userFacade.retrieveAdmin(user);
		if ((admin==null)||(!(admin.checkPassword(this.password)))){
			this.setLoginError("Invalid Email\\Password");
			return "index";
		}
		return "adminPage";
	}

	public String loginCustomer() {
		this.customer= this.userFacade.retrieveCustomer(user);
		if ((customer==null)||(!(customer.checkPassword(this.password)))) {
			this.setLoginError("Invalid Username\\Password");
			return "index";
		}
		return "customerPage";
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getLoginError() {
		return loginError;
	}

	public void setLoginError(String loginError) {
		this.loginError = loginError;
	}
}
