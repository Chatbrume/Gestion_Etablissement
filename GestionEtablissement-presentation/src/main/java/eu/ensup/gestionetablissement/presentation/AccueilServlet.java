package eu.ensup.gestionetablissement.presentation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccueilServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public AccueilServlet()
	{
		super();
	}
	
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
        System.out.println("login="+request.getSession().getAttribute("login"));
        System.out.println("role="+request.getSession().getAttribute("role"));
		this.getServletContext().getRequestDispatcher( "/accueil.jsp" ).forward( request, response );
	}

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        System.out.println("login="+request.getSession().getAttribute("login"));
        System.out.println("role="+request.getSession().getAttribute("role"));
		this.getServletContext().getRequestDispatcher( "/accueil.jsp" ).forward( request, response );
    }
}   