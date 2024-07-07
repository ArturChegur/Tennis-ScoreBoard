<%@ page import="main.entity.MatchScore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Match Score</title>
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
            width: 600px;
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
            margin-bottom: 20px;
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

        .readonly-input {
            border: none;
            background: transparent;
            font-family: 'Maven Pro', sans-serif;
            font-size: 16px;
            color: #333;
            text-align: center;
            pointer-events: none;
            width: 100%;
        }

        .button-container {
            margin-top: 40px;
        }
    </style>
</head>
<body>
<div class="background"></div>
<div class="container">
    <h1>Match Score</h1>
    <table>
        <thead>
        <tr>
            <th>Player</th>
            <th>Sets</th>
            <th>Games</th>
            <th>Points</th>
        </tr>
        </thead>
        <tbody>
        <%
            MatchScore match = (MatchScore) request.getAttribute("match");
        %>
        <tr>
            <td>
                <input type="text" name="player1Name" value="<%= match.getFirstPlayer().getName() %>"
                       class="readonly-input" readonly>
            </td>
            <td id="player1Sets"><%= match.getFirstPlayerSet() %>
            </td>
            <td id="player1Games"><%= match.getFirstPlayerGame() %>
            </td>
            <td id="player1Points"><%= match.getFirstPlayerScore() %>
            </td>
        </tr>
        <tr>
            <td>
                <input type="text" name="player2Name" value="<%= match.getSecondPlayer().getName() %>"
                       class="readonly-input" readonly>
            </td>
            <td id="player2Sets"><%= match.getSecondPlayerSet() %>
            </td>
            <td id="player2Games"><%= match.getSecondPlayerGame() %>
            </td>
            <td id="player2Points"><%= match.getSecondPlayerScore() %>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="button-container">
        <form action="${pageContext.request.contextPath}/match-score" method="post" style="display: inline;">
            <input type="hidden" name="uuid" value="<%= request.getParameter("uuid") %>">
            <input type="hidden" name="winner" value="first">
            <button type="submit" class="button"><%=  match.getFirstPlayer().getName() %> won point</button>
        </form>
        <form action="${pageContext.request.contextPath}/match-score" method="post" style="display: inline;">
            <input type="hidden" name="uuid" value="<%= request.getParameter("uuid") %>">
            <input type="hidden" name="winner" value="second">
            <button type="submit" class="button"><%= match.getSecondPlayer().getName() %> won point</button>
        </form>
    </div>
</div>
</body>
</html>