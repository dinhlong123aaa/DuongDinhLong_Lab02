package iuh.fit.duongdinhlong_lab02.repositories;


import iuh.fit.duongdinhlong_lab02.configs.DatabaseConnection;
import iuh.fit.duongdinhlong_lab02.models.OrderDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class OrderDetailRepository {
    private EntityManager entityManager;

    public OrderDetailRepository() {
        this.entityManager = DatabaseConnection.getInstance().getEntityManager();
    }

    public void addOrderDetail(OrderDetail orderDetail){
        EntityTransaction tr = entityManager.getTransaction();
        try {
            tr.begin();
            entityManager.persist(orderDetail);
            tr.commit();
        }
        catch (Exception e){
            tr.rollback();
            e.printStackTrace();
        }
    }
}