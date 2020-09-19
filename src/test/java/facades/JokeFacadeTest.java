/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.JokeDTO;
import entities.Joke;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author hassanainali
 */
public class JokeFacadeTest {
    
    private static EntityManagerFactory emf;
    private static JokeFacade facade;
    
    public JokeFacadeTest(){
    }
    
    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = JokeFacade.getJokeFacade(emf);
       EntityManager em = emf.createEntityManager();
       em.getTransaction().begin();
       em.createQuery("DELETE FROM Joke j").executeUpdate();
       em.createNativeQuery("ALTER TABLE `JOKE` AUTO_INCREMENT = 1").executeUpdate();
    }
    
    @BeforeEach
    public void setUp(){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE from Joke").executeUpdate();
            em.persist(new Joke("I hate Russian dolls. They're so full of themselves.", "Bad", "Russia"));
            em.persist(new Joke("What do you tell actors to break a leg? Because every play has a cast!", "Bad", "Actors"));
            em.persist(new Joke("What do you call a bus full of lawyers going over a cliff? A good start", "Good", "Law"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    @AfterEach
    public void tearDown() {
        // Remove any data after each test was run
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Joke j").executeUpdate();
        em.createNativeQuery("ALTER TABLE `JOKE` AUTO_INCREMENT = 1").executeUpdate();
        em.getTransaction().commit();
    }
    
    @Test
    public void testGetAllJokes(){
        List <JokeDTO> result = facade.getAllJokes();
        assertEquals(3, result.size());
    }
    
    @Test
    public void testGetJokeByID(){
        long id = 1;
        JokeDTO result = facade.getJokeByID((int) id);
        assertNotNull(result);
    }
    
    @Test
    public void testGetRandom(){
        JokeDTO result = facade.getRandom();
        int jokeLength = 10;
        assertTrue(result.getThe_joke().length() > jokeLength);
    }
    
    @Test 
    public void testGetByType() {
        List<JokeDTO> jDTO = facade.getByType("Bad");
        assertEquals(2,jDTO.size(),"2 bad jokes");
    }
}