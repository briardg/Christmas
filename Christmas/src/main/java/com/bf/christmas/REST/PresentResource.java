package com.bf.christmas.REST;

import com.bf.christmas.DAO.BuyerDAO;
import com.bf.christmas.DAO.PresentDAO;
import com.bf.christmas.DAO.UserDAO;
import com.bf.christmas.model.Present;
import com.bf.christmas.model.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Gaut
 */
@Path("/presents")
public class PresentResource {
    
    @Context
    HttpServletRequest request;
    
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Present> getPresentsByUser() throws Exception{
        try {
            
            //get All presents by users
            List<Present> presents=PresentDAO.readAll( ((User)request.getSession(false).getAttribute("User")).getId() );
            for(Present p : presents){
                // get All buyer by presents
                p.setBuyers(BuyerDAO.readAll(p.getId()));
            }
            
            return presents;
        } catch (Exception ex) {
            Logger.getLogger(PresentResource.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    
    @GET
    @Path("/ByUsers")
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> getPresentsByUsers() throws Exception{
        try {
            //get All Users
            List<User> users = UserDAO.readAllDifferentFrom(( (User)request.getSession(false).getAttribute("User")).getId() );
            
            for(User u : users){
                //get All presents by users
                List<Present> presents=PresentDAO.readAll(u.getId());
                for(Present p : presents){
                    // get All buyer by presents
                    p.setBuyers(BuyerDAO.readAll(p.getId()));
                }
                u.setPresents(presents);
            }
            
            return users;
        } catch (Exception ex) {
            Logger.getLogger(PresentResource.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Present getPresent(@PathParam("id") int id) throws Exception{
        try {
            
           return PresentDAO.read(id);
        } catch (Exception ex) {
            Logger.getLogger(PresentResource.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response savePresent(Present p){
        try {
            //TODO update owner with session 
            p.setOwner( (User)request.getSession(false).getAttribute("User") );
            PresentDAO.save(p);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updatePresent(Present p){
        try {          
            PresentDAO.update(p);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }
    
    @PUT
    @Path("Buy")
    public Response BuyPresent(@QueryParam("id") int id, @QueryParam("percentage") int percentage){
        try {          
            //TODO: change to user id
            PresentDAO.buy( ((User)request.getSession(false).getAttribute("User")).getId() ,id,percentage);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
        
        
    }
    
    
    @DELETE
    @Path("{id}")
    public Response deletePresent(@PathParam("id") Integer id){
        try {
            PresentDAO.delete(id);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }
}
