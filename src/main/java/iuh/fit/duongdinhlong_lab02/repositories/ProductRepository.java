package iuh.fit.duongdinhlong_lab02.repositories;

import iuh.fit.duongdinhlong_lab02.configs.DatabaseConnection;
import iuh.fit.duongdinhlong_lab02.models.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private EntityManager entityManager;

    public ProductRepository() {
        this.entityManager = DatabaseConnection.getInstance().getEntityManager();
    }

    public void addProduct(Product product){
        EntityTransaction tr = entityManager.getTransaction();
        try {
            tr.begin();
            entityManager.persist(product);
            tr.commit();
        }catch (Exception e){
            tr.rollback();
            e.printStackTrace();
        }
    }

    public List<Product> getAll(){
        entityManager.clear();
        return entityManager.createNamedQuery("Product.FIND_ALL").getResultList();
    }

    public Optional<Product>findById(long id){
        entityManager.clear();
        Query query = entityManager.createNamedQuery("Product.FIND_BY_ID");
        query.setParameter("pId", id);
        List<Product> products = query.getResultList();
        if(products.size() == 0){
            return Optional.empty();
        }
        return Optional.of(products.get(0));
    }

    public void deleteProduct(long id){
        EntityTransaction tr = entityManager.getTransaction();
        try {
            tr.begin();
            Query query = entityManager.createNamedQuery("Product.DELETE");
            query.setParameter("id", id);
            query.executeUpdate();
            tr.commit();
        }
        catch (Exception ex){
            tr.rollback();
            System.out.println(ex);
        }
    }

    public void updateProduct(Product product){
        EntityTransaction tr = entityManager.getTransaction();
        try {
            tr.begin();
            entityManager.merge(product);
            tr.commit();
        }
        catch (Exception e){
            tr.rollback();
            e.printStackTrace();
        }
    }

    public void decreaseQuantity(long pId, int quantity){
        EntityTransaction tr = entityManager.getTransaction();
        try {
            tr.begin();
            Query query = entityManager.createNamedQuery("Product.DECREASE_QUANTITY");
            query.setParameter("id", pId);
            query.setParameter("quantity", quantity);
            query.executeUpdate();
            tr.commit();
        }
        catch (Exception e){
            tr.rollback();
            e.printStackTrace();
        }
    }
}