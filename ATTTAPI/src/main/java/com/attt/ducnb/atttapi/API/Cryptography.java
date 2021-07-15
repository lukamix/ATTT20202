package com.attt.ducnb.atttapi.API;

import com.attt.ducnb.Model.EsknZMAC;
import com.attt.ducnb.atttapi.Utils.Schnorr;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "cryptography", urlPatterns = {"/cryptography"})
public class Cryptography extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Fn = request.getParameter("Fn");
        String id = request.getParameter("id");
        String e = request.getParameter("e");
        String s = request.getParameter("s");
        String u = request.getParameter("u");
        String w = request.getParameter("w");
        try {
            EsknZMAC esknzmac = new EsknZMAC(Schnorr.Verify(Fn, id, e, s, u, w));
            Gson gson = new Gson();
            String ersJson = gson.toJson(esknzmac);

            try (PrintWriter printwriter = response.getWriter()) {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                printwriter.write(ersJson);
            }
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(Cryptography.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
