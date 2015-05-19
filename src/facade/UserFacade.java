package facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.user.Admin;
import model.user.Customer;

@Stateless(name="uFacade")
public class UserFacade {

	@PersistenceContext(unitName="leviathan-unit")
	private EntityManager em;

	public Admin retrieveAdmin(String validation) {
		try {
			TypedQuery<Admin> query= em.createQuery("SELECT a FROM Admin a WHERE a.username= :validation", Admin.class);
			query.setParameter("validation", validation);
			Admin admin= query.getSingleResult();
			return admin;
		} catch (Exception e){
			return null;
		}
	}

	public Customer retrieveCustomer(String validation) {
		try {
			TypedQuery<Customer> query= em.createQuery("SELECT c FROM Customer c WHERE c.email= :validation", Customer.class);
			query.setParameter("validation", validation);
			Customer customer= query.getSingleResult();
			return customer;
		} catch (Exception e){
			return null;
		}
	}
}
