package controller;

import java.util.*;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import model.product.Product;
import facade.ProductFacade;

@ManagedBean
public class ProductController {

	@EJB
	private ProductFacade productFacade;
	
	private String code;
	private String name;
	private String description;
	private Float price;
	private String creationFailed;
	private List<Product> productList;
	private Product product;
	
	public String createProduct(){
		this.product = productFacade.createProduct(this.code,this.name, this.price, this.description);
		if(this.product==null){
			this.creationFailed = "Creation Failed.";
			return "creatorProduct";
		}	
		return "showProduct";	
	}

	public String allProduct() {
		this.productList = (this.productFacade.allProduct());
		return "catalog";
	}

	public List<Product> getProductList() {
		return this.productList;
	}


	public void setProductList(List<Product> productList) {
		this.productList = productList;
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


	public String getCreationFailed() {
		return creationFailed;
	}

	public void setCreationFailed(String creationFailed) {
		this.creationFailed = creationFailed;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}
