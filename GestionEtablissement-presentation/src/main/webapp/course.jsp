<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
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
          CourseDTO course= null;
          if( request.getAttribute("course") != null )
            course = (CourseDTO) request.getAttribute("course");

          int    idCourse = (course == null ? -1 : course.getId());
          String subject  = (course == null ? "" : course.getCourseSubject());
          Float  nbhours  = (course == null ? 0f : course.getNbHours());
        %>
        <form name="course" action="<%=request.getAttribute("formaction")%>" method="get" class="needs-validation" novalidate>
          <div class="card-body bg-light">
            <div class="mb-3 row form-group">
              <label for="subject" class="col-lg-2 col-sm-12 form-label">Sujet :</label>
              <div class="col-lg-10 col-sm-12">
                <input type="text" name="subject" class="form-control" id="subject" value="<%=subject%>" required/>
              </div>
            </div>
            <div class="mb-3 row form-group">
              <label for="nbhours" class="col-lg-2 col-sm-12 form-label">Nombre d'heure :</label>
              <div class="col-lg-10 col-sm-12">
                <input type="number" name="nbhours" class="form-control" id="nbhours" value="<%=nbhours%>" required/>
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
                  <h5 class="modal-title" id="staticBackdropLabel">Voulez-vous vraiment supprimer ce cours ?</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                  <input type="submit" class="btn btn-dark" value="Delete" formaction="/GestionEtablissement/course/delete/<%=idCourse%>"/>
                </div>
              </div>
            </div>
          </div>
        </form>
      </div>
    </main>
<jsp:include page="footer.jsp" />
