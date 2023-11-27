package iuh.fit.duongdinhlong_lab02.repositories;

import iuh.fit.duongdinhlong_lab02.configs.DatabaseConnection;
import iuh.fit.duongdinhlong_lab02.models.ProductImage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class ProductImageRepository {
    private EntityManager entityManager;

    public ProductImageRepository() {
        this.entityManager = DatabaseConnection.getInstance().getEntityManager();
    }

    public void addProductImage(ProductImage productImage){
        EntityTransaction tr = entityManager.getTransaction();
        try {
            tr.begin();
            entityManager.persist(productImage);
            tr.commit();
        }
        catch (Exception e){
            tr.rollback();
            e.printStackTrace();
        }
    }

    public void deleteProductImage(long id, long productId){
        EntityTransaction tr = entityManager.getTransaction();
        try {
            tr.begin();
            Query query = entityManager.createNamedQuery("ProductImage.DELETE");
            query.setParameter("id", id);
            query.setParameter("pId", productId);
            query.executeUpdate();
            tr.commit();
        }
        catch (Exception e){
            tr.rollback();
            e.printStackTrace();
        }
    }
}
