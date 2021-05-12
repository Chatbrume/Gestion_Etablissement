<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ page import="eu.ensup.gestionetablissement.dto.CourseDTO" %>
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
							<h5 class="card-title text-center">Liste de tous les cours</h5>
						</div>
						<div class="col-sm-2">
						</div>
					</div>
				</div>
				<div class="card-body bg-light border-dark">
					<a class="btn btn-outline-dark mb-2" href="/GestionEtablissement/course/create">Ajouter un cours</a>
                    <table class="table table-striped table-hover table-bordered border-dark rounded">
                        <thead class="table-dark">
                            <tr class="text-center">
                                <th> Nom du cours </th>
                                <th> Nombre d'heure </th>
                                <th> Actions </th>
                            </tr>
                        </thead>
                        <tbody>
                        <%
                            List<CourseDTO> list = (List<CourseDTO>) request.getAttribute("courses");
                           for(CourseDTO course : list)
                           {
                        %>
                            <tr>
                                <td> <%=course.getCourseSubject()%> </td>
                                <td> <%=course.getNbHours()%> </td>
                                <td class="text-center">
                                    <a class="btn btn-sm btn-dark" href="/GestionEtablissement/course/get/<%=course.getId()%>">Détails</a>
                                    <a class="btn btn-sm btn-dark" href="/GestionEtablissement/course/update/<%=course.getId()%>">Modifier</a>
                                    <a class="btn btn-sm btn-dark" href="/GestionEtablissement/course/delete/<%=course.getId()%>">Supprimer</a>
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