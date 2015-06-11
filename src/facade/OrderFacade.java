package facade;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.order.Order;
import model.order.OrderLine;
import model.user.Customer;


@Stateless(name="oFacade")
public class OrderFacade {

	@PersistenceContext(unitName="leviathan-unit")
	private EntityManager em;


	public Order createOrder(Long idCustomer) {
		Customer customer = this.em.find(Customer.class, idCustomer);
		Order order= new Order(new Date(), customer);
		customer.addOrder(order);
		try{		
			em.persist(order);
			return order;
		} catch (Exception e){
			return null;
		}
	}

	public Order retrieveOrder(Long idOrder) {
		try {
			Order order=em.find(Order.class, idOrder);
			return order;
		} catch (Exception e){
			return null;
		}
	}

	public boolean addOrderLines(long idOrder, List<OrderLine> orderLines) {
		try{
			Order order= this.retrieveOrder(idOrder);

			if (order==null)
				return false;
			order.setOrderLines(orderLines);
			return true;
		} catch (Exception e){
			return false;
		}
	}

	//	public boolean processOrder(long idOrder) {
	//		Order order= this.retrieveOrder(idOrder);
	//		if (order==null)
	//			return false;
	//		
	//		for(OrderLine orderLine: order.getOrderLines()){
	//			Product p= em.find(Product.class, orderLine.g)
	//		}
	//		try{		
	//			em.persist(order);
	//			return true;
	//		} catch (Exception e){
	//			return false;
	//		}
	//	}
}
