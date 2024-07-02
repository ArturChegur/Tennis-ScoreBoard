package main.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.util.ParameterValidator;

import java.io.IOException;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("pages/new-match-page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstPlayer = req.getParameter("firstPlayer");
        String secondPlayer = req.getParameter("secondPlayer");

        String errorMessage = ParameterValidator.validateNames(firstPlayer, secondPlayer);
        if (errorMessage != null) {
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("pages/new-match-page.jsp").forward(req, resp);
        } else {
            //todo going to play match
        }
    }
}
