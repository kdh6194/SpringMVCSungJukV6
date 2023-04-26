<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>성적 조회 결과</title>
</head>
<body>
  <h1>성적 조회 결과</h1>
  <table border="1px" width="750px" cellpadding="10px" cellspacing="0">
      <tr><th>학생번호</th><th>이름</th><th>국어</th><th>영어</th><th>수학</th><th>총점</th><th>평균</th><th>등급</th></tr>
      <tr><td>${sjv.sjno}</td><td>${sjv.name}</td><td>${sjv.kor}</td><td>${sjv.eng}</td><td>${sjv.mat}</td><td>${sjv.tot}</td><td>${sjv.avg}</td><td>${sjv.grd}</td></tr>
  </table>


<p><a href="/update?sjno=${sjv.sjno}">수정하기</a></p>
<p><a href="/delete?sjno=${sjv.sjno}">삭제하기</a></p>
</body>
</html>
