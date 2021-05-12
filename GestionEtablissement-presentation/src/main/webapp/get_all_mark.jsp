<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ page import="eu.ensup.gestionetablissement.dto.MarkDTO" %>
<%@ page import="eu.ensup.gestionetablissement.dto.StudentDTO" %>
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
                            <tr class="text-center">
                                <th> Etudiant </th>
                                <th> Cours </th>
                                <th> Notes </th>
                                <th> Appréciation </th>
                                <th> Actions </th>
                            </tr>
                        </thead>
                        <tbody>
                        <%
                            List<MarkDTO> list = (List<MarkDTO>) request.getAttribute("marks");
                            List<StudentDTO> listStudents = (List<StudentDTO>) request.getAttribute("students");
                            List<CourseDTO> listCourses = (List<CourseDTO>) request.getAttribute("courses");

                            for(MarkDTO mark : list)
                            {
                        %>
                            <tr>
                                <td>
                        <%
                                boolean haveSelectedValue = false;
                                for(StudentDTO student : listStudents)
                                {
                                    if( student.getId() == mark.getIdStudent() )
                                    {
                                        haveSelectedValue = true;
                        %>
                                    <%=student.getLastname() + " " + student.getFirstname()%>
                        <%
                                    }
                                }

                                if( ! haveSelectedValue )
                                {
                                    haveSelectedValue = true;
                        %>
                                    <%="Erreur cet étudiant n'existe plus"%>
                        <%
                                }
                        %>
                                </td>
                                <td>
                        <%
                                haveSelectedValue = false;
                                for(CourseDTO course : listCourses)
                                {
                                    if( course.getId() == mark.getIdCourse() )
                                    {
                                        haveSelectedValue = true;
                        %>
                                    <%=course.getCourseSubject()%>
                        <%
                                    }
                                }

                                if( ! haveSelectedValue )
                                {
                        %>
                                    <%="Erreur ce cours n'existe plus"%>
                        <%
                                }
                        %>
                                </td>
                                <td> <%=mark.getMark()%> </td>
                                <td> <%=mark.getAssessment()%> </td>
                                <td class="text-center">
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