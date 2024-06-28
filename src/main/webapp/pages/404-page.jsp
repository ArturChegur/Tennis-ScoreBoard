<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Page Not Found</title>
    <link href="https://fonts.googleapis.com/css2?family=Maven+Pro:wght@400;500;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Maven Pro', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f8f8f8;
            text-align: center;
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
            max-width: 600px;
            padding: 20px;
            background: rgba(245, 250, 255, 0.5);
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            text-align: center;
            position: relative;
            z-index: 1;
        }

        h1 {
            font-size: 48px;
            margin-bottom: 20px;
            color: #333;
        }

        p {
            font-size: 18px;
            margin-bottom: 20px;
            color: #666;
        }

        a {
            color: #007BFF;
            text-decoration: none;
            font-weight: 500;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="background"></div>
<div class="container">
    <h1>404</h1>
    <p>Sorry, the page you are looking for does not exist.</p>
    <p><a href="/main-page">Go to Home Page</a></p>
</div>
</body>
</html>