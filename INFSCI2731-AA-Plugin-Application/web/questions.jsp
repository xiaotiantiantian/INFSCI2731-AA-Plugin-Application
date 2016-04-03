<%-- 
    Document   : questions
    Created on : Apr 2, 2016, 5:36:23 PM
    Author     : shao dai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Security Questions</title>
    </head>
    <body>
        <h1>Security Questions</h1>
        <p>Please answer this security question to continue</p>
        <form name="resetform-question" method="POST" action="">
            <div>${requestScope.question}</div>
            <input type=hidden name="questionid" value="${requestScope.question_id}">
            <input placeholder="Your answer" />
            <input type="submit">
        </form>
    </body>
</html>
