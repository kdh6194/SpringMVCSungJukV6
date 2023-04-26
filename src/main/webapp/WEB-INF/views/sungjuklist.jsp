<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--자주 사용하는 태그들을 --%>
<html>
<head>
    <title>성적 리스트</title>
</head>
<body>
    <h1>성적 리스트</h1>
    <table border="1px" width="750px" cellpadding="10px" cellspacing="0">
        <tr><th>이름</th><th>국어</th><th>영어</th><th>수학</th></tr>
        <%--
            for (SungJuk sj : sjs) {
            <tr><td>sj.getName()</td><td>sj.getKor()</td><td>sj.getEng()</td><td>sj.getMat()</td></tr>
            }
        --%>
        <c:forEach items="${sjs}" var="sj">
            <tr><td>${sj.name}</td><td>${sj.kor}</td><td>${sj.eng}</td><td>${sj.mat}</td></tr>
        </c:forEach>
    </table>
    <p>${sjs}</p>
</body>
</html>
