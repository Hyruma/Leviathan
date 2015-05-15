package facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Admin;
import model.Customer;

@Stateless(name="uFacade")
public class UserFacade {

	@PersistenceContext(unitName="leviathan-unit")
	private EntityManager em;
	
	public Admin retrieveAdmin(String validation){
		try {
			TypedQuery<Admin> q= em.createQuery("SELECT a FROM Admin a WHERE a.username= :validation", Admin.class);
			q.setParameter("validation", validation);
			Admin a= q.getSingleResult();
			return a;
		} catch (Exception e){
			return null;
		}
	}
	
	public Customer retrieveCustomer(String validation){
		try {
			TypedQuery<Customer> q= em.createQuery("SELECT c FROM Customer c WHERE c.email= :validation", Customer.class);
			q.setParameter("validation", validation);
			Customer c= q.getSingleResult();
			return c;
		} catch (Exception e){
			return null;
		}
	}
}
