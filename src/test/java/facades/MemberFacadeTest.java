package facades;

import dtos.MemberDTO;
import utils.EMF_Creator;
import entities.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberFacadeTest {

    private static EntityManagerFactory emf;
    private static MemberFacade facade;

    public MemberFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = MemberFacade.getMemberFacade(emf);
       EntityManager em = emf.createEntityManager();
       em.getTransaction().begin();
       em.createQuery("DELETE FROM Member m").executeUpdate();
       em.createNativeQuery("ALTER TABLE `MEMBER` AUTO_INCREMENT = 1").executeUpdate();
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE from Member").executeUpdate();
            em.persist(new Member("Abed", "cph-ab123", "Breaking Bad"));
            em.persist(new Member("Ali", "cph-cd234", "Fresh Prince"));
            em.persist(new Member("Cecilie", "cph-ef345", "Friends"));
            em.persist(new Member("Rasmus", "cph-gh456", "The Simpsons"));
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
        em.createQuery("DELETE FROM Member m").executeUpdate();
        em.createNativeQuery("ALTER TABLE `MEMBER` AUTO_INCREMENT = 1").executeUpdate();
        em.getTransaction().commit();
    }
    
    @Test
    public void testGetAllMembers(){
        List<MemberDTO> result = facade.getAllMembers();
        assertEquals(4, result.size());
    }
}