<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ page import="eu.ensup.gestionetablissement.dto.StudentDTO" %>
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
							<h5 class="card-title text-center">Liste de tous les étudiants</h5>
						</div>
						<div class="col-sm-2">
						</div>
					</div>
				</div>
				<div class="card-body bg-light border-dark">
					<a class="btn btn-outline-dark mb-2" href="/GestionEtablissement/student/create">Ajouter un étudiant</a>
                    <table class="table table-striped table-hover table-bordered border-dark rounded">
                        <thead class="table-dark">
                            <tr class="text-center">
                                <th> Nom </th>
                                <th> Prenom </th>
                                <th> Email </th>
                                <th> Adresse </th>
                                <th> Téléphone </th>
                                <th> Date de naissance </th>
                                <th> Actions </th>
                            </tr>
                        </thead>
                        <tbody>
                        <%
                            List<StudentDTO> list = (List<StudentDTO>) request.getAttribute("students");
                            for(StudentDTO person : list)
                            {
                        %>
                            <tr>
                                <td> <%=person.getLastname()%> </td>
                                <td> <%=person.getFirstname()%> </td>
                                <td> <%=person.getMailAddress()%> </td>
                                <td> <%=person.getAddress()%> </td>
                                <td> <%=person.getPhoneNumber()%> </td>
                                <td> <%=((StudentDTO) person).getDateOfBirth().toString()%> </td>
                                <td class="text-center">
                                    <a class="btn btn-sm btn-dark" href="/GestionEtablissement/student/get/<%=person.getId()%>">Détails</a>
                                    <a class="btn btn-sm btn-dark" href="/GestionEtablissement/student/update/<%=person.getId()%>">Modifier</a>
                                    <a class="btn btn-sm btn-dark" href="/GestionEtablissement/student/delete/<%=person.getId()%>">Supprimer</a>
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