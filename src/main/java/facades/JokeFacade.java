/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.JokeDTO;
import entities.Joke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author rh
 */
public class JokeFacade {

    private static JokeFacade instance;
    private static EntityManagerFactory emf;

    public JokeFacade() {
    }

    public static JokeFacade getJokeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<JokeDTO> getAllJokes() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Joke> query = em.createQuery("SELECT j From Joke j", Joke.class);
        List<Joke> jokes = query.getResultList();
        List<JokeDTO> jDto = new ArrayList();
        jokes.forEach((Joke joke) -> {
            jDto.add(new JokeDTO(joke));
        });
        return jDto;

    }

    public JokeDTO getJokeByID(int id) {
        EntityManager em = emf.createEntityManager();
        Joke j = em.find(Joke.class, id);
        return new JokeDTO(j);
    }

    public JokeDTO getRandom() {
         EntityManager em = emf.createEntityManager();
         Random rand = new Random();
         
         try {
             TypedQuery<Joke> query = em.createQuery("SELECT j From Joke j", Joke.class);
             List<Joke> jokes = query.getResultList();
             int random = rand.nextInt(jokes.size());
             return new JokeDTO(jokes.get(random));
         } finally {
             em.close();
         }
    }
    
    
    

}
