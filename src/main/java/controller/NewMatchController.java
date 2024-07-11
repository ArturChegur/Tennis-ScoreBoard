package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchController extends HttpServlet {
    private static final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("pages/new-match-page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstPlayer = req.getParameter("firstPlayer").trim();
        String secondPlayer = req.getParameter("secondPlayer").trim();
        String errorMessage = validateNames(firstPlayer, secondPlayer);
        if (errorMessage != null) {
            req.setAttribute("firstPlayer", firstPlayer);
            req.setAttribute("secondPlayer", secondPlayer);
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("pages/new-match-page.jsp").forward(req, resp);
        } else {
            UUID uuid = ongoingMatchesService.addMatch(firstPlayer, secondPlayer);
            resp.sendRedirect("match-score?uuid=" + uuid.toString());
        }
    }

    private static String validateNames(String firstPlayer, String secondPlayer) {
        if (firstPlayer == null || firstPlayer.trim().isEmpty() || secondPlayer == null || secondPlayer.trim().isEmpty()) {
            return "Player name cannot be empty or whitespace";
        }
        if (!firstPlayer.matches("[a-zA-Z0-9]+") || !secondPlayer.matches("[a-zA-Z0-9]+")) {
            return "Player name can only contain English letters and numbers";
        }
        if (firstPlayer.length() > 20 || secondPlayer.length() > 20) {
            return "Player name is too long";
        }
        if (firstPlayer.equals(secondPlayer)) {
            return "Player names must be different";
        }
        return null;
    }
}