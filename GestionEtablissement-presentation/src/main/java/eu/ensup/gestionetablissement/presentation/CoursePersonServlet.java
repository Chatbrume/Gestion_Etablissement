package eu.ensup.gestionetablissement.presentation;

import eu.ensup.gestionetablissement.dto.CourseDTO;
import eu.ensup.gestionetablissement.dto.PersonDTO;
import eu.ensup.gestionetablissement.service.ExceptionService;
import eu.ensup.gestionetablissement.service.MarkService;
import eu.ensup.gestionetablissement.service.PersonService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CoursePersonServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public CoursePersonServlet()
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
        Integer idStudent = null;

        if( servletPath.equals("couseperson") && pathInfo.length == 4 )
        {
            action = "create";
            if( pathInfo[0].equals("course") )
                idCourse = Integer.parseInt(pathInfo[1]);
            if( pathInfo[2].equals("student") )
                idStudent = Integer.parseInt(pathInfo[3]);
        }
        else
        {
            action = "get";
        }

        if( servletPath.equals("couseperson"))
        {
            if( request.getParameter("idStudent") != null )
                idStudent = Integer.parseInt(request.getParameter("idStudent"));
            if( request.getParameter("idCourse") != null )
                idCourse = Integer.parseInt(request.getParameter("idCourse"));

            if( idStudent != null && idCourse != null )
                action = "create";
        }

        if( action == "create" && idCourse != null && idStudent != null )
            this.create(request, response, idCourse, idStudent);
        else if( action == "get" )
            this.get(request, response);
        else
            this.getServletContext().getRequestDispatcher( "/accueil.jsp" ).forward( request, response );
    }

    private void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;

        try {
            request.setAttribute("students", StudentServlet.getAllStudent());
            request.setAttribute("courses", CourseServlet.getAllCourse());
            dispatcher= request.getRequestDispatcher("/link_person_course.jsp");
        }
        catch (ExceptionService exceptionService) {
            request.setAttribute("alert_type", "danger");
            request.setAttribute("alert_text", exceptionService.getMessage());
            dispatcher= request.getRequestDispatcher("/accueil.jsp");
        }

        dispatcher.forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response, Integer idCourse, Integer idStudent) throws ServletException, IOException
    {
        RequestDispatcher dispatcher;

        PersonService ps = new PersonService();
        try {
            ps.linkToCourse(idStudent, idCourse);
            dispatcher= request.getRequestDispatcher("/accueil.jsp");
        }
        catch (ExceptionService exceptionService)
        {
            request.setAttribute("alert_type", "danger");
            request.setAttribute("alert_text", exceptionService.getMessage());
            dispatcher= request.getRequestDispatcher("/accueil.jsp");
        }

        dispatcher.forward(request, response);
    }
}
