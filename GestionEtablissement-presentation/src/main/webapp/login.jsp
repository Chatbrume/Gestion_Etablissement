<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Amélie Nioche">
        <title>GestionEtablissement</title>
        <!-- Bootstrap core CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    </head>
    <body class="container-fluid d-flex justify-content-center min-vh-100 align-items-center">
        <main class="d-flex h-100">
            <div class="card text-white border-dark mb-3" style="max-width: 18rem;">
                <div class="card-header bg-dark">
                    <h5 class="card-title text-center">Please sign in</h5>
                    <% if(request.getAttribute("error") != null) {%>
                        <div class="error"><%=request.getAttribute("error")%></div>
                    <%}%>
                </div>
                <form id="loginform" method="post" action="/GestionEtablissement/login">
                	<input type="text" name="connexion" value="login" hidden>
                	<div class="card-body text-dark bg-light border-dark">
                        <div class="input-group flex-nowrap py-2">
                            <span class="input-group-text bg-dark text-light" id="addon-wrapping">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
                                    <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
                                </svg>
                            </span>
                            <input type="username" class="form-control" name="username" placeholder="Username" aria-label="Username" aria-describedby="addon-wrapping" required>
                        </div>
                        <div class="input-group flex-nowrap">
                            <span class="input-group-text bg-dark text-light" id="addon-wrapping">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-lock" viewBox="0 0 16 16">
                                    <path d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2zm3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2zM5 8h6a1 1 0 0 1 1 1v5a1 1 0 0 1-1 1H5a1 1 0 0 1-1-1V9a1 1 0 0 1 1-1z"/>
                                </svg>
                            </span>
                            <input type="password" class="form-control" name="password" placeholder="Password" aria-label="Password" aria-describedby="addon-wrapping" required>
                        </div>
                	</div>
	                <div class="card-footer bg-dark text-center">
	                    <input class="btn btn-lg btn-outline-light" type="submit" value="Sign in"/>
	                </div>
                </form>
            </div>
        </main>
    </body>
</html>