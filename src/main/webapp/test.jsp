<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
        <script>
            function copyText()
            {
                document.getElementById("field2").value=document.getElementById("field1").value;
                <%System.out.println("hahhah");%>
            }
        </script>
</head>
<body>

Field1: <input type="text" id="field1" value="Hello World!"><br>
Field2: <input type="text" id="field2"><br><br>

<button onclick="copyText()">复制文本</button>

<p>当按钮被单击时触发函数。此函数把文本从 Field1 复制到 Field2 中。</p>

</body>
</html>
