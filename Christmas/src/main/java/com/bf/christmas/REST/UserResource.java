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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


/**
 *
 * @author Gaut
 */
@Path("/users")
public class UserResource {
    
    @Context
    HttpServletRequest request;
    
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> getUsers() throws Exception{
        try {
            
            return UserDAO.readAll();
        } catch (Exception ex) {
            Logger.getLogger(PresentResource.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

}
