<%@ page import="main.entity.Match" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Matches</title>
    <link href="https://fonts.googleapis.com/css2?family=Maven+Pro:wght@400;500;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Maven Pro', sans-serif;
            margin: 0;
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .background {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: url('images/background.jpg') no-repeat center center fixed;
            background-size: cover;
            filter: blur(9px);
            z-index: -1;
        }

        .container {
            background: rgba(255, 255, 255, 0.5);
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
            width: 800px;
            text-align: center;
            position: relative;
            z-index: 1;
        }

        h1 {
            color: #333;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            margin-top: 40px;
            border-collapse: collapse;
            border: 1px solid #666;
        }

        th, td {
            padding: 10px;
            border: 1px solid #666;
            text-align: center;
        }

        th {
            background-color: #f4f4f4;
        }

        .button {
            padding: 10px 15px;
            font-size: 16px;
            background-color: transparent;
            color: black;
            border: 2px solid black;
            border-radius: 5px;
            transition: transform 0.15s ease, background-color 0.15s ease;
            cursor: pointer;
            margin: 5px;
            display: inline-block;
        }

        .button:hover {
            background-color: black;
            color: white;
        }

        .input-field {
            padding: 10px;
            font-size: 16px;
            border: 1px solid #666;
            border-radius: 5px;
            margin: 5px;
            display: inline-block;
            width: 300px;
        }

        .form-container {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 40px;
        }

        .home-link {
            font-size: 16px;
            color: black;
            text-decoration: none;
            margin-right: auto;
        }

        .home-link:hover {
            text-decoration: underline;
            color: black;
        }

        .pagination {
            margin-top: 20px;
        }

        .pagination a {
            margin: 0 5px;
            padding: 10px 15px;
            text-decoration: none;
            color: #333;
            border: 1px solid #666;
            border-radius: 5px;
        }

        .pagination a:hover {
            background-color: #666;
            color: white;
        }

    </style>
</head>
<body>
<div class="background"></div>
<div class="container">
    <h1>All Matches</h1>
    <div class="form-container">
        <a href="${pageContext.request.contextPath}/" class="home-link">Return to Home Page</a>
        <form action="${pageContext.request.contextPath}/matches" method="get"
              style="display: flex; align-items: center;">
            <input type="text" name="playerName" class="input-field" placeholder="Enter player name"
                   value="<%= (request.getParameter("playerName") != null) ? request.getParameter("playerName") : "" %>">
            <button type="submit" class="button">Search Matches</button>
        </form>
    </div>
    <table>
        <thead>
        <tr>
            <th>Player 1</th>
            <th>Player 2</th>
            <th>Winner</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Match> matches = (List<Match>) request.getAttribute("matches");
            for (Match match : matches) {
        %>
        <tr>
            <td><%= match.getFirstPlayer().getName() %>
            </td>
            <td><%= match.getSecondPlayer().getName() %>
            </td>
            <td><%= match.getWinner().getName() %>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <div class="pagination">
        <%
            int currentPage = (int) request.getAttribute("currentPage");
            int totalPages = (int) request.getAttribute("totalPages");
            String playerName = (request.getParameter("playerName") != null) ? request.getParameter("playerName") : "";
            if (currentPage > 1) {
        %>
        <a href="?page=<%= currentPage - 1 %>&playerName=<%= playerName %>">&laquo;</a>
        <% } %>
        <% for (int i = 1; i <= totalPages; i++) { %>
        <a href="?page=<%= i %>&playerName=<%= playerName %>"<%= (i == currentPage) ? "class='active'" : "" %>><%= i %>
        </a>
        <% } %>
        <% if (currentPage < totalPages) { %>
        <a href="?page=<%= currentPage + 1 %>&playerName=<%= playerName %>">&raquo;</a>
        <% } %>
    </div>
</div>
</body>
</html>