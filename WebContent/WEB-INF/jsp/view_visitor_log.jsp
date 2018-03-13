<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="date" class="java.util.Date" /> 
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link href="${contextPath}/images/ctasitelogo.png" rel="shortcut icon">
    <title>Web | visitor_log </title>
    <base href="${contextPath}"/>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="${contextPath}/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${contextPath}/bootstrap/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${contextPath}/bootstrap/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${contextPath}/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="${contextPath}/dist/css/skins/_all-skins.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <!-- ADD THE CLASS layout-top-nav TO REMOVE THE SIDEBAR. -->
  <body class="hold-transition skin-blue layout-top-nav">
    <div class="wrapper">

      <header class="main-header">
        <nav class="navbar navbar-static-top">
          <div class="container">
            <div class="navbar-header">
              <a href="javascript:void(0)" class="navbar-brand"><b>CTAC</b> - VISITOR LOG IN SHEET</a>
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
                <i class="fa fa-bars"></i>
              </button>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse pull-left" id="navbar-collapse">
              <ul class="nav navbar-nav">
              </ul>
            </div><!-- /.navbar-collapse -->
            <!-- Navbar Right Menu -->
              <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                </ul>
              </div><!-- /.navbar-custom-menu -->
          </div><!-- /.container-fluid -->
        </nav>
      </header>
      <!-- Full Width Column -->
      <div class="content-wrapper">
        <div class="container">
          <!-- Content Header (Page header) --
          <section class="content-header">
            <h1>
              Top Navigation
              <small>Example 2.0</small>
            </h1>
            <ol class="breadcrumb">
              <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
              <li><a href="#">Layout</a></li>
              <li class="active">Top Navigation</li>
            </ol>
          </section>-->

          <!-- Main content -->
          <section class="content">
            <div class="box box-primary">
              <div class="box-header with-border">
                <h3 class="box-title">Consultar visitas</h3>
              </div>
              <div class="box-body">
                <form data-toggle="validator" id="fomrSearchVisit" role="form">
                <div class="form-group form-horizontal">
                   <label for="txtvisit" class="col-sm-2 control-label" style="width: auto;">Number or Name </label>
                      <div class="input-group input-group-sm">
                    <input type="text" class="form-control" id="txtvisit" name="codeorname" required >
                    <span class="input-group-btn">
                      <button class="btn btn-info btn-flat" type="submit">Search!</button>
                    </span>
                  </div>
                  <div class="help-block with-errors"></div>
                    </div>
                  </form>
              </div><!-- /.box-body -->
              <div id="divdataVisit" class='box-footer box-comments'style="display: none">
              	
                  <div class='box-comment'>
                    <!-- User image -->
                    <img class='img-circle img' src='${contextPath}/images/icono_calendari_visita.png' alt='user image'>
                    <div class='comment-text'>
                      <span class="username">
                        <h4 class="box-title" style="margin-top: 5px;" >About visit data</h4>
                        <!-- <span class='text-muted pull-right'>8:03 PM Today</span> -->
                      </span>
                      <div class="col-sm-4 invoice-col">
              Visitor
              <address>
                <strong id="full_name_visitor"></strong><br>
                Company: <span id="company_name"></span><br>
                ID: <span id="idNumber"></span><br>
                citizen ship: <span id="citizen_ship"></span><br>
                Phone: <span id="phone_number"></span><br>
                Email: <span id="email"></span>
              </address>
            </div><!-- /.col -->
            <div class="col-sm-4 invoice-col">
              Employe
              <address>
                <strong id="full_name_employee"></strong><br>
              </address>
            </div><!-- /.col -->
            <div class="col-sm-4 invoice-col">
            Visit
            <address>
              <b id="id_visit_schedule"> </b><br>
              <b>Reason:</b> <span id="reasons_name"></span><br>
              <b>Date hour:</b><span id="date_hour"></span><br>
              <!--<b>Account:</b> 968-34567-->
              </address>
            </div><!-- /.col -->
                    </div><!-- /.comment-text -->
                  </div><!-- /.box-comment -->
                  <div class='box-comment'>
                    <div class='comment-text'>
                     ingreso
                    </div><!-- /.comment-text -->
                  </div><!-- /.box-comment -->
                </div><!-- /.box-footer -->
                <div class="box-footer">
                 <div id="msjSearchVisit" ></div>
                </div><!-- /.box-footer -->
            </div><!-- /.box -->
          </section><!-- /.content -->
        </div><!-- /.container -->
      </div><!-- /.content-wrapper -->
      <footer class="main-footer">
        <div class="container">
          <div class="pull-right hidden-xs">
            <b></b> 
          </div>
          <strong>Copyright &copy; <fmt:formatDate value="${date}" pattern="yyyy" /> <a href="javascript:void(0)">CTAC</a>.</strong> All rights reserved.
        </div><!-- /.container -->
      </footer>
    </div><!-- ./wrapper -->
	
    <!-- jQuery 2.1.4 -->
    <script src="${contextPath}/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="${contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <!-- SlimScroll -->
    <script src="${contextPath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <!-- FastClick -->
    <script src="${contextPath}/plugins/fastclick/fastclick.min.js"></script>
    <!-- AdminLTE App -->
    <script src="${contextPath}/dist/js/app.min.js"></script>
    <!-- validator -->
	<script src="${contextPath}/plugins/validator/validator.js"></script>
	<!-- blockUI -->
    <script src="${contextPath}/plugins/blockUI/jquery.blockUI.js"></script>
	<!-- funcion general -->
    <script src="${contextPath}/js/functions.js"></script>
    <!-- visitorLog -->
    <script src="${contextPath}/js/visitorLog.js"></script>
  </body>
</html>