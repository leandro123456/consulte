<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />


    <!-- Logs -->
    <script src='<c:url value="/resources/js/logs.js" />'></script>
    <!--
	<div>
		<div class="w100 aligned mvsButton">
		  <h2>Logs: ccm</h2>
		  <form>
		    <div class="form-group">
		      <textarea class="form-control" rows="12" id="comment"></textarea>
		    </div>
		  </form>
		</div>
	</div>
    <div>-->
    <script src='<c:url value="/resources/js/jqueryViewLogs.js" />'></script>
    <div class="w100 aligned mvsButton" id="CCM">
        <h2 class="aligned">CCM</h2>
        <textarea id="logTextAreaSMSC" name="logTextAreaSMSC" class="form-control" rows="20"></textarea>
        <br />
        <br />
        <input  class="w20" type="submit" value="Update" onclick="logUpdate('SMSC', 'logTextAreaSMSC');" />
        &nbsp;
        <input  class="w20" type="submit" value="Clear" onclick="logClear('SMSC', 'logTextAreaSMSC');" />
        &nbsp;
        <input  class="w20" type="submit" value="Add Separator" onclick="logSeparator('SMSC', 'logTextAreaSMSC');" />
    </div>
    
    
    
    <div class="w100 aligned mvsButton" id="DP">
        <h2 class="aligned">DP</h2>
        <textarea id="logTextAreaTLS" name="logTextAreaTLS" class="form-control"" rows="20"></textarea>
        <br />
        <br />
        <input  class="w20" type="submit" value="Update" onclick="logUpdate('TLS', 'logTextAreaTLS');" />
        &nbsp;
        <input  class="w20" type="submit" value="Clear" onclick="logClear('TLS', 'logTextAreaTLS');" />
        &nbsp;
        <input  class="w20" type="submit" value="Add Separator" onclick="logSeparator('TLS', 'logTextAreaTLS');" />
    </div>
     <div class="w100 aligned mvsButton" id="SR">
            <h2 class="aligned">SR</h2>
            <textarea id="logTextAreaWEB" name="logTextAreaWEB" class="form-control"" rows="20"></textarea>
            <br />
            <br />
            <input  class="w20" type="submit" value="Update" onclick="logUpdate('WEB', 'logTextAreaWEB');" />
            &nbsp;
            <input  class="w20" type="submit" value="Clear" onclick="logClear('WEB', 'logTextAreaWEB');" />
            &nbsp;
            <input  class="w20" type="submit" value="Add Separator" onclick="logSeparator('WEB', 'logTextAreaWEB');" />
        </div>
    </div>

<jsp:include page="footer.jsp" />
