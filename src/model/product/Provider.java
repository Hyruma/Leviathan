package model.product;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import model.Address;

@Entity
public class Provider {

	@Id
	private String vatin;
	private String phoneNumber;
	private String email;

	@OneToOne(cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name="address_fk")
	private Address address;

	@ManyToMany(fetch=FetchType.LAZY)
	private List<Product> products;

	public Provider() {
		this.products= new LinkedList<>();
	}

	public Provider(String phoneNumber, String email,
			String vatin) {
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.vatin = vatin;
		this.products= new LinkedList<>();
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVatin() {
		return vatin;
	}

	public void setVatin(String vatin) {
		this.vatin = vatin;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public boolean addProd(Product p) {
		return this.products.add(p);
	}

	@Override
	public int hashCode() {
		return this.address.hashCode() +
				this.email.hashCode() +
				this.phoneNumber.hashCode() +
				this.vatin.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		Provider that= (Provider) o;
		return this.vatin.equals(that.getVatin()) &&
				this.email.equals(that.getEmail()) &&
				this.phoneNumber.equals(that.getPhoneNumber());
	}
}
