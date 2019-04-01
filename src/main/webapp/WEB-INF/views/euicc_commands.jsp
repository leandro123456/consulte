<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
<!-- commands for view -->
<script src='<c:url value="/resources/js/euiccCommand.js" />'></script>
<script src='<c:url value="/resources/js/alertify.js" />'></script>
<link href='<c:url value="/resources/css/styleEuiccCommand.css" />'
	rel="stylesheet">

<!-- Page Heading -->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">eReach - eUICC Commands</h1>
	</div>
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12" id="euiccInfo">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">eUICC info</h3>
			</div>

			<div class="panel-body">
				<div class="form-group">
					<div class="row">
						<div class="col-md-4">
							<ul class="list-group">
								<li class="list-group-item"><span class="badge"></span>EID:
									${card.eid}</li>
								<li class="list-group-item"><span class="badge"></span>Eum-id:
									${card.eumId}</li>
								<li class="list-group-item"><span class="badge"></span>Production
									Date: ${card.productionDate}</li>
								<li class="list-group-item"><span class="badge"></span>Platform
									Type: ${card.platformType}</li>
								<li class="list-group-item"><span class="badge"></span>Platform
									Version: ${card.platformVersion}</li>
								<li class="list-group-item"><span class="badge"></span>Remaining
									Memory: ${card.remainingMemory}</li>
							</ul>
						</div>
						<div class="col-md-4">
							<ul class="list-group">
								<li class="list-group-item"><span class="badge"></span>Available
									Memory for Profiles: ${card.availableMemoryForProfiles}</li>
								<li class="list-group-item"><span class="badge"></span>Smsr-Id:
									${card.smSrId}</li>
								<!-- <li class="list-group-item"><span class="badge"></span>eUICC-Capabilities: ${card.eUiccCapabilities}</li> -->
								<li class="list-group-item"><span class="badge"></span>CAT-TP
									Support: ${card.cattpSupport}</li>
								<li class="list-group-item"><span class="badge"></span>CAT-TP
									Version: ${card.cattpVersion}</li>
								<li class="list-group-item"><span class="badge"></span>HTTP
									Support: ${card.httpSupport}</li>
								<li class="list-group-item"><span class="badge"></span>HTTP
									Version: ${card.httpVersion}</li>
								<li class="list-group-item"><span class="badge"></span>Secure
									packet version: ${card.securePacketVersion}</li>
							</ul>
						</div>
						<div class="col-md-4">
							<ul class="list-group">
								<li class="list-group-item"><span class="badge"></span>Remote
									provisioning version: ${card.remoteProvisioningVersion}</li>
								<li class="list-group-item"><span class="badge"></span>Eum
									Certificated: ${card.eumCertificated}</li>
								<li class="list-group-item"><span class="badge"></span>Signature
									Algorithm: ${card.signatureAlgorithm}</li>
								<li class="list-group-item"><span class="badge"></span>Signature:
									${card.signature}</li>
								<li class="list-group-item"><span class="badge"></span>ECASD:
									${card.ecasd}</li>
							</ul>
						</div>
					</div>
				</div>

				<script src='<c:url value="/resources/js/jqueryEuiccCommands.js" />'></script>
				<div id="actions">
					<c:if test="${edit}">
						<a class="btn btn-default btn-sm"
							href="<c:url value="/home/euicc/commands/" />${card.eid}/download-ipp/">Select
							new and Download</a>
						<button type="submit" class="btn btn-default disabled btn-sm"
							disabled="disabled">Generate and Download</button>
						<a class="btn btn-default btn-sm" id="btnEnableFallback"
							onclick="refreshView()"
							href="<c:url value="/home/euicc/commands/" />${card.eid}/${true}">Enable
							Fall-Back</a>
						<a class="btn btn-default btn-sm" id="btnDisableFallback"
							onclick="refreshView()"
							href="<c:url value="/home/euicc/commands/" />${card.eid}/${false}">Disable
							Fall-Back</a>
						<button type="button" class="btn btn-default btn-sm"
							data-toggle="modal" data-target="#ModalTimer${card.eid}">Action
							Timer</button>
					</c:if>
				</div>

				<div class="modal fade" id="ModalTimer${card.eid}" role="dialog">
					<div class="modal-dialog modal-sm">
						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<center>
									<h4 class="modal-title">Number of Status Command for
										FallBack and RollBack Mechanism activation</h4>
								</center>
							</div>
							<div class="modal-body">
								<form action="<c:url value="/home/euicc/commands/" />${card.eid}/number" method="get">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
										type="hidden" name="action" value="setStatusNumber" /> <input
										type="number" name="quantity" min="0" max="100"
										placeholder="    0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="submit" class="btn btn-default btn-sm">Send</button>
								</form>
							</div>
							<div class="modal-footer">
								<button style="background-color: #777777; border: #777777"
									type="button" class="btn btn-primary btn-sm btn-block"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- /.row -->
<div class="row">
	<div id="tableEuicc" class="col-lg-12">
		<c:if test="${not empty msg}">
			<div class="alert alert-warning">${msg}</div>
		</c:if>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">eUICC info upload</h3>
			</div>
			<div class="table-responsive" id="profiles"
				data-value=${card.simpleProfileCards.size()}>
				<table class="table table-bordered table-hover table-striped"
					style="overflow-x: visible !important; overflow-y: visible !important;">
					<thead>
						<tr>
							<!-- <th>IPP Description</th>-->
							<th>ICCID</th>
							<th>IMSI</th>
							<th>MSISDN</th>
							<th>Category</th>
							<th>Class</th>
							<th>Type</th>
							<th>Group</th>
							<th>Status</th>
							<th>Actions</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${card.simpleProfileCards}" var="profileCard">
							<tr>
								<!-- <td>${profileCard.ippDescription}</td>-->
								<td>${profileCard.iccid}</td>
								<td>${profileCard.imsi}</td>
								<td>${profileCard.msisdn}</td>
								<td>${profileCard.category}</td>
								<td>${profileCard.cclass}</td>
								<td>${profileCard.type}</td>
								<td>${profileCard.group}</td>
								<td align="center"><span id="state_${profileCard.iccid}">${profileCard.state}</span><span
									id="status_${profileCard.iccid}"
									class="glyphicon glyphicon-time"></span></td>
								<td>
									<div id="buttonAction">
										<c:if test="${edit}">
											<button type="button" id="buttonAction${profileCard.iccid}"
												class="btn btn-default btn-sm" data-toggle="modal"
												data-target="#myModal${profileCard.iccid}">Action</button>
										</c:if>
									</div> <!-- Modal --> <c:if test="${profileCard.falback}">
										<!-- Modal -->
										<div class="modal fade" id="myModal${profileCard.iccid}"
											role="dialog">
											<div class="modal-dialog modal-sm">

												<!-- Modal content-->
												<div class="modal-content">
													<div class="modal-header">
														<center>
															<h4 class="modal-title">Action IPP Selected</h4>
														</center>
													</div>
													<div class="modal-body">
														<form
															action="<c:url value="/home/euicc/commands/" />${card.eid}/"
															method="post">
															<input type="hidden" name="iccid"
																value="${profileCard.iccid}" /> <input type="hidden"
																name="action" value="enable" />
															<center>
																<button id='buttonEnable' style="display: none"
																	type="submit" class="btn btn-default btn-sm btn-block">Enable</button>
															</center>
														</form>
														<c:if test="${profileCard.state ne 'Enabled'}">
															<form
																action="<c:url value="/home/euicc/commands/" />${card.eid}/"
																method="post">
																<input type="hidden" name="iccid"
																	value="${profileCard.iccid}" /> <input type="hidden"
																	name="action" value="enable" />
																<center>
																	<button id='buttonEnable' type="submit"
																		class="btn btn-default btn-sm btn-block">Enable</button>
																</center>
															</form>
														</c:if>
														<p></p>
														<form
															action="<c:url value="/home/euicc/update/pol2/" />${card.eid}/"
															method="get">
															<input type="hidden" name="iccid"
																value="${profileCard.iccid}" />
															<center>
																<button id='buttonUpdatePol2' type="submit"
																	class="btn btn-default btn-sm btn-block">Update
																	Pol2</button>
															</center>
														</form>
														<p></p>
														<form
															action="<c:url value="/home/euicc/commands/" />${card.eid}/"
															method="post">
															<input type="hidden" name="iccid"
																value="${profileCard.iccid}" /> <input type="hidden"
																name="action" value="setemergencyprofile" />
															<center>
																<button id='buttonEmergencyProfile' type="submit"
																	class="btn btn-default btn-sm btn-block">Set
																	Emergency Profile</button>
															</center>
														</form>
													</div>
													<div class="modal-footer">
														<button style="background-color: #777777; border: #777777"
															type="button" class="btn btn-primary btn-sm btn-block"
															data-dismiss="modal">Close</button>
													</div>
												</div>

											</div>
										</div>
									</c:if> <c:if test="${profileCard.falback eq false}">
										<!-- Modal -->
										<div class="modal fade" id="myModal${profileCard.iccid}"
											role="dialog">
											<div class="modal-dialog modal-sm">

												<!-- Modal content-->
												<div class="modal-content">
													<div class="modal-header">
														<center>
															<h4 class="modal-title">Action IPP Selected</h4>
														</center>
													</div>
													<div class="modal-body">
														<c:if test="${profileCard.state ne 'Disabled'}">
															<form
																action="<c:url value="/home/euicc/commands/" />${card.eid}/"
																method="post">
																<input type="hidden" name="iccid"
																	value="${profileCard.iccid}" /> <input
																	id='buttonEnable' type="hidden" name="action"
																	value="enable" />
																<center>
																	<button type="submit" style="display: none"
																		class="btn btn-default btn-sm btn-block">Enable</button>
																</center>
															</form>
															<p></p>
															<form
																action="<c:url value="/home/euicc/commands/" />${card.eid}/"
																method="post">
																<input type="hidden" name="iccid"
																	value="${profileCard.iccid}" /> <input type="hidden"
																	name="action" value="disable" />
																<center>
																	<button id='buttonDisable' type="submit"
																		class="btn btn-default btn-sm btn-block">Disable</button>
																</center>
															</form>
														</c:if>
														<p></p>
														<c:if test="${profileCard.state ne 'Enabled'}">
															<form
																action="<c:url value="/home/euicc/commands/" />${card.eid}/"
																method="post">
																<input type="hidden" name="iccid"
																	value="${profileCard.iccid}" /> <input type="hidden"
																	name="action" value="enable" />
																<center>
																	<button id='buttonEnable' type="submit"
																		class="btn btn-default btn-sm btn-block">Enable</button>
																</center>
															</form>
															<p></p>
															<form
																action="<c:url value="/home/euicc/commands/" />${card.eid}/"
																method="post">
																<input type="hidden" name="iccid"
																	value="${profileCard.iccid}" /> <input type="hidden"
																	name="action" value="disable" />
																<center>
																	<button id='buttonDisable' style="display: none"
																		type="submit" class="btn btn-default btn-sm btn-block">Disable</button>
																</center>
															</form>
														</c:if>
														<p></p>
														<form
															action="<c:url value="/home/euicc/commands/" />${card.eid}/"
															method="post">
															<input type="hidden" name="iccid"
																value="${profileCard.iccid}" /> <input type="hidden"
																name="action" value="delete" />
															<center>
																<button type="submit" id='buttonDelete'
																	class="btn btn-default btn-sm btn-block">Delete</button>
															</center>
														</form>
														<p></p>
														<form
															action="<c:url value="/home/euicc/commands/" />${card.eid}/"
															method="post">
															<input type="hidden" name="iccid"
																value="${profileCard.iccid}" /> <input type="hidden"
																name="action" value="setfallbackattribute" />
															<center>
																<button id='buttonSetFallback' type="submit"
																	class="btn btn-default btn-sm btn-block">Set
																	Fallback Attribute</button>
															</center>
														</form>
														<p></p>
														<form
															action="<c:url value="/home/euicc/commands/" />${card.eid}/"
															method="post">
															<input type="hidden" name="iccid"
																value="${profileCard.iccid}" /> <input type="hidden"
																name="action" value="setemergencyprofile" />
															<center>
																<button id='buttonEmergencyProfile' type="submit"
																	class="btn btn-default btn-sm btn-block">Set
																	Emergency Profile</button>
															</center>
														</form>
														<p></p>
														<form
															action="<c:url value="/home/euicc/update/pol2/" />${card.eid}/"
															method="get">
															<input type="hidden" name="iccid"
																value="${profileCard.iccid}" />
															<center>
																<button is='buttonUpdatePol2' type="submit"
																	class="btn btn-default btn-sm btn-block">Update
																	Pol2</button>
															</center>
														</form>
													</div>
													<div class="modal-footer">
														<button style="background-color: #777777; border: #777777"
															type="button" class="btn btn-primary btn-sm btn-block"
															data-dismiss="modal">Close</button>
													</div>
												</div>

											</div>
										</div>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</div>

<jsp:include page="footer.jsp" />

<script type="text/javascript">
	$(document).ready(function(){
		check();
		checking();
		fallbackbnt(${fallbackstatus});
	});
</script>
