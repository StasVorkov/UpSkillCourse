package com.ws.calc.servlets;

import com.ws.calc.service.Calculator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CalculatorServlet",urlPatterns = "/calc")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter pr = response.getWriter();
        Calculator calculator = new Calculator();
        String expression = calculator.getExpression(request);
        pr.println(calculator.calculateExpression(expression));
    }
}
