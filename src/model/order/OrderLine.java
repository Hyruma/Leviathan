package model.order;

import javax.persistence.*;

import model.product.Product;


@Entity
@Table(name="order_line")
public class OrderLine {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	private Float unitPrice;
	private Integer quantity;

	@OneToOne
	private Product product;

	public OrderLine() {
	}

	public OrderLine(Float unitPrice, Integer quantity, Product product) {
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.product = product;
	}

	public Long getId() {
		return this.id;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
} 
