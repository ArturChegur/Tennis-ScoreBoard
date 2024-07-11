package controller;

import entity.MatchScore;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MatchScoreCalculationService;
import service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {
    private static final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();
    private static final MatchScoreCalculationService matchScoreCalculationService = MatchScoreCalculationService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MatchScore matchScore = getMatchFromRequest(req, resp);
        if (matchScore == null) {
            return;
        }
        req.setAttribute("match", matchScore);
        req.getRequestDispatcher("pages/match-score-page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String winner = req.getParameter("winner");
        UUID uuid = getUuidFromRequest(req, resp);
        if (uuid == null) {
            return;
        }
        MatchScore matchScore = ongoingMatchesService.readMatch(uuid);
        if (matchScore == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        matchScoreCalculationService.addPoint(matchScore, winner);
        if (matchScore.getWinner() != null) {
            ongoingMatchesService.deleteMatch(uuid);
            resp.sendRedirect("matches");
            return;
        }
        doGet(req, resp);
    }

    private MatchScore getMatchFromRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UUID uuid = getUuidFromRequest(req, resp);
        if (uuid == null) {
            return null;
        }
        MatchScore matchScore = ongoingMatchesService.readMatch(uuid);
        if (matchScore == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        return matchScore;
    }

    private UUID getUuidFromRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uuidParam = req.getParameter("uuid");
        if (uuidParam == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        try {
            return UUID.fromString(uuidParam);
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }
}