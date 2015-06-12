package facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.product.Product;
import model.product.Provider;

@Stateless
public class ProductFacade {

	@PersistenceContext(unitName="leviathan-unit")
	private EntityManager em;

	public ProductFacade() {
	}

	public Product createProduct(String code, String name, Float price, String description) {
		Product product = new Product(code, name, description, price);
		try{
			em.persist(product);
			return product;
		}catch(Exception e){
			return null;
		}
	}

	public Product retrieveProduct(Long idProduct) {
		//try {
			Product product = em.find(Product.class, idProduct);
			return product;
		//} catch (Exception e){
		//	return null;
		//}
	}

	public boolean addProvider(Long idProduct,Long idProvider) {

		try {
			Product product= em.find(Product.class, idProduct);
			Provider provider= em.find(Provider.class, idProvider);

			product.addProv(provider);
			provider.addProd(product);

			return true;
		} catch (Exception e) {
			return false;
		} 
	}

	public List<Product> allProduct() {
		try {
			TypedQuery<Product> query = em.createNamedQuery("findAllProduct", Product.class);
			List<Product> productList = query.getResultList();
			return productList;
		} catch (Exception e){
			return null;
		}
	}
}