package main.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.entity.Match;
import main.service.MatchScoreCalculationService;
import main.service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {
    private static final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();
    private static final MatchScoreCalculationService matchScoreCalculationService = MatchScoreCalculationService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("uuid") == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        Match match = ongoingMatchesService.readMatch(uuid);
        if (match == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        req.setAttribute("firstName", match.getFirstPlayer().getName());
        req.setAttribute("secondName", match.getSecondPlayer().getName());
        req.setAttribute("firstSets", match.getMatchScore().getFirstPlayerSet());
        req.setAttribute("firstGames", match.getMatchScore().getFirstPlayerGame());
        req.setAttribute("firstPoints", match.getMatchScore().getFirstPlayerScore());
        req.setAttribute("secondSets", match.getMatchScore().getSecondPlayerSet());
        req.setAttribute("secondGames", match.getMatchScore().getSecondPlayerGame());
        req.setAttribute("secondPoints", match.getMatchScore().getSecondPlayerScore());
        req.getRequestDispatcher("pages/match-score-page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String winner = req.getParameter("winner");
        if (req.getParameter("uuid") == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        Match match = ongoingMatchesService.readMatch(uuid);
        matchScoreCalculationService.addPoint(match, winner);
        if (match.getWinner() != null) {
            ongoingMatchesService.deleteMatch(uuid);
            req.getRequestDispatcher("pages/main-page.jsp").forward(req, resp);
        }
        doGet(req, resp);
    }
}
