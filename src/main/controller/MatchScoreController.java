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
        Match match = getMatchFromRequest(req, resp);
        if (match == null) {
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
        UUID uuid = getUuidFromRequest(req, resp);
        if (uuid == null) {
            return;
        }
        Match match = ongoingMatchesService.readMatch(uuid);
        if (match == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        matchScoreCalculationService.addPoint(match, winner);
        if (match.getWinner() != null) {
            ongoingMatchesService.deleteMatch(uuid);
            resp.sendRedirect("/matches");
            return;
        }
        doGet(req, resp);
    }

    private Match getMatchFromRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UUID uuid = getUuidFromRequest(req, resp);
        if (uuid == null) {
            return null;
        }
        Match match = ongoingMatchesService.readMatch(uuid);
        if (match == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return match;
    }

    private UUID getUuidFromRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uuidParam = req.getParameter("uuid");
        if (uuidParam == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        try {
            return UUID.fromString(uuidParam);
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }
}