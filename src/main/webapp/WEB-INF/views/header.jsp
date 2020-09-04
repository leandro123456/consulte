<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<link href='<c:url value="/resources/images/favicon.ico" />'  rel='shortcut icon'  type="image/x-icon"/>
<link href='<c:url value="/resources/cdash_logo/logo.css" />' rel="stylesheet"     type="text/css" />
    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion toggled" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      
		<form role="form" action="<c:url value="home"/>" method="get" enctype="multipart/form-data">
		      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="javascript:;" onclick="parentNode.submit();">
<!-- 		        <div class="sidebar-brand-icon"> -->
				<div class="logodash">
				<img alt="" src='<c:url value="/resources/loginresources/css/images/logoBig.png"  />'/>
		        </div>
		        <div class="sidebar-brand-text mx-3 logodash-text">cDash<sup></sup></div>
<!-- 				<div class="logodash-text">cDash<sup></sup></div> -->
		      </a>
		</form>
      <!-- Divider -->
      <hr class="sidebar-divider my-0">

      <!-- Nav Item - Dashboard -->
      <li class="nav-item active">
	      <form role="form" action="<c:url value="/home"/>" method="get" enctype="multipart/form-data">
	        <a class="nav-link" href="javascript:;" onclick="parentNode.submit();">
	          <i class="fas fa-fw fa-tachometer-alt"></i>
<!-- 	          Dashboard -->
	          <span>Pantalla Principal</span></a>
		  </form>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
<!--         Interface -->
		Interfaz
      </div>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-fw fa-cog"></i>
          <span>Dispositivos</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Dispositivos</h6>
            <a class="collapse-item" href="<c:url value='home/componentmyown' />">Mis Dispositivos</a>
<%--             <a class="collapse-item" href="<c:url value='/home/componentshared' />">Shared with me</a> --%>
          </div>
        </div>
      </li>
      
            <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="<c:url value='ayuda' />"  aria-expanded="true" aria-controls="collapseUtilities">
          <i class="far fa-question-circle"></i>
          <span>Ayuda</span>
        </a>
      </li>

<!--       Nav Item - Utilities Collapse Menu -->
<!--       <li class="nav-item"> -->
<!--         <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities"> -->
<!--           <i class="fas fa-fw fa-wrench"></i> -->
<!--           <span>Tools</span> -->
<!--         </a> -->
<!--         <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar"> -->
<!--           <div class="bg-white py-2 collapse-inner rounded"> -->
<!--             <h6 class="collapse-header">Soporte</h6> -->
<%--             <a class="collapse-item" href="<c:url value='/Hfaq' />">Preguntas Frecuentes</a> --%>
<%--             <a class="collapse-item" href="<c:url value='/ayuda' />">Ayuda</a> --%>
<!--           </div> -->
<!--         </div> -->
<!--       </li> -->

      <!-- Divider -->
      <hr class="sidebar-divider d-none d-md-block">

      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

          <!-- Sidebar Toggle (Topbar) -->
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>

          <!-- Topbar Navbar -->
          <ul class="navbar-nav ml-auto">

            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
            <li class="nav-item dropdown no-arrow d-sm-none">
              <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-search fa-fw"></i>
              </a>
            </li>

            <!-- Nav Item - User Information -->
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${user.firstname}</span>
                <img class="img-profile rounded-circle" src="/cDash/resources/css/img/usuario.jpeg">
              </a>
              <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">                
                <form role="form" action="<c:url value="profileuser/userid"/>" method="get" enctype="multipart/form-data">
			        <a class="dropdown-item" href="javascript:;" onclick="parentNode.submit();">
			          <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                  		Editar Usuario
                  	</a>
				</form>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="login" data-toggle="modal" data-target="#logoutModal">
                  <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                  Salir
                </a>
              </div>
            </li>

          </ul>

        </nav>
        <!-- End of Topbar -->

  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">¿Esta seguro de Salir?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">¿Está listo para finalizar su sesión actual?</div>
        <div class="modal-footer">
          <form role="form" action="<c:url value="/logoutsession"/>" method="get" enctype="multipart/form-data">
          	<button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
          	<a class="btn btn-primary" href="javascript:;" onclick="parentNode.submit();">Salir</a>
          </form>
        </div>
      </div>
    </div>
  </div>
  
  
  
    
  
