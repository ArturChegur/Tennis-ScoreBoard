<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>New Match</title>
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
            width: 400px;
            text-align: center;
            position: relative;
            z-index: 1;
        }

        h1 {
            color: #333;
            margin-bottom: 20px;
        }

        input[type="text"] {
            font-size: 16px;
            width: calc(100% - 22px);
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .button {
            padding: 15px 15px;
            margin-top: 20px;
            font-size: 16px;
            background-color: transparent;
            color: black;
            border: 2px solid black;
            border-radius: 5px;
            transition: transform 0.15s ease, background-color 0.15s ease;
            cursor: pointer;
        }

        .button:hover {
            background-color: black;
            color: white;
        }

        .error-message {
            color: #007BFF;
            font-size: 14px;
            margin-top: 10px;
            font-weight: 450;
        }
    </style>
</head>
<body>
<div class="background"></div>
<div class="container">
    <h1>Start a New Match</h1>
    <form action="/new-match" method="post">
        <input type="text" name="firstPlayer" placeholder="First player" required
               value="<%= (request.getAttribute("firstPlayer") != null) ? request.getAttribute("firstPlayer") : "" %>">
        <input type="text" name="secondPlayer" placeholder="Second player" required
               value="<%= (request.getAttribute("secondPlayer") != null) ? request.getAttribute("secondPlayer") : "" %>">
        <div class="error-message">
            <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "Player names must be different and in English" %>
        </div>
        <button type="submit" class="button">Start Match</button>
    </form>
</div>
</body>
</html>