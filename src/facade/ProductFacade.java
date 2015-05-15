package facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Product;
import model.Provider;

@Stateless(name="pFacade")
public class ProductFacade {

	@PersistenceContext(unitName="leviathan-unit")
	private EntityManager em;

	public ProductFacade(){
	}

	public Product createProduct(String nome, String descrizione, Float prezzo, 
			String codice){

		Product p= new Product();
		p.setCode(codice);
		p.setDescription(descrizione);
		p.setName(nome);
		p.setPrice(prezzo);

		try{		
			em.persist(p);
			return p;
		} catch (Exception e){
			return null;
		}
	}

	public Product retrieveProduct(Long idProdotto){
		try {
			Product p=em.find(Product.class, idProdotto);
			return p;
		} catch (Exception e){
			return null;
		}
	}

	public boolean addProvider(Long idProduct,Long idProvider){

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

	public List<Product> allProduct(){
		try {
			TypedQuery<Product> q= em.createNamedQuery("findAllProduct", Product.class);
			List<Product> lp= q.getResultList();
			return lp;
		} catch (Exception e){
			return null;
		}
	}
}