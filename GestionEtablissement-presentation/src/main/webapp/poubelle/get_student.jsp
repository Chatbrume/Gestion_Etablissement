<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Amélie Nioche">
        <title>GestionEtablissement</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js" integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js" integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc" crossorigin="anonymous"></script>
        <script type="text/javascript" src="/WebAppTest/create_new_student.js" defer></script>
     </head>
    <body>
        <header class="bg-dark">
            <nav class="navbar navbar-expand-lg navbar-dark justify-content-between">
              <div class="container-fluid">
                <a class="navbar-brand" href="#">Gestion d'un établissement</a>
                <a class="btn btn-outline-light my-0 my-sm-0" href="/GestionEtablissement/login">Logout</a>
              </div>
            </nav>
        </header>
        
        <main class="container py-5">
            <div id="alerts" name="alerts" class="hidden">
                <%@ include file="/alerts.jsp" %>
            <div>
          <div class="card">
            <div class="card-header bg-dark text-light">
              <a class="btn btn-outline-light" href="/GestionEtablissement/accueil">Return</a>
              <h5 class="card-title text-center">Etudiant</h5>
            </div>
              <div class="card-body bg-light">
                <div class="mb-3 row form-group">
                  <label for="name" class="col-lg-2 col-sm-12 form-label">Nom :</label>
                  <div class="col-lg-10 col-sm-12"><input type="name" name="name" class="form-control" id="name" required></div>
                </div>
                <div class="mb-3 row form-group">
                  <label for="firstname" class="col-lg-2 col-sm-12 form-label">Prénom :</label>
                  <div class="col-lg-10 col-sm-12"><input type="firstname" name="firstname" class="form-control" id="firstname" required></div>
                </div>
                <div class="mb-3 row form-group">
                  <label for="address" class="col-lg-2 col-sm-12 form-label">Adresse :</label>
                  <div class="col-lg-10 col-sm-12"><input type="address" name="address" class="form-control" id="address"></div>
                </div>
                <div class="mb-3 row form-group">
                  <label for="telephone" class="col-lg-2 col-sm-12 form-label">Téléphone :</label>
                  <div class="col-lg-10 col-sm-12"><input type="tel" name="telephone" class="form-control" id="telephone"></div>
                </div>
                <div class="mb-3 row form-group">
                  <label for="dateofbirth" class="col-lg-2 col-sm-12 form-label">Date de naissance :</label>
                  <div class="col-lg-10 col-sm-12"><input type="date" name="dateofbirth" class="form-control" id="dateofbirth"></div>
                </div>
                <div class="mb-3 row form-group">
                  <label for="email" class="col-lg-2 col-sm-12 form-label">Votre adresse mail :</label>
                  <div class="col-lg-10 col-sm-12"><input type="email" name="email" class="form-control" id="email"></div>
                </div>
              </div>
              <div class="card-footer bg-dark">
                <div class="row">
                  <div class="col-lg-4 col-sm-4 text-center"><input type="reset" class="btn btn-outline-light" value="Reset"/></div>
                  <div class="col-lg-4 col-sm-4 text-center"></div>
                  <div class="col-lg-4 col-sm-4 text-center"><button type="button" class="btn btn-outline-light" id="btnsubmit">Submit</button></div>
                </div>
                <button type="button" class="btn btn-primary" id="btnsubmithidden" data-bs-toggle="modal" data-bs-target="#verifyaddstudent" hidden>Submit</button>
              </div>
          </div>
        </main>
    </body>
</html>