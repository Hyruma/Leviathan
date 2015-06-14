package facade;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.WarehouseLine;
import model.order.OrderLine;

@Stateless(name="warehouseFacade")
public class WarehouseFacade {
	
	@PersistenceContext(unitName="leviathan-unit")
	private EntityManager em;
	
	
	public WarehouseLine retrieveWarehouseLineByProduct(Long id){
		TypedQuery<WarehouseLine> query = this.em.createQuery("SELECT w FROM WarehouseLine w WHERE w.product.id = :id", WarehouseLine.class);
		query.setParameter("id", id);
		WarehouseLine warehouse = query.getSingleResult();
		return warehouse;
	}

	public void decrementQuantity(Map<Long, WarehouseLine> idProduct2WarehouseLine, List<OrderLine> orderLines){
		for(OrderLine orderLine : orderLines){
			Long idProduct = orderLine.getProduct().getId();
			
			Integer quantityWarehouse = idProduct2WarehouseLine.get(idProduct).getQuantity();
			WarehouseLine warehouseLine = idProduct2WarehouseLine.get(idProduct);
			warehouseLine.setQuantity(quantityWarehouse - orderLine.getQuantity());
			this.em.merge(warehouseLine);
		}
	}
}
