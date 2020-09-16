package utils;

import entities.Member;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class GenerateMembers {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.createQuery("DELETE from Member").executeUpdate();
            em.persist(new Member("Abed", "cph-ab123", "Breaking Bad"));
            em.persist(new Member("Ali", "cph-cd234", "Fresh Prince"));
            em.persist(new Member("Cecilie", "cph-ef345", "Friends"));
            em.persist(new Member("Rasmus", "cph-gh456", "The Simpsons"));
        } finally {
            em.close();
        }
    }
}
