package io.dev.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.dev.model.Pessoa;

@WebServlet(urlPatterns={"/mensagem"})
public class MensagemServlet extends HttpServlet {
    @Override
    protected void doGet(
        HttpServletRequest req,
        HttpServletResponse resp) throws ServletException, IOException {

    	Pessoa pessoa = new Pessoa();
    	
        PrintWriter out = resp.getWriter();
        out.println("<h1 style='color: red;'>Hello Servlets</h1>");
        out.println("<p>Exibindo pessoa: </p><p>" + pessoa + "</p>");
        out.close();
    }    
}