<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="eu.ensup.gestionetablissement.dto.MarkDTO" %>
<jsp:include page="header.jsp"/>
		<main class="container py-5">
		    <div id="alerts" name="alerts">
		        <jsp:include page="alerts.jsp"/>
            <div>
			<div class="card">
				<div class="card-header bg-dark text-light">
					<div class="row">
						<div class="col-sm-2">
							<a class="btn btn-outline-light" href="/GestionEtablissement/accueil"><</a>
						</div>
						<div class="col-sm-8">
							<h5 class="card-title text-center">Liste de tous les notes</h5>
						</div>
						<div class="col-sm-2">
						</div>
					</div>
				</div>
				<div class="card-body bg-light border-dark">
					<a class="btn btn-outline-dark mb-2" href="/GestionEtablissement/mark/create">Ajouter une notes</a>
                    <table class="table table-striped table-hover table-bordered border-dark rounded">
                        <thead class="table-dark">
                            <tr>
                                <th> idStudent </th>
                                <th> idCourse </th>
                                <th> Notes </th>
                                <th> assessment </th>
                                <th> Actions </th>
                            </tr>
                        </thead>
                        <tbody>
                        <%
                            List<MarkDTO> list = (List<MarkDTO>) request.getAttribute("marks");
                           for(MarkDTO mark : list)
                           {
                        %>
                            <tr>
                                <td> <%=mark.getIdStudent()%> </td>
                                <td> <%=mark.getIdCourse()%> </td>
                                <td> <%=mark.getMark()%> </td>
                                <td> <%=mark.getAssessment()%> </td>
                                <td>
                                    <a class="btn btn-sm btn-dark" href="/GestionEtablissement/mark/get/<%=mark.getId()%>">Détails</a>
                                    <a class="btn btn-sm btn-dark" href="/GestionEtablissement/mark/update/<%=mark.getId()%>">Modifier</a>
                                    <a class="btn btn-sm btn-dark" href="/GestionEtablissement/mark/delete/<%=mark.getId()%>">Supprimer</a>
                                </td>
                            </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
                <div class="card-footer bg-dark">
                </div>
            </div>
		</main>
<jsp:include page="footer.jsp" />