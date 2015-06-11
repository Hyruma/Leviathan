package model.product;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedQuery(name= "findAllProduct", query= "SELECT p FROM Product p")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String code;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private Float price;

	@ManyToMany(mappedBy="products", cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Provider> providerList;

	public Product() {
		this.providerList= new LinkedList<Provider>();
	}

	public Product(String code, String name, String description,
			Float price) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.price = price;
		this.providerList= new LinkedList<Provider>();
	}

	//TODO pecionata necessaria (mi serve get e set id)
	public Long getId() {
		return id;
	}

	//TODO pecionata necessaria (mi serve get e set id)
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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




}




