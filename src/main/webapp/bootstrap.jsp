<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/18 0018
  Time: 上午 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.js"></script>
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
                            <a href="#">Link</a>
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
                                <li>
                                    <a href="#">Something else here</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="#">Separated link</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="#">One more separated link</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <form class="navbar-form navbar-left" role="search" action="#">
                        <div class="form-group">
                            <input class="form-control" type="text" />
                        </div> <button type="submit" class="btn btn-default">Submit</button>
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
                                <li>
                                    <a href="#">Another action</a>
                                </li>
                                <li>
                                    <a href="#">Something else here</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="#">Separated link</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>

            </nav>
            <form method="post" action="#" id="updateform">
                <a id="modal-247305" href="#modal-container-247305" role="button" class="btn" data-toggle="modal">触发遮罩窗体</a>

                <div class="modal fade" id="modal-container-247305" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                <h4 class="modal-title" id="myModalLabel">
                                    标题
                                </h4>
                            </div>
                            <input type="text" placeholder="hahahaha" name="username" >
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> <button type="submit" class="btn btn-primary" id="sumbitBtn">保存</button>
                            </div>
                        </div>

                    </div>

                </div>
            </form>
            <script type='text/javascript'>
                var form = $('#updateform');
                $(document).ready(function () {

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
                                    },
                                    regexp: {
                                        regexp: "^([0-9]{7})?$",
                                        message: '手机号码格式错误'
                                    }
                                }
                            }
                            ,account: {
                                validators: {
                                    notEmpty: {
                                        message: '账号不能为空'
                                    },

                                    regexp: {
                                        regexp: "^([0-9]{7})?$",
                                        message: '账号为7位纯数字'
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
                });
                $("#submitBtn").click(function () {
                    //进行表单验证
                    var bv = form.data('bootstrapValidator');
                    bv.validate();
                    $.ajax({
                        type:"post",
                        url:"/admin/addUser",
                        data:$("#updateform").serialize(),
                        success:function(data){
                            $("#span-1").text(data.str);
                            $('#exampleModal').modal('hide')

                        }
                    })

                });
            </script>



            <table class="table">
                <thead>
                <tr>
                    <th>
                        编号
                    </th>
                    <th>
                        产品
                    </th>
                    <th>
                        交付时间
                    </th>
                    <th>
                        状态
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        1
                    </td>
                    <td>
                        TB - Monthly
                    </td>
                    <td>
                        01/04/2012
                    </td>
                    <td>
                        Default
                    </td>
                </tr>
                <tr class="success">
                    <td>
                        1
                    </td>
                    <td>
                        TB - Monthly
                    </td>
                    <td>
                        01/04/2012
                    </td>
                    <td>
                        Approved
                    </td>
                </tr>
                <tr class="error">
                    <td>
                        2
                    </td>
                    <td>
                        TB - Monthly
                    </td>
                    <td>
                        02/04/2012
                    </td>
                    <td>
                        Declined
                    </td>
                </tr>
                <tr class="warning">
                    <td>
                        3
                    </td>
                    <td>
                        TB - Monthly
                    </td>
                    <td>
                        03/04/2012
                    </td>
                    <td>
                        Pending
                    </td>
                </tr>
                <tr class="info">
                    <td>
                        4
                    </td>
                    <td>
                        TB - Monthly
                    </td>
                    <td>
                        04/04/2012
                    </td>
                    <td>
                        Call in to confirm
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
