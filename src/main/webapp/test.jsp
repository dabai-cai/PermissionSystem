<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <title>Bootstrap 101 Template</title>

        <!-- Bootstrap -->
        <link href="/resources/css/bootstrap.min.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
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
                                        <form class="navbar-form navbar-left" role="search">
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
                </div>
        </div>
        <div class="row clearfix">
                <div class="col-md-3 column">
                        <div class="panel-group" id="panel-871904">
                                <div class="panel panel-default">
                                        <div class="panel-heading">
                                                <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-871904" href="#panel-element-487394">Collapsible Group Item #1</a>
                                        </div>
                                        <div id="panel-element-487394" class="panel-collapse collapse">
                                                <div class="panel-body">
                                                        Anim pariatur cliche...
                                                </div>
                                        </div>
                                </div>
                                <div class="panel panel-default">
                                        <div class="panel-heading">
                                                <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-871904" href="#panel-element-6492">Collapsible Group Item #2</a>
                                        </div>
                                        <div id="panel-element-6492" class="panel-collapse collapse">
                                                <div class="panel-body">
                                                        Anim pariatur cliche...
                                                </div>
                                        </div>
                                </div>
                        </div>
                </div>
                <div class="col-md-9 column">
                        <div class="tabbable" id="tabs-738158">
                                <ul class="nav nav-tabs">
                                        <li>
                                                <a href="#panel-307540" data-toggle="tab">Section 1</a>
                                        </li>
                                        <li class="active">
                                                <a href="#panel-196111" data-toggle="tab">Section 2</a>
                                        </li>
                                </ul>
                                <div class="tab-content">
                                        <div class="tab-pane" id="panel-307540">
                                                <p>
                                                        I'm in Section 1.
                                                </p>
                                        </div>
                                        <div class="tab-pane active" id="panel-196111">
                                                <p>
                                                        Howdy, I'm in Section 2.
                                                </p>
                                        </div>
                                </div>
                        </div>
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
                        <ul class="pagination">
                                <li>
                                        <a href="#">Prev</a>
                                </li>
                                <li>
                                        <a href="#">1</a>
                                </li>
                                <li>
                                        <a href="#">2</a>
                                </li>
                                <li>
                                        <a href="#">3</a>
                                </li>
                                <li>
                                        <a href="#">4</a>
                                </li>
                                <li>
                                        <a href="#">5</a>
                                </li>
                                <li>
                                        <a href="#">Next</a>
                                </li>
                        </ul>
                </div>
        </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>




