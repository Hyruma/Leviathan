package model.order;

import java.util.*;

import javax.persistence.*;

import model.product.Product;
import model.user.Customer;

@Entity
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date creationTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Date closingTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Date processingTime;

	@ManyToOne
	private Customer customer;

	@OneToMany(fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name="orders_id")
	private List<OrderLine> orderLines;

	public Order() {
		this.orderLines= new LinkedList<>();
	}

	public Order(Date creationTime, Customer customer) {
		this.creationTime = creationTime;
		this.customer = customer;
		this.orderLines= new LinkedList<>();
	}

	/**
	 * adds an orderLine to the order through product and quantity
	 * @param product
	 * @param quantity
	 */
	public void addOrderLine(Product product, int quantity) {
		this.orderLines.add(new OrderLine(product.getPrice(), quantity, product));
	}
	
	public Long getId() {
		return this.id;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(Date closingTime) {
		this.closingTime = closingTime;
	}

	public Date getProcessingTime() {
		return processingTime;
	}

	public void setProcessingTime(Date processingTime) {
		this.processingTime = processingTime;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public boolean isClosed() {
		return (this.closingTime!=null)&&(this.processingTime==null);
	}

	@Override
	public int hashCode() {
		return //this.customer.hashCode() +
				this.creationTime.hashCode();
				//this.closingTime.hashCode() +
				//this.processingTime.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		Order that= (Order) o;
		return this.customer.equals(that.getCustomer()) &&
				this.creationTime.equals(that.getCreationTime()) &&
				this.closingTime.equals(that.getClosingTime()) &&
				this.processingTime.equals(that.getProcessingTime());				
	}

}
