package com.bf.christmas.authentification;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.bf.christmas.DAO.UserDAO;
import com.bf.christmas.DAO.dbConnection.DBConnection;
import com.bf.christmas.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gaut
 */
@WebServlet(urlPatterns = {"/connection"})
public class Connection extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //test connection params
        String login = request.getParameter("login");
        User u=null;
        try {
            u  = UserDAO.getByLogin(login);
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, "login="+u.getLogin()+" name"+u.getLastName());
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        String contextPath = request.getContextPath();
        if (u!=null){
            //create session put val
            HttpSession session =  request.getSession();    
            session.setAttribute("User", u);
            Cookie c = new Cookie("co", u.getLogin());
            c.setMaxAge(-1);
            response.addCookie(c);
            
            response.sendRedirect(response.encodeRedirectURL(contextPath + "/"));
        }else{
            response.sendRedirect(response.encodeRedirectURL(contextPath + "/?message=wrong user"));
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
