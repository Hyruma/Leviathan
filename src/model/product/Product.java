package model.product;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedQuery(name= "findAllProduct", query= "SELECT p FROM Product p")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String code;
	private String name;
	private String description;
	private Float price;
	private Integer quantity;

	@ManyToMany(mappedBy="products", cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Provider> providerList;

	public Product() {
		this.providerList= new LinkedList<Provider>();
	}

	public Product(String name, String description,
			Float price, int quantity) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity= quantity;
		this.providerList= new LinkedList<Provider>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public List<Provider> getProviderList() {
		return providerList;
	}

	public void setProviderList(List<Provider> providerList) {
		this.providerList = providerList;
	}

	public boolean addProv(Provider provider) {
		return this.providerList.add(provider);
	}

	public String getCode() {
		return code;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode(){
		return this.code.hashCode() +
				this.name.hashCode() +
				this.description.hashCode() +
				this.price.hashCode();
	}

	@Override
	public boolean equals(Object o){
		Product that= (Product) o;
		return this.code.equals(that.getCode()) &&
				this.name.equals(that.getName()) &&
				this.price.equals(that.getPrice()) &&
				this.description.equals(that.getDescription());
	}
}




