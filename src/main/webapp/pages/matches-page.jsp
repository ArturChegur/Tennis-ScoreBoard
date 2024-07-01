<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Played Matches</title>
    <style>
        body {
            font-family: 'Maven Pro', sans-serif;
            margin: 0;
            overflow: hidden;
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
            width: 80%;
            margin: 50px auto;
            background: rgba(255, 255, 255, 0.8);
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }

        th {
            background-color: #f2f2f2;
        }

        .pagination {
            display: flex;
            justify-content: center;
        }

        .pagination a {
            margin: 0 5px;
            padding: 8px 16px;
            text-decoration: none;
            background-color: white;
            color: black;
            border: 1px solid black;
            border-radius: 5px;
            transition: background-color 0.3s ease, color 0.3s ease;
        }

        .pagination a:hover {
            background-color: black;
            color: white;
        }

        .footer {
            margin-top: 20px;
            font-size: 0.9em;
            color: #777;
        }

        .footer a {
            color: #333;
            text-decoration: none;
        }

        .footer a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="background"></div>
<div class="container">
    <h1>Played Matches</h1>
    <table>
        <thead>
        <tr>
            <th>Player 1</th>
            <th>Player 2</th>
            <th>Result</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="match" items="${matches}">
            <tr>
                <td>${match.player1}</td>
                <td>${match.player2}</td>
                <td>${match.result}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination">
        <c:forEach begin="1" end="${totalPages}" var="i">
            <a href="ViewMatchesServlet?page=${i}">${i}</a>
        </c:forEach>
    </div>
    <div class="footer">
        <p>Created by <a href="https://github.com/ArturChegur" target="_blank">ArturChegur</a></p>
    </div>
</div>
</body>
</html>