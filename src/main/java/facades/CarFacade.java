/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.CarDTO;
import dtos.JokeDTO;
import entities.Car;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author abed
 */
public class CarFacade {
    private static CarFacade instance;
    private static EntityManagerFactory emf;

    public CarFacade() {
    }

    public static CarFacade getCarFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<CarDTO> getAllJokes() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Car> query = em.createQuery("SELECT c From Car c", Car.class);
        List<Car> cars = query.getResultList();
        List<CarDTO> cDto = new ArrayList();
        cars.forEach((Car car) -> {
            cDto.add(new CarDTO(car));
        });
        return cDto;

    }
    
}