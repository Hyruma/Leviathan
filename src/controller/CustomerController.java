package controller;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import model.user.Customer;
import facade.UserFacade;

@ManagedBean
public class CustomerController {

	@EJB
	private UserFacade userFacade;

	private String email;
	private String password;
	private String firstname;
	private String lastname;
	private Date birthday;
	private String customerError;
	private Customer customer;
	private String createError;

	public String loginCustomer() {
		this.customer= this.userFacade.retrieveCustomer(email);
		if ((customer==null)||(!(customer.checkPassword(this.password)))) {
			this.customerError= "Invalid Email\\Password";
			return "index";
		}
		return "customerPage";
	}
	
	public String createCustomer(){
		this.customer= this.userFacade.createCustomer(firstname, lastname, email, password, birthday);
		if (this.customer==null){
			this.setCreateError("Internal Error, please try again later");
			return "newCustomer.xhtml";
		}
		return "confirm";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCustomerError() {
		return customerError;
	}

	public void setCustomerError(String customerError) {
		this.customerError = customerError;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCreateError() {
		return createError;
	}

	public void setCreateError(String createError) {
		this.createError = createError;
	}
	
	public UserFacade getUserFacade() {
		return userFacade;
	}

	public void setUserFacade(UserFacade userFacade) {
		this.userFacade = userFacade;
	}
}
