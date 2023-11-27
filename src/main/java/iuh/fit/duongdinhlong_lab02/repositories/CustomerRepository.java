package iuh.fit.duongdinhlong_lab02.repositories;

import iuh.fit.duongdinhlong_lab02.configs.DatabaseConnection;
import iuh.fit.duongdinhlong_lab02.models.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

public class CustomerRepository {
    private EntityManager entityManager;

    public CustomerRepository() {
        this.entityManager = DatabaseConnection.getInstance().getEntityManager();
    }

    public void addCustomer(Customer customer){
        EntityTransaction tr = entityManager.getTransaction();
        try {
            tr.begin();
            entityManager.persist(customer);
            tr.commit();
        }
        catch (Exception e){
            tr.rollback();
            e.printStackTrace();
        }

    }
    public List<Customer> getAll(){
        List<Customer> customers = entityManager.createNamedQuery("Customer.FIND_ALL").getResultList();
        return customers;
    }

    public Optional<Customer> findById(long id){
        return Optional.ofNullable(entityManager.find(Customer.class, id));
    }

    public void updateCustomer(Customer customer){
        EntityTransaction tr = entityManager.getTransaction();
        try {
            tr.begin();
            entityManager.merge(customer);
            tr.commit();
        }
        catch (Exception e){
            tr.rollback();
            e.printStackTrace();
        }
    }
}