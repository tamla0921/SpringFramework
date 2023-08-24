<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
</head>
<body>
    <form:form modelAttribute="login">
        <p>
            <label for="loginType">로그인 타입</label>
            <form:select path="loginType" items="${loginTypes }" />
        </p>
    </form:form>
</body>
</html>