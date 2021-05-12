<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
        <main class="mt-5 container-fluid d-flex justify-content-center align-items-center">
            <div id="alerts" name="alerts">
                <jsp:include page="alerts.jsp"/>
            <div>
	        <div class="d-flex">
	          <div class="card border border-dark">
	            <!--div class="card-header bg-dark text-light">
	              <h5 class="card-title text-center">User:</h5>
	            </div-->
	            <div class="card-body text-center px-5 py-3">
	            	<div class="row">
	                	<a class="btn btn-dark" href="/GestionEtablissement/student/create">Ajouter un étudiant</a>
	            	</div>
	            	<div class="row mt-3">
	                	<a class="btn btn-dark" href="/GestionEtablissement/students">Gerer les étudiants</a>
	            	</div>
	            	<div class="row mt-3">
	                	<a class="btn btn-dark" href="/GestionEtablissement/courses">Gerer les cours</a>
	            	</div>
	            	<div class="row mt-3">
	                	<a class="btn btn-dark" href="/GestionEtablissement/marks">Gerer les notes</a>
	            	</div>
	            	<div class="row mt-3">
	                	<a class="btn btn-dark" href="/GestionEtablissement/stats">Voires les statistiques</a>
	            	</div>
	            </div>
	          </div>
          </div>
        </main>
<jsp:include page="footer.jsp"/>