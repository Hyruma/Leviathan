package controller;

import java.util.*;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import model.Product;
import facade.ProductFacade;

@ManagedBean
public class ProductListController {
	
	@EJB
	private ProductFacade pFacade;
	
	private List<Product> list;
	
	public String allProduct(){
		this.list = (this.pFacade.allProduct());
		return "catalogo";
	}

	public List<Product> getList() {
		return this.list;
	}

	public void setList(List<Product> list) {
		this.list = list;
	}
}
