package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import model.WarehouseLine;
import model.order.Order;
import model.order.OrderLine;
import model.product.Product;
import model.user.Customer;
import facade.OrderFacade;
import facade.ProductFacade;
import facade.WarehouseFacade;


@ManagedBean
public class OrderController {

	@EJB
	private OrderFacade orderFacade;
	@EJB
	private ProductFacade productFacade;
	@EJB
	private WarehouseFacade warehouseFacade;
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
	



	//TODO problemi a passare l'idCustomer come parametro, se uso direttamente 51 come id funziona correttamente
	public String allOrder() {
		this.orderList = this.orderFacade.allOrders(/*new Long(51)*/ idCustomer);
		if(this.orderList.size() == 0)	//testing
			return "index";				//testing
		return "customerOrders";
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
		this.productList = this.productFacade.allProduct();
		return "catalogOrder";
	}

	//TODO non dovrò mica passargli l'idProduct come input dato che ho fatto così nell'xhtml?
	public String addProduct(Long idProduct) {	
		Product product = this.productFacade.retrieveProduct(idProduct);
		if(product == null)
			return "index";	//momentanea
		this.order.addOrderLine(product, quantity);
		
		this.productList = this.productFacade.allProduct();
		
		return "index";
	}


	public String dispatchOrder(){
		List<OrderLine> orderLines = this.orderFacade.lineOrdersByIdOrder(this.idOrderToDispatch);
		
		if(decrementQuantities(orderLines))
			return "navbar";
		return "index";

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
	
	
	private boolean decrementQuantities(List<OrderLine> orderLines){
		Map<Long, WarehouseLine> idProduct2WarehouseLine = new HashMap<Long, WarehouseLine>();
		
		if(!checkQuantities(idProduct2WarehouseLine, orderLines))
			return false;
		
		this.warehouseFacade.decrementQuantity(idProduct2WarehouseLine, orderLines);
		this.orderFacade.dispatchOrder(this.idOrderToDispatch);
		return true;
	}
	
	private boolean checkQuantities (Map<Long, WarehouseLine> idProduct2WarehouseLine, List<OrderLine> orderLines){
		WarehouseLine warehouseLine = null;
		for(OrderLine orderLine : orderLines){
			Long idProduct = orderLine.getProduct().getId();
			warehouseLine = warehouseFacade.retrieveWarehouseLineByProduct(idProduct);
			idProduct2WarehouseLine.put(idProduct, warehouseLine);
			if(warehouseLine.getQuantity() < orderLine.getQuantity())
				return false;
		}
		return true;
	}

}