<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ page import="java.lang.Integer" %>
<%@ page import="eu.ensup.gestionetablissement.dto.StudentDTO" %>
<%@ page import="eu.ensup.gestionetablissement.dto.CourseDTO" %>
<jsp:include page="header.jsp" />
    <main class="container py-5">
      <div id="alerts" name="alerts">
        <jsp:include page="alerts.jsp" />
      </div>
      <div class="card">
        <div class="card-header bg-dark text-light">
          <div class="row">
            <div class="col-sm-2">
              <a class="btn btn-outline-light" href="/GestionEtablissement/accueil"> < </a>
            </div>
            <div class="col-sm-8">
              <h5 class="card-title text-center">Lier</h5>
            </div>
            <div class="col-sm-2"></div>
          </div>
        </div>
        <%
            boolean haveSelectedValue = false;
            
            int idStudent = -1;
            try{
                idStudent = Integer.parseInt(request.getAttribute("idStudent").toString());
            }  
            catch(Exception e) {}

            int idCourse = -1;
            try{
                idCourse = Integer.parseInt(request.getAttribute("idCourse").toString());
            }  
            catch(Exception e) {}

            //if( (List.of(request.getAttributeNames())).contains("idStudent") && request.getAttribute("idStudent") != null )
            //    idStudent = Integer.parseInt(request.getAttribute("idStudent"));
            
            //if( (List.of(request.getAttributeNames())).contains("idCourse") && request.getAttribute("idCourse") != null )
            //    idCourse = Integer.parseInt(request.getAttribute("idCourse"));
        %>
        <form name="mark" action="/GestionEtablissement/couseperson" method="get" class="needs-validation" novalidate>
          <div class="card-body bg-light">
            <div class="mb-3 row form-group">
              <label for="idStudent" class="col-lg-2 col-sm-12 form-label">Etudiant :</label>
              <div class="col-lg-10 col-sm-12">
                <select class="form-select" name="idStudent" id="idStudent" aria-label="Student">
                  <%
                    List<StudentDTO> listStudents = (List<StudentDTO>) request.getAttribute("students");

                    haveSelectedValue = false;
                    for(StudentDTO person : listStudents)
                    {
                      if( (idStudent == -1 && ! haveSelectedValue) || (idStudent != -1 && person.getId() == idStudent) )
                      {
                        haveSelectedValue = true;
                  %>
                    <option value="<%=person.getId()%>" selected><%=person.getLastname()%> <%=person.getFirstname()%></option>
                  <%
                      }
                      else
                      {
                  %>
                    <option value="<%=person.getId()%>"><%=person.getLastname()%> <%=person.getFirstname()%></option>
                  <%
                      }
                    }
                  %>
                </select>
              </div>
            </div>
            <div class="mb-3 row form-group">
              <label for="idCourse" class="col-lg-2 col-sm-12 form-label">Cours :</label>
              <div class="col-lg-10 col-sm-12">
                <select class="form-select" name="idCourse" id="idCourse" aria-label="Course">
                  <%
                    List<CourseDTO> listCourses = (List<CourseDTO>) request.getAttribute("courses");

                    haveSelectedValue = false;
                    for(CourseDTO course : listCourses)
                    {
                      if( (idCourse == -1 && ! haveSelectedValue) || (idCourse != -1 && course.getId() == idCourse) )
                      {
                        haveSelectedValue = true;
                  %>
                    <option value="<%=course.getId()%>" selected><%=course.getCourseSubject()%></option>
                  <%
                      }
                      else
                      {
                  %>
                    <option value="<%=course.getId()%>"><%=course.getCourseSubject()%></option>
                  <%
                      }
                    }
                  %>
                </select>
              </div>
            </div>
          </div>
          <div class="card-footer bg-dark">
            <div class="row">
              <div class="col-lg-4 col-sm-4 text-center">
                <input type="reset" class="btn btn-outline-light" value="Reset" />
              </div>
              <div class="col-lg-4 col-sm-4 text-center">
                <!--input type="button" class="btn btn-outline-light" value="Delete" data-bs-toggle="modal" data-bs-target="#verifydeletestudent" /-->
              </div>
              <div class="col-lg-4 col-sm-4 text-center">
                <input type="submit" class="btn btn-outline-light" value="Submit" />
              </div>
            </div>
          </div>
          <!--div class="modal fade" id="verifydeletestudent" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel"aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="staticBackdropLabel">Voulez-vous vraiment supprimer cet liason ?</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                  <input type="submit" class="btn btn-dark" value="Delete" formaction="/GestionEtablissement/mark/delete/"/>
                </div>
              </div>
            </div>
          </div-->
        </form>
      </div>
    </main>
<jsp:include page="footer.jsp" />
