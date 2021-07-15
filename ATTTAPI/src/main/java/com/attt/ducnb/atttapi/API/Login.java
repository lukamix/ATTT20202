package com.attt.ducnb.atttapi.API;

import com.attt.ducnb.Model.Admin;
import com.attt.ducnb.Model.ERS;
import com.attt.ducnb.atttapi.Utils.Schnorr;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String hpw= request.getParameter("hpw");
        if(id.compareTo(Admin.id)==0 && hpw.compareTo(Admin.hpw)==0){
            ERS ers = new ERS(Schnorr.GenerateERS(id, hpw));
            Gson gson = new Gson();
            String ersJson = gson.toJson(ers);

            try (PrintWriter printwriter = response.getWriter()) {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                printwriter.write(ersJson);
            }
        }
        else {
            try (PrintWriter printwriter = response.getWriter()) {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Login API For USB";
    }

}
