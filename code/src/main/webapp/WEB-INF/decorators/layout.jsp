<%@ page import="com.thoughtworks.placement.web.controllers.LoginController" %>
<%@ page import="com.thoughtworks.placement.web.model.Student" %>
<html>
<head>
    <meta charset="utf-8">
    <!-- This is used to specify base URL for all relative URLs on page -->
    <base href="${pageContext.request.contextPath}/">

    <title>
        <sitemesh:write property='title'/>
    </title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="A web application for colleges">
    <meta name="author" content="Shirish">

    <link href="resources/css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
        body {
            padding-top: 60px;
            padding-bottom: 40px;
        }
    </style>
    <link href="resources/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <link rel="shortcut icon" href="resources/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="resources/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="resources/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="resources/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="resources/ico/apple-touch-icon-57-precomposed.png">

    <script src="resources/js/jquery.js"></script>
    <script src="resources/js/bootstrap-transition.js"></script>
    <script src="resources/js/bootstrap-alert.js"></script>
    <script src="resources/js/bootstrap-modal.js"></script>
    <script src="resources/js/bootstrap-dropdown.js"></script>
    <script src="resources/js/bootstrap-scrollspy.js"></script>
    <script src="resources/js/bootstrap-tab.js"></script>
    <script src="resources/js/bootstrap-tooltip.js"></script>
    <script src="resources/js/bootstrap-popover.js"></script>
    <script src="resources/js/bootstrap-button.js"></script>
    <script src="resources/js/bootstrap-collapse.js"></script>
    <script src="resources/js/bootstrap-carousel.js"></script>
    <script src="resources/js/bootstrap-typeahead.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            var menu = $("meta[name=menuName]").attr("content");
            $('#' + menu).addClass("active");
        });
    </script>

    <sitemesh:write property='head'/>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="#">Placement</a>

            <div class="nav-collapse">

                <%
                    Student user = (Student) session.getAttribute(LoginController.LOGGED_IN_USER_KEY);
                    if (user != null) {
                %>
                <ul class="nav">
                    <li id="home"><a href="index">Home</a></li>
                    <%--<li id="register"><a href="student/register">Register Student</a></li>--%>
                    <li id="list"><a href="student/list">List Students</a></li>
                </ul>
                <form class="navbar-form pull-right" method="POST" action="logout">
                    <button type="submit" class="btn btn-link">Logout</button>
                </form>
                <form class="navbar-form pull-right" method="GET" action="student/profile/<%=user.getSID()%>">
                    <button type="submit" class="btn btn-link">Profile</button>
                </form>
                <%
                    }
                %>
            </div>
            <!--/.nav-collapse -->
        </div>
    </div>
</div>
<div class="container">
    <sitemesh:write property='body'/>
    <footer>
        <p class="modal-footer">
            <small> &copy; ThoughtWorks Inc., 2012</small>
        </p>
    </footer>
</div>
</body>
</html>