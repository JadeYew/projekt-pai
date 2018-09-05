package wypozyczalniaAut.main.java.controller;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Named
public class Connect{


    private static EntityManagerFactory factory;
    private static Connect instance;
    
    public synchronized static Connect getConnect() {
        if (instance == null) {
            instance = new Connect();
        }
        return instance;
    }

    public EntityManagerFactory createEntityManagerFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory("ProjektPai2PU");
        }
        return factory;
    }

    public EntityManager createEntityManager() {
        return this.createEntityManagerFactory().createEntityManager();
    }



    public void closeEntityManagerFactory() {
        if (factory != null) {
            factory.close();
        }
    }
}
