package model;

import javax.persistence.*;


@Entity
@Table(name="order_line")
public class OrderLine {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	private Float unitPrice;
	private Integer quantity;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Product product;
	
	public OrderLine(){
	}

	public OrderLine(Long id, Float unitPrice, Integer quantity) {
		this.id = id;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}
	
	public Long getId(){
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
} 
