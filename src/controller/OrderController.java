package controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import model.order.Order;
import model.product.Product;
import model.user.Customer;
import facade.OrderFacade;
import facade.ProductFacade;


@ManagedBean
public class OrderController {

	@EJB
	private OrderFacade orderFacade;

	@EJB
	private ProductFacade productFacade;

	private Long idOrder;

	//@ManagedProperty(value="#{param.idProduct}")
	private Long idProduct;

	@ManagedProperty(value="#{param.idCustomer}")
	private Long idCustomer;
	private Customer customer;
	private String orderNotFound;
	private Order order;
	//TODO pecionata di prova
	private Integer quantity;
	
	private List<Product> productList;


	public String retrieveCustomer() {
		this.order= this.orderFacade.retrieveOrder(idOrder);
		if(order==null){
			this.orderNotFound="Order Not Found";
			return "creatorOrder";
		}
		this.customer= this.order.getCustomer();
		return "customer";
	}

	public String makeNewOrder() {
		this.order = this.orderFacade.createOrder(idCustomer);
		if(this.order == null) {
			return "customerPage";
		}
		this.productList = this.productFacade.allProduct();
		return "catalogOrder";
	}

	//TODO come posso passargi la quantity da catalogOrder? e soprattutto quando lo eseguo, dove cazzo mi porta?!
	public String addProduct() {
		//Product product = this.productFacade.retrieveProduct(idProduct);
		//if(product == null)
		//	return "adminPage";
		//this.order.addOrderLine(product, quantity);
		return "index";
	}

	public Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}
	
	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public Long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getOrderNotFound() {
		return orderNotFound;
	}

	public void setOrderNotFound(String orderNotFound) {
		this.orderNotFound = orderNotFound;
	}
}
