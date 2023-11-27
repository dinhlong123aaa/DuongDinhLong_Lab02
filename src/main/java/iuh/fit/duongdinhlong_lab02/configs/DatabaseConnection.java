package iuh.fit.duongdinhlong_lab02.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class DatabaseConnection {
    private static DatabaseConnection instance = null;
    private EntityManager entityManager;

    private DatabaseConnection() {
        this.entityManager = Persistence.createEntityManagerFactory("DuongDinhLong_Lab02").createEntityManager();
    }

    public static DatabaseConnection getInstance(){
        if(instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}