<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Played Matches</title>
    <link href="https://fonts.googleapis.com/css2?family=Maven+Pro:wght@400;500;700&display=swap" rel="stylesheet">
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

        .search-container {
            margin-bottom: 20px;
        }

        .search-container input[type="text"] {
            padding: 8px;
            width: 200px;
            margin-right: 10px;
        }

        .search-container input[type="submit"] {
            padding: 8px 16px;
        }
    </style>
</head>
<body>
<div class="background"></div>
<div class="container">
    <h1>Played Matches</h1>
    <div class="search-container">
        <form action="ViewMatchesServlet" method="get">
            <input type="text" name="search" placeholder="Search by player name" value="${param.search}">
            <input type="submit" value="Search">
        </form>
    </div>
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
        <c:if test="${currentPage > 1}">
            <a href="ViewMatchesServlet?page=${currentPage - 1}&search=${param.search}">&laquo; Previous</a>
        </c:if>
        <c:forEach begin="1" end="${totalPages}" var="i">
            <c:choose>
                <c:when test="${i == currentPage}">
                    <a href="ViewMatchesServlet?page=${i}&search=${param.search}" style="font-weight:bold;">${i}</a>
                </c:when>
                <c:otherwise>
                    <a href="ViewMatchesServlet?page=${i}&search=${param.search}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${currentPage < totalPages}">
            <a href="ViewMatchesServlet?page=${currentPage + 1}&search=${param.search}">Next &raquo;</a>
        </c:if>
    </div>
    <div class="footer">
        <p>Created by <a href="https://github.com/ArturChegur" target="_blank">ArturChegur</a></p>
    </div>
</div>
</body>
</html>