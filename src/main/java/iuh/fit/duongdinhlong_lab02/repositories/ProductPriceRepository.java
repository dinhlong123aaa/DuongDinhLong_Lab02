package iuh.fit.duongdinhlong_lab02.repositories;

import iuh.fit.duongdinhlong_lab02.configs.DatabaseConnection;
import iuh.fit.duongdinhlong_lab02.models.ProductPrice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ProductPriceRepository {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public ProductPriceRepository() {
        this.entityManager = DatabaseConnection.getInstance().getEntityManager();
        this.entityTransaction = entityManager.getTransaction();
    }

    public void addProductPrice(ProductPrice productPrice){
        try {
            entityTransaction.begin();
            entityManager.persist(productPrice);
            entityTransaction.commit();
        }
        catch (Exception ex){
            entityTransaction.rollback();
            System.out.println(ex);
        }
    }
}