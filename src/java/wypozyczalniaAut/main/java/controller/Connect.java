package wypozyczalniaAut.main.java.controller;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Named
public class Connect{


    public static EntityManagerFactory createEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("ProjektPai2PU");
    }

    public static EntityManager createEntityManager() {
        return Connect.createEntityManagerFactory().createEntityManager();
    }
}
