package model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	private String firstName;
	private String lastName;
	
	@Temporal(TemporalType.DATE)
	private Date birthday;

	@Column(unique=true, nullable=false)
	private String email;
	private String password;

	@OneToOne(cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name="address_fk")
	private Address address;

	@OneToMany(cascade= {CascadeType.PERSIST,CascadeType.REMOVE},mappedBy="customer")
	@OrderBy("creationTime asc")
	private List<Order> listOrders;

	public Customer(){
		this.listOrders= new LinkedList<>();
	}

	public Customer(String name, String surname, Date birthday,
			String email, String password){
		this.firstName= name;
		this.lastName= surname;
		this.birthday= birthday;
		this.email= email;
		this.password= password;
		this.listOrders= new LinkedList<>();
	}

	public Long getId() {
		return id;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Order> getListOrders() {
		return listOrders;
	}

	public void setListOrders(List<Order> listOrders) {
		this.listOrders = listOrders;
	}
	
	public boolean checkPassword(String password){
		return this.password.equals(password);
	}

	@Override
	public int hashCode(){
		return this.firstName.hashCode() + 
				this.lastName.hashCode() +
				this.email.hashCode() +
				this.birthday.hashCode() +
				this.address.hashCode();
	}

	@Override
	public boolean equals(Object o){
		Customer that= (Customer) o;
		return this.firstName.equals(that.getFirstName()) && 
				this.lastName.equals(that.getLastName()) &&
				this.email.equals(that.email) &&
				this.birthday.equals(that.birthday);
	}
}
