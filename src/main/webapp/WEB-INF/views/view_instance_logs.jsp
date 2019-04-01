<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />

    <!-- Logs -->
    <script src='<c:url value="/resources/js/filterlogs.js" />'></script>

    <!-- Page Heading -->
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">
                eReach - View Instance Logs
            </h1>
        </div>
    </div>
	<div>
		<div class="w100 aligned mvsButton">
				<table class="table table-hover">
					<!--<thead><tr><td class="col-md-2"></td><td class="col-md-8"></td></tr></thead>-->
					<tbody>
						<tr><td class="col-md-1" >MSISDN:</td><td><input class="col-md-10" type="text" id="byMsisdn" name="byMsisdn" ></td></tr>
					</tbody>
				</table>
				<p></p>
				<div>
					<input  class="w20" type="button" value="Retrieve" onclick="logRetrieve('MSISDN', 'byMsisdn');" />
				</div>
			<div id="byMsisdnLogSection"></div>
		</div>
		<p></p>
	</div>

<jsp:include page="footer.jsp" />