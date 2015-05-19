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

	private List<Product> productList;

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
}
