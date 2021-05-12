package eu.ensup.gestionetablissement.presentation;

import eu.ensup.gestionetablissement.dto.MarkDTO;
import eu.ensup.gestionetablissement.service.CourseService;
import eu.ensup.gestionetablissement.service.ExceptionService;
import eu.ensup.gestionetablissement.service.MarkService;
import eu.ensup.gestionetablissement.service.PersonService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StatisticServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public StatisticServlet()
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

        if( servletPath.equals("mark") && pathInfo.length == 1 )
            action = pathInfo[0];
        else
            action = "student";

        if( action.equals("student") )
            this.student(request, response);
        else if( action.equals("course") )
            this.course(request, response);
        else if( action.equals("mark") )
            this.mark(request, response);
        else
            this.getServletContext().getRequestDispatcher( "/accueil.jsp" ).forward( request, response );
    }

    public void student(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher dispatcher;

        PersonService ps = new PersonService();
        try {
            request.setAttribute("student", ps.getAll("Student"));
            dispatcher= request.getRequestDispatcher("/get_all_student.jsp");
        }
        catch (ExceptionService exceptionService) {
            request.setAttribute("alert_type", "danger");
            request.setAttribute("alert_text", exceptionService.getMessage());
            dispatcher= request.getRequestDispatcher("/accueil.jsp");
        }

        dispatcher.forward(request, response);
    }

    public void course(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher dispatcher;

        CourseService cs = new CourseService();
        try {
            request.setAttribute("course", cs.getAll());
            dispatcher= request.getRequestDispatcher("/get_all_course.jsp");
        }
        catch (ExceptionService exceptionService) {
            request.setAttribute("alert_type", "danger");
            request.setAttribute("alert_text", exceptionService.getMessage());
            dispatcher= request.getRequestDispatcher("/accueil.jsp");
        }

        dispatcher.forward(request, response);
    }

    public void mark(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher dispatcher;

        MarkService cs = new MarkService();
        try {
            request.setAttribute("marks", cs.getAll());
            dispatcher= request.getRequestDispatcher("/get_all_mark.jsp");
        }
        catch (ExceptionService exceptionService) {
            request.setAttribute("alert_type", "danger");
            request.setAttribute("alert_text", exceptionService.getMessage());
            dispatcher= request.getRequestDispatcher("/accueil.jsp");
        }

        dispatcher.forward(request, response);
    }
}
