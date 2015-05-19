package controller;

import java.util.*;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import model.product.Product;
import facade.ProductFacade;

@ManagedBean
public class ProductController {

	@EJB
	private ProductFacade pFacade;

	private List<Product> productList;

	public String allProduct() {
		this.productList = (this.pFacade.allProduct());
		return "catalog";
	}

	public List<Product> getProductList() {
		return this.productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
}
