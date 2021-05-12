<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.lang.Integer" %>
<%@ page import="eu.ensup.gestionetablissement.dto.StudentDTO" %>
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
              <select class="form-select" aria-label="<%=request.getAttribute("title") %>" onchange="window.document.location.href='/GestionEtablissement/student/get/'+this.options[this.selectedIndex].value;">
                <%
                  List<StudentDTO> list = (List<StudentDTO>) request.getAttribute("students");
                  StudentDTO currentStudent = null;

                  int idstudent = Integer.parseInt(request.getAttribute("idstudent").toString());
                  boolean haveSelectedValue = false;
                  for(StudentDTO person : list)
                  {
                    if( (idstudent == -1 && ! haveSelectedValue) || (idstudent != -1 && person.getId() == idstudent) )
                    {
                      currentStudent = (StudentDTO) person;
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
            <div class="col-sm-2"></div>
          </div>
        </div>
        <%
          StudentDTO student= null;
          if( currentStudent != null )
            student= (StudentDTO) currentStudent;

          int idStudent= (student == null ? -1 : student.getId());
          String lastname= (student == null ?"" : student.getLastname());
          String firstname= (student == null ?"" : student.getFirstname());
          String dateofbirth= (student == null ?"" : student.getDateOfBirth().toString());
          String password= (student == null ?"" : student.getPassword());
          String address= (student == null ?"" : student.getAddress());
          String email= (student == null ?"" : student.getMailAddress());
          String telephone= (student == null ?"" : student.getPhoneNumber());
        %>
        <form name="student" action="<%=request.getAttribute("formaction")%>" method="get" class="needs-validation" novalidate>
          <div class="card-body bg-light">
            <div class="mb-3 row form-group">
              <label for="lastname" class="col-lg-2 col-sm-12 form-label">Nom :</label>
              <div class="col-lg-10 col-sm-12">
                <input type="text" name="lastname" class="form-control" id="lastname" value="<%=lastname%>" required/>
              </div>
            </div>
            <div class="mb-3 row form-group">
              <label for="firstname" class="col-lg-2 col-sm-12 form-label">Prénom :</label>
              <div class="col-lg-10 col-sm-12">
                <input type="text" name="firstname" class="form-control" id="firstname" value="<%=firstname%>" required/>
              </div>
            </div>
            <div class="mb-3 row form-group">
              <label for="password" class="col-lg-2 col-sm-12 form-label">Password :</label>
              <div class="col-lg-10 col-sm-12">
                <input type="password" name="password" class="form-control" id="password" value="<%=password%>" required/>
              </div>
            </div>
            <div class="mb-3 row form-group">
              <label for="address" class="col-lg-2 col-sm-12 form-label">Adresse :</label>
              <div class="col-lg-10 col-sm-12">
                <input type="address" name="address" class="form-control" id="address" value="<%=address%>" required/>
              </div>
            </div>
            <div class="mb-3 row form-group">
              <label for="telephone" class="col-lg-2 col-sm-12 form-label">Téléphone :</label>
              <div class="col-lg-10 col-sm-12">
                <input type="tel" name="telephone" class="form-control" id="telephone" value="<%=telephone%>" required/>
              </div>
            </div>
            <div class="mb-3 row form-group">
              <label for="dateofbirth" class="col-lg-2 col-sm-12 form-label">Date de naissance :</label>
              <div class="col-lg-10 col-sm-12">
                <input type="date" name="dateofbirth" class="form-control" id="dateofbirth" value="<%=dateofbirth%>" required/>
              </div>
            </div>
            <div class="mb-3 row form-group">
              <label for="email" class="col-lg-2 col-sm-12 form-label">Email :</label>
              <div class="col-lg-10 col-sm-12">
                <input type="email" name="email" class="form-control" id="email" value="<%=email%>" required/>
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
                  <h5 class="modal-title" id="staticBackdropLabel">Voulez-vous vraiment supprimer cet étudiant ?</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                  <input type="submit" class="btn btn-dark" value="Delete" formaction="/GestionEtablissement/student/delete/<%=idStudent%>"/>
                </div>
              </div>
            </div>
          </div>
        </form>
      </div>
    </main>
<jsp:include page="footer.jsp" />
