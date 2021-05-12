package eu.ensup.gestionetablissement.presentation;

import eu.ensup.gestionetablissement.dto.CourseDTO;
import eu.ensup.gestionetablissement.dto.PersonDTO;
import eu.ensup.gestionetablissement.dto.StudentDTO;
import eu.ensup.gestionetablissement.service.CourseService;
import eu.ensup.gestionetablissement.service.ExceptionService;
import eu.ensup.gestionetablissement.service.PersonService;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CourseServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public CourseServlet()
	{
		super();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		doAction( request, response );
	}

	@Override
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		doAction(request, response);
	}

	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if( request.getSession().getAttribute("role") == null )
			this.getServletContext().getRequestDispatcher( "/login.jsp" ).forward( request, response );

		int role = Integer.parseInt(request.getSession().getAttribute("role").toString());

		String servletPath = request.getServletPath().replace("/", "");
		String[] pathInfo = (request.getPathInfo() == null ? new String[0] : (request.getPathInfo().substring(1)).split("/"));
		String  action    = null;
		Integer idCourse = null;

		if( servletPath.equals("course") && pathInfo.length == 2 )
		{
			action    = pathInfo[0];
			idCourse = Integer.parseInt(pathInfo[1]);
		}
		else if( servletPath.equals("course") && pathInfo.length == 1 && pathInfo[0].contains("create") )
		{
			action    = "create";
		}
		else if( servletPath.equals("courses") && pathInfo.length == 0 && role == 1 )
		{
			action    = "getall";
		}

		if(request.getParameter("lastname") != null)
		{
			CourseDTO course = new CourseDTO();
			course.setCourseSubject(request.getParameter("subject"));
			course.setNbHours(Float.parseFloat(request.getParameter("nbhours")));

			request.setAttribute("course", course);
		}

		if( action.equals("create") )
			this.create(request, response);
		else if( action.equals("getall") )
			this.getAll(request, response);
		else if( action.equals("get") )
			this.get(request, response, idCourse);
		else if( action.equals("update") )
			this.update(request, response, idCourse);
		else if( action.equals("delete") )
			this.delete(request, response, idCourse);
		else
			this.getServletContext().getRequestDispatcher( "/accueil.jsp" ).forward( request, response );
	}

	public static List<CourseDTO> getAllCourse() throws ExceptionService
	{
		CourseService cs = new CourseService();

		return cs.getAll();
	}

	public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher dispatcher;

		CourseService cs = new CourseService();
		try {
			request.setAttribute("courses", getAllCourse());
			dispatcher= request.getRequestDispatcher("/get_all_course.jsp");
		}
		catch (ExceptionService exceptionService) {
			request.setAttribute("alert_type", "danger");
			request.setAttribute("alert_text", exceptionService.getMessage());
			dispatcher= request.getRequestDispatcher("/accueil.jsp");
		}

		dispatcher.forward(request, response);
	}

	public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//Traitement des infos
		RequestDispatcher dispatcher;

		CourseDTO course = null;
		if( request.getAttribute("course") != null )
		{
			course = (CourseDTO) request.getAttribute("course");

			CourseService cs = new CourseService();
			try {
				cs.create(course);
				dispatcher= request.getRequestDispatcher("/accueil.jsp");
			}
			catch (ExceptionService exceptionService) {
				request.setAttribute("alert_type", "danger");
				request.setAttribute("alert_text", exceptionService.getMessage());
				dispatcher= request.getRequestDispatcher("/course.jsp");
			}
		}
		else
		{
			request.setAttribute("title", "Ajouter un nouveau cours");
			request.setAttribute("formaction", "/GestionEtablissement/course/create");
			dispatcher= request.getRequestDispatcher("/course.jsp");
		}

		//Redirection
		dispatcher.forward(request, response);
	}

	public void get(HttpServletRequest request, HttpServletResponse response, int idCourse) throws ServletException, IOException
	{
		//Traitement des infos
		RequestDispatcher dispatcher;

		CourseService cs = new CourseService();
		try {
			CourseDTO course = cs.get(idCourse);

			request.setAttribute("title", "Cours");
			request.setAttribute("formaction", "/GestionEtablissement/course/update/"+idCourse);
			request.setAttribute("course", course);
			dispatcher = request.getRequestDispatcher("/course.jsp");

		}
		catch (ExceptionService exceptionService)
		{
			request.setAttribute("alert_type", "danger");
			request.setAttribute("alert_text", exceptionService.getMessage());
			dispatcher= request.getRequestDispatcher("/accueil.jsp");
		}
		//Redirection
		dispatcher.forward(request, response);
	}

	public void update(HttpServletRequest request, HttpServletResponse response, int idCourse) throws ServletException, IOException
	{
		//Traitement des infos
		RequestDispatcher dispatcher = null;

		CourseService cs = new CourseService();

		try {
			if( request.getAttribute("course") != null )
			{
				CourseDTO course = (CourseDTO) request.getAttribute("course");

				cs.update((CourseDTO) request.getAttribute("course"));

				request.setAttribute("alert_type", "info");
				request.setAttribute("alert_text", "Vous avez mis a jour les infomations du cours: "+course.getCourseSubject());

				dispatcher= request.getRequestDispatcher("/accueil.jsp");
			}
			else
			{
				CourseDTO course = (CourseDTO) cs.get(idCourse);

				request.setAttribute("title", "Modifier le cours");
				request.setAttribute("formaction", "/GestionEtablissement/course/update/"+idCourse);
				request.setAttribute("course", course);

				dispatcher = request.getRequestDispatcher("/course.jsp");
			}
		}
		catch (ExceptionService exceptionService)
		{
			request.setAttribute("alert_type", "danger");
			request.setAttribute("alert_text", exceptionService.getMessage());
			dispatcher= request.getRequestDispatcher("/accueil.jsp");
		}

		if( dispatcher == null )
			dispatcher = request.getRequestDispatcher("/accueil.jsp");

		//Redirection
		dispatcher.forward(request, response);
	}

	public void delete(HttpServletRequest request, HttpServletResponse response, int idCourse) throws ServletException, IOException
	{
		//Traitement des infos
		RequestDispatcher dispatcher;

		CourseService cs = new CourseService();
		try {
			CourseDTO course = cs.get(idCourse);

			if( course == null )
			{
				request.setAttribute("alert_type", "warning");
				request.setAttribute("alert_text", "Le cours que vous avez voulue supprimer n'existe pas !");
			}
			else
				{
				cs.delete(idCourse);
				request.setAttribute("alert_type", "info");
				request.setAttribute("alert_text", "Vous avez supprimer le cours: " + course.getCourseSubject());
			}

			dispatcher= request.getRequestDispatcher("/accueil.jsp");
		}
		catch (ExceptionService exceptionService)
		{
			request.setAttribute("alert_type", "danger");
			request.setAttribute("alert_text", exceptionService.getMessage());
			dispatcher= request.getRequestDispatcher("/accueil.jsp");
		}
		//Redirection
		dispatcher.forward(request, response);
	}
}
