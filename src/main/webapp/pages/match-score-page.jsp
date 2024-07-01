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
            background: url('../images/background.jpg') no-repeat center center fixed;
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
        }

        th, td {
            padding: 10px;
            border: 1px solid #ddd;
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
        }

        .button:hover {
            background-color: black;
            color: white;
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
            <th>Score</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td id="player1Name">Player 1</td>
            <td id="player1Score">0</td>
        </tr>
        <tr>
            <td id="player2Name">Player 2</td>
            <td id="player2Score">0</td>
        </tr>
        </tbody>
    </table>
    <form action="/match-score" method="post">
        <input type="hidden" name="uuid" value="<%= request.getParameter("uuid") %>">
        <input type="hidden" name="winner" value="player1">
        <button type="submit" class="button">Player 1 Won Point</button>
    </form>
    <form action="/match-score" method="post">
        <input type="hidden" name="uuid" value="<%= request.getParameter("uuid") %>">
        <input type="hidden" name="winner" value="player2">
        <button type="submit" class="button">Player 2 Won Point</button>
    </form>
</div>
</body>
</html>