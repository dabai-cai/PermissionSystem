
<!DOCTYPE html>
<%@page import="cn.scau.hjr.model.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cn.scau.hjr.model.Pager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <title>用户管理</title>

    <!-- Bootstrap core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!--  IE10 viewport hack for Surface/desktop Windows 8 bug
<link href="http://v3.bootcss.com/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries
[if lt IE 9]>

    <![endif]-->
</head>
<body>


<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <nav class="navbar navbar-default" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#">Brand</a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active">
                            <a href="/admin/index">返回主页</a>
                        </li>
                        <li>
                            <a href="#">Link</a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown<strong class="caret"></strong></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#">Action</a>
                                </li>
                                <li>
                                    <a href="#">Another action</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <form class="navbar-form navbar-left" role="search" action="/admin/userManager"  >
                        <div class="form-group">
                            <input class="form-control" type="text"  name="searchUser"    placeholder="输入关键字查看用户"   />
                        </div> <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询</button>
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">添加用户
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
                    </form>


                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="#">Link</a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown<strong class="caret"></strong></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#">Action</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>

            </nav>
        </div>
    </div>
</div>

<div class="modal fade " id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel">用户添加</h4>
            </div>

            <div class="modal-body">
                <form id="updateform">
                    <div class="form-group">
                        <label for="username" class="control-label">用户名:</label>
                        <input type="text" class="form-control" id="username" name="username">
                    </div>
                    <div class="form-group">
                        <label for="account" class="control-label">账号:</label>
                        <input type="text" class="form-control" id="account" name="account" placeholder="123456">
                    </div>
                    <div class="form-group">
                        <label for="newPwd" class="control-label">密码:</label>
                        <input type="password" class="form-control" id="newPwd" name="password" >
                    </div>
                    <div class="form-group">
                        <label for="checkPwd" class="control-label">确认密码:</label>
                        <input type="password" class="form-control" id="checkPwd" name="checkPwd">
                    </div>
                    <div class="form-group">
                        <label for="sex" class="control-label">性别:</label>
                        男
                        <input type="radio" id="sex" name="sex" value="男">
                        女
                        <input type="radio"  name="sex" value="女" checked>
                    </div>
                    <div class="form-group">
                        <label for="age" class="control-label">年龄:</label>
                        <input type="text" class="form-control" id="age" name="age" placeholder="23">
                    </div>
                    <div class="form-group">
                        <label for="phone" class="control-label">电话:</label>
                        <input type="text" class="form-control" id="phone" name="phone" placeholder="232323">
                    </div>

                    <div class="text-right">
                        <span id="returnMessage" class="glyphicon"> </span>
                        <button type="button" class="btn btn-default right" data-dismiss="modal">关闭</button>
                        <button id="submitBtn" type="button" class="btn btn-primary" data-toggle="modal" >添加</button>

                    </div>
                </form>
            </div>
        </div>
    </div>
</div>




<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myaddModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myaddModal">
                    添加
                </h4>
                <div class="modal-body">
                    用户添加成功
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
            </div>

        </div><!-- /.modal-content glyphicon glyphicon-pencil -->
    </div><!-- /.modal -->
</div>

<script src="/resources/js/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.js"></script>
<table align="center" class="table table-bordered">
    <caption>
        管理员权限
    </caption>
    <thead>
    <tr>
        <th>
            编号:
        </th>

        <th>
            用户名：
        </th>

        <th>
            账号
        </th>
        <th>
            用户密码：
        </th>
        <th>
            性别
        </th>
        <th>
            管理员操作：
        </th>
    </tr>
    </thead>
    <%
        Pager pager =(Pager) session.getAttribute("pager");
        ArrayList userList = (ArrayList) pager.getpagerData();
        int pageindex=pager.getPageOffset();
        for (int i = 0; i < userList.size(); i++) {
            User user = (User) userList.get(i);

            String name = user.getUsername();
            String password = user.getPassword();
            String sex = user.getSex();
            String acount=user.getAccount();
            int age=0;
            String phone=null;
            try{
                age=user.getAge();
            }catch (NullPointerException npe)
            {
            }
            try {
                phone=user.getPhone();
            }catch (NullPointerException npe)
            {

            }


    %>
    <tbody>
    <tr>
        <td>
            <div>
                <%=i+1%>
            </div>
        </td>
        <td contenteditable="true" name="">
            <%=name%>
        </td>
        <td>
            <%=acount %>
        </td>
        <td contenteditable="true">
            <%=password%>
        </td>
        <td contenteditable="true">
            <%=sex%>
        </td >
        <td>
            <button class="btn btn-primary" data-toggle="modal" data-target="#myModa<%=i+1%>">
                修改 <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
            </button>
            <!-- 模态框（Modal） -->
            <div class="modal fade" id="myModa<%=i+1%>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel<%=i+1%>" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                &times;
                            </button>
                            <h4 class="modal-title" id="myModalLabel<%=i+1%>">
                                修改用户信息
                            </h4>
                        </div>

                        <form method="post" action="/admin/updateUser?id=<%=user.getUserId()%>&pageindex=<%=pageindex%>" id="userform">
                            <div class="modal-body">
                                用户名：<input name="username" type="text" value="<%=name%>" class="form-control input-lg website-input">
                            </div>
                            <div class="modal-body">
                                密码：<input name="password" type="text" value="<%=password%>" class="form-control input-lg website-input">
                            </div>
                            <div class="modal-body">

                                性别：<%
                                if(sex!=null)
                                {
                                    if(sex.equals("男")||sex.equals("male"))
                                    {
                                %>

                                男 <input type="radio" name="sex" value="男" checked>
                                女<input type="radio" name="sex" value="女">
                                <%
                                }else{
                                %>
                                男 <input type="radio" name="sex" value="男" >
                                女 <input type="radio" name="sex" value="女" checked>
                                <%
                                    }
                                }else{
                                    %>
                                男 <input type="radio" name="sex" value="男" >
                                女 <input type="radio" name="sex" value="女" checked>
                                <%
                                    }
                            %>


                            </div>
                            <div class="modal-body">
                                年龄：<input name="age" type="text" value="<%=age%>" class="form-control input-lg website-input">
                            </div>
                            <div class="modal-body">
                                电话：<input name="phone" type="text" value="<%=phone%>" class="form-control input-lg website-input">
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                </button>
                                <button type="submit" class="btn btn-primary" id="updateBtn">
                                    修改
                                </button>
                            </div>
                        </form>

                    </div><!-- /.modal-content glyphicon glyphicon-pencil -->
                </div><!-- /.modal -->
            </div>

            <button class="btn btn-info" data-toggle="modal" data-target="#userModa<%=i+1%>">
                查看<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
            </button>
            <!-- 模态框（Modal） -->
            <div class="modal fade" id="userModa<%=i+1%>" tabindex="-1" role="dialog" aria-labelledby="userModalLabel<%=i+1%>" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                &times;
                            </button>
                            <h4 class="modal-title" id="userModalLabel<%=i+1%>">
                                用户个人信息
                            </h4>
                        </div>

                            <div class="modal-body">
                                用户名：<span><%=name%></span>
                            </div>
                            <div class="modal-body">
                                密码：<span><%=password%></span>
                            </div>
                            <div class="modal-body">
                                性别：<span><%=sex%></span>
                            </div>
                            <div class="modal-body">
                                年龄：<span><%=age%></span>
                            </div>
                            <div class="modal-body">
                                电话：<span><%=phone%></span>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                </button>

                            </div>

                    </div><!-- /.modal-content glyphicon glyphicon-pencil -->
                </div><!-- /.modal -->
            </div>


            <button class="btn btn-danger"  onclick="javascript:window.location.href='<%=request.getContextPath()%>/admin/delUser?id=<%=user.getUserId()%>&pageindex=<%=pageindex%>';"
                    >删除
                <span class="glyphicon glyphicon-minus" aria-hidden="true"></span></button>




            <button onclick="javascript:window.location.href='/admin/AssigningRoles?id=<%=user.getUserId()%>';"
                    class="btn btn-success">分配角色
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
        </td>
    </tr>
    <%
        }
    %>
    <tr>

        <td>

            <ul class="pagination">
                <li>
                    <a href="/admin/userManager?ifindex=1">首页</a>
                </li>

                <% for (int i = 1; i <= pager.getTotalPage(); i++) {
                    if(i!=pageindex)
                    {
                %>
                <li>
                    <a href="/admin/userManager?pageindex=<%=i%>"><%=i%></a>
                </li>
                <%
                }
                else{
                %>
                <li><a href="#"><%=i%></a></li>
                <%
                    }
                %>
                <%
                    }
                %>
            </ul>
        </td>

    </tr>
    </tbody>
</table>

<script type='text/javascript'>
    var form = $('#updateform');
    //$(document).ready(function () {

        form.bootstrapValidator({
            message: '输入值不合法',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                username: {
                    message: '用户名不合法',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        },
                        stringLength: {
                            min: 3,
                            max: 30,
                            message: '请输入3到30个字符'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_\. \u4e00-\u9fa5 ]+$/,
                            message: '用户名只能由字母、数字、点、下划线和汉字组成 '
                        }
                    }
                }
                , phone: {
                    validators: {
                        notEmpty: {
                            message: '手机号不能为空'
                        }
                    }
                }
                ,account: {
                    validators: {
                        notEmpty: {
                            message: '账号不能为空'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: '*新密码不能为空'
                        }
                    }
                },
                checkPwd: {
                    validators: {
                        notEmpty: {
                            message: '*确认密码不能为空'
                        },
                        identical: {
                            field: 'password',
                            message: '*两次输入密码不一致'
                        }
                    }
                }
            }
        });
   // });
    $("#submitBtn").click(function () {
//进行表单验证
        var bv = form.data('bootstrapValidator');
        bv.validate();
        if(bv.isValid())
        {
            $.ajax({
                type:"post",
                url:"/admin/addUser",
                data:$("#updateform").serialize(),
                success:function(data){
                    $("#span-1").text(data.str);
                    $('#exampleModal').modal('hide')
                }
            })


        }
    });
</script>


</body>


</html>