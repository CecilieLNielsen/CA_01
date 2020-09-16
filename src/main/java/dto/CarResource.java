/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Car;
import facades.CarFacade;
import facades.JokeFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author abed
 */
@Path("Car")
public class CarResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CarResource
     */
    public CarResource() {
    }

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final CarFacade FACADE = CarFacade.getCarFacade(EMF);
    
    /**
     * Retrieves representation of an instance of dto.CarResource
     * @return an instance of java.lang.String
     */
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return GSON.toJson(FACADE.getAllCars());
    }
}
