package main.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.entity.Match;
import main.service.FinishedMatchesPersistenceService;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class PlayedMatchesServlet extends HttpServlet {
    private static final FinishedMatchesPersistenceService finishedMatchesPersistenceService = FinishedMatchesPersistenceService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String playerName = req.getParameter("playerName");
        String pageStr = req.getParameter("page");
        int currentPage = (pageStr == null) ? 1 : Integer.parseInt(pageStr);
        List<Match> matches;
        int totalPages;

        if (playerName == null || playerName.isEmpty()) {
            totalPages = finishedMatchesPersistenceService.getTotalPages(false, "");
            matches = finishedMatchesPersistenceService.getAllMatches(currentPage);
        } else {
            totalPages = finishedMatchesPersistenceService.getTotalPages(true, playerName.trim());
            matches = finishedMatchesPersistenceService.getPlayerMatches(currentPage, playerName.trim());
        }
        req.setAttribute("matches", matches);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("playerName", playerName);
        req.getRequestDispatcher("pages/matches-page.jsp").forward(req, resp);
    }
}