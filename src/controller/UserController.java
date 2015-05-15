package controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import model.Admin;
import model.Customer;
import facade.UserFacade;

@ManagedBean
public class UserController {
	
	@EJB
	private UserFacade uFacade;
	
	private String validation;
	private String password;
	private String userError;
	private String passwordError;
	private Admin a;
	private Customer c;
	
	
	public String logAdmin(){
		a= this.uFacade.retrieveAdmin(validation);
		if (a==null) {
			this.userError= "User not found";
			return "index";
		}
		
		if (!(a.checkPassword(this.password))){
			this.passwordError= "Wrong Password";
			return "index";
		}
		return "adminPage";
	}
	
	public String logCustomer(){
		c= this.uFacade.retrieveCustomer(validation);
		if (c==null) {
			this.userError= "User not found";
			return "index";
		}
		
		if (!(c.checkPassword(this.password))){
			this.passwordError= "Wrong Password";
			return "index";
		}
		return "customerPage";
	}
	
	public String getValidation() {
		return validation;
	}
	
	public void setValidation(String validation) {
		this.validation = validation;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserError() {
		return userError;
	}

	public void setUserError(String userError) {
		this.userError = userError;
	}

	public String getPasswordError() {
		return passwordError;
	}

	public void setPasswordError(String passwordError) {
		this.passwordError = passwordError;
	}

	public Admin getA() {
		return a;
	}

	public void setA(Admin a) {
		this.a = a;
	}

	public Customer getC() {
		return c;
	}

	public void setC(Customer c) {
		this.c = c;
	}
}
