<%--
  Created by IntelliJ IDEA.
  User: shiyongxiang
  Date: 16/9/24
  Time: 下午8:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传文件</title>
</head>
<body>
    <div>
        <form action="upload" enctype="multipart/form-data" method="post">
            <input type="file" name="file"/>
            <input type="submit" value="上传"/>
        </form>
    </div>
</body>
</html>
