package eu.ensup.gestionetablissement.presentation;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eu.ensup.gestionetablissement.service.ConnectionService;
import eu.ensup.gestionetablissement.service.ExceptionService;

public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	public LoginServlet()
	{
		super();
	}
	
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		if( request.getParameter("connexion") != null && request.getParameter("connexion").equals("login") )
			this.login(request, response);
		else
			this.logout(request, response);
	}

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
		if( request.getParameter("connexion") != null && request.getParameter("connexion").equals("login") )
			this.login(request, response);
		else
			this.logout(request, response);
    }
    
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	//Récupération des info du formulaire
        String login = request.getParameter("username");
        String pwd = request.getParameter("password");

        //Traitement des infos
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        ConnectionService cs = new ConnectionService();
        try {
            int role = cs.checkConnection(login, pwd);
            session.setAttribute("login", login);
            session.setAttribute("role", role);
            dispatcher= request.getRequestDispatcher("/accueil.jsp");
        }
        catch (ExceptionService exceptionService) {
            request.setAttribute("error", "Identifiant ou mot de passe incorrect");
            dispatcher= request.getRequestDispatcher("/login.jsp");
        }
        //Redirection
        dispatcher.forward(request, response);
    }
    
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	//Traitement des infos
        HttpSession session = request.getSession();
        
        if( request.getAttribute("login") != null )
        	session.removeAttribute("login");
        if( request.getAttribute("role") != null )
        	session.removeAttribute("role");

        //Redirection
        this.getServletContext().getRequestDispatcher( "/login.jsp" ).forward( request, response );
    }
}
