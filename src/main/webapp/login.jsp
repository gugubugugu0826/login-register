<%--
  Created by IntelliJ IDEA.
  User: 顾顾不咕咕
  Date: 2022/12/30
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>login</title>
  <link href="css/login.css" rel="stylesheet">
</head>

<body>
<div id="loginDiv" style="height: 350px">
  <form action="/login-register/loginServlet" id="form">
    <h1 id="loginMsg">登录</h1>
    <div id="errorMsg">${login_msg}${register_msg}</div>
    <p>账户:<input id="username" name="username" value="${cookie.username.value}" type="text"></p>

    <p>密码:<input id="password" name="password" value="${cookie.password.value}" type="password"></p>
    <p>记住我:<input id="remember" name="remember" value="1" type="checkbox"></p>
    <div id="subDiv">
      <input type="submit" class="button" value="登录">
      <P>   </P>
      <a href="register.jsp">没有账号？</a>
    </div>
  </form>
</div>

</body>
</html>
