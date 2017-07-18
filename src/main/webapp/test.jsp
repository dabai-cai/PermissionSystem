<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>

</head>
<body>
<table>
    <form id="form-1" method="post" action="/admin/test">
        <input type="text"  name="id"/>
        <button type="submit" id="btn">
        </button>
    </form>
    <span id="span-1"></span>
</table>
</body>
<script>
    $("#btn").click(function(){
        $.ajax({
            type:"post",
            url:"/admin/test",
            data:$("#form-1").serialize(),
            success:function(data){
                $("#span-1").text(data.str);
            }
        })
    })
</script>
</html>

