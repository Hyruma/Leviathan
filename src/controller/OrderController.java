package controller;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import model.order.Order;
import model.order.OrderLine;
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
	@ManagedProperty(value="#{param.idProduct}")
	private Long idProduct;
	@ManagedProperty(value="#{param.idOrderToDispatch}")
	private Long idOrderToDispatch;
	@ManagedProperty(value="#{param.idCustomer}")
	private Long idCustomer;
	private Customer customer;
	private String orderNotFound;
	private Order order;
	//TODO pecionata di prova
	private Integer quantity;

	private List<Order> orderList;
	
	private List<Product> productList;
	


	public String showOrders() {
		return "customerOrders";
	}

	public List<Order> allOrder() {
		return this.orderFacade.allOrders(/*new Long(51)*/ idCustomer);
	}
	
	public String showOrder(Order order) {
		for(OrderLine ol : order.getOrderLines())
			this.productList.add(ol.getProduct());
		return "showOrder";
	}
	
	
	
	public String retrieveCustomer() {
		this.order = this.orderFacade.retrieveOrder(idOrder);
		if(order == null){
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
		return "catalogOrder";
	}
	
	public List<Product> allProduct() {
		return this.productFacade.allProduct();
	}

	//TODO non dovrà mica passargli l'idProduct come input dato che ho fatto così nell'xhtml?
	public String addProduct(/*Long idProduct*/) {	
		Product product = this.productFacade.retrieveProduct(idProduct);
		if(product == null || quantity == null)
			return "index.xhtml";	//momentanea
		this.order.addOrderLine(product, quantity);	//l'order è null: come posso risolvere?
		
		this.productList = this.productFacade.allProduct();
		
		return "index.xhtml";
	}
	
	
	
	public String dispatchOrder(){
		this.orderFacade.dispatchOrder(this.idOrderToDispatch);
		return "navbar.xhtml";
	}
	
	public String dispatchOrder(Long idOrderToDispatch) {
		this.idOrderToDispatch = idOrderToDispatch;
		return dispatchOrder();
	}
	
	public List<Order> getAllProcessedOrders(){
		return this.orderFacade.allProcessedOrders();
	}
	
	public Long getIdOrderToDispatch() {
		return idOrderToDispatch;
	}

	public void setIdOrderToDispatch(Long idOrderToDispatch) {
		this.idOrderToDispatch = idOrderToDispatch;
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
	
	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
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
