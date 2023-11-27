package iuh.fit.duongdinhlong_lab02.repositories;


import iuh.fit.duongdinhlong_lab02.configs.DatabaseConnection;
import iuh.fit.duongdinhlong_lab02.models.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.joda.time.DateTime;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class OrderRepository {
    private EntityManager entityManager;

    public OrderRepository() {
        this.entityManager = DatabaseConnection.getInstance().getEntityManager();
    }

    public void addOrder(Order order){
        EntityTransaction tr = entityManager.getTransaction();
        try {
            tr.begin();
            entityManager.persist(order);
            tr.commit();
        }
        catch (Exception e){
            tr.rollback();
            e.printStackTrace();
        }
    }

    public Optional<Order> findById(long orderId){
        return Optional.ofNullable(entityManager.find(Order.class, orderId));
    }

    public List<Order> getAll(){
        return entityManager.createNamedQuery("Order.GET_ALL").getResultList();
    }
    public List<Order> statisticByDay(String date){
        EntityTransaction tr = entityManager.getTransaction();
        try {
            tr.begin();
            Query query = entityManager.createNamedQuery("Order.STATISTIC_BY_DAY");
            query.setParameter("dateTime", date);
            List<Order> orders = query.getResultList();
            tr.commit();

            return orders;
        }
        catch (Exception e){
            tr.rollback();
            e.printStackTrace();
        }

        return null;
    }

    public List<Order> statisticFromRange(String fromDate, String toDate){
        EntityTransaction tr = entityManager.getTransaction();
        try {
            tr.begin();
            Query query = entityManager.createNamedQuery("Order.STATISTIC_FROM_RANGE");
            query.setParameter("fromDate", fromDate);
            query.setParameter("toDate", toDate);
            List<Order> orders = query.getResultList();
            tr.commit();
            return orders;
        }
        catch (Exception ex){
            tr.rollback();
            System.out.println(ex);
        }
        return null;
    }

    public List<Order> statisicFromRangeByEmployee(String fromDate, String toDate, long empId){
        EntityTransaction tr = entityManager.getTransaction();
        try {
            tr.begin();
            Query query = entityManager.createNamedQuery("Order.STATISTIC_FROM_RANGE_BY_EMPLOYEE");
            query.setParameter("fromDate", fromDate);
            query.setParameter("toDate", toDate);
            query.setParameter("empId", empId);
            List<Order> orders = query.getResultList();
            tr.commit();
            return orders;
        }
        catch (Exception e){
            tr.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public List<Order> statisticByEmployee(long empId){
        EntityTransaction tr = entityManager.getTransaction();
        try {
            tr.begin();
            Query query = entityManager.createNamedQuery("Order.STATISTIC_BY_EMPLOYEE");
            query.setParameter("empId", empId);
            List<Order> orders = query.getResultList();
            tr.commit();
            return orders;
        }
        catch (Exception e){
            tr.rollback();
            e.printStackTrace();
        }

        return null;
    }

    public List<Order> statisticByDayByEmployee(String date, long empId){
        EntityTransaction tr = entityManager.getTransaction();
        try {
            tr.begin();
            Query query = entityManager.createNamedQuery("Order.STATISTIC_BY_DAY_BY_EMPLOYEE");
            query.setParameter("date", date);
            query.setParameter("empId", empId);
            List<Order> orders = query.getResultList();
            tr.commit();
            return orders;
        }
        catch (Exception e){
            tr.rollback();
            e.printStackTrace();
        }

        return null;
    }
}