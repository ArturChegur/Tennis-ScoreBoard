package main.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.service.OngoingMatchesService;
import main.util.ParameterValidator;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {
    private static final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("pages/new-match-page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstPlayer = req.getParameter("firstPlayer").trim();
        String secondPlayer = req.getParameter("secondPlayer").trim();
        String errorMessage = ParameterValidator.validateNames(firstPlayer, secondPlayer);
        if (errorMessage != null) {
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("pages/new-match-page.jsp").forward(req, resp);
        } else {
            UUID uuid = ongoingMatchesService.addMatch(firstPlayer, secondPlayer);
            resp.sendRedirect("/match-score?uuid=" + uuid.toString());
        }
    }
}
