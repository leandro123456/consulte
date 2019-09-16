<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<link rel="stylesheet" type="text/css" href='<c:url value="/resources/cdash_logo/logo.css" />'>
    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      
		<form role="form" action="<c:url value="/home"/>" method="get" enctype="multipart/form-data">
		      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="javascript:;" onclick="parentNode.submit();">
<!-- 		        <div class="sidebar-brand-icon"> -->
				<div class="logodash">
<!-- 		          <i class="fas fa-laugh-wink"></i> -->
				<img alt="" src='<c:url value="/resources/cdash_logo/lohoH-iso-blanco-alcorte.png"  />'/>
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
	          <span>Dashboard</span></a>
		  </form>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        Interface
      </div>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-fw fa-cog"></i>
          <span>Components</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Custom Components:</h6>
            <a class="collapse-item" href="<c:url value='/home/componentmyown' />">My own</a>
            <a class="collapse-item" href="<c:url value='/home/componentshared' />">Shared with me</a>
          </div>
        </div>
      </li>

      <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities">
          <i class="fas fa-fw fa-wrench"></i>
          <span>Tools</span>
        </a>
        <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Debug Utilities:</h6>
            <a class="collapse-item" href="<c:url value='/home/listdebugmyown' />">My own</a>
            <a class="collapse-item" href="<c:url value='/home/listdebugshared' />">Shared with me</a>
          </div>
        </div>
      </li>

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

            <!-- Nav Item - Alerts -->
            <li class="nav-item dropdown no-arrow mx-1">
              <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-bell fa-fw"></i>
                <!-- Counter - Alerts SE MUESTRA CUANDO TIENE MENSAJES-->
<!--                 <span class="badge badge-danger badge-counter">3+</span> -->
              </a>
              <!-- Dropdown - Alerts -->
              <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="alertsDropdown">
                <h6 class="dropdown-header">
                  Alerts Center
                </h6>              
<!--                 <a class="dropdown-item d-flex align-items-center" href="#"> -->
<!--                   <div class="mr-3"> -->
<!--                     <div class="icon-circle bg-warning"> -->
<!--                       <i class="fas fa-exclamation-triangle text-white"></i> -->
<!--                     </div> -->
<!--                   </div> -->
<!--                   <div> -->
<!--                     <div class="small text-gray-500">December 2, 2019</div> -->
<!--                     Spending Alert: We've noticed unusually high spending for your account. -->
<!--                   </div> -->
<!--                 </a> -->
                <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
              </div>
            </li>

            <!-- Nav Item - Messages -->
            <li class="nav-item dropdown no-arrow mx-1">
              <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-envelope fa-fw"></i>
                <!-- Counter - Messages SE MUETRA CUAND TIENE MENSAJES-->
<!--                 <span class="badge badge-danger badge-counter">7</span> -->
              </a>
              <!-- Dropdown - Messages -->
              <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="messagesDropdown">
                <h6 class="dropdown-header">
                  Message Center
                </h6>
<!--                 <a class="dropdown-item d-flex align-items-center" href="#"> -->
<!--                   <div class="dropdown-list-image mr-3"> -->
<!--                     <img class="rounded-circle" src="https://source.unsplash.com/fn_BT9fwg_E/60x60" alt=""> -->
<!--                     <div class="status-indicator bg-success"></div> -->
<!--                   </div> -->
<!--                   <div class="font-weight-bold"> -->
<!--                     <div class="text-truncate">Hi there! I am wondering if you can help me with a problem I've been having.</div> -->
<!--                     <div class="small text-gray-500">Emily Fowler · 58m</div> -->
<!--                   </div> -->
<!--                 </a> -->
                <a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>
              </div>
            </li>

            <div class="topbar-divider d-none d-sm-block"></div>

            <!-- Nav Item - User Information -->
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
<%-- esta ya estaba comentada                 <span class="mr-2 d-none d-lg-inline text-gray-600 small">"${usuario.firstname}"</span> --%>
                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${user.firstname}</span>
                <img class="img-profile rounded-circle" src="https://source.unsplash.com/Mv9hjnEUHR4/60x60">
              </a>
<!--               Dropdown - User Information -->
              <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">                
                <form role="form" action="<c:url value="/profileuser/userid"/>" method="get" enctype="multipart/form-data">
<%-- esta ya estaba comentada 			       ${user.id} --%>
			        <a class="dropdown-item" href="javascript:;" onclick="parentNode.submit();">
			          <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                  		Perfil
                  	</a>
				</form>
                <a class="dropdown-item" href="#">
                  <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                  Settings
                </a>
                <a class="dropdown-item" href="#">
                  <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                  Activity Log
                </a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="/login" data-toggle="modal" data-target="#logoutModal">
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
          <h5 class="modal-title" id="exampleModalLabel">Esta seguro de Salir?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">está listo para finalizar su sesión actual?</div>
        <div class="modal-footer">
          <form role="form" action="<c:url value="/logoutsession"/>" method="get" enctype="multipart/form-data">
          	<button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
          	<a class="btn btn-primary" href="javascript:;" onclick="parentNode.submit();">Salir</a>
          </form>
        </div>
      </div>
    </div>
  </div>
  
  
  
    
  
