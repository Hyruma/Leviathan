package model;
import javax.persistence.*;

@Entity
public class Address{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String street;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	
	public Address(){	
	}
	
	public Address(String street, String city, String state,
			String zipcode, String country) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.country = country;
	}

	public Long getId() {
		return id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	

	@Override
	public int hashCode() {
		return (this.city.hashCode()+this.country.hashCode()+this.state.hashCode()+
				this.street.hashCode()+this.zipcode.hashCode());
	}

	@Override
	public boolean equals(Object o) {
		Address that= (Address) o;
		return this.city.equals(that.getCity())&&
			     this.country.equals(that.getCountry())&&
			     this.state.equals(that.getState())&&
			     this.street.equals(that.getStreet())&&
			     this.zipcode.equals(that.getZipcode());
	}
}
