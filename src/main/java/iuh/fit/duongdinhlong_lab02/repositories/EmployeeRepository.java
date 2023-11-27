package iuh.fit.duongdinhlong_lab02.repositories;


import iuh.fit.duongdinhlong_lab02.configs.DatabaseConnection;
import iuh.fit.duongdinhlong_lab02.models.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

public class EmployeeRepository {
    private EntityManager entityManager;

    public EmployeeRepository() {
        this.entityManager = DatabaseConnection.getInstance().getEntityManager();
    }

    public void addEmployee(Employee employee){
        EntityTransaction tr = entityManager.getTransaction();
        try {
            tr.begin();
            entityManager.persist(employee);
            tr.commit();
        }
        catch (Exception e){
            tr.rollback();
            e.printStackTrace();
        }
    }

    public List<Employee> getAll(){
        return entityManager.createNamedQuery("Employee.FIND_ALL").getResultList();
    }

    public Optional<Employee> findById(long id){
        Query query = entityManager.createNamedQuery("Employee.FIND_BY_ID");
        query.setParameter("empId", id);
        List<Employee> employees = query.getResultList();
        if(employees.size() == 0){
            return Optional.empty();
        }

        return Optional.of(employees.get(0));
    }

    public void updateEmployee(Employee employee){
        EntityTransaction tr = entityManager.getTransaction();
        try {
            tr.begin();
            entityManager.merge(employee);
            tr.commit();
        }
        catch (Exception e){
            tr.rollback();
            e.printStackTrace();
        }
    }

    public void deleteEmployee(long id){
        EntityTransaction tr = entityManager.getTransaction();
        try {
            tr.begin();
            Query query = entityManager.createNamedQuery("Employee.DELETE");
            query.setParameter("empId", id);
            query.executeUpdate();

            tr.commit();
        }
        catch (Exception e){
            tr.rollback();
            e.printStackTrace();
        }

    }
}