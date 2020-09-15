package facades;

import dtos.MemberDTO;
import utils.EMF_Creator;
import entities.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MemberFacadeTest {

    private static EntityManagerFactory emf;
    private static MemberFacade facade;
    
    private Member m1;
    private Member m2;
    private Member m3;
    private Member m4;

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

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        new Member("Abed", "cph-ab123", "Breaking Bad");
        new Member("Ali", "cph-cd234", "Fresh Prince");
        new Member("Cecilie", "cph-ef345", "Friends");
        new Member("Rasmus", "cph-gh456", "The Simpsons");
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE from Member").executeUpdate();
            em.persist(m1);
            em.persist(m2);
            em.persist(m3);
            em.persist(m4);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Member m").executeUpdate();
        em.createNativeQuery("ALTER TABLE `MOVIE` AUTO_INCREMENT = 1").executeUpdate();
        em.getTransaction().commit();
    }
    
    @Test
    public void testGetAllMembers(){
        List<MemberDTO> result = facade.getAllMembers();
        assertEquals(4, result.size());
    }

    // TODO: Delete or change this method 
/*    @Test
    public void testAFacadeMethod() {
        assertEquals(2, facade.getRenameMeCount(), "Expects two rows in the database");
    }
*/
}