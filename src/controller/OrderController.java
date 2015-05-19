package controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import model.order.Order;
import model.user.Customer;
import facade.OrderFacade;


@ManagedBean
public class OrderController {

	@EJB
	private OrderFacade orderFacade;

	private Long idOrder;
	private Customer customer;
	private String orderNotFound;
	private Order order;

	public String retrieveCustomer() {
		this.order= this.orderFacade.retrieveOrder(idOrder);
		if(order==null){
			this.orderNotFound="Order Not Found";
			return "creatorOrder";
		}
		this.customer= this.order.getCustomer();
		return "customer";
	}

	public Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
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
