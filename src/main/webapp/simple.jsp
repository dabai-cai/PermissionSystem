<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="/resources/index/img/favicon.png">

    <title>Siimple - Free Bootstrap 3 Landing Page</title>

    <!-- Bootstrap -->
    <link href="/resources/index/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/index/css/bootstrap-theme.css" rel="stylesheet">

    <!-- siimple style -->
    <link href="/resources/index/css/style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<!-- Fixed navbar -->
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">PermissionSystem</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/user/add">Sign up</a></li>
                <li><a href="/user/login">Sign in</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</div>

<div id="header">
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <h1>Stupid is as stupid does.</h1>
                <h2 class="subtitle">Life was like a box of chocolates, you never know what you'regonna get</h2>
                <form class="form-inline signup" role="form">
                    <div class="form-group">
                        <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Enter your email address">
                    </div>
                    <button type="submit" class="btn btn-theme">Get it now</button>
                </form>
            </div>
            <div class="col-lg-4 col-lg-offset-2">
                <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                    </ol>
                    <!-- slides -->
                    <div class="carousel-inner">
                        <div class="item active">
                            <img src="/resources/index/img/slide1.png" alt="">
                        </div>
                        <div class="item">
                            <img src="/resources/index/img/slide2.png" alt="">
                        </div>
                        <div class="item">
                            <img src="/resources/index/img/slide3.png" alt="">
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<div id="footer">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 col-lg-offset-3">
                <p class="copyright">Copyright @ scau.hjr.com</p>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="/resources/index/js/bootstrap.min.js"></script>
</body>
</html>
