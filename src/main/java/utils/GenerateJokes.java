/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Joke;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rh
 */
public class GenerateJokes {
    
    public static EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    public static void main(String[] args) {
        
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE from Joke").executeUpdate();
            em.persist(new Joke("I hate Russian dolls. They're so full of themselves.", "Bad", "Russia"));
            em.persist(new Joke("What do you tell actors to break a leg? Because every play has a cast!", "Bad", "Actors"));
            em.getTransaction().commit();
            
            
        } finally {
            em.close();
        }
    }
    
}
