package facade;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

	//serve per persistere le linee d'ordine una volta che l'ordine è completato
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
	
	public List<Order> allOrders(Long idCustomer) {
//		try{
			TypedQuery<Order> query = 
					em.createQuery("SELECT o FROM Order o WHERE o.customer.id = :idC", Order.class).setParameter("idC", idCustomer);
			List<Order> orderList = query.getResultList();
			return orderList;
//		}catch(Exception e){
//			return null;
//		}
	}

	public List<Order> allProcessedOrders() {
		try{
			TypedQuery<Order> query = this.em.createQuery("SELECT co FROM Order co WHERE co.processingTime IS NOT NULL AND co.closingTime IS NULL ", Order.class);
			List<Order> processedOrders = query.getResultList();
			return processedOrders;	
		}catch(Exception e){
			return null;
		}
		
	}

	public void dispatchOrder(Long idOrderToDispatch) {
		try{
			Order orderToDispatch = this.em.find(Order.class, idOrderToDispatch);
			orderToDispatch.setClosingTime(new Date());
			this.em.merge(orderToDispatch);
			return;
		}catch(Exception e){
			return;
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
