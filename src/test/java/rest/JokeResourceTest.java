package rest;

import entities.Joke;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static io.restassured.internal.common.assertion.AssertParameter.notNull;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static rest.MemberResourceTest.startServer;
import utils.EMF_Creator;

/**
 *
 * @author hassanainali
 */
public class JokeResourceTest {
    
    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }
    
    @BeforeAll
    public static void setUpClass() {
        // This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Joke j").executeUpdate();
        em.createNativeQuery("ALTER TABLE `JOKE` AUTO_INCREMENT = 1").executeUpdate();
        em.getTransaction().commit();

        httpServer = startServer();

        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }
    
    @AfterAll
    public static void closeTestServer() {
        // System.in.read();
        // Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }
    
    @BeforeEach
    public void setUp(){
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
    public void testGetAll() {
        given()
                .contentType("application/json")
                .get("/joke/all")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("jokeInformation", hasSize(2));
    }
    /*
    @Test
    public void testGetJokeByID(){
        
    }
    */
    /*
    @Test
    public void testGetRandom(){
        given()
                .contentType("application/json")
                .get("/joke/random")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body()
    }
    */
}
