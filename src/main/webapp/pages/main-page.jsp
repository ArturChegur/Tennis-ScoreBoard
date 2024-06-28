<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://fonts.googleapis.com/css2?family=Maven+Pro:wght@400;500;700&display=swap" rel="stylesheet">
    <title>Main Page</title>
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
            width: 50%;
            min-width: 400px;
            background: rgba(245, 250, 255, 0.5);
            padding: 20px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.15);
            text-align: center;
            position: relative;
            z-index: 1;
        }

        h1 {
            color: #333;
        }

        .images img {
            width: 30%;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);
        }

        .buttons {
            margin: 30px 0;
        }

        .buttons form {
            display: inline-block;
            margin: 0 10px;
        }

        .buttons button {
            padding: 12px 35px;
            font-size: 16px;
            background-color: transparent;
            color: black;
            border: 2px solid black;
            border-radius: 5px;
            transition: transform 0.15s ease, background-color 0.15s ease;
            cursor: pointer;
            outline: none;
        }

        .buttons button:hover {
            background: black;
            color: white;
        }

        .footer {
            font-size: 13px;
            margin-top: 45px;
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
    <h1>Welcome to the Tennis Scoreboard</h1>
    <div class="buttons">
        <form action="/new-match" method="get">
            <button type="submit">Play a Match</button>
        </form>
        <form action="/matches" method="get">
            <button type="submit">View Matches</button>
        </form>
    </div>
    <div class="footer">
        <p>Created by <a href="https://github.com/ArturChegur" target="_blank">ArturChegur</a></p>
    </div>
</div>
</body>
</html>