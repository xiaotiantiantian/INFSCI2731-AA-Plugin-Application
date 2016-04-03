/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DbConnect.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author shao dai
 */
@WebServlet(name = "securityquestions", urlPatterns = {"/securityquestions"})
public class GetQuestions extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        
        //Check if email exists
        //if not exists -> redirect back to forgotpassword.jsp with Try count
        //if exists -> get questions -> redirect with questions and question ids
        String account_info_id = getIDFromEmailSQL(email);
        
        if (account_info_id.equals("")) { //if no match
            response.sendRedirect("forgotpassword.jsp");
        } else {
            String[] questions = getQuestionsFromIDSQL(account_info_id);
            if (questions != null) {
                request.setAttribute("question_id", questions[0]);
                request.setAttribute("question", questions[1]);
                RequestDispatcher rd = request.getRequestDispatcher("questions.jsp");
                rd.forward(request, response);
            } else {
                //response.sendRedirect("forgotpassword.jsp");
            }
        }
    
    }
    
    protected String getIDFromEmailSQL(String email) {
        Connection connection;
        PreparedStatement preparedStatement;
        
        try {
            connection = DbConnection.getConnection();
            String selectSQL = "SELECT id FROM INFSCI2731.account_info WHERE email_addr = ?";
            preparedStatement = connection.prepareStatement(selectSQL);  
            preparedStatement.setString(1, email);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            boolean val = rs.next();
            if (!val) {
                return "";
            } else {
                return rs.getString("id"); 
            }
  
        } catch (SQLException e) {
                e.printStackTrace();
                return "";
        }   
    }
    
    protected String[] getQuestionsFromIDSQL(String account_info_id) {
        Connection connection;
        PreparedStatement preparedStatement;
        
        try {
            connection = DbConnection.getConnection();
            String selectSQL = "SELECT security_question.question, security_question_answer.id FROM security_question, security_question_answer WHERE security_question_answer.security_question_id = security_question.id AND security_question_answer.account_info_id = ? ORDER BY RAND() LIMIT 0,1;";
            preparedStatement = connection.prepareStatement(selectSQL);  
            preparedStatement.setString(1, account_info_id);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            boolean val = rs.next();
            if (!val) {
                return null;
            } else {
                String[] questions = new String[2];
                questions[0] = rs.getString("security_question_answer.id");
                questions[1] = rs.getString("security_question.question");
                return questions;
            }
           
  
        } catch (SQLException e) {
                e.printStackTrace();
                return null;
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
