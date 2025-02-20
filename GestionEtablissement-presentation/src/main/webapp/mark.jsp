<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ page import="eu.ensup.gestionetablissement.dto.MarkDTO" %>
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
              <h5 class="card-title text-center"><%=request.getAttribute("title") %></h5>
            </div>
            <div class="col-sm-2"></div>
          </div>
        </div>
        <%
          MarkDTO mark= null;
          if( request.getAttribute("mark") != null )
            mark = (MarkDTO) request.getAttribute("mark");

          int    idMark     = (mark == null ? -1 : mark.getId());
          int    idStudent  = (mark == null ? -1 : mark.getIdStudent());
          int    idCourse   = (mark == null ? -1 : mark.getIdCourse());
          Float  markValue  = (mark == null ? 0f : mark.getMark());
          String assessment = (mark == null ? "" : mark.getAssessment());
        %>
        <form name="mark" action="<%=request.getAttribute("formaction")%>" method="get" class="needs-validation" novalidate>
          <div class="card-body bg-light">
            <div class="mb-3 row form-group">
              <label for="idStudent" class="col-lg-2 col-sm-12 form-label">Etudiant :</label>
              <div class="col-lg-10 col-sm-12">
                <select class="form-select" name="idStudent" id="idStudent" aria-label="Student">
                  <%
                    List<StudentDTO> listStudents = (List<StudentDTO>) request.getAttribute("students");

                    boolean haveSelectedValue = false;
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
            <div class="mb-3 row form-group">
              <label for="mark" class="col-lg-2 col-sm-12 form-label">Note :</label>
              <div class="col-lg-10 col-sm-12">
                <input type="number" name="mark" class="form-control" id="mark" value="<%=markValue%>" min="0" max="20" required/>
              </div>
            </div>
            <div class="mb-3 row form-group">
              <label for="assessment" class="col-lg-2 col-sm-12 form-label">Appréciation :</label>
              <div class="col-lg-10 col-sm-12">
                <input type="text" name="assessment" class="form-control" id="assessment" value="<%=assessment%>"/>
              </div>
            </div>
          </div>
          <div class="card-footer bg-dark">
            <div class="row">
              <div class="col-lg-4 col-sm-4 text-center">
                <input type="reset" class="btn btn-outline-light" value="Reset" />
              </div>
              <div class="col-lg-4 col-sm-4 text-center">
                <input type="button" class="btn btn-outline-light" value="Delete" data-bs-toggle="modal" data-bs-target="#verifydeletestudent" />
              </div>
              <div class="col-lg-4 col-sm-4 text-center">
                <input type="submit" class="btn btn-outline-light" value="Submit" />
              </div>
            </div>
          </div>
          <div class="modal fade" id="verifydeletestudent" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel"aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="staticBackdropLabel">Voulez-vous vraiment supprimer cet note ?</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                  <input type="submit" class="btn btn-dark" value="Delete" formaction="/GestionEtablissement/mark/delete/<%=idMark%>"/>
                </div>
              </div>
            </div>
          </div>
        </form>
      </div>
    </main>
<jsp:include page="footer.jsp" />
