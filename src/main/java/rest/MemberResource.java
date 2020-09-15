package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
<<<<<<< HEAD:src/main/java/rest/RenameMeResource.java
import entities.Car;
import utils.EMF_Creator;
import facades.FacadeExample;
=======
import dtos.MemberDTO;
import utils.EMF_Creator;
import facades.MemberFacade;
>>>>>>> 8763a27dc5047e3346b93a8ab329657d9efc352a:src/main/java/rest/MemberResource.java
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("groupmembers")
public class MemberResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private Gson g = new Gson();
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final MemberFacade FACADE =  MemberFacade.getMemberFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
<<<<<<< HEAD:src/main/java/rest/RenameMeResource.java
    @Path("cars")
=======
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll(){
        List<MemberDTO> allMembers = FACADE.getAllMembers();
        return GSON.toJson(allMembers);
    }
    
    /*
    @Path("count")
>>>>>>> 8763a27dc5047e3346b93a8ab329657d9efc352a:src/main/java/rest/MemberResource.java
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        List<Car> cars = FACADE.getAllCars();
                //System.out.println("--------------->"+count);
        return g.toJson(cars);  //Done manually so no need for a DTO
    }
    */
}
